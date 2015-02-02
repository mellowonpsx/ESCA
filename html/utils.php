<?php
/**
 * utils
 *
 * @author mellowonpsx
 */
function __autoload($classname)
{
	// if i am in class directory, the file is in the directory
    $filename =  strtolower($classname.'.class.php');
    if(file_exists($filename))
    {
		require_once($filename);
	}
	else
	{
		if(file_exists('../'.$filename))
		{
			require_once('../'.$filename);
		}
		else
		{
			if(file_exists('class/'.$filename))
			{
				require_once('class/'.$filename);
			}
		}
	}
}

/*
function getSessionUser()
{
    $sessionId = session_id(); 
    if(empty($sessionId))
    {
        session_start() or die("Could not start session");
    }
    if(isset($_SESSION["user"]))
    {
        $user = $_SESSION["user"];
    }
    else
    {
        $user = NULL;
    }
    return $user;
}

function setSessionUser($user)
{
    $sessionId = session_id(); 
    if(empty($sessionId))
    {
        session_start() or die("Could not start session");
    }
    $_SESSION["user"] = $user;
}

function removeSession()
{
    $sessionId = session_id(); 
    if(empty($sessionId))
    {
        session_start() or die("Could not start session");
    }
    $_SESSION["user"] = NULL;
    session_destroy();
}*/
//$user = getSessionUser();

$config = new Config();
$db = new DB();
