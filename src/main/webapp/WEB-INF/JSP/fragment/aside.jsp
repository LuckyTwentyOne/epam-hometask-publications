<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="visible-xs-block xs-option-container">
	<a class="pull-right" data-toggle="collapse" href="#subjects"><mytags:message key="aside.category"/><span class="caret"></span></a>
	<a data-toggle="collapse" href="#searchForm"><mytags:message key="aside.search"/><span class="caret"></span></a>
</div>
<!-- Search form -->
<tags:search-panel/>
<!-- /Search form -->
<!-- Categories -->
<tags:category-list/>
<!-- /Categories -->
<c:if test="${CURRENT_ACCOUNT.role=='admin' }">
	<a href="/subpub/admin/addPublication" id="add-publication-btn" class="btn btn-success btn-block">Add publication</a>
</c:if>