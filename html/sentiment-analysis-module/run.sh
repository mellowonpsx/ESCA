#!/bin/bash
cd /var/www/html/sentiment-analysis-module

#mv completed file to temp dir (sample-tweets)
files=$(hadoop fs -ls /user/flume/sample-tweets | grep -v ".tmp" | grep -o "/user/flume/sample-tweets/FlumeData.[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")
for p in $files
do
	file_name=$(echo $p | grep -o "FlumeData.[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")
	hadoop fs -mv $p /user/flume/completed/sample-tweets/$file_name
done

#mv completed file to temp dir (user-tweets)
files=$(hadoop fs -ls /user/flume/user-tweets | grep -v ".tmp" | grep -o "/user/flume/user-tweets/FlumeData.[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")
for p in $files
do
	file_name=$(echo $p | grep -o "FlumeData.[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")
	hadoop fs -mv $p /user/flume/completed/user-tweets/$file_name
done

#ELT > from tweets to ESCA_data
sudo -u cloudera hive -f hive-query/loader_twitter-flume-sampling-module.hql
sudo -u cloudera hive -f hive-query/loader_twitter-flume-user-module.hql

#delete temp dir (optional)
hadoop fs -rm /user/flume/completed/sample-tweets/*
hadoop fs -rm /user/flume/completed/user-tweets/*
hadoop fs -expunge

#Sentiment Analysis and reporting
sudo -u cloudera hive -f hive-query/analysis_sentiment-analysis-module.hql

#export to mysql
sudo -u cloudera sqoop export \
	--connect jdbc:mysql://localhost.localdomain:3306/ESCA \
	--username root \
	--password root \
	--table sentiment_data \
	--update-key id,type \
	--export-dir /user/hive/warehouse/ESCA/sentiment_data/ \
	--update-mode allowinsert \
	--input-fields-terminated-by '\001' \
	--lines-terminated-by '\n'
	
sudo -u cloudera sqoop export \
	--connect jdbc:mysql://localhost.localdomain:3306/ESCA \
	--username root \
	--password root \
	--table sentiment_report \
	--update-key full_date_time \
	--export-dir /user/hive/warehouse/ESCA/sentiment_report/ \
	--update-mode allowinsert \
	--input-fields-terminated-by '\001' \
	--lines-terminated-by '\n'	

sudo -u cloudera sqoop export \
	--connect jdbc:mysql://localhost.localdomain:3306/ESCA \
	--username root \
	--password root \
	--table geographic_report \
	--update-key full_date_time,user_time_zone \
	--export-dir /user/hive/warehouse/ESCA/geographic_report/ \
	--update-mode allowinsert \
	--input-fields-terminated-by '\001' \
	--lines-terminated-by '\n'
