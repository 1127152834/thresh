FROM openjdk:8u212-jre
MAINTAINER MrZhang 1127152834@qq.com

COPY ./target/thresh-gateway-2.2-RELEASE.jar /thresh/thresh-gateway-2.2-RELEASE.jar

#ADD agent/ /agent

ENTRYPOINT ["java" ,"-Xms512M","-Xmx512M", "-jar", "/thresh/thresh-gateway-2.2-RELEASE.jar"]
#ENTRYPOINT ["java" ,"-Xms512M","-Xmx512M", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=thresh-gateway", "-Dskywalking.collector.backend_service=skywalkingIp:11800", "-jar", "/thresh/thresh-gateway-2.2-RELEASE.jar"]
