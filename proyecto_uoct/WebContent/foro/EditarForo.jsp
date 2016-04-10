<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List,java.util.Iterator,java.sql.Date,proyecto_uoct.foro.VO.DetForoVO,proyecto_uoct.foro.VO.DocForoVO" errorPage=""%>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%@ page import="java.text.SimpleDateFormat" %>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
DetForoVO detforo = (DetForoVO) request.getAttribute("detForo");
List listadocs = (List) request.getAttribute("docsForo");

%>
<html>
<head>
<title>Foro:
<%= detforo.getTitForo() %></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

  <script type="text/javascript" >

    function confirmaEliminacion(){
      var resp=confirm("Está seguro de continuar con la eliminación?");
      return resp;
}


 function valLargoFile()
{
  var frm = document.forms['docForm'];// reemplazar nombre del form
  archivo=frm.undoc.value; //reemplazar nombre del file
  largo=50; //reemplazar la longitud del campo
  while(archivo.indexOf('\\') !=-1){
  archivo=archivo.slice(archivo.indexOf('\\')+1);
  }
  if(archivo.length>largo){
  alert("nombre del archivo demasiado largo. Max. 50 caracteres");
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
    <td><h3 align="center">Editar Foro</h3></td>
  </tr>
  <tr>
    <td><h4 align="left"><strong>Tema</strong> : <%=detforo.getTemaForo() %></h4></td>
  </tr>
  <tr>
    <td><h4 align="left"><strong>Foro</strong> : <%=detforo.getTitForo() %> </h4></td>
  </tr>
  <tr>
    <td><div align="left"><strong>Fecha de inicio</strong> : <%= sdf.format(detforo.getFechaIniForo()) %> </div></td>
  </tr>
  <tr>
    <td><table width="558" border="1" align="center">
        <form action="foroAction.do" method="post" name="descForm">
          <input type="hidden" name="hacia" value="actualizarDescForo"/>
          <input type="hidden" name="id_foro" value="<%= detforo.getIdForo()%>">
          <tr bgcolor="#ADD8E4">
            <td width="127" valign="top"> <div align="right"><strong>Descripci&oacute;n
                del Foro :</strong> </div></td>
            <td width="335"> <textarea name="descforo" cols="50" rows="6" ><%= detforo.getDescForo() %></textarea>
            </td>
            <td width="74"> <input type="submit" name="Submit2" value="Actualizar">
            </td>
          </tr>
        </form>
        <script language="JavaScript" type="text/javascript">
  var frmvalidator  = new Validator("descForm");
  frmvalidator.addValidation("descforo","req","La descripción del Foro no puede ser nula");
  frmvalidator.addValidation("descforo","maxlen=200","La descripción no puede superar los 200 caracteres");

</script>
        <html:form action="/foro/foroAction.do" method="POST" name="docForm" enctype="multipart/form-data" type="proyecto_uoct.foro.controller.InsertDocForoFB">
        <tr bgcolor="#A6F7BA">
          <td height="28" valign="top"><div align="right"><strong>Agregar archivo</strong></div></td>
          <td> <input type="hidden" name="hacia" value="insertDoc"> <input type="hidden" name="id_foro" value="<%=detforo.getIdForo()%>" />
            <html:file property="undoc"/> </td>
          <td> <input type="submit" name="Submit7" value="Guardar"></td>
        </tr>
        </html:form>
        <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("docForm");
 frmvalidator.setAddnlValidationFunction("valLargoFile");
frmvalidator.addValidation("undoc","req","Debe indicar el archivo");

</script>
      </table>
      <div align="center"></div></td>
  </tr>
  <tr>
    <td> <div align="center">
        <%if(listadocs!=null){
   Iterator iflu=listadocs.iterator();
  request.setAttribute("iflu",iflu);
  %>
        <display:table name="iflu" export="true" requestURI="foroAction.do"  id="flus" class="its">

        <display:column property="tituloDoc" title="Documento(Descargar)" href="foroAction.do?hacia=descargarDoc" paramId="idDoc" paramProperty="idDocForo" >
        </display:column>

        <display:column title="Eliminar">
          <a href="foroAction.do?hacia=borrarDocForo&idForo=<%=detforo.getIdForo()%>&idDoc=<%=(((DocForoVO)flus).getIdDocForo())%>" onClick='return confirmaEliminacion()'>Eliminar</a>
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

      </display:table>
        <% }%>
      </div></td>
  </tr>
</table>
<div align="left"> </div>

<br/>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/foro.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
