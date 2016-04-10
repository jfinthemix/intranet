<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<% String mensaje= (String) request.getAttribute("mensaje"); %>
<html>
<head>
<title>
Mensaje
</title>
</head>
<body bgcolor="#ffffff">
<table width="324" border="1">
  <tr>
    <td width="314">
      <%
if (mensaje != null)
out.print(mensaje);
%>
    </td>
  </tr>
</table>
<br/>

<h3>&nbsp;</h3>
</body>
</html>
