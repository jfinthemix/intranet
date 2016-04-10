<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.ventas.VO.CliVtaVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List clientes=(List) request.getAttribute("clientes");
%>
<html>
<head>
<title>Buscar Cliente de Venta</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function pasaNomId(nom,id){
	window.opener.pasaCli(nom,id);
}

</script>



</head>

<body>
<table width="350" border="0">
  <tr> 
    <td width="380"><h3 align="left">Buscar Cliente</h3></td>
  </tr>
  <tr> 
    <td> <div align="left"> 
        <table width="248" border="1" align="left">
          <tr> 
            <td width="238" bgcolor="#FFFF99"> <div align="center"> <strong> Por 
                palabra clave(*): </strong> </div></td>
          </tr>
          <tr> 
            <td bgcolor="#FFFF99"> <div align="center"> 
                <form action="ventasAction.do" method="POST">
                  <input type="hidden" name="accion" value="busCliVta_pop"/>
                  <input type="text" name="palClave">
                  <br>
                  <input type="submit" name="PorPalabra" value="Buscar">
                </form>
              </div></td>
          </tr>
        </table>
      </div></td>
  </tr>
  <tr> 
    <td><div align="left"><strong>(*)nombre,direcci&oacute;n,giro,contactos o 
        comentario.</strong></div></td>
  </tr>
  <tr> 
    <td> <div align="left"> 
        <%if(clientes!=null){
%>
        <display:table name="clientes" class="its" pagesize="15" requestURI="ventasAction.do" id="clis"> 
        <display:caption>Clientes Encontrados </display:caption> <display:column title="Seleccionar"> 
        <a href="#" onClick="return pasaNomId('<%=((CliVtaVO)clis).getNomCli()%>','<%=((CliVtaVO)clis).getIdCliente()%>')">Seleccionar</a> 
        </display:column> <display:column title="Nombre" media="html" property="nomCli" sortProperty="nomCli" sortable="true" > 
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
      </div></td>
  </tr>
</table>
<h2>&nbsp;</h2>
</body>
</html>
