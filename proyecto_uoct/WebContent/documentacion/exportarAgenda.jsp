<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List,java.util.Iterator,proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.documentacion.VO.DocumentodeListaVO" errorPage=""%>
<%@page import="java.text.SimpleDateFormat,java.util.List, java.util.Iterator, proyecto_uoct.documentacion.VO.ClienteVO"%>
<%
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
  List clientes = (List) request.getAttribute("clientes");
  int col=0;
%>
<html>
<head>
<title>Clientes de UOCT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%
  response.setContentType("application/x-excel "); // Multipart/Related--application/x-msdownload--application/octate*--application/x-excel
  response.setHeader("Content-Disposition", "attachment; filename=\"ClientesUOCT.xls\"");
%>
</head>
<body>
<h2 align="center">Clientes de UOCT</h2>
<table width="1438" border="1">
  <tr>
    <td width="124" bgcolor="#A6F7BA">
      <strong>Nombre</strong>
    </td>
    <td width="100" bgcolor="#A6F7BA">
      <strong>Apellido</strong>
    </td>
    <td width="66" bgcolor="#A6F7BA">
      <strong>Tel&eacute;fono</strong>
    </td>
    <td width="114" bgcolor="#A6F7BA">
      <strong>E-mail</strong>
    </td>
    <td width="84" bgcolor="#A6F7BA">
      <strong>Celular</strong>
    </td>
    <td width="111" bgcolor="#A6F7BA">
      <strong>Cargo</strong>
    </td>
    <td width="163" bgcolor="#A6F7BA">
      <strong>Comentario a cerca del Cliente</strong>
    </td>
    <td width="187" bgcolor="#F7FBC4">
      <strong>Nombre de la Empresa</strong>
    </td>
    <td width="94" bgcolor="#F7FBC4">
      <strong>Tel&eacute;fono de la Empresa</strong>
    </td>
    <td width="99" bgcolor="#F7FBC4">
      <strong>Direcci&oacute;n de la Empresa</strong>
    </td>
    <td width="226" bgcolor="#F7FBC4">
      <strong>Sitio Web</strong>
    </td>
  </tr>
<%
  if (clientes != null) {
    Iterator i = clientes.iterator();
    while (i.hasNext()) {
      ClienteVO cli = (ClienteVO) i.next();
%>
  <tr bgcolor="<%
  if (col==0){
    out.print("#F7FBC4");
    col=1;}
    else{
    out.print("#CCCCCC");
    col=0;
  }
  %>">
    <td><%= cli.getNomCli() %>    </td>
    <td><%=cli.getApeCli()%>    </td>
    <td>
    <%
      if (cli.getFonoCli() != null) {
        out.print(cli.getFonoCli());
      }
      else {
        out.print("&nbsp;");
      }
    %>
    </td>
    <td>
    <%
      if (cli.getEmailCli() != null) {
        out.print(cli.getEmailCli());
      }
      else {
        out.print("&nbsp;");
      }
    %>
    </td>
    <td>
    <%
      if (cli.getCelCli() != null) {
        out.print(cli.getCelCli());
      }
      else {
        out.print("&nbsp;");
      }
    %>
    </td>
    <td>
    <%
      if (cli.getCargo() != null) {
        out.print(cli.getCargo());
      }
      else {
        out.print("&nbsp;");
      }
    %>
    </td>
    <td>
    <%
      if (cli.getComentCli() != null) {
        out.print(cli.getComentCli());
      }
      else {
        out.print("&nbsp;");
      }
    %>
    </td>
    <td>
    <%
      if (cli.getNomEnt() != null) {
        out.print(cli.getNomEnt());
      }
      else {
        out.print("&nbsp;");
      }
    %>
    </td>
    <td>
    <%
      if (cli.getTelefonoEnt() != null) {
        out.print(cli.getTelefonoEnt());
      }
      else {
        out.print("&nbsp;");
      }
    %>
    </td>
    <td>
    <%
      if (cli.getDirEnt() != null) {
        out.print(cli.getDirEnt());
      }
      else {
        out.print("&nbsp;");
      }
    %>
    </td>
    <td>
    <%
      if (cli.getWebEnt() != null) {
        out.print(cli.getWebEnt());
      }
      else {
        out.print("&nbsp;");
      }
    %>
    </td>
  </tr>
<%
  }
      }
%>
</table>
</body>
</html>
