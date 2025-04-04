# Liferay AI Tasks and AI Content Wizard Showcase

A Docker Compose setup to demonstrate using the AI Content Wizard and AI Tasks with Liferay DXP.

## Setup environment
### Clean up old assets (optional)
```
(rm liferay/files/deploy/*.jar && rm -r liferay/files/osgi/client-extensions)
```
### Deploy a DXP license
```
(cd liferay/files/deploy && docker container rm -f liferay-dxp-latest && docker create --pull always --name liferay-dxp-latest liferay/dxp:latest && docker export liferay-dxp-latest | tar -xv --strip-components=3 -C . opt/liferay/deploy)
```
### Build Client Extensions and OSGi modules
This step builds the CX assets and modules before extracting the ZIP and JAR files for the Liferay runtime.
```
(cd liferay/files && docker container rm -f liferay-workspace-latest && docker build ../. -t liferay-workspace:latest && docker create --name liferay-workspace-latest liferay-workspace:latest && docker export liferay-workspace-latest | tar -xv --strip-components=4 -C . opt/app/build/docker/client-extensions opt/app/build/docker/deploy && mv ./client-extensions ./osgi/client-extensions)
```
### Extracts the AI Content Wizard Bun Microservice so it can be containerised
```
(cd contentwizard && unzip -o ../liferay/files/osgi/client-extensions/liferay-content-wizard-bun.zip -x Dockerfile LCP.json liferay-content-wizard-bun.client-extension-config.json)
```

## Start environment
```
docker compose up -d --build
```
This will start the following services:

- PostgreSQL (§4)
- Elasticsearch (8.11.4)
- Liferay DXP (2024.q4.7)
- AI Content Wizard CX Bun

Once everything started, you need to login into Liferay DXP and configure the AI services. For example, configure the AI Content Wizard to use your OpenAI key. The services use volumes and therefore any database or document library changes will be persisted, so you only need to configure your services the first time.

> Do not commit any AI keys to source control as this will likely invalidate them.