<%@page import="java.util.Date,java.util.List,java.util.Iterator,java.text.SimpleDateFormat,proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.common.util.UtilString"%>
<%@taglib prefix="display" uri="/displaytag_12"%>


<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
List usuarios= (List) request.getAttribute("usuarios");
String cumple="";
UtilString us=new UtilString();

%>
<html>
<head>
<title>cumpleaños</title>

</head>
<body >

  <% if(usuarios!=null){
Iterator iusus=usuarios.iterator();
request.setAttribute("ususes",iusus);
%>
<h2>Cumpleaños UOCTinos</h2>

<div class="box">
<h4>Listado de personas</h4>
  <display:table requestURI="index2Action.do" name="ususes" id="usus" class="table table-striped table-bordered table-hover tablesorter">

  <display:column title="Nombre" sortable="true" sortProperty="nombreUsu">
  <% if(((UsuarioVO)usus).getNombreUsu2()!=null){ %>
  <%= ((UsuarioVO)usus).getNombreUsu()+" "+((UsuarioVO)usus).getNombreUsu2()+" "+((UsuarioVO)usus).getApellUsu()%>
  <%}else{%>
  <%= ((UsuarioVO)usus).getNombreUsu()+" "+((UsuarioVO)usus).getApellUsu()%>
  <% } %>
  </display:column>

  <display:column title="Cumpleaños" sortProperty="cumpleanos" sortable="true">
  <% if(((UsuarioVO)usus).getCumpleanos()!=null){
      cumple=us.formatoFecha(sdf.format(((UsuarioVO)usus).getCumpleanos()));

    %>
  <%=cumple %>
  <%}else{%>
  <%="&nbsp;"%>
  <%}%>
  </display:column>

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
  <display:setProperty name="export.pdf.label" value="<img src='util/img/pdf.gif' width='10' height='10'>"/>
  <display:setProperty name="export.excel.label" value="<img src='util/img/excel.gif' width='10' height='10'>"/>
  <display:setProperty name="export.amount" value="list"/>

</display:table>
  <% }%>

</div>
<div class="verMas">
							<a href="javascript:volver()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
						<script>
						function volver()
						{
							var previa=$('#hidPreviousPage').val();
							LlamadaPagina(previa);
							
						}
						</script>

</body>
</html>
