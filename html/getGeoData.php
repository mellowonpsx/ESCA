<?php

//$a = array(array('code' => 'AF','z' => 31412), array('code' => 'US', 'z'=> 310384), array('code'=> 'AU', 'z' => 22268));
//echo json_encode($a, JSON_NUMERIC_CHECK); return;

	require_once('utils.php');
	global $db, $config;
	$result_array = array();

	if(isset($_GET["time"]))
	{
		$time = $db->escape(filter_var($_GET["time"], FILTER_SANITIZE_NUMBER_INT));
	}
	else
	{
		$time = 0;
	}
	
	if($time > 0)
	{
		$query = "SELECT user_time_zone as 'code', count_entity as 'z' FROM geographic_report
					WHERE full_date_time = ".$time."
					ORDER BY user_time_zone ASC";
	}
	else
	{
		$query = "SELECT user_time_zone as 'code', sum(count_entity) as 'z' FROM geographic_report
					GROUP BY user_time_zone
					ORDER BY user_time_zone ASC";
	}
				
	$result = $db->query($query);
	if($result)
	{
		while($row = mysqli_fetch_assoc($result))
		{
			array_push($result_array,$row);
		}	
		echo json_encode($result_array, JSON_NUMERIC_CHECK);
		return;
	}
	else
	{
		echo "error: ".$db->error();
	}
	return;

?>
