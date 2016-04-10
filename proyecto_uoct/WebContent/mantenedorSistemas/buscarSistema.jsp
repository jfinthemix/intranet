<!DOCTYPE html>

<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,proyecto_uoct.mantenedorSistemas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje          = (String) request.getAttribute("mensaje");
//inicializo lista_sistema
LinkedList listaSistema                      = new LinkedList();
listaSistema                                 = (LinkedList) request.getAttribute("listaSistema");
Integer idPerfil                              = (Integer) request.getAttribute("idPerfil");
Integer cero                                  = new Integer(0);
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


<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<script language="JavaScript" type="text/javascript">
function valida_envia(){
  document.formMantenedorSistema.submit();
}
function confirmaEliminacion(){
  var resp=confirm("Al eliminar este sistema se eliminará todo lo asociado a él.\n ¿Desea continuar con la eliminación?");
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
					
						<h2>Mantenedor de Sistemas</h2>
						
						 <%
        if (mensaje != null){
          out.print("<br>"+mensaje+"<br>");
        }
        %>
						
    <display:table  name="listaSistema" export="true" class="table table-striped table-bordered table-hover" id="ls" pagesize="20" requestURI="sistemaAction.do">
          <display:column media="html excel pdf" class="centrado" title="nombre del sistema"  sortable="false" sortProperty="nombre"><%=((SistemaVO)ls).getNombre() %></display:column>
          <display:column media="html"           class="centrado" title="modificar "          sortable="false" sortProperty="modificar">
            <a href="sistemaAction.do?hacia=modificar&&id_sistema=<%out.print(((SistemaVO)ls).getIdSistema());%>&&nombre=<%out.print(((SistemaVO)ls).getNombre());%>" title="Modificar sistema"><img src="imagenes/icono_detalle.png" width="17" height="17" alt="" border="0"></a>
          </display:column>

          <% if(idPerfil.equals(cero)){
           idPerfil=idPerfil;
         }
          else{%>
          <display:column media="html"           class="centrado" title="eliminar "           sortable="false" sortProperty="eliminar">
            <a href="sistemaAction.do?hacia=eliminar&&id_sistema=<%out.println(((SistemaVO)ls).getIdSistema());%>" title="Eliminar sistema" onClick='return confirmaEliminacion()'><img src="imagenes/icono_delete.png" alt="" width="17" height="17" border="0"></a>
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
        <display:setProperty name="export.excel.filename" value="ListaSistema.xls"/>
        <display:setProperty name="export.pdf.filename" value="ListaSistema.pdf"/>
        <display:setProperty name="export.xml.filename" value="ListaSistema.xml"/>
        <display:setProperty name="export.csv.filename" value="ListaSistema.csv"/>
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
    <script src="js/uoct_falla1.js"></script>
  </body>
</html>
