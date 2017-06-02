<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="mytags.tld" prefix="mytags"%>
<form action="/subpub/search">
	<div id="searchForm" class="panel panel-default collapse">
	  <div class="panel-heading"><mytags:message key="aside.search"/></div>
	    <div class="panel-body">
		  <div class="input-group">
		      <input type="text" name="query" class="form-control" placeholder='<mytags:message key="aside.search.query"/>' value="${searchForm.query }">
		      <span class="input-group-btn">
		        <button type="submit" class="btn btn-default"><mytags:message key="aside.search.btn"/></button>
		      </span>
		   </div>
		   <div class="sort-by">
			<a data-toggle="collapse" href="#sortOptions"><mytags:message key="aside.search.sortBy"/> <span class="caret"></span></a>
		   </div>
	  </div>
	  <div id="sortOptions" class="collapse ${searchForm.sorted ? 'in' : ''}">
	    	<div class="panel-body sortPanel">
	    		<div class="radio">
	    			<label><input type="radio" name="sortBy" value="name" ${searchForm.sortedBy=='name'? 'checked' : ''}> <mytags:message key="aside.search.sortBy.name"/></label>
	    		</div>
	    		<div class="radio">
	    			<label><input type="radio" name="sortBy" value="price" ${searchForm.sortedBy=='price'? 'checked' : ''}> <mytags:message key="aside.search.sortBy.price"/></label>
	    		</div>
	    	</div>
	  </div>
	</div>
</form>