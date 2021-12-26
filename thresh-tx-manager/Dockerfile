FROM openjdk:8u212-jre
MAINTAINER MrZhang 1127152834@qq.com

COPY ./target/thresh-tx-manager-2.2-RELEASE.jar /thresh/thresh-tx-manager-2.2-RELEASE.jar

#ADD agent/ /agent

ENTRYPOINT ["java" ,"-Xms256M","-Xmx256M", "-jar", "/thresh/thresh-tx-manager-2.2-RELEASE.jar"]
#ENTRYPOINT ["java" ,"-Xms256M","-Xmx256M", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=thresh-tx-manager", "-Dskywalking.collector.backend_service=skywalkingIp:11800", "-jar", "/thresh/thresh-tx-manager-2.2-RELEASE.jar"]
