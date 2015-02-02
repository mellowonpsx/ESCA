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
						<li class="active"><a href="setup.php">Setup</a></li>
						<li><a href="result.php">Result</a></li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</nav> <!-- end navigation -->
		<div class="spacers">
		</div>
		<!-- content -->
		<div class="container container-fullpage">
			<div class="jumbotron container-trasparent text-center">
				<div class="container">
					<h1>Setup</h1>
					<h2>Data Source</h2>
					<p>
						To set up a new Data Source, select available one and and follow the set up steps.
						<?php
						require_once('utils.php');
						global $db, $config;
						$source_names = json_decode($config->getParam("source_names"));
						if(!empty($source_names))
						{
							foreach ($source_names as $source)
							{
								$name = $source->name;
								$status = $config->getParam("configured_source_".$name);
								if($status != "true") //remember, is a string
								{
									echo "<p>Source $name: <a href=\"configure_source_$name.php\"> setup </a></p>";
								}
								else
								{
									echo "<p>Source $name: already configured</p>";
								}
							}
						}else
						{
							echo "<p>No source to configure</p>";
						}
						?>
					</p>
					<h2>Data Analysis</h2>
					<p>
						To set up a new Data Analysis, select available one and and follow the set up steps.
						<?php
						require_once('utils.php');
						global $db, $config;
						$analysis_names = json_decode($config->getParam("analysis_names"));
						if(!empty($analysis_names))
						{
							foreach ($analysis_names as $analysis)
							{
								$name = $analysis->name;
								$status = $config->getParam("configured_analysis_".$name);
								if($status != "true") //remember, is a string
								{
									echo "<p>Analysis $name: <a href=\"configure_analysis_$name.php\"> setup </a></p>";
								}
								else
								{
									echo "<p>Analysis $name: already configured</p>";
								}
							}
						}else
						{
							echo "<p>No analysis to configure</p>";
						}
						?>
					</p>
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
	</body>
</html>
