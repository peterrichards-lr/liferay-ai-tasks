#!/bin/sh
usage() { echo "Usage: $0 -p <string>" 1>&2; exit 1; }

while getopts ":s:p:" o; do
    case "${o}" in
        p)
            p=${OPTARG}
            ;;
        *)
            usage
            ;;
    esac
done
shift $((OPTIND-1))

if [ -z "${p}" ]; then
    usage
fi

rm ./*.p12
rm ./*.pem
rm ./*.srl

# Root CA
openssl genrsa -out root-ca-key.pem 2048
openssl req -new -x509 -sha256 -key root-ca-key.pem -subj "/C=US/ST=California/L=DiamondBar/O=Liferay/OU=SalesEngineering/CN=root" -out root-ca.pem -days 730
# Admin cert
openssl genrsa -out admin-key-temp.pem 2048
openssl pkcs8 -inform PEM -outform PEM -in admin-key-temp.pem -topk8 -nocrypt -v1 PBE-SHA1-3DES -out admin-key.pem
openssl req -new -key admin-key.pem -subj "/C=US/ST=California/L=DiamondBar/O=Liferay/OU=SalesEngineering/CN=opensearch-admin" -out admin.csr
openssl x509 -req -in admin.csr -CA root-ca.pem -CAkey root-ca-key.pem -CAcreateserial -sha256 -out admin.pem -days 730
# Node cert 1
openssl genrsa -out node1-key-temp.pem 2048
openssl pkcs8 -inform PEM -outform PEM -in node1-key-temp.pem -topk8 -nocrypt -v1 PBE-SHA1-3DES -out node1-key.pem
openssl req -new -key node1-key.pem -subj "/C=US/ST=California/L=DiamondBar/O=Liferay/OU=SalesEngineering/CN=opensearch-node1" -out node1.csr
echo 'subjectAltName=DNS:opensearch-node1' > node1.ext
openssl x509 -req -in node1.csr -CA root-ca.pem -CAkey root-ca-key.pem -CAcreateserial -sha256 -out node1.pem -days 730 -extfile node1.ext
# Node cert 2
openssl genrsa -out node2-key-temp.pem 2048
openssl pkcs8 -inform PEM -outform PEM -in node2-key-temp.pem -topk8 -nocrypt -v1 PBE-SHA1-3DES -out node2-key.pem
openssl req -new -key node2-key.pem -subj "/C=US/ST=California/L=DiamondBar/O=Liferay/OU=SalesEngineering/CN=opensearch-node2" -out node2.csr
echo 'subjectAltName=DNS:opensearch-node2' > node2.ext
openssl x509 -req -in node2.csr -CA root-ca.pem -CAkey root-ca-key.pem -CAcreateserial -sha256 -out node2.pem -days 730 -extfile node2.ext

# Convert node certificates
cat node1.pem node1-key.pem root-ca.pem > combined-node1.pem
openssl pkcs12 -export -in combined-node1.pem -out node1-cert.p12 -name opensearch-node1 -passout pass:"${p}"
keytool -importkeystore -srckeystore node1-cert.p12 -srcstoretype pkcs12 -destkeystore keystore.p12 -srcstorepass "${p}" -deststorepass "${p}"

cat node2.pem node2-key.pem root-ca.pem > combined-node2.pem
openssl pkcs12 -export -in combined-node2.pem -out node2-cert.p12 -name opensearch-node2 -passout pass:"${p}"
keytool -importkeystore -srckeystore node2-cert.p12 -srcstoretype pkcs12 -destkeystore keystore.p12 -srcstorepass "${p}" -deststorepass "${p}"

# Convert admin certificate
cat admin.pem admin-key.pem root-ca.pem > combined-admin.pem
openssl pkcs12 -export -in combined-admin.pem -out admin-cert.p12 -name opensearch-admin -passout pass:"${p}"
keytool -importkeystore -srckeystore admin-cert.p12 -srcstoretype pkcs12 -destkeystore keystore.p12 -srcstorepass "${p}" -deststorepass "${p}"

# Import certificates to truststore
keytool -importcert -noprompt -keystore truststore.p12 -alias root -file root-ca.pem -storepass "${p}" -trustcacerts -deststoretype pkcs12

# Cleanup
rm admin-key-temp.pem
rm admin.csr
rm node1-key-temp.pem
rm node1.csr
rm node1.ext
rm node2-key-temp.pem
rm node2.csr
rm node2.ext

rm combined-admin.pem
rm combined-node1.pem
rm combined-node2.pem