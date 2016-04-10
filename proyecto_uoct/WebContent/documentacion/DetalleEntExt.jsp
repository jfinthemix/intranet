<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, proyecto_uoct.documentacion.VO.EntExtVO" errorPage="" %>
<%
EntExtVO entidad= (EntExtVO) request.getAttribute("datosEntidad");
%>
<html>
<head>
<title>
Detalle de Entidad Externa
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">
<table width="750" border="0">
  <tr>
    <td><h3 align="center">Datos de: <%=entidad.getNomEnt() %></h3></td>
  </tr>
  <tr>
    <td><div align="center">
        <table width="63%" border="1" align="center">
          <tr>
            <td width="19%" bgcolor="#ADD8E4"><div align="right"><strong>Nombre:</strong></div></td>
            <td width="81%"><%=entidad.getNomEnt() %></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Tel&eacute;fono:</strong></div></td>
            <td> <%
    if(entidad.getFonoEnt()!=null){
    out.print(entidad.getFonoEnt());}
        else{
    out.print("&nbsp;");
    }
     %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Direcci&oacute;n:</strong></div></td>
            <td> <%
    if(entidad.getDirEnt()!=null){
    out.print(entidad.getDirEnt());
    }    else{
    out.print("&nbsp;");
    }
      %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Sitio Web:</strong></div></td>
            <td> <%
    if (entidad.getWeb()!=null){
      out.print(entidad.getWeb());
    }    else{
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
</p>
</body>
</html>
