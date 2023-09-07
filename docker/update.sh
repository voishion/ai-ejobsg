#!/bin/sh

usage() {
	echo "Usage: sh update.sh"
	exit 1
}
read -p "Enter need update container name: " name
containerName=$name
echo "Start update $containerName container"

docker stop $containerName
docker rm -f $containerName
docker images | grep $containerName | awk '{print $3}' | xargs docker rmi
docker-compose up -d $containerName

echo "Update $containerName container success"
