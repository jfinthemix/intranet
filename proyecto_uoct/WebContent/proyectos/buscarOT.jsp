<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%@ page import="proyecto_uoct.common.VO.IdStrVO,java.util.Iterator,java.text.SimpleDateFormat,java.util.List,java.util.Date,proyecto_uoct.proyecto.VO.DetalleOTVO,proyecto_uoct.proyecto.VO.DetalleProyectoVO" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

List ot=(List)request.getAttribute("ot");
request.setAttribute("ots",ot);

List est=(List)request.getAttribute("est");


%>

<!DOCTYPE html>
<html lang="en">
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
					
						<h2>Buscar OT</h2>
						
						<div class="box boxpost">
				 			<h4>Datos de la OT</h4>
				 			<form class="form-horizontal" action="proyectoAction.do" name="busOTform" id="busOTform"method="POST">
				 			 <input type="hidden" name="hacia" value="buscarOT" />
				 				<div class="form-group">
    								<label for="inputPalabra" class="col-sm-4 control-label">Palabra clave</label>
    								<div class="col-sm-8">
      								<input class="form-control" name="palClave" id="palClave">
    									<span id="helpBlock" class="help-block">Palabra en el nombre de la OT, nombre de la Iniciativa o en el estado de pago.</span>
    								</div>
    							</div>
				 				<div class="form-group">
    								<label for="selectEstado" class="col-sm-4 control-label">Estado</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="idEstado" name="idEstado">
      									
    										 <% if(est!=null){
        Iterator ie=est.iterator();
        while (ie.hasNext()){
          IdStrVO id=(IdStrVO)ie.next();
        %>
                  <option value="<%=id.getId()%>"><%=id.getStr() %></option>
                  <%}}%>
    									</select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputVto" class="col-sm-4 control-label">Fecha Vencimiento</label>
    								<div class="col-sm-8">
      								<input class="form-control inputFecha" id="fechaVenc" name="fechaVenc"  type="date" placeholder="DD-MM-YYYY"  maxlength="10" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])-(0[1-9]|1[012])-[0-9]{4}">
    								</div>
    							</div>
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    									<input type="hidden" name="buscar" id="buscar" value="Buscar">
    										<a href="javascript:void(0)" onclick="submitThisForm1();" class="botoVerde busca"><span class="glyphicons glyphicons-search"></span> Buscar</a>
      								</div>
  									</div>
    							</div>
  							</form>
						</div>
						
				
						
	 <%if(ot!=null){
    %>
        <display:table name="ots" class="table table-striped table-bordered table-hover tablesorter" pagesize="15" requestURI="proyectoAction.do" id="o">

         <display:column title="Orden de Trabajo" property="nomOT" sortable="true" href="javascript:Llamadalink('detalleOT','#')"  paramProperty="idOT" paramId="idOT">
        </display:column> <display:column title="Estado de la OT" property="estadoOT" sortable="true">
        </display:column> <display:column title="Fecha Vencimiento"> <%=sdf.format(((DetalleOTVO)o).getVencimiento()) %> </display:column> <display:column title="Proyecto" sortable="true">
        <a href="javascript:Llamadalink('detalleIni','&idProy=<%=((DetalleOTVO)o).getDetProy().getIdProy()%>')"><%=((DetalleOTVO)o).getDetProy().getNomProy()%></a> </display:column>

				 <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
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
        <display:setProperty name="export.amount" value="list"/>

		 </display:table>
        <%}%>					
						
	<script>
	function Llamadalink(hacia, link) {
												
		link = link.replace('#', '');
		link = link.replace('?', '&');
		link = 'proyectos/proyectoAction.do'+ '?hacia=' + hacia + link ;
		
		LlamadaPagina(link);

												//clienteAction.do?hacia=detalleEntExt
	}
	
	</script>			 			
				 		
				 		
					
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

<script type="text/javascript">
											function submitThisForm1() {
												
												var formulario = $('#busOTform');

												var action = 'proyectos/proyectoAction.do'
												SubmitFormulario(action,
														formulario);

											}
										</script>

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