<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%
String id_usu_s= (String) request.getAttribute("id_usu");
String nomUsu=(String) request.getAttribute("nomUsu");
%>
<html>
<head>
<title>
Cambiar Contrase&ntilde;a
</title>

<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script type="text/javascript">

function avisoCambio(form){
  cambiar = window.confirm('Al reasignar una contraseña se eliminará la anterior.\r\n ¿Está seguro de la operación?');
  (cambiar)?validacontrasena(form):'return false';

}

function validacontrasena(form){

if(form.nuevaContrasena.value==""){
alert ("La nueva contraseña no puede ser nula");
form.nuevaContrasena.focus();
return;
}


var car=form.nuevaContrasena.value.search("[^A-Za-z0-9\-_]");
if(car >=0){
alert("la nueva contraseña no puede contener caracteres que no sean alfanumericos, - o _");
form.nuevaContrasena.focus();
return;
}

var lar=form.nuevaContrasena.value.length;
if(lar<6){
  alert("la contraseña no puede ser menor de 6 caracteres");
  form.nuevaContrasena.focus();
  return;
}

form.submit();
}
</script>

</head>
<body bgcolor="#ffffff">
<table width="498" border="0">
  <form name="form1" method="post" action="usuarioAction.do">
    <tr>
      <td width="492"><h3 align="left">Cambiar la Contrase&ntilde;a al usuario:<%=nomUsu %>
        </h3></td>
    </tr>
    <tr>
      <td><div align="left">
          <input name="hacia" type="hidden" id="hacia" value="reasignarContrasena">
          <input name="id_usu" type="hidden" id="id_usu" value="<%=id_usu_s %>">
          <table width="398" border="1" align="left">
            <tr>
              <td width="163" bgcolor="#ADD8E4"> <div align="right"><strong>Nueva
                  Contrase&ntilde;a</strong></div></td>
              <td width="193"><input name="nuevaContrasena" type="password"></td>
            </tr>
          </table>
        </div></td>
    </tr>
    <tr>
      <td><div align="left">
          <input type="button" name="Submit" value="Cambiar" onClick="avisoCambio(this.form)">
        </div></td>
    </tr>
  </form>
</table>

<hr><div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>
<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>

</body>
</html>
