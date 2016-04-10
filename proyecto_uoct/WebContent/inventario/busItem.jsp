<%@ page import="proyecto_uoct.usuario.VO.AreaVO,java.util.List,java.util.LinkedList"%>
<%@page import="java.util.Iterator" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List items=(List)request.getAttribute("items");
String mensaje=(String)request.getAttribute("mensaje");
%>
<html>
<head>
<title>
busItem
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">
<table width="750" border="0">
  <tr>
    <td><h3>Buscar &iacute;tem
        <%if(mensaje!=null){
 out.print("<strong><font color=\"red\">"+mensaje+"</font></strong>");}
  %>
      </h3></td>
  </tr>
  <tr>
    <td><form action="inventarioAction.do" method="POST" name="busItemForm">
        <input type="hidden" name="accion" value="buscarItem" />
        <table width="358" border="1">
          <tr>
            <td width="23%" bgcolor="#F7FBC4"> <div align="right"><strong>Categoria</strong></div></td>
            <td width="77%"><select name="idCategoria" size="1">
              <option value="0">----</option>
              <option value="1">Aseo</option>
              <option value="2">Escritorio</option>
              <option value="3">Computaci&oacute;n</option>
            </select></td>
          </tr>
          <tr>
            <td bgcolor="#A6F7BA"> <div align="right"><strong>Palabra Clave</strong></div></td>
            <td><input type="text" name="palClave"></td>
          </tr>
          <tr>
            <td colspan="2"><div align="center">
                <input type="submit" name="buscar" value="Buscar">
              </div></td>
          </tr>
        </table>
      </form></td>
  </tr>
  <tr>
    <td>
      <%
if(items!=null){
Iterator ite=items.iterator();
request.setAttribute("listaitems",ite);
%>
      <div align="center">
        <display:table name="listaitems" requestURI="inventarioAction.do" export="true" class="its">
        <display:caption>Items encontrados</display:caption>

        <display:column title="IDItem" property="idItem" sortable="true">
        </display:column>
        <display:column title="Nombre"  property="nomItem" class="texto">
        </display:column>


        <display:column title="Categoría" media="html" sortable="true" property="nomCategoria" maxLength="30">
        </display:column>

        <display:column title="Categoría" media="excel pdf" property="nomCategoria">
        </display:column>

        <display:column title="Cantidad" property="cantidadItem">
        </display:column>

        <display:column title="Unidad"  property="unidad" class="texto">
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
        <display:setProperty name="export.excel.filename" value="inventario"/>
        <display:setProperty name="export.pdf.filename" value="inventario"/>
        <display:setProperty name="export.xml.filename" value="inventario"/>
        <display:setProperty name="export.csv.filename" value="inventario"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/>
      </display:table> </div>
      <%}%>
    </td>
  </tr>
</table>

<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/inventario.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
