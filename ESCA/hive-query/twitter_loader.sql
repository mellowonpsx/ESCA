-- dev, no cli passing parameter
set screen_name=mellowonpsx; 
-- EXAMPLE: cli passing parameter
-- hive -f twitter_loader.sql -hiveconf screen_name="mellowonpsx"
-- end dev
-- dev: show header
set hive.cli.print.header = true;
-- end dev 

ADD JAR /usr/lib/hive/lib/json-serde-1.3-jar-with-dependencies.jar;

DROP TABLE IF EXISTS direct_message_raw;
DROP TABLE IF EXISTS tweets_raw;

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
LOCATION '/user/hive/warehouse/ESCA/tweets/';

-- create tweets_raw table structure -----------------------------------
DROP TABLE IF EXISTS tweets_raw;
CREATE EXTERNAL TABLE IF NOT EXISTS tweets_raw
(
	id BIGINT,
	created_at STRING,
	text STRING,
	user STRUCT
	<
		lang: STRING,
		id: BIGINT,
		name: STRING,
		followers_count: BIGINT,
		friends_count: BIGINT,
		screen_name: STRING,
		time_zone: STRING
	>
)
ROW FORMAT SERDE 'org.openx.data.jsonserde.JsonSerDe'
STORED AS TEXTFILE
LOCATION '/user/hive/warehouse/ESCA/tweets/';

-- direct message extraction -------------------------------------------
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
		direct_message IS NOT NULL
) temp_table3
WHERE -- purge all incomplete direct_message
(	
	id IS NOT NULL
	AND
	created_at IS NOT NULL
	AND
	text IS NOT NULL
	AND
	user.id IS NOT NULL
	AND
	user.screen_name IS NOT NULL
	AND
	user.name IS NOT NULL
	AND
	user.lang IS NOT NULL
	AND
	user.followers_count IS NOT NULL
	AND
	user.time_zone IS NOT NULL
);

-- tweets extraction ---------------------------------------------------
DROP TABLE IF EXISTS tweets_clean;
CREATE TABLE tweets_clean AS
SELECT 
	id,
	created_at,
	text,
	"TwitterTweets" as type,
	user.id AS user_id,
	user.screen_name AS user_screen_name,
	user.name AS user_name,
	user.lang AS user_lang,
	user.followers_count AS user_followers_count,
	user.time_zone AS user_time_zone
FROM
(
	SELECT 
		id, 
		created_at, 
		text,
		user
	FROM 
		tweets_raw
	WHERE 
	(
		(id IS NOT NULL)
		AND
		(created_at IS NOT NULL)
		AND
		(text IS NOT NULL)
		AND
		(user IS NOT NULL)
		AND
		(user.id IS NOT NULL)
		AND
		(user.screen_name IS NOT NULL)
		AND
		(user.name IS NOT NULL)
		AND
		(user.lang IS NOT NULL)
		AND
		(user.followers_count IS NOT NULL)
		AND
		(user.time_zone IS NOT NULL)
	)
) temp_table;

-- dev	
--SELECT COUNT(*) AS count FROM tweets_clean;
--EXIT;

-- union all table in existing one -------------------------------------
CREATE TABLE IF NOT EXISTS ESCA_data
(
	id BIGINT,
	created_at STRING,
	text STRING,
	type STRING,
	user_id BIGINT,
	user_screen_name STRING,
	user_name STRING,
	user_lang STRING,
	user_followers_count BIGINT,
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
	user_screen_name,
	user_name,
	user_lang,
	user_followers_count,
	user_time_zone
FROM 
  tweets_clean  
WHERE
(
	(id IS NOT NULL)
	AND
	(created_at IS NOT NULL)
	AND
	(text IS NOT NULL)
	AND
	(user IS NOT NULL)
	AND
	(user.id IS NOT NULL)
	AND
	(user.screen_name IS NOT NULL)
	AND
	(user.name IS NOT NULL)
	AND
	(user.lang IS NOT NULL)
	AND
	(user.followers_count IS NOT NULL)
	AND
	(user.time_zone IS NOT NULL)
);

SELECT COUNT(*) AS count FROM ESCA_data;
SELECT DISTINCT COUNT(*) AS count_distinct FROM ESCA_data;
EXIT;

-- distinct data
DROP TABLE IF EXISTS temp_table;
CREATE TABLE temp_table
SELECT DISTINCT
	* 
FROM
	ESCA_data;

TRUNCATE TABLE ESCA_data;

INSERT INTO TABLE ESCA_data
SELECT 
	*
FROM
	temp_table;
