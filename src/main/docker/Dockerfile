FROM java:8
VOLUME /tmp
ADD slambook.jar
RUN bash -c 'touch /slambook.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/slambook.jar"]