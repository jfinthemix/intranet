<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@page import="java.util.List, java.util.Iterator,proyecto_uoct.infoyrep.VO.ArchivoInfoVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List lista = (List) request.getAttribute("lista");
%>
<html>
<head>
<title>Informaci&oacute;n institucional</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="574" border="0">
  <tr>
    <td> <div align="left">
        <%if (lista!=null){
  Iterator lis=lista.iterator();
  request.setAttribute("lista",lis);%>
  <h4>Archivos de Informaci&oacute;n Institucional</h4>
        <display:table id="archivos" name="lista" class="its" requestURI="index2Action.do">

        <display:column title="Nombre"> <%=((ArchivoInfoVO)archivos).getNomArchivo() %> </display:column>
        <display:column title="Descripción"> <%=((ArchivoInfoVO)archivos).getDescripcion() %> </display:column>
        <display:column title="Descargar" href="info_instit_y_report/infoinstitAction.do?hacia=descargarArchivo" paramId="idFile" paramProperty="idFile" >
        Descargar </display:column>
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
        <display:setProperty name="export.amount" value="list"/> </display:table>
        <%} %>
      </div></td>
  </tr>
</table>
<hr>
  <div align="right"><a href="ayuda/infoinstit.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
