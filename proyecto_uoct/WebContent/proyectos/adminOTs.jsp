<!DOCTYPE html>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="proyecto_uoct.proyecto.VO.DetalleProyectoVO,java.util.List,java.util.Iterator" errorPage="" %>
<%@ page import="proyecto_uoct.usuario.VO.UsuarioVO" %>
<%@ page import="proyecto_uoct.proyecto.VO.DocumentodeListaProyVO,proyecto_uoct.proyecto.VO.OTdeListaVO"%>
<%@ page import="proyecto_uoct.proyecto.VO.NLOdeListaVO,proyecto_uoct.documentacion.VO.DocumentoInVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

DetalleProyectoVO detproy= (DetalleProyectoVO) request.getAttribute("detalleProy");


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
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>
<script type="text/javascript">

function confirmaEliminacion(){
  alert("Advertencia:La operación de eliminar una OT no se puede deshacer");
var resp=confirm("Al eliminar una OT también se eliminarán las NLO asociadas.\n ¿Está seguro de continuar? ");
return resp;
}

</script>
	
	
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						
						<h3>OTs de la Iniciativa : <%=detproy.getNomProy() %></h3>
						<div class="box boxpost">
				 			<h4>OTs de la Iniciativa</h4>
				 			 <%
 List ots=(List) detproy.getOTs();
 if(ots.size()!=0){
 request.setAttribute("otss",ots);
 %>
          <display:table id="o" name="otss" requestURI="proyectoAction.do" class="table table-striped table-bordered table-hover">

          <display:column title="Nombre de la OT" property="str" href="proyectoAction.do?hacia=detalleOT" paramId="idOT" paramProperty="id" maxLength="50">
          </display:column> <display:column title="Fecha Vencimiento" ><%= sdf.format(((OTdeListaVO)o).getFechaInicio()) %> </display:column> <display:column title="Estado" property="estadoOT">
          </display:column> <display:column title="Editar Fechas" href="proyectoAction.do?hacia=editarFechasOT" paramId="idOT" paramProperty="id">
          Editar Fechas </display:column> <display:column title="Editar OT"> <a href="proyectoAction.do?hacia=editarOT&idProy=<%=detproy.getIdProy()%>&idOT=<%=((OTdeListaVO)o).getId()%>" >Editar
          OT</a> </display:column> <display:column title="Editar NLOs de la OT">
          <a href="proyectoAction.do?hacia=adminNLOdelaOT&idOT=<%=((OTdeListaVO)o).getId()%>" >Editar
          NLO de OT</a> </display:column> <display:column title="Borrar OT"> <a href="proyectoAction.do?hacia=borrarOT&idOT=<%=((OTdeListaVO)o).getId()%>&idProy=<%=detproy.getIdProy()%>" onClick="return confirmaEliminacion();" >Borrar
          OT</a> </display:column>


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
          <%} %>
        					<div class="boxOpciones">
    							<div class="form-group">
    								<div class="col-sm-offset-2 col-sm-10">
    								
      								<a href="javascript:Llamadalink2('aRegOT','&idProy=<%=detproy.getIdProy()%>')" class="botoVerde">Registrar OT</a>
    								</div>
  								</div>
    						</div>
						</div>
				 			
<script>
	function Llamadalink2(hacia, link) {
												
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
