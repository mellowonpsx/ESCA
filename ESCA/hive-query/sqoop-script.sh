#working sentiment_data sqoop
sqoop export \
	--connect jdbc:mysql://localhost.localdomain:3306/ESCA \
	--username root \
	--password root \
	--table sentiment_data \
	--update-key id,type \
	--export-dir /user/hive/warehouse/ESCA/sentiment_data/ \
	--update-mode allowinsert \
	--input-fields-terminated-by '\001' \
	--lines-terminated-by '\n'
	
#export daily report
sqoop export \
	--connect jdbc:mysql://localhost.localdomain:3306/ESCA \
	--username root \
	--password root \
	--table sentiment_report \
	--update-key full_date_time \
	--export-dir /user/hive/warehouse/ESCA/sentiment_report/ \
	--update-mode allowinsert \
	--input-fields-terminated-by '\001' \
	--lines-terminated-by '\n'	

#export geo report
sqoop export \
	--connect jdbc:mysql://localhost.localdomain:3306/ESCA \
	--username root \
	--password root \
	--table geographic_report \
	--update-key full_date_time,user_time_zone \
	--export-dir /user/hive/warehouse/ESCA/geographic_report/ \
	--update-mode allowinsert \
	--input-fields-terminated-by '\001' \
	--lines-terminated-by '\n'	

#export global report
#sqoop export \
	#--connect jdbc:mysql://localhost.localdomain:3306/ESCA \
	#--username root \
	#--password root \
	#--table sentiment_global_report \
	#--update-key full_date \
	#--export-dir /user/hive/warehouse/ESCA/sentiment_global_report/ \
	#--update-mode allowinsert \
	#--input-fields-terminated-by '\001' \
	#--lines-terminated-by '\n'
