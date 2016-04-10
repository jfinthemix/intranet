<!DOCTYPE html>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.reservas.VO.RecursoVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%

List recursos=(List)request.getAttribute("RECURSOS");
String mensaje=(String) request.getAttribute("mensaje");
Iterator ir=recursos.iterator();
request.setAttribute("ir",ir);
String estado="";
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
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>

	<script>
	function Llamadalink(hacia, link) {
												
		link = link.replace('#', '');

		link = 'recursos/recursosAction.do'+ hacia + link ;
		
		LlamadaPagina(link);

												//clienteAction.do?hacia=detalleEntExt
	}
	
	function submitThisForm1() {
												
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
					
						<h2>Administrar recursos</h2>
						
				 		<div class="box boxpost">
				 			<h4>Ingresar recurso:</h4>
				 			  <%if(mensaje!=null){out.print(mensaje);} %>
				 			<form class="form-horizontal" action="recursosAction.do" name="form1" id="form1" method="POST">
				 			        <input type="hidden" name="accion" value="REGISTRAR_RECURSO" />

				 				<div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" name="nombre" id="nombre">
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputDescripcion" class="col-sm-4 control-label">Descripción</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="desc" name="desc">
    								</div>
    							</div>
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    									<input type="hidden" id="Submit" name="Submit" value="Ingresar">
    										<a href="javascript:void(0)" onclick="javascript:submitThisForm1();" class="botoVerde">Ingresar</a>
      								</div>
  									</div>
    							</div>
  							</form>
						</div>
						
						<div class="box boxpost">
						
			 <display:table name="ir" class="able table-striped table-bordered table-hover tablesorter" requestURI="recursosAction.do" id="idRecList" >

      <display:column title="Nombre Recurso"> <%=((RecursoVO)idRecList).getNombre() %>
      </display:column>
	   <display:column title="Descripción" class="texto"> <%=((RecursoVO)idRecList).getDescripcion() %>
      </display:column>
	  <display:column title="Estado">
      <%if(((RecursoVO)idRecList).getIsActivo()){
   estado="Activo";}
   else{estado="Inactivo";}
    %>
      <%=estado %> </display:column>
	  <display:column title="Editar" href="javascript:Llamadalink('?accion=EDITAR_RECURSO','#')" paramId="idRecurso" paramProperty="idRecurso">
      <%="Editar" %> </display:column>


	  	<display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
        <display:setProperty name="basic.empty.showtable" value="true"/>
		<display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/>
		<display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
		<display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
		<display:setProperty name="export.csv" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.xml.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.csv.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/>


	  </display:table>
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
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
