<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, proyecto_uoct.documentacion.VO.ClienteVO" errorPage="" %>
<%
ClienteVO cli= (ClienteVO) request.getAttribute("datoscli");
Integer ses_idtipousu=(Integer) request.getAttribute("ses_id_tipousu");
String mensaje= (String) request.getAttribute("mensaje");
%>
<html>
<head>
<title><%=cli.getNomCli() %> <%=cli.getApeCli() %></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="center">Datos de :<%=cli.getNomCli() %> <%= cli.getApeCli() %>
        <%if(mensaje!=null){
%>
      </h3>
      <h4 align="center"><b><%=mensaje %></b></h4>
      <div align="center">
        <%}%>
      </div></td>
  </tr>
  <tr>
    <td><div align="center">
        <table width="505" border="1" align="center">
          <tr>
            <td width="179" bgcolor="#ADD8E4"><div align="right"><strong>Nombre:</strong></div></td>
            <td width="310"><%= cli.getNomCli() %></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Apellido:</strong></div></td>
            <td> <%= cli.getApeCli() %></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Telefono:</strong></div></td>
            <td> <%

    if (cli.getFonoCli()!=null){
      out.print(cli.getFonoCli());
    }
    else{
      out.print("&nbsp;");
    }
      %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Celular:</strong></div></td>
            <td> <% if( cli.getCelCli()!=null){
      out.print(cli.getCelCli());
    }    else{
    out.print("&nbsp;");
    }
      %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Email:</strong></div></td>
            <td> <%if (cli.getEmailCli()!=null){
      out.print(cli.getEmailCli());
    }    else{
    out.print("&nbsp;");
    }
      %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Entidad a la que
                pertenece:</strong></div></td>
            <td> <%
   if( cli.getNomEnt()!=null){
     out.print("<a href=clienteAction.do?hacia=detalleEntExt&id_ent="+cli.getIdEnt()+">"+ cli.getNomEnt()+"</a>");
   }    else{
    out.print("&nbsp;");
    }
     %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Cargo:</strong></div></td>
            <td> <% if (cli.getCargo()!=null){
    out.print(cli.getCargo());
    } else{
    out.print("&nbsp;");
    }
    %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Comentario:</strong></div></td>
            <td> <% if(cli.getComentCli()!=null){
      out.print(cli.getComentCli());
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
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div><div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a> </div>
</body>
</html>
