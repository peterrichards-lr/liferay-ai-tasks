```
(cd liferay/files/deploy && docker container rm -f liferay-dxp-latest && docker create --pull always --name liferay-dxp-latest liferay/dxp:latest && docker export liferay-dxp-latest | tar -xv --strip-components=3 -C . opt/liferay/deploy)
```
```
(cd liferay/files && docker container rm -f liferay-workspace-latest && docker build ../. -t liferay-workspace:latest && docker create --name liferay-workspace-latest liferay-workspace:latest && docker export liferay-workspace-latest | tar -xv --strip-components=4 -C . opt/app/build/docker/client-extensions opt/app/build/docker/deploy && mkdir ./osgi && mv ./client-extensions ./osgi/client-extensions)
```