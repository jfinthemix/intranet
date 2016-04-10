<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, proyecto_uoct.documentacion.VO.EntExtVO" errorPage=""%>
<%EntExtVO entidad = (EntExtVO) request.getAttribute("entidad");%>
<html>
<head>
<title>Detalle de Entidad Externa</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
</head>
<body bgcolor="#ffffff" bgproperties="fixed">
<table width="750" border="0">
  <tr>
    <td height="25">
<h3 align="center">Datos de: <%=entidad.getNomEnt() %></h3></td>
  </tr>
  <form action="clienteAction.do" method="POST" name="form1">
    <input type="hidden" name="hacia" value="actualizarEntExt"/>
    <input type="hidden" name="idEnt" value="<%=entidad.getIdEnt() %>"/>
    <tr>
      <td><div align="center">
          <table width="44%" border="1" align="center">
            <tr>
              <td width="33%" bgcolor="#ADD8E4"> <div align="right"><strong>Nombre</strong>
                </div></td>
              <td width="67%"><%=entidad.getNomEnt() %> </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>Estado</strong>
                </div></td>
              <td> <strong>
                <%
        if (entidad.getIsActivo().intValue() == 1) {
          out.print("Activado");
        }
        else {
          out.print("Desactivado");
        }
      %>
                </strong> </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>Tel&eacute;fono</strong>
                </div></td>
              <td> <input type="text" maxlength="35" name="fonoEnt" value="<%
   if(entidad.getFonoEnt()!=null){
    out.print(entidad.getFonoEnt());}
     %>"/> </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>Direcci&oacute;n</strong>
                </div></td>
              <td> <input type="text" name="dirEnt" value="<%
   if(entidad.getDirEnt()!=null){
     out.print(entidad.getDirEnt());}
     %>"/> </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>Sitio Web</strong>
                </div></td>
              <td> <input type="text" name="webEnt" value="<%
   if(entidad.getWeb()!=null){
    out.print(entidad.getWeb());}
     %>"/> </td>
            </tr>
          </table>
        </div></td>
    </tr>
    <tr>
      <td><div align="center">
          <input name="submit" type="submit" value="Guardar los Calmbios"/>
          &nbsp;
          <input name="reset" type="reset" value="Restablecer los Valores"/>
        </div></td>
    </tr>
  </form>
  <script language="JavaScript" type="text/javascript">
  var frmvalidator  = new Validator("form1");

  frmvalidator.addValidation("fonoEnt","alnumspace");
  frmvalidator.addValidation("fonoEnt","maxlen=30","Teléfono no puede superar los 15 dígitos");
  frmvalidator.addValidation("webEnt","maxlen=150","Sitio Web no puede superar los 150 caracteres");
  frmvalidator.addValidation("dirEnt","maxlen=70","Dirección no puede superar los 50 caracteres");
  frmvalidator.addValidation("dirEnt","alnumspace");

</script>
  <tr>
    <td><form action="clienteAction.do" method="post">
        <div align="center">
          <input type="hidden" name="hacia" value="cambiarEstadoEntExt"/>
          <input type="hidden" name="idEntExt" value="<%= entidad.getIdEnt()%>">
          <input type="submit" name="Submit2" value="<%
if (entidad.getIsActivo().intValue()==1){
out.print("Desactivar Entidad");
}
else{
out.print("Activar Entidad");
}
%> "/>
        </div>
      </form></td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div><div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a> </div>
</body>
</html>
