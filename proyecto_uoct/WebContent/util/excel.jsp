<html>
<head>
<title>excel</title>
<%
  response.setContentType("application/x-excel "); // Multipart/Related--application/x-msdownload--application/octate*--application/x-excel
  response.setHeader("Content-Disposition", "attachment; filename=\"esunexcel\"");
%>
</head>
<body bgcolor="#ffffff">
<h2>Tabla desde Intranet </h2>
<table>
  <tr>
    <td>hola</td>
    <td>chau</td>
  </tr>
  <tr>
    <td>hola2</td>
    <td>chau2</td>
  </tr>
</table>
</body>
</html>
