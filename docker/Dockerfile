FROM nsos-pr1-nex.atldc.nscorp.com:8083/openjdk:14
VOLUME /tmp
COPY BOOT-INF/lib /app/lib
COPY META-INF /app/META-INF
COPY BOOT-INF/classes /app
RUN mkdir -p keys
EXPOSE 8099
ENTRYPOINT ["java","-cp","app:app/lib/*","ServiceLauncher"]
