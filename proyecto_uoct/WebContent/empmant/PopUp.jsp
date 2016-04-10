<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,proyecto_uoct.EmpMant.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String nombre            = (String) request.getAttribute("nombre");
String direccion         = (String) request.getAttribute("direccion");
String telefono          = (String) request.getAttribute("telefono");
Integer vigencia         = (Integer) request.getAttribute("vigencia");
String tipo_dispositivo  = (String) request.getAttribute("tipo_dispositivo");
String mail_1            = (String) request.getAttribute("mail_1");
String tipo_dispositivo2 = (String) request.getAttribute("tipo_dispositivo2");
String mail_2            = (String) request.getAttribute("mail_2");

Integer uno  = new Integer(1);
Integer cero = new Integer(0);
%>
<html>
<head>
<title>Empresa mantenedora</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" text="#000000">
  <form name="form_popup" method="post" action="../empmant/empMantAction.do">
    <table width="85%" border="0" align="center">
      <tr>
        <td colspan="2">Empresa mantenedora</td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2">
          <table width="100%" border="1" align="center">
            <tr>
              <td width="30%" bgcolor="#ADD8E4"> <div align="right"><strong>empresa</strong></div></td>
              <td width="70%"><% out.print(nombre);%></td>
            </tr>
            <tr>
              <td width="30%" bgcolor="#ADD8E4"> <div align="right"><strong>direccion</strong></div></td>
              <td width="70%"><% out.print(direccion);%></td>
            </tr>
            <tr>
              <td width="30%" bgcolor="#ADD8E4"> <div align="right"><strong>telefono</strong></div></td>
              <td width="70%"><% out.print(telefono);%></td>
            </tr>
            <tr>
              <td width="30%" bgcolor="#ADD8E4"> <div align="right"><strong>vigencia</strong></div></td>
              <td width="70%"><%
              if(vigencia.equals(uno)){
                out.print("SI");
              }
              if(vigencia.equals(cero))
                out.print("NO");
              %></td>
            </tr>
            <tr>
              <td width="30%" bgcolor="#ADD8E4"> <div align="right"><strong>mail terreno</strong></div></td>
              <td width="70%"><% out.print(mail_1);%></td>
            </tr>
            <tr>
              <td width="30%" bgcolor="#ADD8E4"> <div align="right"><strong>mail sala</strong></div></td>
              <td width="70%"><% out.print(mail_2);%></td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </form>
</body>
</html>
