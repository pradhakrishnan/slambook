FROM java:8
VOLUME /tmp
ADD slambook.jar slam.jar
RUN bash -c 'touch /slam.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/slam.jar"]