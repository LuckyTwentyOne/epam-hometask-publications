;$(function(){
	var init = function(){
		initBuyBtn();
		$('#subscribe-btn').click(submitSubscription);
		$('#subscribePublicationPopup .count').change(calculateCost);
		$('#loadMorePublications').click(loadMorePublications);
	};
	
	var showSubscribePublicationPopup = function(){
		var idPublication = $(this).attr('data-id-publication');
		var publication = $('#publication'+idPublication);
		$('#subscribePublicationPopup').attr('data-id-publication', idPublication);
		$('#subscribePublicationPopup .publication-image').attr('src', publication.find('.thumbnail img').attr('src'));
		$('#subscribePublicationPopup .name').text(publication.find('.name').text());
		var price = publication.find('.price').text();
		$('#subscribePublicationPopup .price').text(price);
		$('#subscribePublicationPopup .category').text(publication.find('.category-name').text());
		$('#subscribePublicationPopup .description').text(publication.find('.description').text());
		$('#subscribePublicationPopup .conditions').text(publication.find('.conditions').text());
		$('#subscribePublicationPopup .count').val(1);
		$('#subscribePublicationPopup .cost').text(price);
		$('#subscribePublicationPopup').modal({
			show:true
		});
	};
	var initBuyBtn = function() {
		$('.buy-btn').click(showSubscribePublicationPopup)
	};
	
	var submitSubscription = function(){
		var idPublication = $('#subscribePublicationPopup').attr('data-id-publication');
		var count = $('#subscribePublicationPopup .count').val();
		var totalCost = $('#subscribePublicationPopup .cost').text();
		var btn = $('#subscribe-btn');
		convertButtonToLoader(btn, 'btn-primary');
		$.ajax({
			url : '/iShop/ajax/json/subscribe',
			method : 'POST',
			data : {
				idPublication : idPublication,
				count : count,
				totalCost: totalCost
			},
			success : function(data) {
				alert('Successfull subscription');
				$('#addProductPopup').modal('hide');
				convertLoaderToButton(btn, 'btn-primary', submitSubscription);
			},
			error : function(xhr) {
				convertLoaderToButton(btn, 'btn-primary', submitSubscription);
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}
		});
	};
	
	var calculateCost = function(){
		var priceStr = $('#subscribePublicationPopup .price').text();
		var price = parseFloat(priceStr);
		var count = parseInt($('#subscribePublicationPopup .count').val());
		var min = parseInt($('#subscribePublicationPopup .count').attr('min'));
		var max = parseInt($('#subscribePublicationPopup .count').attr('max'));
		if(count >= min && count <= max) {
			var cost = price * count;
			$('#subscribePublicationPopup .cost').text(cost);
		} else {
			$('#subscribePublicationPopup .count').val(1);
			$('#subscribePublicationPopup .cost').text(priceStr);
		}
	};
	
	var convertButtonToLoader = function (btn, btnClass) {
		btn.removeClass(btnClass);
		btn.removeClass('btn');
		btn.addClass('load-indicator');
		var text = btn.text();
		btn.text('');
		btn.attr('data-btn-text', text);
		btn.off('click');
	};
	var convertLoaderToButton = function (btn, btnClass, actionClick) {
		btn.removeClass('load-indicator');
		btn.addClass('btn');
		btn.addClass(btnClass);
		btn.text(btn.attr('data-btn-text'));
		btn.removeAttr('data-btn-text');
		btn.click(actionClick);
	};
	
	var loadMorePublications = function (){
		var btn = $('#loadMorePublications');
		convertButtonToLoader(btn, 'btn-success');
		var pageNumber = parseInt($('#publicationList').attr('data-page-number'));
		var path=location.pathname.substring('/subpub'.length);
		var searchQuery = location.search.substring(1);
		var url = '/subpub/ajax/html/more' + path + '?page=' + (pageNumber+1)+ '&' + searchQuery;
		$.ajax({
			url : url,
			success : function(html) {
				$('#publicationList .row').append(html);
				pageNumber++;
				var pageCount = parseInt($('#publicationList').attr('data-page-count'));
				$('#publicationList').attr('data-page-number', pageNumber);
				if(pageNumber < pageCount) {
					convertLoaderToButton(btn, 'btn-success', loadMorePublications);
				} else {
					btn.remove();
				}
				initBuyBtn();
				
			},
			error : function(data) {
				convertLoaderToButton(btn, 'btn-success', loadMorePublications);
				alert('Error');
			}
		});
	};
	
	var confirm = function (msg, okFunction) {
		if(window.confirm(msg)) {
			okFunction();
		}
	};
	
	var removePublication = function (){
		var btn = $(this);
		confirm('Are you sure?', function(){
			executeRemovePublication(btn);
		});
	};
	
	var executeRemovePublication = function (btn) {
		var idPublication = btn.attr('data-id-publication');
		convertButtonToLoader(btn, 'btn-danger');
		$.ajax({
			url : '/iShop/ajax/json/publication/remove',
			method : 'POST',
			data : {
				idPublication : idPublication
			},
			success : function(data) {
				alert('Successfull remove');
				$('#publication' + idProduct).remove();
			},
			error : function(data) {
				convertLoaderToButton(btn, 'btn-danger', removePublication);
				alert('Error');
			}
		});
	};
	
	init();
});