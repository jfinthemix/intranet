<%
String mensaje=(String)request.getAttribute("mensaje"); %>
<html>
<head>
<title>
Reservar código de Documento
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">
<table width="386" border="0">
  <tr>
    <td width="380"><h3>C&oacute;digo reservado:</h3></td>
  </tr>
  <tr>
    <td><h2><font color="red"><%=mensaje %></font></h2></td>
  </tr>
</table>
<hr>
<div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a> </div>
</body>
</html>
