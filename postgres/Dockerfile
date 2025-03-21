FROM postgres:14-alpine

COPY database /docker-entrypoint-initdb.d/init.sql
USER root
RUN chown postgres:postgres /docker-entrypoint-initdb.d/init.sql
USER postgres
ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["postgres"]