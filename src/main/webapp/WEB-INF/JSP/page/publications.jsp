<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<c:choose>
	<c:when test="${CURRENT_MESSAGE != null }">
		<div class="alert alert-danger hidden-print" role="alert">${CURRENT_MESSAGE }</div>
	</c:when>
	<c:when test="${SUCCESS_MESSAGE != null }">
		<div class="alert alert-success hidden-print" role="alert">${SUCCESS_MESSAGE }</div>
	</c:when>
</c:choose>
<div id="publicationList" data-page-count="${pageCount}" data-page-number="1">
	<div class="row">
		<jsp:include page="../fragment/publication-list.jsp" />
	</div>
	<c:if test="${pageCount > 1 }">
		<div class="text-center hidden-print">
				 <a class="btn btn-success"	id="loadMorePublications" href="#"><mytags:message key="publications.loadMore"/></a>
		</div>
	</c:if>
</div>
<tags:subscribe-publication-popup/>
<tags:sign-in-popup/>