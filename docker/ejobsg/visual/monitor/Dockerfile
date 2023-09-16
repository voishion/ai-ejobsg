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
    /home/ejobsg/temp

# 指定路径
WORKDIR /home/ejobsg
# 复制jar文件到路径
COPY ./jar/ejobsg-visual-monitor.jar /home/ejobsg/ejobsg-visual-monitor.jar
# 启动系统服务
ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dspring.profiles.active=dev", \
            "-jar", "ejobsg-visual-monitor.jar"]
