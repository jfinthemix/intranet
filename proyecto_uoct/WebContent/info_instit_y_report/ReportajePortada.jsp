<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,proyecto_uoct.infoyrep.VO.ReportajeVO" errorPage="" %>
<%
ReportajeVO reportaje= (ReportajeVO) request.getAttribute("reportaje");
reportaje.setDescRep(reportaje.getDescRep().replaceAll("\r\n",
                    "<br>"));


%>
<html>
<head>
<title><%=reportaje.getTitRep() %></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-cache">


<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>
<table  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="14" height="20" background="../util/img/esquinasuperiorder.jpg">&nbsp;</td>
    <td width="540" background="../util/img/horizontalSup.jpg"><strong></strong></td>
    <td width="21" background="../util/img/esquinasuperiorIzq.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td background="../util/img/verticalIzq.jpg">&nbsp;</td>
    <td><strong><font size="2"><%= reportaje.getTitRep() %></font></strong></td>
    <td background="../util/img/verticalDer.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td background="../util/img/verticalIzq.jpg"><div align="center"></div></td>
    <td><br /><div align="center">
        <img src="reportAction.do?hacia=getFoto&idRep=<%=reportaje.getIdRep()%>" width="250" height="200">
        </div>
      </td>
    <td background="../util/img/verticalDer.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td background="../util/img/verticalIzq.jpg"> <div align="justify"> </div></td>
    <td align="justify"><div align="justify"><br><%= reportaje.getDescRep()  %></div></td>
    <td background="../util/img/verticalDer.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="19" background="../util/img/esquinainferiorDer.jpg">&nbsp;</td>
    <td background="../util/img/horizontalInf.jpg">&nbsp;</td>
    <td background="../util/img/esquinainferiorIzq.jpg">&nbsp;</td>
  </tr>
</table>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/portada.html" target="_blank">Ayuda</a>
  </div></body>
</html>
