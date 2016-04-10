<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, proyecto_uoct.foro.VO.DetForoVO" errorPage=""%>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
DetForoVO detforo = (DetForoVO) request.getAttribute("detforo");
Integer id_foro= (Integer)request.getAttribute("id_foro");
%>
<html>
<head>
<title>Postear</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>


</head>
<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="center">Ingresar post al Foro</h3></td>
  </tr>
  <tr>
    <td><h4 align="left"><strong>Tema</strong> : <%=detforo.getTemaForo() %></h4></td>
  </tr>
  <tr>
    <td><h4 align="left"><strong>Foro</strong> : <%=detforo.getTitForo() %> </h4></td>
  </tr>
  <tr>
    <td><div align="left"><strong>Fecha de inicio</strong> : <%= sdf.format(detforo.getFechaIniForo()) %> </div></td>
  </tr>
  <form action="foroAction.do" method="POST" name="postearForm">
    <tr>
      <td> <input type="hidden" name="hacia" value="ingresarPost" /> <input type="hidden" name="id_foro" value="<%=id_foro%>" />
        <table width="491" border="1" align="center">
          <tr>
            <td width="80" bgcolor="#A6F7BA"> <strong>Comentario</strong> </td>
            <td width="395">
              <div align="center">
                <textarea name="comentario" cols="60" rows="6" wrap="virtual"></textarea>
              </div></td>
          </tr>
        </table>
        <div align="center"></div></td>
    </tr>
    <tr>
      <td><div align="center">
          <input type="submit" name="Submit" value="Guardar">
        </div></td>
    </tr>
  </form>
</table>
        <script language="JavaScript" type="text/javascript">
      var frmvalidator  = new Validator("postearForm");
      frmvalidator.addValidation("comentario","req","Debe ingresar un comentario");
      frmvalidator.addValidation("comentario","maxlen=1000","Comentario no puede superar los 1000 caracteres");

    </script>

<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/foro.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
