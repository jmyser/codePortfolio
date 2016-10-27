<h2>New Member Account</h2>
<form method="post" action="">

<div class="entry">
<label for="uid">Userid:</label><span class="field"><input name="uid" required="" size="25" autofocus=""/> (5 or more charcters)</span>
</div>

<div class="entry">
<label for="pass">Password:</label><span class="field"><input type="password"
name="pass" id="pass" required="" size="25"/> (8 or more charcters)</span>
</div>

<div class="entry">
<label for="pass2">Password again:</label><span class="field"><input type="password"
onblur="checkpass('pass', 'pass2')"
name="pass2" id="pass2" required="" size="25"/></span>
</div>

<div class="entry">
<label for="firstname">First Name:</label>
<span class="field">
	<input name="firstname" type="text" id="firstname" required="" size="25"/>
</span>
</div>

<div class="entry">
<label for="lastname">Last Name:</label>
<span class="field">
	<input name="lastname" type="text" id="lastname" required="" size="25"/>
</span>
</div>

<div class="entry">
<label for="email">Email:</label><span class="field"><input name="email" 
type="email" id="email" required="" size="25"/></span>
</div>

<div class="entry">
<label for="address">Address:</label>
<span class="field">
	<input name="address" type="text" id="address" required="" size="40"/>
</span>
</div>

<div class="entry">
<label for="state">State:</label>
<span class="field">
	<input name="state" type="text" id="state" required="" size="2"/>
</span>
</div>

<div class="entry">
<label for="zip">Zip:</label>
<span class="field">
	<input name="zip" type="text" id="zip" required="" size="5"/>
</span>
</div>

<div class="entry">
<label></label>
<input type="submit" name="submit" value="Register" /></div>
</form>
