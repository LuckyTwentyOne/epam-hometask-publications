<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<nav class="navbar navbar-inverse ">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#subpubNav" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/subpub/publications">The Press</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="subpubNav">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/subpub/switch"><mytags:message key="header.change-language"/></a></li> 
        <c:choose>
        	<c:when test="${CURRENT_ACCOUNT == null and CURRENT_REQUEST_URL == '/subpub/sign-in'}"></c:when>
			<c:when test="${CURRENT_ACCOUNT != null and CURRENT_ACCOUNT.role=='reader'}">
				<li><a><mytags:message key="header.user.info"/>&nbsp;${CURRENT_ACCOUNT.description }</a></li>
				<li><a href="/subpub/my-subscriptions"><mytags:message key="header.my-subscriptions"/></a></li>
				<li><a href="/subpub/my-balance"><mytags:message key="header.my-account"/></a></li>
				<li><a href="javascript:void(0);" class="post-request" data-url="/subpub/sign-out"><mytags:message key="header.sign-out"/></a></li>
			</c:when>
			<c:when test="${CURRENT_ACCOUNT != null and CURRENT_ACCOUNT.role=='admin'}">
				<li><a><mytags:message key="header.user.info"/>&nbsp;${CURRENT_ACCOUNT.role }</a></li>
				<li><a href="/subpub/admin/accounts"><mytags:message key="header.admin.accounts"/></a></li>
				<li><a href="javascript:void(0);" class="post-request" data-url="/subpub/sign-out"><mytags:message key="header.sign-out"/></a></li>
			</c:when>
			<c:otherwise>
			    <li><a class="sigh-in-btn"><mytags:message key="sign-in.sign-in"/></a></li>
			</c:otherwise>
        </c:choose>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>