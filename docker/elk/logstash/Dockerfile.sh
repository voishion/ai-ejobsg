#!/bin/sh
image=harbor.ejobsg.mobi/library/ai-app-logstash:7.4.0-16G
docker build -t ${image} ./
docker push ${image}
