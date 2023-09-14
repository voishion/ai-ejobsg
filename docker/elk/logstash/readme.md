```json
docker build -t logstash-7.4.0- .
docker login harbor.meixiu.mobi
docker images
docker tag 2f96dceeb9bf harbor.meixiu.mobi/library/ai-app-logstash:7.4.0-16G-mysql5
docker push harbor.meixiu.mobi/library/ai-app-logstash:7.4.0-16G-mysql5
```
- ai-app-logstash:7.4.0-adb-debug-v1  debug很多info日志
- ai-app-logstash:7.4.0-adb-batch-log-v1 只打印insert sql
- ai-app-logstash:7.4.0-adb-batch-log-v2 不打印日志