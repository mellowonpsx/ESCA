<?php

	require_once('utils.php');

	global $db, $config;
	$result_array = array();

	/*$query = " SELECT (UNIX_TIMESTAMP(full_date_time)*1000) as full_date_time, sentiment_value, polarity_value, 
				positive_sentiment, negative_sentiment, neutral_sentiment FROM sentiment_daily_report 
				ORDER BY full_date_time ASC";
	*/			
	$query = " SELECT full_date_time, sentiment_value, polarity_value, positive_sentiment, negative_sentiment, neutral_sentiment FROM sentiment_report ORDER BY full_date_time ASC";
				
	$result = $db->query($query);
	if($result)
	{
		while($row = mysqli_fetch_assoc($result))
		{
			array_push($result_array,$row);
		}	
		echo json_encode($result_array,  JSON_NUMERIC_CHECK);
		return;
	}
	else echo "error";
	return;

?>
