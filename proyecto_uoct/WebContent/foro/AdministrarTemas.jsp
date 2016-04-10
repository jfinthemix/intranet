<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage=""%>
<%@page import="java.util.List,proyecto_uoct.foro.VO.TemasVO,java.util.Iterator"%>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%List listadetemas = (List) request.getAttribute("listadetemas");%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-cache">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" >

    function confirmaEliminacion(){
      var resp=confirm("Al eliminar un Tema se eliminarán todos los foros asociados a él.\n ¿Desea continuar con la eliminación?");
      return resp;
}
  </script>

  <!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

</head>
<body>
<table width="551" border="0">
  <tr>
    <td colspan="2"><h3 align="center">Administrar Temas</h3></td>
  </tr>
  <tr>
    <td width="187" bgcolor="#ADD8E4"><div align="right"><strong>Agregar Tema:</strong></div></td>
    <td width="354"><form action="foroAction.do" method="POST" name="temaForm">
        <div align="left">
          <input type="hidden" name="hacia" value="agregarTema" />
          <input type="text" name="nuevotema" maxlength="50">
          <input type="submit" name="Submit" value="Agregar">
          <script language="JavaScript" type="text/javascript">
  var frmvalidator  = new Validator("temaForm");
  frmvalidator.addValidation("nuevotema","req","Debe ingresar el nombre del tema");
  frmvalidator.addValidation("nuevotema","maxlen=50","El tema no puede superar los 50 caracteres");
  frmvalidator.addValidation("nuevotema","alnumspace");
  </script>
        </div>
      </form></td>
  </tr>
  <tr>
    <td colspan="2" align="left"><h4>Temas de Foros</h4>
      </td></tr>
<tr>
  <td colspan="2" align="left">

        <display:table name="listadetemas" id="lt" requestURI="foroAction.do" pagesize="15" class="its">
          <display:column title="Tema" property="tema" sortable="true" sortProperty="tema">
          </display:column>

          <display:column title="Borrar">
           <a href="foroAction.do?hacia=borrarTema&id_tema=<%=((TemasVO)lt).getIdTema()%>" onclick='return confirmaEliminacion()' >borrar</a>
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
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/>

        </display:table>

      </div>
    </td>
  </tr>
</table>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/foro.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
