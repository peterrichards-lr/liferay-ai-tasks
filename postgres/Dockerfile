FROM postgres:14-alpine

#COPY setup/init.sql /docker-entrypoint-initdb.d/
#USER root
#RUN chown postgres:postgres /docker-entrypoint-initdb.d/init.sql
ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["postgres"]