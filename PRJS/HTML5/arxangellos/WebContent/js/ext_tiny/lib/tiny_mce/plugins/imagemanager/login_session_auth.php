<?php
	// Some settings
	$msg = "";
	

	if (isset($_POST['submit_button'])) {
		
			
			// Set session
			session_start();
			$_SESSION['isLoggedIn'] = true;
			$_SESSION['user'] = $_POST['login'];
			
			
			// Override any config option
			//$_SESSION['imagemanager.filesystem.rootpath'] = 'some path';
			//$_SESSION['filemanager.filesystem.rootpath'] = 'some path';

			
			// Redirect
			header("location: " . $_POST['return_url']);
			die;
			
			
			
			
	}
	
	
	
	
?>

<html>
<head>
<title>Image Manager</title>
<style>
body { font-family: Arial, Verdana; font-size: 11px; }
fieldset { display: block; width: 170px; }
legend { font-weight: bold; }
label { display: block; }
div { margin-bottom: 10px; }
div.last { margin: 0; }
div.container { position: absolute; top: 50%; left: 50%; margin: -100px 0 0 -85px; }
h1 { font-size: 14px; }
.button { border: 1px solid gray; font-family: Arial, Verdana; font-size: 11px; }
.error { color: red; margin: 0; margin-top: 10px; }
</style>
</head>
<body>

<div class="container">
	
	<form action="login_session_auth.php" method="post">
		
		<input type="hidden" name="return_url" value="<?php echo isset($_REQUEST['return_url']) ? htmlentities($_REQUEST['return_url']) : ""; ?>" />


			
		
			<div class="last">
			   
				<input width="200" hieght="40"  type="submit" name="submit_button" value="Click here to Open Image Manager " class="button" />
			</div>

<?php if ($msg) { ?>
			<div class="error">
				<?php echo $msg; ?>
			</div>
<?php } ?>
		
		
		
	</form>
</div>

</body>
</html>
