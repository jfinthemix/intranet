<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List,java.util.Iterator, proyecto_uoct.documentacion.VO.ClienteVO,proyecto_uoct.common.VO.IdStrVO"  errorPage="" %>
<%
ClienteVO cli= (ClienteVO) request.getAttribute("datoscli");


%>

<html>
<head>
<title><%=cli.getNomCli() %> <%=cli.getApeCli() %></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<script type="text/JavaScript">
var otra=null;
function popUp(href, target, features) {
    otra = window.open(href, target, features);
    otra.window.focus();
  }

  function pasaEntExt(nomEntExt, idEntExt){
    formulario.nomEntExt.value = nomEntExt;
    formulario.idEntExt.value = idEntExt;
    otra.window.close();
  }

    function ningunaEntidad(){
  formulario.nomEntExt.value="ninguna (cliente independiente)";
  formulario.idEntExt.value="";
  }
</script>


</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="center">Editar Datos de :<%=cli.getNomCli() %> <%= cli.getApeCli() %></h3></td>
  </tr>
  <form action="clienteAction.do" name="formulario">
    <tr>
      <td><div align="center">
          <input type="hidden" name="hacia" value="actualizarDatosCli">
          <input type="hidden" name="id_cli" value="<%= cli.getIdCli() %>">
          <table width="889" border="1" align="center">
            <tr >
              <td bgcolor="#ADD8E4" width="136"><div align="right"><strong>Nombre*:</strong></div></td>
              <td width="737"><input name="nomCli" type="text" value="<%= cli.getNomCli() %>" size="30" /></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Apellido*:</strong></div></td>
              <td><input name="apeCli" type="text" value="<%= cli.getApeCli() %>" size="30" />
              </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Estado</strong></div></td>
              <td><strong>
                <%
      if (cli.getIsActivo().intValue()==1){
        out.print("Activado");
}
else{
  out.print("Desactivado");
}
%>
                </strong></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Telefono:</strong></div></td>
              <td><input name="telCli" type="text" value="<%
    if (cli.getFonoCli()!=null){
      out.print(cli.getFonoCli());
    } %>" size="30" maxlength="25" /></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Celular:</strong></div></td>
              <td><input name="celCli" type="text" value="<%
      if (cli.getCelCli()!=null){
        out.print(cli.getCelCli());
      }
        %>" size="30" maxlength="25" /></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Email:</strong></div></td>
              <td><input name="emailCli" type="text" value="<%
           if (cli.getEmailCli()!=null){
        out.print(cli.getEmailCli());
      }  %>" size="30" /></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Entidad Externa:</strong></div></td>
              <td><input type="text" name="nomEntExt" size="35" readonly="readonly" value="<%if (cli.getNomEnt()!=null){
      out.print(cli.getNomEnt());
      }%>" /> <input type="hidden" name="idEntExt" value="<%if (cli.getIdEnt()!=null){
      out.print(cli.getIdEnt());
      }%>" /> <a href="clienteAction.do?hacia=abuscarEntidadExt" onClick="popUp(this.href, this.target, 'width=500,height=700,scrollbars=yes'); return false;">Buscar
                Entidad Externa</a>&nbsp;<a href="#" onClick="ningunaEntidad();">Cliente
                Independiente</a> </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Cargo:</strong></div></td>
              <td><input name="cargoCli" type="text" value="<%
      if(cli.getCargo()!=null){
      out.print(cli.getCargo());
      }
      %>" size="30"></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Comentario:</strong></div></td>
              <td><textarea name="comentarioCli" cols="50" rows="8"><% if(cli.getComentCli()!=null){out.print(cli.getComentCli());} %></textarea></td>
            </tr>
          </table>
        </div></td>
    </tr>
    <tr>
      <td> <div align="center">
          <input name="submit" type="submit" value="Guardar los Cambios" />
          &nbsp;
          <input name="reset" type="reset" value="Restablecer Valores" />
        </div></td>
    </tr>
  </form>
  <script language="JavaScript" type="text/javascript">
	var frmvalidator  = new Validator("formulario");
	frmvalidator.addValidation("nomCli","req","Debe ingresar el nombre");
	frmvalidator.addValidation("nomCli","maxlen=50","Nombre no puede superar los 50 caracteres");
	frmvalidator.addValidation("nomCli","alnumspace");

	frmvalidator.addValidation("apeCli","req","Debe ingresar el apellido");
	frmvalidator.addValidation("apeCli","maxlen=50","Apellido: no puede superar los 50 caracteres");
	frmvalidator.addValidation("apeCli","alnumspace");

	frmvalidator.addValidation("telCli","maxlen=25","Teléfono puede tener hasta 25 caracteres");

	frmvalidator.addValidation("emailCli","maxlen=50","Email no puede superar los 50 caracteres");
	frmvalidator.addValidation("emailCli","email");

	frmvalidator.addValidation("celCli","numeric","Celular:solamente numeros");
	frmvalidator.addValidation("celCli","maxlen=25","Celular: no puede superar los 25 caracteres");

	frmvalidator.addValidation("cargoCli","maxlen=100","Cargo: no puede superar los 100 caracteres");
	frmvalidator.addValidation("cargoCli","alnumspace");

	frmvalidator.addValidation("comentarioCli","maxlen=200","Comentario: no puede superar los 200 caractéres");

	</script>
  <tr>
    <form action="clienteAction.do" method="post">
      <input type="hidden" name="hacia" value="cambiarEstadoCli"/>
      <input type="hidden" name="idCli" value="<%= cli.getIdCli()%>">
      <td><div align="center">
          <input type="submit" name="Submit2" value="<%
	if (cli.getIsActivo().intValue()==1){
	out.print("Desactivar Cliente");
	}
	else{
	out.print("Activar Cliente");
	}
	%> "/>
        </div></td>
    </form>
  </tr>
</table>

    <p align="right">



<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div><div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a> </div>


</p>


</body>
</html>
