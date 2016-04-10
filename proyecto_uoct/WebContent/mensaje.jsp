<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<% String mensaje= (String) request.getAttribute("mensaje"); %>
<html>
<head>

<title>
Mensaje
</title>

<link href="../util/styla.css" rel="stylesheet" type="text/css">

</head>
<body>
<br/>

<h2><%
if (mensaje != null)
out.print(mensaje);
%></h2>
</body>
</html>
