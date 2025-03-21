FROM debian:bookworm-slim

RUN apt update && apt --assume-yes install openjdk-17-jdk git
RUN useradd -ms /bin/bash liferay

WORKDIR /opt/app

COPY liferay-workspace .

RUN ./gradlew clean deploy

ENTRYPOINT ["tail", "-f", "/dev/null"]