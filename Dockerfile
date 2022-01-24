FROM openjdk:18
#VOLUME /devops-final-project
#ARG JAR_FILE=build/libs/devops-final-project-0.0.1.jar
EXPOSE 10333
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD build/libs/devops-final-project-0.0.1.jar /app/app.jar
RUN apt-get update && apt-get install cut
RUN curl -Ls https://download.newrelic.com/install/newrelic-cli/scripts/install.sh | bash && sudo NEW_RELIC_API_KEY=NRAK-KJTGXJPXVQUQHK6D6LF6QRHG6TS NEW_RELIC_ACCOUNT_ID=3399241 NEW_RELIC_REGION=EU /usr/local/bin/newrelic install -n java-agent-installer


ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=dev ${JAVA_OPTS} -javaagent:/usr/local/bin/newrelic/newrelic.jar -jar /app/app.jar"]
