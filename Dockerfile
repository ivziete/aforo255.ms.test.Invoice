FROM openjdk:13
VOLUME "/tmp"
EXPOSE 8081
ADD ./target/Invoice-1.0.jar /invoice.jar
ENTRYPOINT [ "java","-jar","/invoice.jar" ]