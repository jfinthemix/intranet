<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.ventas.VO.CliVtaVO" %>
<%
List clientes=(List) request.getAttribute("clientes");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%
  response.setContentType("application/x-excel "); // Multipart/Related--application/x-msdownload--application/octate*--application/x-excel
  response.setHeader("Content-Disposition", "attachment; filename=\"ClientesVentas.xls\"");
%>
</head>
<body>
<title>
Agenda de Clientes de Ventas
</title>
</head>
<body bgcolor="#ffffff">
<table border="1">
<%if(clientes!=null){
%>
  <tr>
    <td>Nombre</td>
    <td>Rut</td>
    <td>Dirección</td>
    <td>Teléfono</td>
    <td>Giro</td>
    <td>Email</td>
    <td>Comentario</td>
  </tr>
<%Iterator ic=clientes.iterator();
while (ic.hasNext()){
  CliVtaVO cli=(CliVtaVO)ic.next();
  %>
  <tr>

    <td><%= cli.getNomCli() %></td>
    <td><%if(cli.getRut().intValue()!=0 && cli.getCodRutCli()!='\0'){
   out.print(cli.getRut()+"-"+cli.getCodRutCli());
   }else{
   out.print("&nbsp;");
   }
   %></td>
    <td><%
   if(cli.getDireccion()!=null){
   out.print(cli.getDireccion());
   }else{
   out.print("&nbsp;");
   }
   %></td>
    <td><%
   if(cli.getTelefono()!=null){
   out.print(cli.getTelefono());
   }else{
     out.print("&nbsp;");
   }
    %></td>
    <td><%
    if(cli.getGiro()!=null){
      out.print(cli.getGiro());
    }else{
      out.print("&nbsp;");
    }
    %></td>
    <td><%
   if(cli.getEmail()!=null){
   out.print(cli.getEmail());
   }else{
   out.print("&nbsp;");
   }
    %></td>
    <td><%
    if(cli.getComentario()!=null){
      out.print(cli.getComentario());
    }else{
      out.print("&nbsp;");
    }
    %></td>
  </tr>
<% }
  }%>
</table>
</body>
</html>
