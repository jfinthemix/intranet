<!DOCTYPE html>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.ventas.VO.CliVtaVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List clientes=(List) request.getAttribute("clientes");
%>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="jfanasco" >
		<link rel="icon" href="img/favicon.ico">
		
		<title>Unidad Operativa de Control de Tránsito</title>
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">

					<div class="col-sm-6 desarrollo">
					
						<h2>Buscar Clientes</h2>
						
						<div class="box clearfix">
							<h4>Selecciona una de las formas:</h4>
				 			<div class="col-sm-6 searchtab tabMini">
				 				<form id="form1" name="form1" action="ventasAction.do" method="POST">
				 				       <input type="hidden" name="accion" value="buscarCli"/>
				 				        <input type="hidden" name="PorPalabra" value="Buscar">
  									<div class="form-group">
  										<label for="inputBusca1">
  											<span class="glyphicons glyphicons-search"></span><br>
  											Por palabra clave*<br>
  										</label>
    									<input type="text" class="form-control" id="palClave" name="palClave">
  									</div>
  									<div class="form-group">
    									<div class="col-sm-12">

    										
      										<a OnClick="submitThisForm1();" href="javascript:void(0)"
											class="botoVerde"> 
											Buscar
										</a>
										
										<script  type="text/javascript" >
		function submitThisForm1() {

			var formulario = $('#form1');
			
			var action = 'ventas/ventasAction.do'
			SubmitFormulario(action, formulario);
			

		}


	</script>
										
										
      								</div>
  									</div>

								</form>
				 			</div>
				 			<div class="col-sm-6 searchtab tabMini">
				 				<form id="exportForm" name="exportForm"action="ventasAction.do">
				 				                <input type="hidden" name="accion" value="exportarAgendaCli">
				 				                <input type="hidden" name="exportarAgenda" value="Exportar Agenda">
  									<div class="form-group">
  										<label for="inputExportar">
  											<span class="glyphicons glyphicons-file-export"></span><br>
  											Exportar agenda
  										</label>
    								</div>
  									<div class="form-group">
    									<div class="col-sm-12">

    										
      										<a OnClick="submitThisForm2();" href="javascript:void(0)"
											class="botoVerde"> 
											Exportar
										</a>
										
										<script  type="text/javascript" >
		function submitThisForm2() {

			var formulario = $('#exportForm');
			
			var action = 'ventas/ventasAction.do'
			SubmitFormulario(action, formulario);
			

		}


	</script>
										
										
      								</div>
  									</div>  									
  									
  									
  									
								</form>
				 			</div>
				 			<div class="col-sm-12">
				 				<p>*Nombre, dirección, giro, contactos o comentario.</p>
				 			</div>
				 		</div>
						
						<div class="box boxpost encuentra">
 <%if(clientes!=null){
%>
        <display:table name="Clientes" class="table table-striped table-bordered table-hover tablesorter" pagesize="20" requestURI="ventasAction.do" export="true" id="clis">
        <display:caption>Clientes Encontrados </display:caption> <display:column title="Nombre" media="html" property="nomCli" sortProperty="nomCli" sortable="true" href="ventasAction.do?accion=detalleCli" paramId="idCli" paramProperty="idCliente" >
        </display:column> <display:column title="Nombre" media="xml excel csv rtf" property="nomCli">
        </display:column> <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
        <display:setProperty name="basic.empty.showtable" value="true"/> <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/> <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
        <display:setProperty name="export.csv" value="false"/> <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.rtf" value="false"/> <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.filename" value="clientesVentas"/>
        <display:setProperty name="export.xml.filename" value="clientesVentas"/>
        <display:setProperty name="export.csv.filename" value="clientesVentas"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/> </display:table>
        <%}%>


						</div>
				 			
				 		<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
				 		
					
					</div>
			
				
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

      <div class="container">
			<footer>
				<div class="row">
					<div class="col-sm-12">
						<p>Unidad Operativa de Control de Tránsito. <span id="pie"></span></p>
					</div>
				</div>
        	</footer>
		</div> <!-- /container -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/jquery.tablesorter.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
