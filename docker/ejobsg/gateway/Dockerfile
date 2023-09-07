# 基础镜像
FROM anapsix/alpine-java:8_server-jre_unlimited
# author
MAINTAINER ejobsg

ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 挂载目录
VOLUME /home/ejobsg
# 创建目录
RUN mkdir -p /home/ejobsg \
    /home/ejobsg/logs  \
    /home/ejobsg/temp  \
    /home/skywalking/agent

ENV SERVER_PORT=8080

# 指定路径
WORKDIR /home/ejobsg
# 复制jar文件到路径
COPY ./jar/ejobsg-gateway.jar /home/ejobsg/ejobsg-gateway.jar
# 启动网关服务
ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dserver.port=${SERVER_PORT}", \
            "-Dspring.profiles.active=dev", \
            "-Dskywalking.agent.service_name=ejobsg::ejobsg-gateway", \
            "-javaagent:/home/skywalking/agent/skywalking-agent.jar", \
            "-jar", "ejobsg-gateway.jar"]

EXPOSE ${SERVER_PORT}
EXPOSE 8719-8729
