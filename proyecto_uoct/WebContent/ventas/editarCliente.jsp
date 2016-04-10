<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@page import="proyecto_uoct.ventas.VO.CliVtaVO" %>
<%
CliVtaVO cli=(CliVtaVO) request.getAttribute("cliente");
%>
<html>
<head>
<title>Detalle de cliente</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3>Editar datos de :<%= cli.getNomCli() %></h3></td>
  </tr>
  <tr>
    <td><form action="ventasAction.do" name="actuCliVtaForm" method="POST">
        <input type="hidden" name="accion" value="actualizarCliente" />
        <input type="hidden" name="idCliente" value="<%=cli.getIdCliente() %>" />
        <table width="323" border="1" align="left">
          <tr>
            <td width="75" bgcolor="#ADD8E4"><strong>Nombre:</strong></td>
            <td width="232"><%= cli.getNomCli() %></td>
          </tr>
          <tr>
            <td width="75" bgcolor="#ADD8E4"><strong>RUT:</strong></td>
            <td width="232"> <input name="rut" type="text" size="8" maxlength="8" value="<%if(cli.getRut().intValue()!=0){
   out.print(cli.getRut());}%>"/>
              -
              <input type="text" size="1" name="codRut" maxlength="1" value="<%if(cli.getCodRutCli()!='\0'){out.print(cli.getCodRutCli());}%>" />
            </td>
          </tr>
          <tr>
            <td width="75" bgcolor="#ADD8E4"><strong>Dirección:</strong></td>
            <td width="232"><input type="text" name="dir_cli" value="<%
   if(cli.getDireccion()!=null){
   out.print(cli.getDireccion());}
   %>"/></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><strong>Telefono:</strong></td>
            <td><input type="text" name="fono_cli" value="<%
   if(cli.getTelefono()!=null){
   out.print(cli.getTelefono());
   }%>"/></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><strong>Email:</strong></td>
            <td><input type="text" name="email_cli" value="<%
   if(cli.getEmail()!=null){
   out.print(cli.getEmail());
   }
    %>"/></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><strong>Giro:</strong></td>
            <td><input type="text" name="giro" value="<%
    if(cli.getGiro()!=null){
      out.print(cli.getGiro());
    }%>"/></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><strong>Contactos:</strong></td>
            <td><textarea name="contactos" cols="50" rows="5"><%
    if(cli.getContactos()!=null){
      out.print(cli.getContactos());
    }%></textarea></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><strong>Comentario:</strong></td>
            <td><textarea name="comentario" cols="50" rows="5"><%
    if(cli.getComentario()!=null){
      out.print(cli.getComentario());
    }%></textarea></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><strong>Estado:</strong></td>
            <td><select name="idEstado">
                <option value="0" <%if(!cli.getIsActivo()){out.print(" selected");} %>>Desactivado</option>
                <option value="1" <% if(cli.getIsActivo()){out.print(" selected");} %>>Activado</option>
              </select></td>
          </tr>
          <tr>
            <td colspan="2"><input name="submit" type="submit" value="Guardar los Cambios"/>
              &nbsp; <input name="reset" type="reset" value="Restaurar los Valores"/></td>
          </tr>
        </table>
      </form></td>
  </tr>

</table>
	  <script language="JavaScript" type="text/javascript">
	var frmvalidator  = new Validator("actuCliVtaForm");

	frmvalidator.addValidation("rut","num");
	frmvalidator.addValidation("rut","maxlen=8");

	frmvalidator.addValidation("codRut","alnum");
	frmvalidator.addValidation("codRut","maxlen=1","Sólo 1 caracter en el codigo del rut");

	frmvalidator.addValidation("dir_cli","alnumspace");
	frmvalidator.addValidation("dir_cli","maxlen=100", "Hasta 100 caracteres en la Dirección" );

	frmvalidator.addValidation("fono_cli","alnumspace");
	frmvalidator.addValidation("fono_cli","maxlen=15","Hasta 15 caracteres en el télefono" );

	frmvalidator.addValidation("email_cli","email");
	frmvalidator.addValidation("email_cli","maxlen=40","Hasta 40 caracteres en el email" );

	frmvalidator.addValidation("giro","alnumspace");
	frmvalidator.addValidation("giro","maxlen=50","Hasta 50 caracteres en el giro");

	frmvalidator.addValidation("contactos","alnumspace");
	frmvalidator.addValidation("contactos","maxlen=100","Hasta 100 caracteres en el comentario" );

	frmvalidator.addValidation("comentario","alnumspace");
	frmvalidator.addValidation("comentario","maxlen=200","Hasta 200 caracteres en el comentario" );

	</script>
<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>

