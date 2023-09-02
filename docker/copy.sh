#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}

# copy sql
echo "begin copy sql "
rm -rf ./mysql/db/*
cp ../sql/ai-ejobsg.sql ./mysql/db
cp ../sql/ai-ejobsg-config.sql ./mysql/db
cp ../sql/ai-ejobsg-seata.sql ./mysql/db

# copy html
echo "begin copy html "
rm -rf ./nginx/html/dist/*
cp -r ../ejobsg-ui/dist/** ./nginx/html/dist

# copy jar
echo "begin copy ejobsg-gateway "
rm -f ./ejobsg/gateway/jar/*
cp ../ejobsg-gateway/target/ejobsg-gateway.jar ./ejobsg/gateway/jar

echo "begin copy ejobsg-auth "
rm -f ./ejobsg/auth/jar/*
cp ../ejobsg-auth/target/ejobsg-auth.jar ./ejobsg/auth/jar

echo "begin copy ejobsg-visual "
rm -f ./ejobsg/visual/monitor/jar/*
cp ../ejobsg-visual/ejobsg-visual-monitor/target/ejobsg-visual-monitor.jar  ./ejobsg/visual/monitor/jar

echo "begin copy ejobsg-modules-system "
rm -f ./ejobsg/modules/system/jar/*
cp ../ejobsg-modules/ejobsg-modules-system/target/ejobsg-modules-system.jar ./ejobsg/modules/system/jar

echo "begin copy ejobsg-modules-file "
rm -f ./ejobsg/modules/file/jar/*
cp ../ejobsg-modules/ejobsg-modules-file/target/ejobsg-modules-file.jar ./ejobsg/modules/file/jar

echo "begin copy ejobsg-modules-job "
rm -f ./ejobsg/modules/job/jar/*
cp ../ejobsg-modules/ejobsg-modules-job/target/ejobsg-modules-job.jar ./ejobsg/modules/job/jar

echo "begin copy ejobsg-modules-gen "
rm -f ./ejobsg/modules/gen/jar/*
cp ../ejobsg-modules/ejobsg-modules-gen/target/ejobsg-modules-gen.jar ./ejobsg/modules/gen/jar

echo "begin copy ejobsg-modules-recruitment "
rm -f ./ejobsg/modules/recruitment/jar/*
cp ../ejobsg-modules/ejobsg-modules-recruitment/target/ejobsg-modules-recruitment.jar ./ejobsg/modules/recruitment/jar

