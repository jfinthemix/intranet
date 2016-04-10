<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,proyecto_uoct.mantenedorSubsistemas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje          = (String) request.getAttribute("mensaje");

LinkedList listaSubsistema                   = new LinkedList();
listaSubsistema                              = (LinkedList) request.getAttribute("listaSubsistema");
Integer idPerfil                              = (Integer) request.getAttribute("idPerfil");
Integer cero                                  = new Integer(0);
%>


<!DOCTYPE html>
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
		
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>		
		<script language="JavaScript" type="text/javascript">
function valida_envia(){
  document.formMantenedorSubsistema.submit();
}
function confirmaEliminacion(){
  var resp=confirm("Al eliminar este subsistema se eliminará todo lo asociado a él.\n ¿Desea continuar con la eliminación?");
  return resp;
}
</script>
	</head>
	
	<body onload="valida_cambioInicial();">
		<div class="preheader headerlog">
			<div class="container">
				<div class="row">
					<div class="col-sm-3 col-xs-3">
						<a href="index_logeado.html">
							<h1>UOCT | <span>Intranet</span></h1>
						</a>
					</div>
					
				</div>	
			</div>
		</div>
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Mantenedor de Subsistemas</h2>
						
       <%
        if (mensaje != null){
          out.print("<br>"+mensaje+"<br>");
        }
        %>						
						
						<div class="box boxpost">
				 			<h4>Subsistemas <span class="pull-right"><small>Exportar:</small> <a href="javascript:void(0)" class="pull-right"><img src="img/ico_excel.png"></a> <a href="javascript:void(0)" class="pull-right"><img src="img/ico_pdf.png"></a></span></h4>
				 			<table class="table table-striped table-bordered table-hover">
      						          <display:table  name="listaSubsistema" export="true" class="table table-striped table-bordered table-hover" id="ls" pagesize="20" requestURI="subsistemaAction.do">
          <display:column media="html excel pdf" class="centrado" title="nombre"  sortable="true" sortProperty="nombre"><%=((SubsistemaVO)ls).getNombreSubsistema() %></display:column>
          <display:column media="html excel pdf" class="centrado" title="sistema asociado"  sortable="true" sortProperty="nombre"><%=((SubsistemaVO)ls).getNombreSistema() %></display:column>
          <display:column media="html"           class="centrado" title="modificar "          sortable="false" sortProperty="modificar">
            <a href="subsistemaAction.do?hacia=modificar&&id_subsistema=<%out.print(((SubsistemaVO)ls).getIdSubsistema());%>&&nombre=<%out.print(((SubsistemaVO)ls).getNombreSubsistema());%>&&id_sistema=<%out.print(((SubsistemaVO)ls).getIdSistema());%>&&nombre_sistema=<%out.print(((SubsistemaVO)ls).getNombreSistema());%>" title="Modificar subsistema"><img src="imagenes/icono_detalle.png" width="17" height="17" alt="" border="0"></a>
          </display:column>

          <% if(idPerfil.equals(cero)){}
         else{ %>
          <display:column media="html"           class="centrado" title="eliminar "           sortable="false" sortProperty="eliminar">
            <a href="subsistemaAction.do?hacia=eliminar&&id_subsistema=<%out.println(((SubsistemaVO)ls).getIdSubsistema());%>" title="Eliminar subsistema" onClick='return confirmaEliminacion()'><img src="imagenes/icono_delete.png" alt="" width="17" height="17" border="0"></a>
          </display:column>
          <% }%>


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
        <display:setProperty name="export.excel.filename" value="ListaSubsistema.xls"/>
        <display:setProperty name="export.pdf.filename" value="ListaSubsistema.pdf"/>
        <display:setProperty name="export.xml.filename" value="ListaSubsistema.xml"/>
        <display:setProperty name="export.csv.filename" value="ListaSubsistema.csv"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10' border='0'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10' border='0'>"/>
        <display:setProperty name="export.amount" value="list"/>
        </display:table>

				 			
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
						<p>Unidad Operativa de Control de Tránsito, <span id="pie"></span></p>
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
    <script src="js/uoct_falla1.js"></script>
  </body>
</html>
