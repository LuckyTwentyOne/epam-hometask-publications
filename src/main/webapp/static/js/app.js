;$(function(){
	var init = function(){
		initBuyBtn();
		$('.sigh-in-btn').click(showLoginPopup);
		$('#subscribe-btn').click(submitSubscription);
		$('#subscribePublicationPopup .count').change(calculateCost);
		$('#loadMorePublications').click(loadMorePublications);
		$('[data-toggle="tooltip"]').tooltip();
		$('#sign-in-email, #sign-in-password, #registration-email, #registration-first-name, #registration-conf-password, #registration-last-name, #registration-password').unbind().blur(validateForm);
		$('.post-request').click(function(){
			postRequest($(this).attr('data-url'));
		});
		$('#makeSubscription').click(function(){
			makeSubscription($(this).attr('data-url'));
		});
	};
	
	var postRequest = function(url){
		var form = '<form id="postRequestForm" action="'+url+'" method="post"></form>';
		$('body').append(form);
		$('#postRequestForm').submit();
	};
	
	var makeSubscription = function(url) {
		var idPublication = $('#subscribePublicationPopup').attr('data-id-publication');
		var numberOfMonthes = $('#subscribePublicationPopup .count').val();
		var form = '<form id="makeSubscriptionForm" action="'+url+'" method="post"><input type="hidden" name="idPublication" value="'+idPublication+'"></input><input type="hidden" name="numberOfMonthes" value="'+numberOfMonthes+'"></input></form>';
		$('body').append(form);
		$('#makeSubscriptionForm').submit();
	}
	
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
	
	var showLoginPopup = function(){
		$('#sighinPopup').modal({
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
				$('#loadMorePublications').focus();
				$('html, body').animate({
					scrollTop : $('#loadMorePublications').offset().top
				}, 2000);
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
	
	var validateForm = function() {
		var id = $(this).attr('id');
        var val = $(this).val();
        switch(id)
        {
              case 'sign-in-email':
            	  var pattern_email = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
                  if(val != '' && pattern_email.test(val))
                  {
                	  $('#sign-in-email-input').removeClass('has-error');
                	  $('#sign-in-email-input').addClass('has-success');
                  }
                  else
                  {
                	  $('#sign-in-email-input').addClass('has-error');
                	  $('#sign-in-email').val('');
                	  $('#sign-in-email').attr('placeholder', 'Incorrect format')
                  }
                  break;
                  
              case 'registration-email':
            	  var pattern_email = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
                  if(val != '' && pattern_email.test(val))
                  {
                	  $('#registration-email-input').removeClass('has-error');
                	  $('#registration-email-input').addClass('has-success');
                  }
                  else
                  {
                	  $('#registration-email-input').addClass('has-error');
                	  $('#registration-email').val('');
                	  $('#registration-email').attr('placeholder', 'Incorrect format')
                  }
                  break;
              
              case 'sign-in-password':
            	  var pattern_password = /^[a-zA-Zа-яА-Я0-9]+$/;
            	  if(val.length > 6 && val.length < 30 && val != '' && pattern_password.test(val))
            	  {
            		  $('#sign-in-password-input').removeClass('has-error');
                	  $('#sign-in-password-input').addClass('has-success');
                  }
                  else
                  {
                	  $('#sign-in-password-input').addClass('has-error');
                	  $('#sign-in-password').val('');
                	  $('#sign-in-password').attr('placeholder', 'Incorrect password format')
                  }
            	  break;
            	  
              case 'registration-first-name':
            	  var pattern_name = /^[a-zA-Zа-яА-Я0-9]+$/;
            	  if(val.length > 2 && val.length < 30 && val != '' && pattern_name.test(val))
            	  {
            		  $('#registration-first-name-input').removeClass('has-error');
                	  $('#registration-first-name-input').addClass('has-success');
                  }
                  else
                  {
                	  $('#registration-first-name-input').addClass('has-error');
                	  $('#registration-first-name').val('');
                	  $('#registration-first-name').attr('placeholder', 'Incorrect name format')
                  }
            	  break;
            	  
              case 'registration-last-name':
            	  var pattern_name = /^[a-zA-Zа-яА-Я0-9]+$/;
            	  if(val.length > 2 && val.length < 30 && val != '' && pattern_name.test(val))
            	  {
            		  $('#registration-last-name-input').removeClass('has-error');
                	  $('#registration-last-name-input').addClass('has-success');
                  }
                  else
                  {
                	  $('#registration-last-name-input').addClass('has-error');
                	  $('#registration-last-name').val('');
                	  $('#registration-last-name').attr('placeholder', 'Incorrect last-name format')
                  }
            	  break;
            	  
              case 'registration-password':
            	  var pattern_password = /^[a-zA-Zа-яА-Я0-9]+$/;
            	  if(val.length > 6 && val.length < 30 && val != '' && pattern_password.test(val))
            	  {
            		  $('#registration-password-input').removeClass('has-error');
                	  $('#registration-password-input').addClass('has-success');
                  }
                  else
                  {
                	  $('#registration-password-input').addClass('has-error');
                	  $('#registration-password').val('');
                	  $('#registration-password').attr('placeholder', 'Incorrect password format')
                  }
            	  break;
            	  
              case 'registration-conf-password':
            	  var password = $('#registration-password').val();
            	  alert(password);
            	  if(val==password)
            	  {
            		  $('#registration-conf-password-input').removeClass('has-error');
                	  $('#registration-conf-password-input').addClass('has-success');
                  }
                  else
                  {
                	  $('#registration-conf-password-input').addClass('has-error');
                	  $('#registration-conf-password').val('');
                	  $('#registration-conf-password').attr('placeholder', 'Passwords do not match');
                  }
            	  break;
        }
	}
	
	init();
});