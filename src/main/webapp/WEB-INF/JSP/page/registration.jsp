<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4 class="text-center"><mytags:message key="registration.registration" /></h4>
<hr />
<c:if test="${UNSUCCESS_MESSAGE != null }">
	<div class="alert alert-danger hidden-print" role="alert">${UNSUCCESS_MESSAGE }</div>
</c:if>
<form action="/subpub/registration" method="post">
	<div id="registration-first-name-input" class="form-group">
		<label><mytags:message key="registration.firstName" /></label>
		 <input id="registration-first-name" maxlength="30" name="firstName" class="form-control" value="${form.firstName }" placeholder="<mytags:message key="registration.firstName"/>">
	</div>
	<div id="registration-last-name-input" class="form-group">
		<label><mytags:message key="registration.lastName" /></label>
		<input id="registration-last-name" maxlength="30" name="lastName" class="form-control " value="${form.lastName }" placeholder="<mytags:message key="registration.lastName"/>">
	</div>
	<div id="registration-email-input" class="form-group">
		<label><mytags:message key="registration.email" /></label> 
		<input id="registration-email" type="email" name="email" class="form-control " maxlength="25" value="${form.email }" placeholder="<mytags:message key="registration.email"/>">
	</div>
	<div id="registration-password-input" class="form-group">
		<label><mytags:message key="registration.password" /></label> 
		<input id="registration-password" maxlength="30" name="password" type="password" class="form-control" placeholder="<mytags:message key="registration.password"/>">
	</div>
	<div id="registration-conf-password-input" class="form-group">
		<label><mytags:message key="registration.confirmPassword" /></label> 
		<input id="registration-conf-password" maxlength="25" name="confirmPassword" type="password" class="form-control" placeholder="<mytags:message key="registration.confirmPassword"/>">
	</div>
	<div class="text-center">
		<button type="submit" class="btn btn-default registration-btn">
			<mytags:message key="registration.submit" />
		</button>
	</div>
</form>
<tags:sign-in-popup/>