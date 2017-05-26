<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4 class="text-center">
	<mytags:message key="sign-in.sign-in" />
</h4>
<hr />
<form class="form-horizontal col-md-10 col-md-offset-1">
	<div id="sign-in-email-input" class="form-group">
		<label class="col-sm-2 control-label">Email</label>
		<div class="col-sm-10">
			<input type="email" class="form-control" id="sign-in-email" placeholder="Email">
		</div>
	</div>
	<div id="sign-in-password-input" class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
		<div class="col-sm-10">
			<input type="password" class="form-control" id="sign-in-password" placeholder="Password">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<div class="checkbox">
				<label> <input type="checkbox"> Remember me
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">Sign in</button>
		</div>
	</div>
</form>
<div class="clear"></div>
<hr />
<form class="text-center" action="/subpub/sign-in/facebook" method="post">
	<button type="submit" class="btn btn-primary"><i class="fa fa-facebook-official" aria-hidden="true"></i> Sign in</button>
	<a href="/subpub/registration" class="btn btn-default">Registration</a>
</form>
<tags:sign-in-popup />