<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,proyecto_uoct.EmpMant.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje          = (String) request.getAttribute("mensaje");
%>
<html>
<head>
<title>Empresa mantenedora</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
function valida_envia(){
  document.form_nueva_empmant.submit();
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
  <form name="form_nueva_empmant" method="post" action="../empmant/empMantAction.do">
    <table width="85%" border="0" align="center">
      <tr>
        <td colspan="2">Empresa mantenedora</td>
      </tr>
      <tr>
        <td colspan="2"><font color="red">
        <%
        if (mensaje != null)
        out.print(mensaje);
        %></font>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2">

          <table width="100%" border="1" align="center" id="tabla">
            <tr>
              <td width="17%" bgcolor="#ADD8E4"> <div align="right"><strong>nombre</strong></div></td>
              <td width="83%"> <input name="nombre" type="text" value="" size="60" maxlength="50"></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>direcci&oacute;n</strong></div></td>
              <td><font size="1"><input name="direccion" type="text" value="" size="60" maxlength="50"></font> </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>tel&eacute;fono</strong></div></td>
              <td><font size="1"><input type="text" name="telefono" size="20" value=""></font> </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>vigencia</strong></div></td>
              <td>
                <select name="vigente">
                  <option selected value="1">Vigente</option>
                  <option value="0">No vigente</option>
                </select>
              </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>mail terreno</strong></div></td>
              <td>
                <font size="1">
                  <input name="mail_terreno" type="text" value=" - " size="60" maxlength="50">
                  [Separar por "," si es m&aacute;s de un mail]
                </font>
              </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>mail sala</strong></div></td>
              <td>
                <font size="1">
                  <input name="mail_sala" type="text" value=" - " size="60" maxlength="50">
                  [Separar por "," si es m&aacute;s de un mail]
                </font>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td><font size="2">
                <input type="submit" name="hacia" value="Grabar">
                </font>
              </td>
              <td><div align="right"><font size="2">
                <input type="reset" name="BotonCancelar" value="Cancelar">
                </font></div>
              </td>
              </tr>
            </table>
            <script language="JavaScript" type="text/javascript">
            var frmvalidator  = new Validator("form_nueva_empmant");
            frmvalidator.addValidation("nombre","req","Debe ingresar el nombre");
            frmvalidator.addValidation("telefono","req","Debe ingresar el telefono");
            frmvalidator.addValidation("direccion","req","Debe ingresar la direccion");
            frmvalidator.addValidation("mail_terreno","req","Debe ingresar el o los mails que correspondan a mantenciones en terreno.");
            frmvalidator.addValidation("mail_sala","req","Debe ingresar el o los mails que correspondan a mantenciones en terreno.");
          </script>
        </td>
      </tr>
    </table>
  </form>
</body>
</html>
