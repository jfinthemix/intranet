<%@page import="java.util.List,java.util.Iterator" %>
<%@page import="proyecto_uoct.usuario.VO.AnexoVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
List anexos=(List)request.getAttribute("anexos");
List anexos_usu=(List)request.getAttribute("anexos_usu");
if(anexos_usu!=null){
  Iterator iau=anexos_usu.iterator();
  while(iau.hasNext()){
    anexos.add(iau.next());
  }
}
%>
<html>
<head>
<title>Cuadro de Anexos</title>

</head>
<body >

  <%if (anexos!=null){
  Iterator i=anexos.iterator();
  request.setAttribute("anexoses",i);
  %>

 <h2>Anexos telefónicos</h2>
<div class="box">
<h4>Listado de anexos</h4>
  <display:table requestURI="usuarioAction.do" name="anexoses" id="anexs" class="table table-striped table-bordered table-hover tablesorter">
   <display:column title="Nombre" sortable="true"><%=((AnexoVO)anexs).getNomAnexo() %> </display:column> <display:column title="Anexo">
  <%if (((AnexoVO)anexs).getAnexo()!=null){%>
  <%=((AnexoVO)anexs).getAnexo() %>
  <%}else{ %>
  <%="--" %>
  <%}%>
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
</body>
</html>
