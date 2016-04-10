<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>Mensaje UOCT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<SCRIPT LANGUAGE="JavaScript">
<!-- Begin
function popUp(URL,form) {
  URL=URL+"?accion=escribir&mens="+form.mens;
day = new Date();
id = day.getTime();
eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=3072,height=200');");
}
</script>
</head>

<body>
<p><strong>Introduzca el mensaje:</strong></p>
<form name="form1" method="post" action="">

  <p>
    <input name="mens" type="text" size="100">
  </p>
  <p>
    <input type="button" name="Submit" value="enviar" onclick="javascript:popUp('textoAction.do',this.form)">
  </p>
</form>
</body>
</html>
