<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List, java.util.Iterator,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.documentacion.VO.CliEntVO" errorPage=""%>
<%@taglib prefix="display" uri="/displaytag_12"%>


<%
  List clientes = (List) request.getAttribute("listaCliEnt");
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
		
		<link href="../recursos/css/grid.css" rel="stylesheet">
		<link href="../recursos/css/glyphs.css" rel="stylesheet">
		<link href="../recursos/css/style.css" rel="stylesheet">
		
		
		<!--[if lt IE 9]>		
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<script type="text/javascript">
function pasaNomId(nom,id){
	window.opener.pasaCli(nom,id);
}

</script>
	</head>
		<body>
		<div class="main" id="modalBuscaCliente" tabindex="-1" role="dialog" aria-labelledby="modalBuscaCliente">
  			<div class="modal-dialog">
    			<div class="modal-content">
      			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title">Buscar Cliente</h4>
      			</div>
      			<div class="modal-body clearfix">
      				<div class="col-sm-12 desarrollo">
        					
        					<div class="box clearfix">
								<h4>Selecciona un tipo de búsqueda:</h4>
				 				<div class="col-md-6 searchtab tabMini">
				 					<form id="form1" name="form1" action="clienteAction.do" method="POST">
				 					    <input type="hidden" name="hacia" value="busCli_pop"/>
  										<div class="form-group">
  											<label for="inputBusca1">
  												<span class="glyphicons glyphicons-search"></span><br />
  												Por palabra clave<br />
  											</label>
    										<input type="text" class="form-control" id="palClave" name="palClave">
    										
  										</div>
<input type="submit" name="PorPalabra" value="Buscar" class="botoVerde">
						
  										
									</form>
				 				</div>
				 				<div class="col-md-6 searchtab tabMini">
				 					<form action="clienteAction.do"id="form2" name="form2">
				 					 	<input type="hidden" name="hacia" value="busCli_pop">
                    				<input type="hidden" name="Particulares" value="Buscar"/>
  										<div class="form-group">
  											<label>
  												<span class="glyphicons glyphicons-eye-open"></span><br />
  												Clientes particulares
  											</label>
    									</div>
  										<input type="submit" class="botoVerde" name="Particulares" value="Buscar"/>
						
  										
									</form>
				 				</div>
				 			</div>
						
				 			<div class="box clearfix">

<%if(clientes!=null){
  Iterator icl=clientes.iterator();
  request.setAttribute("icl",icl);
  %>
        <h4>Lista de Clientes </h4>
		<display:table name="icl" pagesize="35" requestURI="clienteAction.do" id="icls" class="table table-striped table-bordered table-hover tablesorter" >
        <display:column title="Seleccionar"><a href="#" onClick="return pasaNomId('<%=((CliEntVO)icls).getNomCli()+" "+((CliEntVO)icls).getApeCli()%>','<%=((CliEntVO)icls).getIdCli()%>')">Seleccionar</a>
        </display:column> <display:column sortable="true" title="Cliente"> <%=((CliEntVO)icls).getNomCli()+" "+((CliEntVO)icls).getApeCli() %> </display:column> <display:column sortable="true" title="Entidad externa">
        <%if(((CliEntVO)icls).getNomEnt()!=null){ %>
        <%=((CliEntVO)icls).getNomEnt()%>
        <%}else{ %>
        <%="&nbsp;"%>
        <%} %>
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
        <%} %>				 			
				 			
  							</div>
						
				 		
				 		</div>
      			</div>
      			<div class="modal-footer">
        				<button type="button" class="botoRojo" data-dismiss="modal"><span class="glyphicons glyphicons-circle-remove"></span> Cerrar</button>
        			</div>
    			</div><!-- /.modal-content -->
  			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="../recursos/js/bootstrap.min.js"></script>
    <script src="../recursos/js/bootstrap-datepicker.min.js"></script>
    <script src="../recursos/js/bootstrap-datepicker.es.min.js"></script>
    <script src="../recursos/js/moment.js"></script>
    <script src="../recursos/js/truncate.js"></script>
    <script src="../recursos/js/fullcalendar.min.js"></script>
    <script src="../recursos/js/uoct.js"></script>
  </body>
</html>