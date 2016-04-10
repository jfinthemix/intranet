<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%
String id_usu_s= (String) request.getAttribute("id_usu");
%>
<html>
<head>
<title>
Cambiar Contrase&ntilde;a
</title>

<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function validacontrasena(form){
if(form.vieja_c.value==""){
alert ("Ingrese la contraseña antigua");
form.vieja_c.focus();
return;
}
if(form.nueva_c.value==""){
alert ("Ingrese la contraseña nueva");
form.nueva_c.focus();
return;
}

if(form.confirmacion.value==""){
alert ("Ingrese la confirmación de la contraseña nueva");
form.confirmacion.focus();
return;
}

if(form.confirmacion.value!=form.nueva_c.value){
alert("La contraseña nueva y la confirmación no coinciden");
form.confirmacion.focus();
return;
}

var car=form.nueva_c.value.search("[^A-Za-z0-9\-_]");
if(car >=0){
alert("la nueva contraseña no puede contener caracteres que no sean alfanumericos, - o _");
form.nueva_c.focus();
return;
}
var lar=form.nueva_c.value.length;
if(lar<6){
  alert("la contraseña no puede ser menor de 6 caracteres");
  form.nueva_c.focus();
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
      <td width="492"><h3 align="left">Cambiar Contrase&ntilde;a</h3></td>
    </tr>
    <tr>
      <td><div align="left">
          <input name="hacia" type="hidden" id="hacia" value="cambiarContrasena">
          <input name="id_usu" type="hidden" id="id_usu" value="<%=id_usu_s %>">
          <table width="398" border="1" align="left">
            <tr>
              <td width="163" bgcolor="#ADD8E4"> <div align="right"><strong>Antigua
                  contrase&ntilde;a</strong></div></td>
              <td width="193"> <input type="password" name="vieja_c"></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>Nueva Contrase&ntilde;a</strong></div></td>
              <td><input type="password" name="nueva_c"></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>Confirmar la nueva
                  Contrase&ntilde;a</strong></div></td>
              <td><input name="confirmacion" type="password" id="confirmacion"></td>
            </tr>
          </table>
        </div></td>
    </tr>
    <tr>
      <td><div align="left">
          <input type="button" name="Submit" value="Cambiar" onClick="validacontrasena(this.form)">
        </div></td>
    </tr>
  </form>
</table>

<hr>
  <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>
<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>

</body>
</html>
