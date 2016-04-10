<%@page import="java.util.*,proyecto_uoct.documentacion.VO.EntExtVO"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%List entidades = (List) request.getAttribute("listaEnt");%>
<html>
<head>
<title>Lista de Entidades Externas</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">
<table width="750" border="0">
  <tr>
    <td><h3>Lista de Entidades Externas</h3></td>
  </tr>
  <tr>
    <td>
      <%if(entidades!=null){
  Iterator ie=entidades.iterator();
  request.setAttribute("entList",ie);
  %>
      <div align="center"> <display:table class="its" name="entList" id="ents" requestURI="clienteAction.do" pagesize="40" >
        <display:column title="Entidad Externa" property="nomEnt" sortable="true" href="clienteAction.do?hacia=detalleEntExt" paramId="id_ent" paramProperty="idEnt">
        </display:column> <display:column title="Estado" sortable="true">
        <%
        if(((EntExtVO)ents).getIsActivo().intValue()==1){%>
        <%="Activada"%>
        <%}
       else{%>
        <%="Desactivada"%>
        <%} %>
        </display:column>
        <display:column title="Editar" href="clienteAction.do?hacia=aeditarEntidadExterna" paramId="id_ent" paramProperty="idEnt">Editar
        </display:column>

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


      </display:table> </div>
      <% }%>
    </td>
  </tr>

</table>

  <p><hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div><div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a> </div></p></body>
</html>
