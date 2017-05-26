<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="categories" class="panel panel-default collapse">
  <div class="panel-heading">Publication's subject</div>
    <div class="list-group">
		<c:forEach var="category" items="${CATEGORY_LIST }">
	     	<a href="/subpub/publications${category.url }" class="list-group-item ${selectedCategoryUrl == category.url ? 'active' : ''}">
	     	 	<span class="badge">${category.publicationCount }</span> ${category.name }
	     	</a>
	    </c:forEach>
  </div>
</div>