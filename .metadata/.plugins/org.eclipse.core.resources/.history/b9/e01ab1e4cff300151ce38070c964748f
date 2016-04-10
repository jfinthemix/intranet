<%@page language="java"%>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.infoyrep.VO.ArchivoInfoVO"%>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%

List lista=(List) request.getAttribute("lista");
String mensaje=(String) request.getAttribute("mensaje");
%>
<html>
<head>
  <title>Administraci&oacute;n de Archivos de Informaci&oacute;n Institucional</title>
  <script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
  <link href="../util/styla.css" rel="stylesheet" type="text/css">
    <!-- validador -->
    <script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
<script type="text/javascript">
 function valLargoFile()
{
  var frm = document.forms['form1'];// reemplazar nombre del form
  archivo=frm.unArchivo.value; //reemplazar nombre del file
  largo=50; //reemplazar la longitud del campo
  while(archivo.indexOf('\\') !=-1){
  archivo=archivo.slice(archivo.indexOf('\\')+1);
  }
  if(archivo.length>largo){
  alert("nombre del archivo demasiado largo");
  return false;
  }else{
  return true;
  }

}

function eliminarInfo(id){
  form2.idFile.value=id;
elim=window.confirm('¿Está seguro de eliminar este archivo?');
(elim)?form2.submit():'return false';
}

</script>
  </head>
<body>
<div align="center">
  <table width="750" border="0">
    <tr>
      <td><h3 align="left"><strong>Publicar Archivo </strong> </h3>
          <%if(mensaje!=null){%>
        <h3><font color="red"><strong><%=mensaje %></strong></font></h3> <%} %>
  </td>
    </tr>
    <tr>
      <td>
	  <html:form action="/info_instit_y_report/infoinstitAction.do" enctype="multipart/form-data" name="form1" type="proyecto_uoct.infoyrep.controller.fileup1ActionForm" >
	  <input type="hidden" name="hacia" value="agregarArchivo">
        <table width="494" border="1" align="left">
          <tr>
            <td width="146" bgcolor="#ADD8E4"><strong>Seleccione el Archivo:</strong></td>
            <td width="332"><html:file property="unArchivo"/></td>
        </tr>
        <tr>
            <td height="107" bgcolor="#ADD8E4"><strong>Descripci&oacute;n:</strong>
            </td>
          <td><textarea name="descripcion" cols="45" rows="5"></textarea></td>
        </tr>
        <tr>
      <td colspan="2"><div align="center">
        <input type="submit" name="Submit" value="Guardar">
        </div></td>
      </tr>
    </table>

</html:form>
	<script language="JavaScript" type="text/javascript">
	var frmvalidator  = new Validator("form1");
	frmvalidator.addValidation("unArchivo","req","Debe indicar el archivo");
	frmvalidator.addValidation("descripcion","req","Debe indicar una descripción del archivo");
	frmvalidator.addValidation("descripcion","maxlen=300","La descripción no puede superar los 300 carcteres");
	frmvalidator.addValidation("descripcion","alnumspace");
	</script>

    <script language="JavaScript" type="text/javascript">
    var frmvalidator  = new Validator("form1");

    frmvalidator.setAddnlValidationFunction("valLargoFile");

    frmvalidator.addValidation("unArchivo","req","Debe ingresar el archivo");
    frmvalidator.addValidation("descripcion","req","Debe Ingresar Descripción");
    frmvalidator.addValidation("descripcion","maxlen=300","Descripción no puede superar los 300 caracteres");
  </script></td>
    </tr>
    <tr>
      <td> <div align="left">
          <%if (lista!=null){
  Iterator lis=lista.iterator();
  request.setAttribute("lista",lis);%>
 <form action="infoinstitAction.do" name="form2">

         <display:table id="archivos" name="lista" class="its" requestURI="infoinstitAction.do">
          <display:caption>Archivos de Informaci&oacute;n Institucional</display:caption>
          <display:column title="Nombre"> <%=((ArchivoInfoVO)archivos).getNomArchivo() %> </display:column>
          <display:column title="Descripción" class="texto">
          <%=((ArchivoInfoVO)archivos).getDescripcion() %> </display:column>
          <display:column title="Descargar" href="infoinstitAction.do?hacia=descargarArchivo" paramId="idFile" paramProperty="idFile" >
          Descargar </display:column>
          <display:column  title="Eliminar">
          <a href="#" onclick="eliminarInfo(<%=((ArchivoInfoVO)archivos).getIdFile()%>)">Eliminar</a>
        </display:column>


          <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
          <display:setProperty name="basic.empty.showtable" value="true"/> <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
          <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
          <display:setProperty name="paging.banner.placement" value="bottom"/>
          <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
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


 </div>

   <input type="hidden" name="hacia" value="eliminarArchivo" />
   <input type="hidden" name="idFile" value="" />
 </form>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/infoinstit.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
