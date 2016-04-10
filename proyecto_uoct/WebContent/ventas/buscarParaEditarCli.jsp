<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.ventas.VO.CliVtaVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List clientes=(List) request.getAttribute("clientes");
%>


<!DOCTYPE html>

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
					
						<h2>Buscar Clientes para editar</h2>
						
						<div class="box clearfix">
				 			<div class="col-sm-12 searchtab tabMini mTop10">
				 				<form action="ventasAction.do" method="POST" id="form1" name="form1">
				 				   <input type="hidden" id="accion" name="accion" value="buscarparaEditarCli"/>
				 				 <input type="hidden" name="PorPalabra" value="Buscar">
  									<div class="form-group">
  										<label for="inputBusca1">
  											<span class="glyphicons glyphicons-search"></span><br>
  											Por palabra clave*<br>
  										</label>
    									<input type="text" class="form-control" id="palClave" name="palClave">
  									</div>


    										
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
										
										

								</form>
				 			</div>
				 			<div class="col-sm-12">
				 				<p>*Nombre, dirección, giro, contactos o comentario.</p>
				 			</div>
						</div>
						
						<div class="box boxpost encuentra">
				 		
				 			
				 			
				 			<%if(clientes!=null){
%>	<h4>Clientes encontrados</h4>
        <display:table name="clientes" class="able table-striped table-bordered table-hover tablesorter" pagesize="15" requestURI="ventasAction.do" id="clis"> 
        <display:caption>Clientes Encontrados </display:caption> <display:column title="Nombre" media="html" property="nomCli" sortProperty="nomCli" sortable="true" href="ventasAction.do?accion=editarCli" paramId="idCli" paramProperty="idCliente" > 
        </display:column> <display:column title="Estado" media="html" sortable="true" > 
        <%if(((CliVtaVO)clis).getIsActivo()){ out.print("Activado");}else{out.print("Desactivado");} %>
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
        <display:setProperty name="export.pdf.filename" value="registroDocumentacion"/> 
        <display:setProperty name="export.xml.filename" value="registroDocumentacion"/> 
        <display:setProperty name="export.csv.filename" value="registroDocumentacion"/> 
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
		
		</div> <!-- /main -->
	

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
