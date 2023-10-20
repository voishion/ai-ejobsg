# 基础镜像
FROM nginx:1.21.5
# author
MAINTAINER ejobsg

# 挂载目录
VOLUME /home/ejobsg-ui
# 创建目录
RUN mkdir -p /home/ejobsg-ui \
    /etc/nginx/ssl

# 指定路径
WORKDIR /home/ejobsg-ui
# 复制html文件到路径
COPY ./html/dist /home/ejobsg-ui
