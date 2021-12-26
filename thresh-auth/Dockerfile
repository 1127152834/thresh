FROM openjdk:8u212-jre
MAINTAINER MrZhang 1127152834@qq.com

COPY ./target/thresh-auth-2.2-RELEASE.jar /thresh/thresh-auth-2.2-RELEASE.jar
#ADD agent/ /agent

ENTRYPOINT ["java" ,"-Xms256M","-Xmx256M", "-jar", "/thresh/thresh-auth-2.2-RELEASE.jar"]

#ENTRYPOINT ["java" ,"-Xms512M","-Xmx512M", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=thresh-auth", "-Dskywalking.collector.backend_service=skywalkingIp:11800", "-jar", "/thresh/thresh-auth-2.2-RELEASE.jar"]
