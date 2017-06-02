<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<div class="alert alert-info">
  <p><mytags:message key="publications.searchResult.found"/> <strong>${publicationCount }</strong> <mytags:message key="publications.searchResult.publications"/></p>
</div>

<jsp:include page="publications.jsp" />