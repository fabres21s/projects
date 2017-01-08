$(window).load(function() 
{
	"use strict";
	$('#goto-menu a').click(function(e) {
        e.preventDefault();
		scrollTo($(this).attr('href'),-80);
    });
	
	
	$('#input-autocomplete').tagsInput({
		width: 'auto',

		//autocomplete_url:'test/fake_plaintext_endpoint.html' //jquery.autocomplete (not jquery ui)
		autocomplete_url:'templates/files/fake_json_endpoint.html', // jquery ui autocomplete requires a json endpoint
/*		autocomplete:{
		source: function(request, response) {
		  $.ajax({
			 url: "templates/files/fake_json_endpoint.html",
			 dataType: "json",
			 data: {
				postalcode_startsWith: request.term
			 },
			 success: function(data) {
				response( $.map( data.postalCodes, function( item ) {
								return {
									label: item.countryCode + "-" + item.placeName,
									value: item.postalCode
								}
							}));
			 }
		  })	
		}} */
	});
	

	var availableTags = [
	"ActionScript",
	"AppleScript",
	"Asp",
	"BASIC",
	"C",
	"C++",
	"Clojure",
	"COBOL",
	"ColdFusion",
	"Erlang",
	"Fortran",
	"Groovy",
	"Haskell",
	"Java",
	"JavaScript",
	"Lisp",
	"Perl",
	"PHP",
	"Python",
	"Ruby",
	"Scala",
	"Scheme"
	];
	
	$( "#normal-autocomplete" ).autocomplete({
		source: availableTags
	});
	


	 $.widget( "custom.catcomplete", $.ui.autocomplete, {
		_renderMenu: function( ul, items ) {
			var that = this,
			currentCategory = "";
			$.each( items, function( index, item ) {
				if ( item.category != currentCategory ) {
					ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
					currentCategory = item.category;
				}
					that._renderItemData( ul, item );
			});
		}
	});

	var data = [
	{ label: "anders", category: "" },
	{ label: "andreas", category: "" },
	{ label: "antal", category: "" },
	{ label: "annhhx10", category: "Products" },
	{ label: "annk K12", category: "Products" },
	{ label: "annttop C13", category: "Products" },
	{ label: "anders andersson", category: "People" },
	{ label: "andreas andersson", category: "People" },
	{ label: "andreas johnson", category: "People" }
	];
	$( "#category-autocomplete" ).catcomplete({
		delay: 0,
		source: data
	});

		
	var data_image = [
		{ label: "anders", desc:"Lorem ipsum doler sit amet.", icon: "img/avatar/avatar.jpg" },
		{ label: "andreas", desc:"Lorem ipsum doler sit amet.", icon: "img/avatar/avatar-2.jpg" },
		{ label: "antal", desc:"Lorem ipsum doler sit amet.", icon: "img/avatar/avatar-3.jpg" },
		{ label: "annhhx10", desc:"Lorem ipsum doler sit amet.", icon: "img/avatar/avatar-4.jpg" },
		{ label: "annk K12", desc:"Lorem ipsum doler sit amet.", icon: "img/avatar/avatar-5.jpg" },
		{ label: "annttop C13", desc:"Lorem ipsum doler sit amet.", icon: "img/avatar/avatar-6.jpg" },
		{ label: "anders andersson", desc:"Lorem ipsum doler sit amet.", icon: "img/avatar/avatar-7.jpg"},
		{ label: "andreas andersson", desc:"Lorem ipsum doler sit amet.", icon: "img/avatar/avatar-8.jpg" },
		{ label: "andreas johnson", desc:"Lorem ipsum doler sit amet.", icon: "img/avatar/avatar-9.jpg" }
	];	
	$( "#image-autocomplete" ).autocomplete({
		minLength: 0,
		source: data_image,
		focus: function( event, ui ) {
		$( "#image-autocomplete" ).val( ui.item.label );
			return false;
		}
	})
	.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
		return $( "<li>" )
		.append( "<a href='javascript:void(0)'><span class='menu-icon'><img src='" + item.icon + "' alt='"+ item.icon +"'></span><span class='menu-text'>" + item.label + "<span class='menu-info'>" + item.desc + "</span></span></a>" )
		.appendTo( ul );
	};


	/* Multiple Values */
	function split( val ) {
		return val.split( /,\s*/ );
	}
	function extractLast( term ) {
		return split( term ).pop();
	}
	$( "#multiple-autocomplete" )
	// don't navigate away from the field on tab when selecting an item
	.bind( "keydown", function( event ) {
		if ( event.keyCode === $.ui.keyCode.TAB &&
		$( this ).data( "ui-autocomplete" ).menu.active ) {
		event.preventDefault();
	}
	})
	.autocomplete({
		minLength: 0,
		source: function( request, response ) {
		// delegate back to autocomplete, but extract the last term
		response( $.ui.autocomplete.filter(
		availableTags, extractLast( request.term ) ) );
		},
		focus: function() {
		// prevent value inserted on focus
		return false;
		},
		select: function( event, ui ) {
		var terms = split( this.value );
		// remove the current input
		terms.pop();
		// add the selected item
		terms.push( ui.item.value );
		// add placeholder to get the comma-and-space at the end
		terms.push( "" );
		this.value = terms.join( ", " );
		return false;
		}
	});
	
	$( "#datepicker-normal" ).datepicker({ dateFormat: 'dd M yy'});
	$( "#datepicker-multiple" ).datepicker({
		numberOfMonths: 3,
		showButtonPanel: true,
		dateFormat: 'dd M yy'
	});	
	$( "#datepicker-from" ).datepicker({
		defaultDate: "+1w",
		dateFormat: 'dd M yy',
		changeMonth: true,
		numberOfMonths: 3,
		onClose: function( selectedDate ) {
		$( "#to" ).datepicker( "option", "minDate", selectedDate );
		}
	});
	$( "#datepicker-to" ).datepicker({
		defaultDate: "+1w",
		dateFormat: 'dd M yy',
		changeMonth: true,
		numberOfMonths: 3,
		onClose: function( selectedDate ) {
		$( "#from" ).datepicker( "option", "maxDate", selectedDate );
		}
	});	
	$( "#datepicker-icon" ).datepicker({ dateFormat: 'dd M yy'});
	$( '[data-datepicker]' ).click(function(e){ 
		var data=$(this).data('datepicker');
		$(data).focus();
	});
	$( "#datepicker-restrict" ).datepicker({ minDate: -20, maxDate: "+1M +10D" });	
	$( "#datepicker-widget" ).datepicker();	
	
	
	$('#datepicker-daterangepicker').daterangepicker(
		{
		  ranges: {
			 'Today': [moment(), moment()],
			 'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
			 'Last 7 Days': [moment().subtract('days', 6), moment()],
			 'Last 30 Days': [moment().subtract('days', 29), moment()],
			 'This Month': [moment().startOf('month'), moment().endOf('month')],
			 'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
		  },
		  startDate: moment().subtract('days', 29),
		  endDate: moment()
		},
		function(start, end) {
			$('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
		}
	);	
	
	$('#datepicker-datetime').daterangepicker({ timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A' });	

    $('#timepicker-default').timepicker();
	$('#timepicker-full').timepicker({
		minuteStep: 1,
		template: false,
		showSeconds: true,
		showMeridian: false,
	});		

	$('#colorpicker-hex').ColorPicker({
		color: '#ff00ff',
		onSubmit: function(hsb, hex, rgb, el) {
			$(el).val(hex);
			$(el).ColorPickerHide();
		},
		onBeforeShow: function () {
			$(this).ColorPickerSetColor(this.value);
		},
		onChange: function (hsb, hex, rgb) {
			$('#colorpicker-hex').val('#' + hex);
			$('#colorpicker-hex').siblings().css({'color' : '#' + hex});
		}			
	})
	.bind('keyup', function(){
		$(this).ColorPickerSetColor(this.value);
	}).siblings().click(function(e){ 
		$(this).siblings().click();
	});	
	

	
	
	$('#colorpicker-rgba').ColorPicker({
		color: '#ff00ff',
		onSubmit: function(hsb, hex, rgb, el) {
			$(el).val(hex);
			$(el).ColorPickerHide();
		},
		onBeforeShow: function () {
			$(this).ColorPickerSetColor(this.value);
		},
		onChange: function (hsb, hex, rgb) {
			$('#colorpicker-rgba').val('rgb(' + rgb['r'] +',' + rgb['g']+',' + rgb['b'] + ')');
			$('#colorpicker-rgba').siblings().css({'color' : 'rgb(' + rgb['r'] +',' + rgb['g']+',' + rgb['b'] + ')'});
		}			
	})
	.bind('keyup', function(){
		$(this).ColorPickerSetColor(this.value);
	}).siblings().click(function(e){ 
		$(this).siblings().click();
	});	

	//CKEDITOR.replace( $('[data-rel^="ckeditor"]') );
	$( '[data-rel^="ckeditor"]' ).ckeditor();



	
})