ADD JAR /usr/lib/hive/lib/json-serde-1.3-jar-with-dependencies.jar;

-- dev: show header
-- set hive.cli.print.header = true;
-- end dev 

-- drop everything on start, in case of falied execution
DROP TABLE IF EXISTS dictionary;
DROP TABLE IF EXISTS sentence_subdivision;
DROP TABLE IF EXISTS word_subdivision;
DROP TABLE IF EXISTS word_evaluation;
DROP TABLE IF EXISTS sentiment_evaluation;
DROP TABLE IF EXISTS sentiment_data;
DROP TABLE IF EXISTS sentiment_daily_report;
DROP TABLE IF EXISTS sentiment_global_report;

-- create the dictionary table -----------------------------------------
DROP TABLE IF EXISTS dictionary;
CREATE EXTERNAL TABLE dictionary
(
    type string,
    length int,
    word string,
    pos string,
    stemmed string,
    polarity string
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' 
STORED AS TEXTFILE
LOCATION '/user/hive/warehouse/ESCA/dictionary/';

-- subdivide text in sentence ------------------------------------------
DROP TABLE IF EXISTS sentence_subdivision;
create TABLE sentence_subdivision as
select
	id,
	words
from 
	ESCA_data
lateral view
	explode(sentences(lower(text))) dummy as words;

-- subdivide sentence in word ------------------------------------------
DROP TABLE IF EXISTS word_subdivision;
create TABLE word_subdivision as
select
	id,
	word
from
	sentence_subdivision
lateral view
	explode(words) dummy as word;

-- evaluate each single word -------------------------------------------
DROP TABLE IF EXISTS word_evaluation;
create TABLE word_evaluation as
select 
	id, 
	word_subdivision.word,
	case dictionary.polarity 
		when 'negative'
			then -1
		when 'positive'
			then 1 
		else 0
	end as polarity 
from word_subdivision left outer join dictionary on word_subdivision.word = dictionary.word;

-- regroup word --------------------------------------------------------
DROP TABLE IF EXISTS sentiment_evaluation;
create TABLE sentiment_evaluation as 
select 
	id,
	sum(polarity) as polarity,
	case 
		when sum(polarity) > 0
			then 'positive' 
		when sum(polarity) < 0
			then 'negative'  
		else 'neutral' 
	end as sentiment 
from word_evaluation
group by id;

-- create sentiment_data table for sqoop export ------------------------
DROP TABLE IF EXISTS sentiment_data;
CREATE TABLE sentiment_data 
STORED AS TEXTFILE
LOCATION '/user/hive/warehouse/ESCA/sentiment_data/' AS
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
	(ESCA_data.created_at IS NOT NULL AND ESCA_data.created_at <> '');

-- create sentiment_daily_report for sqoop export ----------------------
DROP TABLE IF EXISTS sentiment_report;
CREATE TABLE sentiment_report
STORED AS TEXTFILE
LOCATION '/user/hive/warehouse/ESCA/sentiment_report/' AS
SELECT
	full_date_time,
	sum(count_sentiment) as sentiment_value,
	sum(pol_sentiment) as polarity_value,
	sum(positive_sentiment) as positive_sentiment,
	sum(negative_sentiment) as negative_sentiment,
	sum(neutral_sentiment) as neutral_sentiment
FROM
(
	SELECT
		unix_timestamp(concat(substring(data_timestamp,0,14),'00:00'))*1000 as full_date_time,
		count(sentiment) as check_count,
		sum(polarity) as pol_sentiment,
		case
			when sentiment = 'positive'
				then count(sentiment)
			when sentiment = 'negative'
				then -count(sentiment)
			else 
				0
		end as count_sentiment,
		if(sentiment = 'positive', count(sentiment), 0) as positive_sentiment,
		if(sentiment = 'negative', count(sentiment), 0) as negative_sentiment,
		if(sentiment = 'neutral', count(sentiment), 0) as neutral_sentiment,
		sentiment
	FROM 
		sentiment_data
	GROUP BY
		unix_timestamp(concat(substring(data_timestamp,0,14),'00:00'))*1000, sentiment
) temp_table
GROUP BY
	full_date_time;
	
-- create geographic_report for scoop_export ---------------------------
DROP TABLE IF EXISTS geographic_report;
CREATE TABLE geographic_report
STORED AS TEXTFILE
LOCATION '/user/hive/warehouse/ESCA/geographic_report/' AS
SELECT
	unix_timestamp(concat(substring(data_timestamp,0,14),'00:00'))*1000 as full_date_time,
	count(data_timestamp) as count_entity,
	user_time_zone
FROM 
	sentiment_data
GROUP BY
	unix_timestamp(concat(substring(data_timestamp,0,14),'00:00'))*1000, user_time_zone;
	
-- final drop of temp tables -------------------------------------------
DROP TABLE IF EXISTS dictionary;
DROP TABLE IF EXISTS sentence_subdivision;
DROP TABLE IF EXISTS word_subdivision;
DROP TABLE IF EXISTS word_evaluation;
DROP TABLE IF EXISTS sentiment_evaluation;
