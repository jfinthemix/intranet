<%@page contentType="text/html; charset=iso-8859-1" language="java" import="proyecto_uoct.usuario.VO.UsuarioVO" errorPage=""%>
<%@page import="java.io.*,proyecto_uoct.common.util.UtilString,java.text.SimpleDateFormat,java.util.List,java.util.Iterator,proyecto_uoct.usuario.VO.InformeActividadesVO"%>
<%
  UsuarioVO usuario = (UsuarioVO) request.getAttribute("usuario");
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
  List informes = (List) request.getAttribute("informes");

  UtilString us=new UtilString();
  int cont = 0;
  File fotoFile = new File("foto.jpg");
  if(usuario.getFoto()!=null){
  try {
    DataOutputStream dostr = new DataOutputStream(new
    BufferedOutputStream(new FileOutputStream(
    fotoFile.getName())));
    dostr.write(usuario.getFoto());
    dostr.close();
  }
  catch (FileNotFoundException ex) {
    ex.printStackTrace();
  }}
%>
<html>
<head>
<title>Datos de Funcionario</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<meta http-equiv="Cache-Control" content="no-cache">

</head>
<body>
<table width="532" border="0">
  <tr>
    <td colspan="2"><h3>Datos de : <%= usuario.getNombreUsu() %>
        <%
  if (usuario.getNombreUsu2() != null) {
    out.print(usuario.getNombreUsu2());
  }
%>
        <%= usuario.getApellUsu() %></h3></td>
  </tr>
  <tr>
    <td width="402"><table width="400" border="1" align="left">
        <tr>
          <td width="106" bgcolor="#ADD8E4">
            <div align="right"><strong>Nombre:</strong>
            </div></td>
          <td width="278"><%= usuario.getNombreUsu() %> <%
      if (usuario.getNombreUsu2() != null) {
        out.print(usuario.getNombreUsu2());
      }
    %> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4">
            <div align="right"><strong>Apellidos</strong>
            </div></td>
          <td><%= usuario.getApellUsu() %> <%
      if (usuario.getApellUsu2() != null) {
        out.print(usuario.getApellUsu2());
      }
    %> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4">
            <div align="right"><strong>Tel&eacute;fono </strong>
            </div></td>
          <td> <%
      if (usuario.getTelefono().intValue() != 0) {
        out.print(usuario.getTelefono());
      }
      else {
        out.print("<font size=\"2\" face=\"Helvetica, sans-serif\"><em>teléfono no registrado</em></font>");
      }
    %> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4">
            <div align="right"><strong>Celular</strong> </div></td>
          <td> <%
      if (usuario.getCelular().intValue() != 0) {
        out.print(usuario.getCelular());
      }
      else {
        out.print("<font size=\"2\" face=\"Helvetica, sans-serif\"><em>celular no registrado</em></font> ");
      }
    %> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4">
            <div align="right"><strong>E-mail</strong> </div></td>
          <td> <%
      if (usuario.getEmail() != null) {
        out.print(usuario.getEmail());
      }
      else {
        out.print("<font size=\"2\" face=\"Helvetica, sans-serif\"><em>email no registrado</em></font> ");
      }
    %> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4">
            <div align="right"><strong>Direcci&oacute;n</strong>
            </div></td>
          <td> <%
      if (usuario.getDir() != null) {
        out.print(usuario.getDir());
      }
      else {
        out.print("<font size=\"2\" face=\"Helvetica, sans-serif\"><em>dirección no registrada</em></font> ");
      }
    %> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4">
            <div align="right"><strong>Cumplea&ntilde;os</strong>
            </div></td>
          <td> <%
      if (usuario.getCumpleanos() != null) {
        String c = us.formatoFecha(sdf.format(usuario.getCumpleanos()));
        out.print(c);
      }
      else {
        out.print("<font size=\"2\" face=\"Helvetica, sans-serif\"><em>cumpleaños no registrado</em></font> ");
      }
    %> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4">
            <div align="right"><strong>Area de Trabajo</strong>
            </div></td>
          <td><%= usuario.getArea() %> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4">
            <div align="right"><strong>Anexo telef&oacute;nico</strong>
            </div></td>
          <td> <%
      if (usuario.getAnexo() != null) {
        out.print(usuario.getAnexo());
      }
      else {
        out.print("<font size=\"2\" face=\"Helvetica, sans-serif\"><em>Anexo no registrado</em></font> ");
      }
    %> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4">
            <div align="right"><strong>Curr&iacute;culum</strong>
            </div></td>
          <td> <%if (usuario.getIsCurric()){ %> <a href="usuarioAction.do?hacia=descargarCurriculoUsu&idUsu=<%=usuario.getIdUsu()%>">Descargar</a>
            <% }else{out.print("&nbsp;");}%> </td>
        </tr>
      </table></td>
    <td width="120" valign="top"><img src="usuarioAction.do?hacia=getFotoUsu&idUsu=<%= usuario.getIdUsu()%>" width="120px" height="150px"  align="right" alt="fotografía del usuario"></td>
  </tr>
  <tr>
    <td colspan="2"> <div align="left">
        <%
if(informes!=null){
  Iterator i = informes.iterator();
  if (i.hasNext()) {
%>
        <table width="458" border="1" align="left">
          <tr>
            <td width="146" bgcolor="#C0C0C0">
              <div align="center"> <strong>Fecha del Informe</strong> </div></td>
            <td width="152" bgcolor="#C0C0C0">
              <div align="center"> <strong>Nombre del archivo</strong> </div></td>
            <td width="75" bgcolor="#C0C0C0">
              <div align="center"> <strong>Descargar</strong>
              </div></td>
          </tr>
          <%
    while (i.hasNext()) {
      InformeActividadesVO inf = (InformeActividadesVO) i.next();
  %>
          <tr bgcolor="<% if (cont==0) {out.print("#A6F7BA");cont=1;} else{out.print("#F7FBC4");cont=0;} %>">
            <td> <%out.print(sdf.format(inf.getFechaInfor()));      %> </td>
            <td><%= inf.getNomInfor() %> </td>
            <td> <form action="usuarioAction.do" method="POST">
                <input type="hidden" name="hacia" value="descargarInforme"/>
                <input type="hidden" name="idInf" value="<%= inf.getIdInfor()%>"/>
                <div align="center">
                  <input name="submit" type="submit" value="Descargar"/>
                </div>
              </form></td>
          </tr>
          <%}  %>
        </table>
        <%}}%>
      </div></td>
  </tr>
</table>

<hr>

  <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>
<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>

</body>
</html>
