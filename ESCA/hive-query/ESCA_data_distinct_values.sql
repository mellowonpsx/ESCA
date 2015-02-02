-- starting drop, useful on non-endend query
DROP TABLE IF EXISTS temp_table;

-- put all distinct values in a temp table -----------------------------
DROP TABLE IF EXISTS temp_table;
CREATE TABLE temp_table AS
SELECT DISTINCT
	id,
	created_at,
	text,
	type,
	user_id,
	user_username,
	user_name,
	user_lang,
	user_influence,
	user_time_zone
FROM
	ESCA_data;

-- TRUNC ESCA_data table -----------------------------------------------
DROP TABLE IF EXISTS ESCA_data;
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

-- copy values to ESCA_data from temp table ----------------------------
INSERT INTO TABLE ESCA_data
SELECT
	*
FROM
	temp_table;
	
-- final drop of temp tables -------------------------------------------
DROP TABLE IF EXISTS temp_table;
