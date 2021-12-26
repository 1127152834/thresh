FROM openjdk:8u212-jre
MAINTAINER MrZhang 1127152834@qq.com

COPY ./target/thresh-server-job-2.2-RELEASE.jar /thresh/thresh-server-job-2.2-RELEASE.jar

#ADD agent/ /agent

ENTRYPOINT ["java","-Xms128M","-Xmx128M", "-jar", "/thresh/thresh-server-job-2.2-RELEASE.jar"]
#ENTRYPOINT ["java","-Xms128M","-Xmx128M", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=thresh-server-job", "-Dskywalking.collector.backend_service=skywalkingIp:11800", "-jar", "/thresh/thresh-server-job-2.2-RELEASE.jar"]
