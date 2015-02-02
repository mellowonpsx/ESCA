-- dev, no cli passing parameter
-- set screen_name=mellowonpsx; 
-- EXAMPLE: cli passing parameter
-- hive -f twitter_loader.sql -hiveconf screen_name="mellowonpsx"
-- end dev

-- dev: show header
-- set hive.cli.print.header = true;
-- end dev 

ADD JAR /usr/lib/hive/lib/json-serde-1.3-jar-with-dependencies.jar;

-- starting drop, useful on non-endend query
DROP TABLE IF EXISTS tweets_raw;
DROP TABLE IF EXISTS tweets_clean;
DROP TABLE IF EXISTS tweets_clean_geo;
DROP TABLE IF EXISTS georeference;

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
LOCATION '/user/flume/completed/sample-tweets/';
-- LOCATION '/user/hive/warehouse/ESCA/tweets/';

-- tweets cleaning -----------------------------------------------------
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
	tweets_raw
WHERE 
(
	(id IS NOT NULL)
	AND
	(created_at IS NOT NULL AND created_at <> '')
	AND
	(text IS NOT NULL AND text <> '')
	AND
	(user IS NOT NULL)
);

-- timezone georeference -----------------------------------------------
DROP TABLE IF EXISTS georeference;
CREATE EXTERNAL TABLE IF NOT EXISTS georeference
(
	iso_code STRING,
	twitter_code STRING
)
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE
LOCATION '/user/hive/warehouse/ESCA/georeference';

-- join tweet with georeference ----------------------------------------
DROP TABLE IF EXISTS tweets_clean_geo;
CREATE TABLE tweets_clean_geo AS
SELECT 
	id,
	created_at,
	text,
	"TwitterTweets" as type,
	user_id,
	user_screen_name,
	user_name,
	user_lang,
	user_followers_count,
	georeference.iso_code AS user_time_zone
FROM
	tweets_clean
JOIN
	georeference
ON
	(lower(trim(tweets_clean.user_time_zone)) = lower(trim(georeference.twitter_code)))
WHERE 
(
	(id IS NOT NULL)
	AND
	(created_at IS NOT NULL AND created_at <> '')
	AND
	(text IS NOT NULL AND text <> '')
	AND
	(type IS NOT NULL AND type <> '')
	AND
	(user_id IS NOT NULL)
	AND
	(user_screen_name IS NOT NULL AND user_screen_name <> '')
	AND
	(user_name IS NOT NULL AND user_name  <> '')
	AND
	(user_lang IS NOT NULL AND user_lang <> '')
	AND
	(user_followers_count IS NOT NULL)
	AND
	(user_time_zone IS NOT NULL AND user_time_zone <> '')
	AND
	(length(user_time_zone) > 2)
);

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
	tweets_clean_geo;
  

-- final drop of temp tables
DROP TABLE IF EXISTS tweets_raw;
DROP TABLE IF EXISTS tweets_clean;
DROP TABLE IF EXISTS tweets_clean_geo;
DROP TABLE IF EXISTS georeference;
