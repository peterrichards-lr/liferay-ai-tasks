FROM azul/zulu-openjdk-alpine:21 AS builder

ENV NPM_CONFIG_PREFIX=/home/liferay/.npm-global
ENV PATH=$PATH:/home/liferay/.npm-global/bin
ENV PATH=$PATH:/home/liferay/.npm-global/lib
ENV NODE_ENV=production

ARG LIFERAY_UID

RUN apk --update --no-cache --repository https://dl-cdn.alpinelinux.org/alpine/v3.20/main/ add \
    gcompat \
    nodejs=20.15.1-r0 \
    npm=10.9.1-r0 \
    dos2unix && \
    addgroup -S liferay && \
    adduser -S liferay -G liferay -u ${LIFERAY_UID:-100}

USER liferay
WORKDIR /home/liferay

COPY --chown=liferay:liferay gradle gradle
COPY --chown=liferay:liferay gradlew .
COPY --chown=liferay:liferay gradle.properties .

RUN mkdir -p .liferay/bundles && \
    cat gradle.properties | grep liferay.workspace.bundle.url= | cut -d'=' -f2 | \
    xargs wget -P .liferay/bundles && \
    dos2unix gradlew && \
    ./gradlew

ARG TARGET_ENV=prod

COPY --chown=liferay:liferay settings.gradle .
COPY --chown=liferay:liferay build.gradle .
COPY --chown=liferay:liferay configs/common configs/common
COPY --chown=liferay:liferay configs/$TARGET_ENV configs/$TARGET_ENV
COPY --chown=liferay:liferay modules modules
COPY --chown=liferay:liferay client-extensions client-extensions

RUN ./gradlew initBundle -Pliferay.workspace.environment=$TARGET_ENV

RUN rm -r bundles/osgi/state

RUN ./gradlew deploy -PnodeDownload=false

FROM liferay/dxp:2024.q4.7

ENV JAVA_VERSION=zulu21

COPY --chown=liferay:liferay --from=builder /home/liferay/bundles/deploy/*.war /opt/liferay/osgi/war
COPY --chown=liferay:liferay --from=builder /home/liferay/bundles/osgi/modules /opt/liferay/osgi/modules
COPY --chown=liferay:liferay --from=builder /home/liferay/bundles/osgi/client-extensions /opt/liferay/osgi/client-extensions
COPY --chown=liferay:liferay --from=builder /home/liferay/bundles/osgi/configs /opt/liferay/osgi/configs
COPY --chown=liferay:liferay --from=builder /home/liferay/bundles/*.properties /opt/liferay/

