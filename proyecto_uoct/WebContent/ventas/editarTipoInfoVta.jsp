<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@page import="java.util.Iterator,java.util.List,proyecto_uoct.ventas.VO.InfoVtaVO" %>

<%
InfoVtaVO inf=(InfoVtaVO)request.getAttribute("detInfo");
String mensaje=(String)request.getAttribute("mensaje");

String spre=inf.getPrecio().toString();
String ent=spre.substring(0,spre.indexOf("."));
String dec=spre.substring(spre.indexOf(".")+1);

%>


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
    <td><h3>
        <%if(mensaje!=null){ out.print("<div align=\"center\"><font color=\"red\"><strong>"+mensaje+"</strong></font></div>");} %>
      </h3>
      </td>

  </tr>
  <tr>
    <td><form action="ventasAction.do" method="POST" name="infoVtaForm">
        <input type="hidden" name="accion" value="actualizarInfoVta" />
        <input type="hidden" name="idInfoVta" value="<%=inf.getIdInfo() %>" />
        <table width="725" border="1">
          <tr>
            <td width="375" bgcolor="#A6F7BA">
              <div align="center"><strong>Nombre
                del Documento</strong></div></td>
            <td width="150" bgcolor="#ADD8E4">
              <div align="center"><strong>Unidad
                de Venta</strong></div></td>
            <td width="178" bgcolor="#F7FBC4">
              <div align="center"><strong>Precio(en
                UF sin IVA)</strong></div></td>
          </tr>
          <tr>
            <td>
              <strong><%=inf.getTipoInfo() %></strong><input type="hidden" name="nom" value="<%=inf.getTipoInfo() %>"/>
              </td>
            <td><input type="text" name="unidad" value="<%=inf.getUnidad()%>"></td>
            <td><input type="text" maxlength="5" size="4"  name="precio1" value="<%=ent %>">
              <font size="5">.</font>
              <input type="text" maxlength="5" name="precio2" value="<%=dec %>" size="4"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><select name="idEstado">
                <option value="1" <%if(inf.getIsActivo()){out.print(" selected");}%> >Activado</option>
                <option value="0"  <%if(!inf.getIsActivo()){out.print(" selected");}%>>Desactivado</option>
              </select></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="4"><div align="center">
                <input type="submit" name="Submit" value="Actualizar">
                &nbsp;
                <input type="reset" name="Submit2" value="Restablecer">
              </div></td>
          </tr>
        </table>
      </form></td>
  </tr>
</table>
<script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("infoVtaForm");
frmvalidator.addValidation("nom","req","Debe indicar el  Nombre del Tipo de Venta");
frmvalidator.addValidation("nom","maxlen=100","Nombre no puede superar los 100 caracteres");
frmvalidator.addValidation("nom","alnumspace");

frmvalidator.addValidation("unidad","req","Debe indicar la Unidad");
frmvalidator.addValidation("unidad","alnumspace");
frmvalidator.addValidation("unidad","maxlen=20","Hasta 20 caracteres en la Unidad" );

frmvalidator.addValidation("precio1","req","Debe indicar el precio");
frmvalidator.addValidation("precio1","num");
frmvalidator.addValidation("precio1","maxlen=5","Sólo 5 dígitos en el precio");

frmvalidator.addValidation("precio2","req","Debe indicar el decimal del precio");
frmvalidator.addValidation("precio2","num");
frmvalidator.addValidation("precio2","maxlen=5","Sólo 5 dígitos en el decimal del precio");

</script>
<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
