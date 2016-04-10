/*  
	========================================
	Scripts para Intranet UOCT
	Autor	: @marsonauta
	========================================
*/
$(document).ready(function(){
	
	// Fecha actual
	var hoy = moment().locale('es').format('dddd D [de] MMMM [de] YYYY');
	var muestraHoy = document.getElementById('hoy'); 
	muestraHoy.innerHTML = hoy;
	
	// Fecha footer
	var pie = moment().locale('es').format('YYYY');
	var muestraPie = document.getElementById('pie'); 
	muestraPie.innerHTML = pie;
	
	// Truncate de previsualizacion de Noticias
	$('.resNoticia').truncate({
    	'maxLines': 11,
    	'truncateString': '&nbsp;&#8230;',
   });
   
   // Datepicker
   $('.inputFecha').datepicker({
   	format: "dd/mm/yyyy",
   	language: "es"
	});
	$('.input-daterange').datepicker({
   	todayBtn: "linked",
   	format: "dd/mm/yyyy",
   	language: "es"
	});
	
	// Timepicker http://timepicker.co/
	if( $('.inputHora').length ){
	 	$('.inputHora').timepicker({
  			timeFormat: "HH:mm"
		});
	}
	
	// Scroll Ayuda
	
	$('body').scrollspy({ target: '#contenidos' });
	
	$("#contenidos a").on('click', function(event) {
		event.preventDefault();
		var hash = this.hash;

    	$('html, body').animate({
      	scrollTop: $(hash).offset().top
    	}, 800, function(){
   
      	window.location.hash = hash;
    	});
  	});
	
	// Simula resultados de busqueda
	$(".busca").click(function(){
    	$(".encuentra").fadeIn( "slow" );
	});
	
	// Tabla agregar/quitar
	$('#botoAdd').click(function() { 
		return !$('#grupoA option:selected').remove().appendTo('#grupoB');
  	});  
 	$('#botoRemove').click(function() {  
  		return !$('#grupoB option:selected').remove().appendTo('#grupoA');  
 	});
 	
 	// Input agregar/quitar
 	var cualInput = $( "#inputRedes" );
 	$('#botoAgregar').click(function() {
 		$('#selectB').append($('<option>', {
 			value: cualInput.val(),
 			text: cualInput.val()
 		}));
 	});
 	$('#botoBorrar').click(function() {
 		$("#selectB option:selected").remove();
 	});
 	
 	// Input agregar/quitar encargados
 	
 	$('.addDoble').click(function() {
 		$('#selectEncargado').empty();
    	var $options = $("#grupoB > option").clone();
    	$('#selectEncargado').append($options);
 	});
 	
 	$('.quitaDoble').click(function() {
 		$('#selectEncargado').empty();
    	var $options = $("#grupoB > option").clone();
    	$('#selectEncargado').append($options);
 	});
	   	
 	// Checkbox para upload
 	$('.checkUp').click(function () {
 		if ($(this).is(':checked')) {
 			$(this).closest('label').find('.fileUp').removeAttr('disabled');
 		} else {
 			$(this).closest('label').find('.fileUp').attr('disabled', true);
 		}
 	});
 	
 	// Busqueda por Contenido o Sentido
	$(".buscaCod").click(function(){
    	$(".boxCod").fadeIn( "slow" );
    	$(".boxSen").hide();
	});
	$(".buscaSen").click(function(){
    	$(".boxSen").fadeIn( "slow" );
    	$(".boxCod").hide();
	});
	
	// Calendario con reservas, documentacion: http://fullcalendar.io/docs/
	
	if( $('#calendarioDSDB').length ){
		$("#calendarioDSDB").fullCalendar({
				defaultDate: '2015-02-12',
				defaultView: 'agendaDay',
				scrollTime: '09:00:00',
				minTime: '09:00:00',
    			maxTime: '23:00:00',
				height: 'auto',
				monthNames:['Enero', 'Febrero', 'Marzo', 'Abril',  'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
				editable: false,
				axisFormat: 'HH:mm',
				eventRender: function(event, element) {
					$(element).tooltip({title: event.title});             
				},
				events: [
					{
						title: 'Christian Enrique Olivos Muñoz - adm',
						start: '2015-02-12T10:00:00',
						end: '2015-02-12T11:00:00'
					},
					{
						title: 'Alejandra Fuentes Moya - JEP-DTPM',
						start: '2015-02-12T15:00:00',
						end: '2015-02-12T17:00:00'
					}
				],
				titleFormat: {
					day: 'DD [de] MMMM [de] YYYY'
				}, 
				timeFormat: 'HH:mm'
		});
	}
	if( $('#calendarioDVTS').length ){
		$("#calendarioDVTS").fullCalendar({
				defaultDate: '2015-02-12',
				defaultView: 'agendaDay',
				scrollTime: '09:00:00',
				minTime: '09:00:00',
    			maxTime: '23:00:00',
				height: 'auto',
				monthNames:['Enero', 'Febrero', 'Marzo', 'Abril',  'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
				axisFormat: 'HH:mm',
				editable: false,
				eventRender: function(event, element) {
					$(element).tooltip({title: event.title});             
				},
				events: [
					{
						title: 'Christian Enrique Olivos Muñoz - adm',
						start: '2015-02-12T11:00:00',
						end: '2015-02-12T12:00:00'
					},
					{
						title: 'Alejandra Fuentes Moya - JEP-DTPM',
						start: '2015-02-12T14:00:00',
						end: '2015-02-12T18:00:00'
					}
				],
				titleFormat: {
					day: 'DD [de] MMMM [de] YYYY'
				},
				timeFormat: 'HH:mm'
		});
	}
	if( $('#calendarioSala1').length ){
		$("#calendarioSala1").fullCalendar({
				defaultDate: '2015-02-12',
				defaultView: 'agendaWeek',
				scrollTime: '09:00:00',
				minTime: '09:00:00',
    			maxTime: '23:00:00',
				height: 'auto',
				axisFormat: 'HH:mm',
				hiddenDays: [ 0, 6 ],
				columnFormat: 'dddd DD/MM/YYYY',
				dayNames:['Domingo', 'Lunes', 'Martes', 'Miércoles',  'Jueves', 'Viernes', 'Sábado'],
				editable: false,
				eventRender: function(event, element) {
					$(element).tooltip({title: event.title});             
				},
				events: [
					{
						title: 'Christian Enrique Olivos Muñoz - adm',
						start: '2015-02-12T11:00:00',
						end: '2015-02-12T12:00:00'
					},
					{
						title: 'Alejandra Fuentes Moya - JEP-DTPM',
						start: '2015-02-12T14:00:00',
						end: '2015-02-12T18:00:00'
					}
				],
				timeFormat: 'HH:mm'
		});
	}
	if( $('#calendarioSala1').length ){
		$("#calendarioSala2").fullCalendar({
				defaultDate: '2015-02-12',
				defaultView: 'agendaWeek',
				scrollTime: '09:00:00',
				minTime: '09:00:00',
    			maxTime: '23:00:00',
				height: 'auto',
				axisFormat: 'HH:mm',
				hiddenDays: [ 0, 6 ],
				columnFormat: 'dddd DD/MM/YYYY',
				dayNames:['Domingo', 'Lunes', 'Martes', 'Miércoles',  'Jueves', 'Viernes', 'Sábado'],
				editable: false,
				eventRender: function(event, element) {
					$(element).tooltip({title: event.title});             
				},
				events: [
					{
						title: 'Christian Enrique Olivos Muñoz - adm',
						start: '2015-02-12T11:00:00',
						end: '2015-02-12T12:00:00'
					},
					{
						title: 'Alejandra Fuentes Moya - JEP-DTPM',
						start: '2015-02-12T14:00:00',
						end: '2015-02-12T18:00:00'
					}
				],
				timeFormat: 'HH:mm'
		});
	}

	
	
	// Detalles ventas
	var vA = $("#inputVCantidad");
	var vB = $("#selectVTipo option:selected");
	var vC = $("#txtVDetalle"); 
	$('#listAdd').click(function() {
		$('#listaDetalles').append($('<option>', {
 			value: vA.val() + ' ' + vB.text() + ' - ' + vC.val(),
 			text: vA.val() + ' ' + vB.text() + ' - ' + vC.val()
 		}));
 	});
 	
 	$('#listRemove').click(function() {
 		$("#listaDetalles option:selected").remove();
 	});
 	
});

(function($) {
   // Sorter tabla
 	$("table").tablesorter(); 
})(jQuery);

	// Titulo asunto correo falla
	
	function cambiarTitulo(){
  		document.form_nueva_falla.titulo.value = "Falla " + document.form_nueva_falla.dispositivo.options[document.form_nueva_falla.dispositivo.selectedIndex].text;
  		document.form_nueva_falla.nomDispositivo.value = document.form_nueva_falla.dispositivo.options[document.form_nueva_falla.dispositivo.selectedIndex].text;
	}