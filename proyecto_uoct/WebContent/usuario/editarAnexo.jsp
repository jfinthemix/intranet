<%@page import="proyecto_uoct.usuario.VO.AnexoVO" %>
<%
AnexoVO anx= (AnexoVO) request.getAttribute("anx");
%>
<head>
<title>
  Editar Anexo
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">

  <script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

  <script language="JavaScript" type="text/javascript">
function confirmarEnvio(f){
borrar = window.confirm('Seguro que desea eliminar el anexo');
(borrar)?f.submit():'return false';
}
</script>



</head>
<body bgcolor="#ffffff">

<div align="center">
  <table width="750" border="0">
    <tr>
      <td><h3 align="left">Anexo:&nbsp; <%=anx.getNomAnexo() %></h3></td>
    </tr>
    <form action="usuarioAction.do" method="POST" name="formanx">
      <tr>
        <td> <div align="left">
            <input type="hidden" name="hacia" value="actualizarAnexo" />
            <input type="hidden" name="idAnx" value="<%= anx.getIdAnexo()%>" />
            <input type="text" name="anx" value="<%= anx.getAnexo() %>" />
          </div></td>
      </tr>
      <tr>
        <td><div align="left">
            <input name="submit" type="submit" value="Actualizar" />
            <input name="reset" type="reset" value="Restaurar Anexo" />
          </div></td>
      </tr>
    </form>
    <tr>
      <td>
          <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("formanx");
frmvalidator.addValidation("anx","req","Debe indicar el  Número de anexo");
frmvalidator.addValidation("anx","num");
</script>
        <form action="usuarioAction.do" method="POST">
          <div align="left">
            <input type="hidden" name="hacia" value="borrarAnexo" />
            <input type="hidden" name="idAnx" value="<%=anx.getIdAnexo() %>" />
            <input name="button" type="button" onClick="confirmarEnvio(this.form);" value="Eliminar este anexo" />
          </div>
        </form></td>
    </tr>
  </table>
</div>
<hr>
  <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>

<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>

</body>
</html>
