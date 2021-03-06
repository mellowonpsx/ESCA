<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="ESCA - Extensible Social-Oriented Content Analysis">
		<meta name="author" content="Sergio Leoni">
		<link rel="icon" href="img/favico.png">
		<title>ESCA - Extensible Social-Oriented Content Analysis</title>
		<!-- Bootstrap core CSS -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- Custom styles for this template -->
		<!--<link href="css/bootstrap-theme.css" rel="stylesheet">-->
		<link href="css/bootstrap-esca.css" rel="stylesheet">
	</head>
	<body>
		<!-- navigation -->
		<nav class="navbar transparent navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.html">ESCA<!--<img src="img/logo.png" class="header-image">--></a>
				</div>
				<div id="navbar" class="collapse navbar-collapse pull-right">
					<ul class="nav navbar-nav">
						<li><a href="index.html">Home</a></li>
						<li><a href="setup.php">Setup</a></li>
						<li class="active"><a href="result.php">Result</a></li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</nav> <!-- end navigation -->
		<div class="spacers">
		</div>
		<!-- content -->
		<div class="container-fluid">
			<div class="jumbotron container-trasparent text-center">
				<div class="container-fluid">
					<h1>Results</h1>
					<h2>sentiment-analysis</h2>
					<p>
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
			<div id="globalChart" class="col-md-12" style="height: 400px; padding: 10px;" ></div>
			<div id="comparisonChart" class="col-md-6" style="height: 400px;"></div>
			<div id="bubblemapChart" class="col-md-6" style="height: 400px;"></div>
		<?php
	}
?>
					</p>
					<div class="container">
						<p>
							&nbsp;
						</p>
						<p>
							<a href="result_analysis_sentiment-analysis-module_graph_full_page.php">View graphs full page</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<div class="footer navbar transparent navbar-fixed-bottom">
			<footer>
				<div class="container text-center">
					<p>&copy; Rightocopy <a href="https://github.com/mellowonpsx">@mellowonpsx<img src="img/mellow_logo.png" class="footer-image"></a></p>
				</div>
			</footer>
		</div> <!-- end of master-container -->
		<!-- Bootstrap core JavaScript -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="jquery/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
<?php
	require_once('utils.php');
	
	global $db, $config;
	
	$status = $config->getParam("configured_analysis_sentiment-analysis-module");
	
	if($status == "true")
	{
		?>
		<script src="hs/js/highstock.js"></script>
		<script src="hs/js/highcharts-more.js"></script>
		<script src="hs/js/modules/exporting.js"></script>
		<script src="hm/js/modules/map.js"></script>
		<script src="hm/js/modules/data.js"></script>
		<script src="js/sentimentChart.js"></script>
		<script src="js/world-highres.js"></script>
		<?php
	}
?>
	</body>
</html>
