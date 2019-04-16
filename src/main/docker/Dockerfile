FROM openjdk

VOLUME /tmp
ADD maven/QuoteMakerQuotesMicroservice-0.0.1-SNAPSHOT.jar quoteMakerQuotes.jar
RUN sh -c 'touch /quoteMakerQuotes.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/quoteMakerQuotes.jar"]
