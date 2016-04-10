<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="proyecto_uoct.proyecto.VO.DocumentodeListaProyVO,java.util.List" errorPage="" %>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%

String nom_proy = (String) request.getAttribute("nomProy");
String id_proy = (String)request.getAttribute("idProy");
String mensaje=(String) request.getAttribute("mensaje");
List docs=(List)request.getAttribute("docs");

%>

<html>
<head>
<title>Documentos de Iniciativa</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

  <script type="text/javascript">

function confirmaEliminacion(){
var resp=confirm("¿Está seguro que desea eliminar este documento?");
return resp;
}

</script>

<script type="text/javascript">


 function valLargoFile()
{
  var frm = document.forms['docIniForm'];// reemplazar nombre del form
  archivo=frm.docProy.value; //reemplazar nombre del file
  largo=50; //reemplazar la longitud del campo
  while(archivo.indexOf('\\') !=-1){
  archivo=archivo.slice(archivo.indexOf('\\')+1);
  }
  if(archivo.length>largo){
  alert("nombre del archivo demasiado largo(50 car. máximo)");
  return false;
  }else{
  return true;
  }

}
</script>
</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="left">Iniciativa:<%=nom_proy %>
        <%if(mensaje!=null){ %>
      </h3>
      <div align="center"> <font color="red"><strong> <%=mensaje %> </strong></font>
      </div>
      <% }%>
    </td>
  </tr>
  <tr>
    <td><html:form action="/proyectos/proyectoAction.do" name="docIniForm" method="post" type="proyecto_uoct.proyecto.controller.AgregarDocProy_fb" enctype="multipart/form-data">
      <input type="hidden" name="hacia" value="regDocIni" />
      <input type="hidden" name="idProy" value="<%=id_proy%>" />
      <input type="hidden" name="nomProy" value="<%=nom_proy%>" />
      <table width="590" border="1" align="left">
        <tr>
          <td width="228" bgcolor="#ADD8E4"><strong>Agregar Documento al proyecto</strong>
          </td>
          <td width="346"> <html:file property="docProy"> </html:file> </td>
        </tr>
        <tr>
          <td colspan="2"><div align="center">
              <input type="submit" name="Submit" value="Registrar">
            </div></td>
        </tr>
      </table>
      </html:form> <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("docIniForm");
 frmvalidator.setAddnlValidationFunction("valLargoFile");
frmvalidator.addValidation("docProy","req","Debe indicar el archivo");

</script> </td>
  </tr>
</table>
<div align="left">
  <% if(docs!=null){%>
  <display:table class="its" name="docs" pagesize="10" requestURI="proyectoAction.do" id="docss">
    <display:column title="Documento"  href="proyectoAction.do?hacia=descargarDocIni" property="str" paramId="idDoc" paramProperty="id">
  </display:column>
  <display:column title="Eliminar"> <a href="proyectoAction.do?hacia=eliminarDoc&idDoc=<%=((DocumentodeListaProyVO)docss).getId()%>&nomProy=<%=nom_proy%>&idProy=<%=id_proy%>" onclick='return confirmaEliminacion()'>Eliminar</a>
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
  <display:setProperty name="export.amount" value="list"/> </display:table>
  <%
}%>
</div>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/iniciativas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>

