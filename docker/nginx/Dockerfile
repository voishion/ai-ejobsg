# 基础镜像
FROM nginx
# author
MAINTAINER ejobsg

# 挂载目录
VOLUME /home/ejobsg/projects/ejobsg-ui
# 创建目录
RUN mkdir -p /home/ejobsg/projects/ejobsg-ui
# 指定路径
WORKDIR /home/ejobsg/projects/ejobsg-ui
# 复制conf文件到路径
COPY ./conf/nginx.conf /etc/nginx/nginx.conf
# 复制html文件到路径
COPY ./html/dist /home/ejobsg/projects/ejobsg-ui
