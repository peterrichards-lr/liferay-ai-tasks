FROM opensearchproject/opensearch:latest

RUN ./bin/opensearch-plugin install analysis-icu analysis-kuromoji analysis-smartcn analysis-stempel

EXPOSE 9200/tcp
EXPOSE 9300/tcp
EXPOSE 9600/tcp
EXPOSE 9650/tcp