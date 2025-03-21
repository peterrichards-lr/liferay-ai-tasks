#!/bin/sh
(rm liferay/files/deploy/*.jar && rm -r liferay/files/osgi/client-extensions)

(cd liferay/files/deploy && docker container rm -f liferay-dxp-latest && docker create --pull always --name liferay-dxp-latest liferay/dxp:latest && docker export liferay-dxp-latest | tar -xv --strip-components=3 -C . opt/liferay/deploy)

(cd liferay/files && docker container rm -f liferay-workspace-latest && docker build ../. -t liferay-workspace:latest && docker create --name liferay-workspace-latest liferay-workspace:latest && docker export liferay-workspace-latest | tar -xv --strip-components=4 -C . opt/app/build/docker/client-extensions opt/app/build/docker/deploy && mv ./client-extensions ./osgi/client-extensions)

(cd contentwizard && unzip -o ../liferay/files/osgi/client-extensions/liferay-content-wizard-bun.zip -x Dockerfile LCP.json liferay-content-wizard-bun.client-extension-config.json)

docker compose up -d --build