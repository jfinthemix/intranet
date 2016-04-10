<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@page import="proyecto_uoct.ventas.VO.CliVtaVO" %>
<%
CliVtaVO cli=(CliVtaVO) request.getAttribute("cliente");

%>
<html>
<head>
<title>Detalle de cliente</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="750" border="0">
  <tr> 
    <td><h3 align="left">Datos de :<%= cli.getNomCli() %></h3></td>
  </tr>
  <tr> 
    <td><div align="left"> 
        <table width="323" border="1" align="left">
          <tr> 
            <td width="81" bgcolor="#ADD8E4"><div align="right"><strong>Nombre:</strong></div></td>
            <td width="226"><%= cli.getNomCli() %></td>
          </tr>
          <tr> 
            <td width="81" bgcolor="#ADD8E4"><div align="right"><strong>RUT:</strong></div></td>
            <td width="226"> <%
   if(cli.getRut().intValue()!=0 && cli.getCodRutCli()!='\0'){
   out.print(cli.getRut()+"-"+cli.getCodRutCli());
   }else{
   out.print("&nbsp;");
   }
   %> </td>
          </tr>
          <tr> 
            <td width="81" bgcolor="#ADD8E4"><div align="right"><strong>Dirección:</strong></div></td>
            <td width="226"> <%
   if(cli.getDireccion()!=null){
   out.print(cli.getDireccion());
   }else{
   out.print("&nbsp;");
   }
   %> </td>
          </tr>
          <tr> 
            <td bgcolor="#ADD8E4"><div align="right"><strong>Telefono:</strong></div></td>
            <td> <%
   if(cli.getTelefono()!=null){
   out.print(cli.getTelefono());
   }else{
     out.print("&nbsp;");
   }
    %> </td>
          </tr>
          <tr> 
            <td bgcolor="#ADD8E4"><div align="right"><strong>Email:</strong></div></td>
            <td> <%
   if(cli.getEmail()!=null){
   out.print(cli.getEmail());
   }else{
   out.print("&nbsp;");
   }
    %> </td>
          </tr>
          <tr> 
            <td bgcolor="#ADD8E4"><div align="right"><strong>Giro:</strong></div></td>
            <td> <%
    if(cli.getGiro()!=null){
      out.print(cli.getGiro());
    }else{
      out.print("&nbsp;");
    }
    %> </td>
          </tr>
          <tr> 
            <td bgcolor="#ADD8E4"><div align="right"><strong>Contactos:</strong></div></td>
            <td> <%
    if(cli.getContactos()!=null){
      out.print(cli.getContactos());
    }else{
      out.print("&nbsp;");
    }
    %> </td>
          </tr>
          <tr> 
            <td bgcolor="#ADD8E4"><div align="right"><strong>Comentario:</strong></div></td>
            <td> <%
    if(cli.getComentario()!=null){
      out.print(cli.getComentario());
    }else{
      out.print("&nbsp;");
    }
    %> </td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a> 
  </div>
</body>
</html>

