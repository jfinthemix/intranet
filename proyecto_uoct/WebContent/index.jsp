<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<% Integer mensaje= (Integer) request.getAttribute("id_usu"); %>
<html>
<head>
<title>
Index
</title>
<link href="util/styla.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">
<h1>&nbsp; </h1>
<br/>

<h2><%
if (mensaje != null)
out.print(mensaje);
%></h2>
</body>
</html>
