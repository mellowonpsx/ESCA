-- dev, no cli passing parameter
set screen_name=mellowonpsx; 
-- EXAMPLE: cli passing parameter
-- hive -f twitter_loader.sql -hiveconf screen_name="mellowonpsx"
-- end dev
-- dev: show header
-- set hive.cli.print.header = true;
-- end dev 

ADD JAR /usr/lib/hive/lib/json-serde-1.3-jar-with-dependencies.jar;

-- starting drop, useful on non-endend query
DROP TABLE IF EXISTS direct_message_raw;
DROP TABLE IF EXISTS direct_message_clean;

-- create direct_message_raw table structure ---------------------------
DROP TABLE IF EXISTS direct_message_raw;
CREATE EXTERNAL TABLE IF NOT EXISTS direct_message_raw
(
   	direct_message STRUCT
	<
		id: BIGINT,
		created_at: STRING,
		text: STRING,
		recipient_screen_name: STRING,
		recipient_id: BIGINT,
		sender_screen_name: STRING,
		sender_id: BIGINT,
		recipient: STRUCT
		<
			lang: STRING,
			id: BIGINT,
			name: STRING,
			followers_count: BIGINT,
			friends_count: BIGINT,
			screen_name: STRING,
			time_zone: STRING
		>,
		sender: STRUCT
		<
			lang: STRING,
			id: BIGINT,
			name: STRING,
			followers_count: BIGINT,
			friends_count: BIGINT,
			screen_name: STRING,
			time_zone: STRING
		>
	>
)
ROW FORMAT SERDE 'org.openx.data.jsonserde.JsonSerDe'
STORED AS TEXTFILE
--LOCATION '/user/hive/warehouse/ESCA/tweets/';
LOCATION '/user/hive/warehouse/ESCA/tweets/'; -- change to flume twDM folder

-- tweets cleaning -----------------------------------------------------
DROP TABLE IF EXISTS direct_message_clean;
CREATE TABLE direct_message_clean AS
SELECT 
	id,
	created_at,
	text,
	"TwitterDM" as type,
	user.id AS user_id,
	user.screen_name as user_screen_name,
	user.name as user_name,
	user.lang as user_lang,
	user.followers_count as user_followers_count,
	user.time_zone as user_time_zone
FROM
(
	SELECT 
		direct_message.id AS id, 
		direct_message.created_at AS created_at, 
		direct_message.text AS text, 
		-- "" is necessary to make parameter interpreted as string and not column name
		if("${hiveconf:screen_name}" = direct_message.recipient_screen_name, direct_message.sender, direct_message.recipient) AS user
	FROM 
		direct_message_raw
	WHERE 
	(
		(direct_message IS NOT NULL)
		AND
		(direct_message.id IS NOT NULL)
		AND 
		(direct_message.created_at IS NOT NULL)
		AND 
		(direct_message.text IS NOT NULL)
	)
) temp_table;

-- append new tweets to ESCA_data --------------------------------------
CREATE TABLE IF NOT EXISTS ESCA_data
(
	id BIGINT,
	created_at STRING,
	text STRING,
	type STRING,
	user_id BIGINT,
	user_username STRING,
	user_name STRING,
	user_lang STRING,
	user_influence BIGINT,
	user_time_zone STRING
)
STORED AS TEXTFILE
LOCATION '/user/hive/warehouse/ESCA/data/';

INSERT INTO TABLE ESCA_data
SELECT 
	id,
	created_at,
	text,
	type,
	user_id,
	user_screen_name as user_username,
	user_name,
	user_lang,
	user_followers_count as user_influence,
	user_time_zone
FROM 
  direct_message_clean;
	
-- final drop of temp tables -------------------------------------------
DROP TABLE IF EXISTS direct_message_raw;
DROP TABLE IF EXISTS direct_message_clean;
