<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<form action="/subpub/search">
	<div id="searchForm" class="panel panel-default collapse">
	  <div class="panel-heading">Search publication</div>
	    <div class="panel-body">
		  <div class="input-group">
		      <input type="text" name="query" class="form-control" placeholder="Search query" value="${searchForm.query }">
		      <span class="input-group-btn">
		        <button type="submit" class="btn btn-default">Go!</button>
		      </span>
		   </div>
		   <div class="sort-by">
			<a data-toggle="collapse" href="#sortOptions">Sort by <span class="caret"></span></a>
		   </div>
	  </div>
	  <div id="sortOptions" class="collapse ${searchForm.sorted ? 'in' : ''}">
	    	<div class="panel-body sortPanel">
	    		<div class="radio">
	    			<label><input type="radio" name="sortBy" value="name" ${searchForm.sortedBy=='name'? 'checked' : ''}> Name</label>
	    		</div>
	    		<div class="radio">
	    			<label><input type="radio" name="sortBy" value="price" ${searchForm.sortedBy=='price'? 'checked' : ''}> Price</label>
	    		</div>
	    	</div>
	  </div>
	</div>
</form>