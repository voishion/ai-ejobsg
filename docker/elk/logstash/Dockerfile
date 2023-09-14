FROM logstash:7.16.3

# author
MAINTAINER ejobsg

# MySQL连接器
COPY ./libs /usr/share/logstash/logstash-core/lib/jars

#RUN logstash-plugin install logstash-output-analyticdb
#RUN mkdir -p vendor/jar/jdbc
#RUN cd vendor/jar/jdbc
#
#ADD libs/mysql-connector-java-5.1.37.jar vendor/jar/jdbc
#COPY analyticdb.rb vendor/bundle/jruby/2.5.0/gems/logstash-output-analyticdb-5.4.0.10/lib/logstash/outputs
