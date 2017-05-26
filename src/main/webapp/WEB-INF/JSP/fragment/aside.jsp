<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<div class="visible-xs-block xs-option-container">
	<a class="pull-right" data-toggle="collapse" href="#subjects">Publication's subject <span class="caret"></span></a>
	<a data-toggle="collapse" href="#searchForm">Search publication <span class="caret"></span></a>
</div>
<!-- Search form -->
<tags:search-panel/>
<!-- /Search form -->
<!-- Categories -->
<tags:category-list/>
<!-- /Categories -->