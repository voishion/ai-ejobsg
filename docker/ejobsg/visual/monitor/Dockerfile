# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER ejobsg

ENV TZ=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 挂载目录
VOLUME /home/ejobsg
# 创建目录
RUN mkdir -p /home/ejobsg
# 指定路径
WORKDIR /home/ejobsg
# 复制jar文件到路径
COPY ./jar/ejobsg-visual-monitor.jar /home/ejobsg/ejobsg-visual-monitor.jar
# 启动系统服务
ENTRYPOINT ["java", "-jar", "ejobsg-visual-monitor.jar", "--spring.profiles.active=dev", "-Djava.security.egd=file:/dev/./urandom"]
