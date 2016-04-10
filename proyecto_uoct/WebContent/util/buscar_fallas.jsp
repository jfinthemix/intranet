<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,java.util.Iterator"  errorPage=""%>
<%List lista_sistema = (List) request.getAttribute("lista_sistema");
  List lista_subsistema = (List) request.getAttribute("lista_subsistema");
  List lista_dispositivo = (List) request.getAttribute("lista_dispositivo");%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<html>
<head>
<title>Mantenedor de Fallas</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- script del calendario-->
<!-- American format mm/dd/yyyy -->
<script language="JavaScript" src="calendar2.js" type="text/javascript"></script><!-- Date only with year scrolling -->
<!-- American format mm/dd/yyyy -->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script><!-- Date only with year scrolling -->
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="fallasAction.do">


  <table width="95%" border="0" align="center">
    <tr>
      <td colspan="2"> <h3>Mantenedor de Fallas</h3></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"> <table width="60%" border="1" align="center">
          <tr>
            <td width="35%" bgcolor="#ADD8E4"> <div align="right"><strong>sistema</strong></div></td>
            <td width="65%"> <select name="sistema" size="1" id="sistema">
					<option>-------</option>
					<%
					Iterator i = lista_sistema.iterator();
					while (i.hasNext()) {
						proyecto_uoct.fallas.VO.FallaVO sistema = (proyecto_uoct.fallas.VO.FallaVO) i.next();
                                                //out.print("<option value=" + sistema.get_id_sistema() + ">" + sistema.get_nombre_sistema() + "</option>");
					}
        			%>


              </select> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>subsistema</strong></div></td>
            <td> <select name="subsistema" id="subsistema">
               		<option>-------</option>
					<%
					Iterator ii = lista_subsistema.iterator();
					while (ii.hasNext()) {
						proyecto_uoct.fallas.VO.FallaVO subsistema = (proyecto_uoct.fallas.VO.FallaVO) ii.next();
						//out.print("<option value=" + subsistema.get_id_subsistema() + ">" + subsistema.get_nombre_subsistema() + "</option>");
					}
					%>
              </select> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>dispositivo</strong></div></td>
            <td><select name="dispositivo" id="dispositivo">
               		<option>-------</option>
					<%
					Iterator iii = lista_dispositivo.iterator();
					while (iii.hasNext()) {
						proyecto_uoct.fallas.VO.FallaVO dispositivo = (proyecto_uoct.fallas.VO.FallaVO) iii.next();
						//out.println("<option value=" + dispositivo.get_id_dispositivo() + ">" + dispositivo.get_nombre_dispositivo() + "</option>");
					}
					%>
              </select></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>rango fecha de ingreso</strong></div></td>
            <td> <input name="fecha_ini" type="Text" value="" size="10" maxlength="10" readonly>
              <a href="javascript:cal1.popup();"> <img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha"></a>
			  &nbsp;-&nbsp;
              <input name="fecha_fin" type="Text" value="" size="10" maxlength="10" readonly>
              <a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha"></a>
            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>estado</strong></div></td>
            <td><select name="estado" size="1" id="select">
                <option value="2" selected>Todas</option>
                <option value="1">Iniciada</option>
                <option value="0">Cerrada</option>
              </select></td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td colspan="2"><div align="center">
          <table width="60%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><font face="Arial, Helvetica, sans-serif"><font size="2"> </font><font face="Arial, Helvetica, sans-serif"><font size="2">
                <input name="BotonNueva" type="submit" value="Nueva falla">
                </font></font><font size="2"> </font></font></td>
              <td><div align="right"><font face="Arial, Helvetica, sans-serif"><font face="Arial, Helvetica, sans-serif"><font size="2">
                  <input name="BotonBuscar" type="submit" value="Buscar fallas">
                  </font></font><font size="2"> </font></font></div></td>
            </tr>
          </table>
          <font face="Arial, Helvetica, sans-serif"><font size="2"> </font></font></div></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><table width="70%" border="1" align="center" cellpadding="0" cellspacing="0">
          <tr bgcolor="#ADD8E4">
            <td><div align="center"><b>n</b></div></td>
            <td bgcolor="#ADD8E4"><div align="center"><b>ingreso</b></div></td>
            <td><div align="center"><b>t&iacute;tulo</b></div></td>
            <td bgcolor="#ADD8E4"><div align="center"><b>estado</b></div></td>
            <td><div align="center"><strong>ver detalle</strong></div></td>
            <td><div align="center"><b>eliminar</b></div></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="center"><strong>1</strong></div></td>
            <td><div align="center">01-01-2008 18:00</div></td>
            <td><div align="center">Problemas de imagen</div></td>
            <td><div align="center">Cerrada</div></td>
            <td><div align="center"><img src="imagenes/lupa3.JPG" width="20" height="20" alt=""></div></td>
            <td><div align="center"> <a href="foroAction.do?hacia=buscar_falla_periodo&id_falla=<%=%>&id_sistema=<%=%>" onClick='return confirmaEliminacion()'>
                <img src="imagenes/eliminar.JPG" alt="" width="20" height="20" border="0"></a></div></td>
          </tr>
        </table></td>
    </tr>
  </table>
  </form>

<div align="right"><a href="../ayuda/foro.html" target="_blank">Ayuda</a></div>

</body>
</html>

