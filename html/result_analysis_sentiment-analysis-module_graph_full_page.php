<?php
	require_once('utils.php');
	
	global $db, $config;
	
	$status = $config->getParam("configured_analysis_sentiment-analysis-module");
	
	if($status == "false")
	{
		echo "ERROR: not configured";
	}
	else if($status != "true")
	{
		echo "ERROR: misconfiguration";
	}
	else
	{
		?>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>ESCA Report</title>
		<!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>-->
	</head>
	<body>
		<div id="globalChart" style="height: 400px; min-width: 310px"></div>
		<div style="width: 100%">
			<span id="comparisonChart" style="height: 400px; width: 50%; display: inline-block"></span><span id="bubblemapChart" style="height: 400px; width: 50%; display: inline-block"></span>
		</div>
		<script src="jquery/jquery.min.js"></script>
		<script src="hs/js/highstock.js"></script>
		<script src="hs/js/highcharts-more.js"></script>
		<script src="hs/js/modules/exporting.js"></script>
		<script src="hm/js/modules/map.js"></script>
		<script src="hm/js/modules/data.js"></script>
		<script src="js/sentimentChart.js"></script>
		<script src="js/world-highres.js"></script>
	</body>
</html>
		<?php
	}
?>
