<h2><?php echo $realm; ?> Login</h2>
<form method="post" action="">
<div class="entry">
<label for="uid">Userid:</label><span class="field"><input name="uid" id="uid" required="" size="25" autofocus=""/></span>
</div>
<div class="entry">
<label for="pass">Password:</label><span class="field"><input type="password"
name="pass" id="pass" required="" size="25" /></span>
</div>
<div class="entry">
<label></label>
<input type="submit" name="submit" value="Login" /></div>
</form>
<p><a href="register.php">No userid?</a></p>
<p><a href="forgot.php">Forgot my password?</a></p>
