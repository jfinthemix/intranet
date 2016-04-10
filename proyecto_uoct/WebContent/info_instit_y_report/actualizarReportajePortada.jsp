<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,proyecto_uoct.infoyrep.VO.ReportajeVO" errorPage="" %>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%
ReportajeVO reportaje= (ReportajeVO) request.getAttribute("reportaje");
%>
<html>
<head>
<title>Publicaci&oacute;n de reportajes</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="left">Actualizar Reportaje</h3></td>
  </tr>
  <html:form action="/info_instit_y_report/reportAction.do" enctype="multipart/form-data">
  <tr>
    <td> <div align="center"><html:hidden property="hacia" value="actualizarReportaje" />
      <html:hidden property="idRep" value="<%=reportaje.getIdRep().toString()%>" />
        <table width="551" border="1" align="left">
          <tr>
            <td width="187" bgcolor="#ADD8E4"> <div align="right"><strong> T&iacute;tulo
                del reportaje</strong></div></td>
            <td width="348"><html:text property="tit_rep" size="40" value="<%=reportaje.getTitRep() %>"></html:text>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>Imagen</strong></div></td>
            <td> <html:file property="foto"/><br>(Dimensiones ideales:250/200 px)
            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>Contenido del reportaje</strong></div></td>
            <td><html:textarea property="desc_rep" rows="20" cols="50" value="<%=reportaje.getDescRep() %>"></html:textarea></td>
          </tr>
        </table>
      </div>
      <div align="left"></div></td>
  </tr>
  <tr>
    <td> <div align="left"></div>
      <html:submit value="Actualizar" /> </td>
  </tr>
  </html:form>
</table>

 <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("IngReportajeFBean");

frmvalidator.addValidation("tit_rep","req","Debe Ingresar título del reportaje");
frmvalidator.addValidation("tit_rep","maxlen=120","Titulo no puede superar los 120 caracteres");

frmvalidator.addValidation("desc_rep","req","Debe Ingresar descripción del reportaje");
frmvalidator.addValidation("desc_rep","maxlen=4000","Descripción no puede superar los 4000 caracteres");

</script>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/portada.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
