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
					<h1>Setup Data Source</h1>
					<h2>twitter-flume-user-module</h2>
					<p>
<?php

	session_start();
	require_once('utils.php');
	require_once('twitteroauth/twitteroauth.php');

	global $db, $config;
	
	$status = $config->getParam("configured_source_twitter-flume-user-module");
	
	if($status == "true")
	{
		echo "ERROR: already configured";
	}
	else if($status != "false")
	{
		echo "ERROR: misconfiguration";
	}
	else
	{
		if(empty($_REQUEST['oauth_token']) || empty($_REQUEST['oauth_verifier']))
		//if (empty($_SESSION['access_token']) || empty($_SESSION['access_token']['oauth_token']) || empty($_SESSION['access_token']['oauth_token_secret']))
		{
			session_destroy();
			echo 'To complete the set up phase, connect your twitter account </p><p><a href="./redirect.php"><button type="button" class="btn btn-primary btn-lg">Connect your twitter account <img height="19px" src="img/twitter_logo_white.png" alt="twitter logo"></button></a>';
		}
		else
		{
			/* Create TwitteroAuth object with app key/secret and token key/secret from default phase */
			$connection = new TwitterOAuth($config->getParam("consumer_key"), $config->getParam("consumer_secret"), $_SESSION['oauth_token'], $_SESSION['oauth_token_secret']);
			/* Request access tokens from twitter */
			$access_token = $connection->getAccessToken($_REQUEST['oauth_verifier']);
			$_SESSION['access_token'] = $access_token;
			unset($_SESSION['oauth_token']);
			unset($_SESSION['oauth_token_secret']);			
			
			$ck = $config->getParam("consumer_key");
			$cs = $config->getParam("consumer_secret");
			$ot = $access_token['oauth_token'];
			$os = $access_token['oauth_token_secret'];
			$user = $connection->get('account/verify_credentials');
			$sn = $user->screen_name;
			
			if(empty($ck) || empty($cs) || empty($ot) || empty($os) || empty($sn))
			{
				//
			}
			else
			{
				$flume_conf = fopen('twitter-flume-user-module/flume.conf', 'a');
				if($flume_conf == null) return;
				fwrite($flume_conf, "\nTwitterAgent.sources.Twitter.consumerKey=".$ck);
				fwrite($flume_conf, "\nTwitterAgent.sources.Twitter.consumerSecret=".$cs);
				fwrite($flume_conf, "\nTwitterAgent.sources.Twitter.accessToken=".$ot);
				fwrite($flume_conf, "\nTwitterAgent.sources.Twitter.accessTokenSecret=".$os);
				fwrite($flume_conf, "\nTwitterAgent.sources.Twitter.screenName=".$sn);
				fwrite($flume_conf, "\nTwitterAgent.sources.Twitter.isTesting=false");
				fwrite($flume_conf, "\nTwitterAgent.sources.Twitter.language=en");
				fclose($flume_conf);
				//set up cron work
				echo "Setup requires to run shell script, this is the result:";
				echo "<pre>";
				echo shell_exec("cd twitter-flume-user-module 2>&1; ./addCron.sh 2>&1");
				echo "</pre>";
				$config->updateParam("configured_source_twitter-flume-user-module", "true");
			}
		}
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
