set hive.cli.print.header = true;

DROP TABLE IF EXISTS result_table;
CREATE TABLE result_table AS
SELECT
	*
FROM
(
	SELECT 
		ESCA_data.*,
		sentiment_evaluation.polarity,
		sentiment_evaluation.sentiment,
		from_unixtime(unix_timestamp(concat(substring(ESCA_data.created_at, 27,4),' ',substring(ESCA_data.created_at, 5,15)), 'yyyy MMM dd hh:mm:ss')) as data_timestamp
	FROM 
		ESCA_data
	JOIN
		sentiment_evaluation
	ON
		(ESCA_data.id = sentiment_evaluation.id)
	WHERE
	(
		(ESCA_data.created_at IS NOT NULL AND ESCA_data.created_at <> '')
		AND
		(ESCA_data.text IS NOT NULL AND ESCA_data.text <> '')
		AND
		(ESCA_data.type IS NOT NULL AND ESCA_data.type <> '')
		AND
		(user_id IS NOT NULL)
		AND
		(ESCA_data.user_screen_name IS NOT NULL AND ESCA_data.user_screen_name <> '')
		AND
		(ESCA_data.user_name IS NOT NULL AND ESCA_data.user_name  <> '')
		AND
		(ESCA_data.user_lang IS NOT NULL AND ESCA_data.user_lang <> '')
		AND
		(user_followers_count IS NOT NULL)
		AND
		(ESCA_data.user_time_zone IS NOT NULL AND ESCA_data.user_time_zone <> '')
	)
		
) temp_table
WHERE
(
	(data_timestamp IS NOT NULL AND data_timestamp <> '' )
	AND
	(type IS NOT NULL AND type <> '')
);

SELECT 
	* 
FROM 
	result_table
LIMIT 10;
