<!DOCTYPE html>
<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%@ page import="proyecto_uoct.reservas.VO.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>


<%
List recursos = (List) request.getAttribute("recursos");
List reservas = (List) request.getAttribute("RESERVAS");

SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
%>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="Unidad Operativa de Control de Tránsito">
		<link rel="icon" href="img/favicon.ico">
		
		<title>Unidad Operativa de Control de Tránsito</title>
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.css" rel="stylesheet">
		<link href="css/fullcalendar.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>
	 <script type="text/javascript">



  var reservar = null;
  function popUp(href, target, features) {
    reservar = window.open(href, target, features);
    reservar.window.focus();
  }

	function submitThisForm1() {
		form1.accion.value = "GENERAR_REPORTE";
		
		var formulario = $('#form1');

		var action = 'recursos/recursosAction.do';
		SubmitFormulario(action,
				formulario);

	}
  

  </script>
  
  
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Reporte de reservas</h2>
						
						<div class="box boxpost">
				 			<h4>Buscar reservas</h4>
				 			<form class="form-horizontal" action="recursosAction.do" method="POST" name="form1" id="form1" >
<input type="hidden" name="idReserva"/>

        <input type="hidden" name="accion" value="GENERAR_REPORTE"/>

	
        <input type="hidden" name="buscar" id="buscar" value="Buscar" />		 			
				 			
				 			
				 				<div class="form-group input-daterange">
    								<div class="col-sm-2">
    									<label class="control-label">Entre el:</label>
    								</div>
    								<div class="col-sm-4">
      								<input type="text" class="form-control inputFecha" name="fechaDesde" id="fechaDesde">
    								</div>
    								<div class="col-sm-2">
      								<label class="control-label">Hasta el:</label>
    								</div>
    								<div class="col-sm-4">
    									<input type="text" class="form-control inputFecha" name="fechaHasta" id="fechaHasta">
    								</div>
  								</div>
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">

    										<a href="javascript:void(0)" onClick="javascript:submitThisForm1();" class="botoVerde busca"><span class="glyphicons glyphicons-search"></span> Buscar</a>
      								</div>
  									</div>
    							</div>
  							</form>
						</div>
						

 <%
    if(reservas != null){
    	Iterator iRec= reservas.iterator();
    	int cont=0;
    	String nomRec= null;
   			while (iRec.hasNext()){
   				List resXrec=(List)iRec.next();
   				cont++;
   				Iterator iRes= resXrec.iterator();
   					if(iRes.hasNext()){   //obtenemos el nombre del recurso
   						ReservaVO res_tmp=(ReservaVO)iRes.next();
   						Iterator ir=recursos.iterator();

   						while(ir.hasNext()){
     						RecursoVO rec_tmp=(RecursoVO) ir.next();
     							if(res_tmp.getIdRecurso().intValue()== rec_tmp.getIdRecurso().intValue()){
       								nomRec=rec_tmp.getNombre();
     							}
   						}
 					}
 					String nom_lista="reservas"+Integer.toString(cont);
 					request.setAttribute(nom_lista,iRes);

 %>						
						
						
						
				 		<div class="box">
				 			<h4>Reservas de <%=nomRec %> <span class="pull-right"><small>Exportar:</small> <a href="javascript:void(0)" class="pull-right"><img src="img/ico_excel.png"></a> <a href="javascript:void(0)" class="pull-right"><img src="img/ico_pdf.png"></a></span></h4>
				 			<display:table name="<%=nom_lista %>" id="res" requestURI="recursosAction.do" class="table table-striped table-bordered table-hover" export="true">
     <display:column title="Fecha"> <%= fecha.format(((ReservaVO)res).getFechaReserva())%> </display:column>

     <display:column title="Hora" class="hora"> <%= hora.format(((ReservaVO)res).getHoraDeInicio()) + " - " + hora.format(((ReservaVO)res).getHoraDeFin())%> </display:column>

     <display:column title="Solicitante"><%= ((ReservaVO)res).getNombreUsuario()%> </display:column>

     <display:column title="Motivo/Destino">
       <%= ((ReservaVO)res).getMotivo()%>
     </display:column>

     <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
     <display:setProperty name="paging.banner.placement" value="bottom"/>
     <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
     <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
     <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
     <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
     <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a hreAf=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>

         <display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
           <display:setProperty name="export.csv" value="false"/> <display:setProperty name="export.xml" value="false"/>
           <display:setProperty name="export.rtf" value="false"/> <display:setProperty name="export.excel.filename" value="ReporteDeReservas.xls"/>
           <display:setProperty name="export.pdf.filename" value="ReporteDeReservas.pfd"/>
           <display:setProperty name="export.xml.filename" value="ReporteDeReservas.xml"/>
           <display:setProperty name="export.csv.filename" value="ReporteDeReservas.csv"/>
           <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' border='0' height='10'>"/>
             <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' border='0' height='10'>" />
               <display:setProperty name="export.amount" value="list"/>

             </display:table>


				 		</div>
				 		
				 		
			         <%}
   						}%>	 		
				 		
				 		
				 			
				 		
				 		
					
					</div>
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

      
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
	 <script src="js/uoct.js"></script>
  </body>
</html>
