FROM openjdk:11-jdk-slim as builder

ENV GRADLE_VER=6.8.3
RUN apt-get update -qqy \
  && apt-get install -qqy \
   --no-install-recommends \
   curl \
   ca-certificates \
   unzip

RUN curl -L -o /mysql-connector-java-8.0.26.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.26/mysql-connector-java-8.0.26.jar

RUN mkdir -p /opt/ && cd /opt/ \
    && echo "Downloading gradle.." \
    && curl -sSfL "https://services.gradle.org/distributions/gradle-${GRADLE_VER}-bin.zip" -o gradle-$GRADLE_VER-bin.zip \
    && unzip gradle-$GRADLE_VER-bin.zip -d /opt/ \
    && rm gradle-$GRADLE_VER-bin.zip

# Export some environment variables
ENV GRADLE_HOME=/opt/gradle-$GRADLE_VER/
ENV PATH=$PATH:$GRADLE_HOME/bin

RUN mkdir -p /home/app/libs

ENV GRADLE_OPTS="-Dorg.gradle.daemon=false"
WORKDIR /home/app

COPY . /home/app/

RUN gradle build
RUN find . 

FROM openfaas/of-watchdog:0.7.6 as watchdog

FROM openjdk:11-jre-slim as ship
RUN apt-get update -qqy \
  && apt-get install -qqy \
   --no-install-recommends \
   unzip
RUN addgroup --system app \
    && adduser --system --ingroup app app

COPY --from=watchdog /fwatchdog /usr/bin/fwatchdog
RUN chmod +x /usr/bin/fwatchdog

WORKDIR /home/app
COPY --from=builder /home/app/function/build/distributions/function-1.0.zip ./function-1.0.zip
user app
RUN unzip ./function-1.0.zip

WORKDIR /home/app/

ENV upstream_url="http://127.0.0.1:8082"
ENV mode="http"
ENV CLASSPATH="/home/app/function-1.0/function-1.0.jar:/home/app/function-1.0/lib/*:/mysql-connector-java-8.0.26.jar"

ENV fprocess="java -XX:+UseContainerSupport com.openfaas.entrypoint.App"
EXPOSE 8080

HEALTHCHECK --interval=5s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
