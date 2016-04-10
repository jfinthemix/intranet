<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@page import="java.util.Iterator,java.util.List,proyecto_uoct.ventas.VO.InfoVtaVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<% List t=(List)request.getAttribute("tiposInfo"); %>
<% String mensaje=(String)request.getAttribute("mensaje"); %>
<html>
<head>
<title>Administración de tipos de Información para la Venta</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>


</head>

<body>
<table width="750" border="0">
  <tr> 
    <td><h3 align="left">Administrar Tipos de Informaci&oacute;n para Ventas 
        <%if(mensaje!=null){ out.print("<div align=\"center\"><font color=\"red\"><strong>"+mensaje+"</strong></font></div>");} %>
      </h3></td>
  </tr>
  <tr> 
    <td><form action="ventasAction.do" method="POST" name="infoVtaForm">
        <div align="left"> 
          <input type="hidden" name="accion" value="agregarInfoVta" />
          <table width="725" border="1" align="left">
            <tr> 
              <td width="375" bgcolor="#A6F7BA"> <div align="center"><strong>Nombre 
                  del Documento</strong></div></td>
              <td width="150" bgcolor="#ADD8E4"> <div align="center"><strong>Unidad 
                  de Venta</strong></div></td>
              <td width="178" bgcolor="#F7FBC4"> <div align="center"><strong>Precio(en 
                  UF sin IVA)</strong></div></td>
            </tr>
            <tr> 
              <td><input name="nom" type="text" size="60" maxlength="70"></td>
              <td><input type="text" name="unidad"></td>
              <td><input type="text" name="precio" maxlength="5" size="4"> <font size="5">.</font> 
                <input type="text" name="precio2" size="4" maxlength="5" /></td>
            </tr>
            <tr> 
              <td colspan="3"><div align="center"> 
                  <input type="submit" name="Submit" value="Ingresar">
                </div></td>
            </tr>
          </table>
          <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("infoVtaForm");
frmvalidator.addValidation("nom","req","Debe indicar el  Nombre");
frmvalidator.addValidation("nom","maxlen=100","Nombre no puede superar los 100 caracteres");
frmvalidator.addValidation("nom","alnumspace");

frmvalidator.addValidation("unidad","req","Debe ingresar la unidad");
frmvalidator.addValidation("unidad","maxlen=20","Unidad no puede superar los 20 caracteres");
frmvalidator.addValidation("unidad","alnumspace");

frmvalidator.addValidation("precio","req","Debe indicar el precio");
frmvalidator.addValidation("precio","req","Debe indicar el decimal del precio");
frmvalidator.addValidation("precio","num");
frmvalidator.addValidation("precio2","num");
frmvalidator.addValidation("precio","maxlen=5","Precio no puede superar los 5 dígitos");
frmvalidator.addValidation("precio2","maxlen=5","El decimal del precio no puede superar los 5 dígitos");
</script>
        </div>
      </form></td>
  </tr>
  <tr>
    <td> <div align="left">
        <% if(t!=null){
 Iterator ite=t.iterator();
 request.setAttribute("lista",ite);
 %>
        <display:table name="lista" id="lis" class="its"> <display:caption>Tipos 
        de Información para la Venta </display:caption> <display:column title="Tipo de Información" property="tipoInfo"> 
        </display:column> <display:column title="Unidad de Venta" property="unidad"> 
        </display:column> <display:column title="Precio(UF)" property="precio"> 
        </display:column> <display:column title="Editar" href="ventasAction.do?accion=editarInfoVta" paramId="idInfoVta" paramProperty="idInfo">Editar 
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
      </div></td>
  </tr>
</table>
 
<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a> 
  </div>
</body>
</html>
