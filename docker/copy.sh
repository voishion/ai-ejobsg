#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ai-ejobsg-20230706.sql ./mysql/db
cp ../sql/ai-ejobsg-config-20220929.sql ./mysql/db
cp ../sql/ai-ejobsg-seata-20210128.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../ejobsg-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy ejobsg-gateway "
cp ../ejobsg-gateway/target/ejobsg-gateway.jar ./ejobsg/gateway/jar

echo "begin copy ejobsg-auth "
cp ../ejobsg-auth/target/ejobsg-auth.jar ./ejobsg/auth/jar

echo "begin copy ejobsg-visual "
cp ../ejobsg-visual/ejobsg-visual-monitor/target/ejobsg-visual-monitor.jar  ./ejobsg/visual/monitor/jar

echo "begin copy ejobsg-modules-system "
cp ../ejobsg-modules/ejobsg-system/target/ejobsg-modules-system.jar ./ejobsg/modules/system/jar

echo "begin copy ejobsg-modules-file "
cp ../ejobsg-modules/ejobsg-file/target/ejobsg-modules-file.jar ./ejobsg/modules/file/jar

echo "begin copy ejobsg-modules-job "
cp ../ejobsg-modules/ejobsg-job/target/ejobsg-modules-job.jar ./ejobsg/modules/job/jar

echo "begin copy ejobsg-modules-gen "
cp ../ejobsg-modules/ejobsg-gen/target/ejobsg-modules-gen.jar ./ejobsg/modules/gen/jar

