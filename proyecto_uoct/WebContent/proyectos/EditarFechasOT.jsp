<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page import= "java.util.List,java.util.Iterator" %>
<%@ page import= "proyecto_uoct.usuario.VO.UsuarioVO" %>
<%@ page import= "proyecto_uoct.proyecto.VO.DetalleOTVO" %>
<%@ page import= "proyecto_uoct.proyecto.VO.NLOVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
DetalleOTVO detot= (DetalleOTVO) request.getAttribute("detalleOT");
String mensaje=(String)request.getAttribute("mensaje");
%>
<html>
<head>
<title>
EditarFechasOT
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">

  <!-- European format dd-mm-yyyy -->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script>


<script type="text/javascript" language="JavaScript">

function validarFechas(formulario){

	if(formulario.fecha_ot.value==''){
	alert("Debe indicar la fecha de OT" );
	formulario.fecha_ot.focus();
	return false;

	}
	if(formulario.fecha_pejec.value==''){
	alert("Debe indicar la fecha de Vencimiento" );
	return true;
	}

	if(formulario.fecha_tconc.value!='' && formulario.fecha_susc.value==''){
	alert("Si la OT tiene fecha de finalización de tareas, también debe indicar la fecha de suscripción" );
	formulario.fecha_susc.focus();
	return true;
	}

	if( formulario.fecha_apro.value!='' &&(formulario.fecha_tconc.value=='' || formulario.fecha_susc.value=='')){
		if(formulario.fecha_susc.value==''){
		alert("si se indica la fecha de aprobación, se debe indicar la fecha de suscripción de la OT");
		formulario.fecha_susc.focus();
		return true;
		}

		if(formulario.fecha_tconc.value==''){
		alert("Si se indica la fecha de aprobación, se debe indicar la fecha de finalización de tareas");
		formulario.fecha_tconc.focus();
		return true;
		}
	}


	 formulario.submit();
}

</script>



</head>
<body bgcolor="#ffffff">
<table width="750" border="0">
  <tr>
    <td><h3>Fechas</h3></td>
  </tr>
  <tr>
    <td><h4>Iniciativa:<%=detot.getDetProy().getNomProy() %></h4></td>
  </tr>
  <tr>
    <td><h4>OT: <%=detot.getNomOT() %>
        <%if(mensaje!=null){ %>
      </h4>
      <div align="center"><strong><font color="red"><%=mensaje %></font></strong></div>
      <% }%>
    </td>
  </tr>
  <form action="proyectoAction.do" name="form1" method="POST">
  <tr>
      <td>
        <input type="hidden" name="hacia" value="actualizarFechas" />
          <input type="hidden" name="idOT" value="<%=detot.getIdOT() %>" />


          <table width="380" border="1" align="left">
          <tr>
            <td width="154" bgcolor="#ADD8E4"><div align="right"><strong>Fecha
                OT</strong></div></td>
            <td width="210"> <input name="fecha_ot" type="text" id="fecha_ot" size=12 readonly="readonly" value="<%
                      if (detot.getFechaOT()!=null){
         out.print(sdf.format(detot.getFechaOT()));}
           %>"> &nbsp; <a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
              </a></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Fecha de Vencimiento</strong></div></td>
            <td> <input type="text" name="fecha_pejec" id="fecha_pejec" size=12 readonly="readonly" value="<%
            if (detot.getVencimiento()!=null){
         out.print(sdf.format(detot.getVencimiento()));}
         %>"> &nbsp; <a href="javascript:cal2.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
              </a> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Fecha de Suscripci&oacute;n</strong></div></td>
            <td> <input type="text" name="fecha_susc" id="fecha_susc" size=12 readonly="readonly" value="<%

		    	    if (detot.getSuscrip()!=null){
        		    out.print(sdf.format(detot.getSuscrip()));}
		        %>"> &nbsp; <a href="javascript:cal3.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
              </a> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Fecha de finalizaci&oacute;n
                de tareas</strong></div></td>
            <td> <input type="text" name="fecha_tconc" id="fecha_tconc" size=12 readonly="readonly" value="<%

		          if (detot.getFinTareas()!=null){
        	      out.print(sdf.format(detot.getFinTareas()));}
                  %>"> &nbsp; <a href="javascript:cal4.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
              </a> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Aprobaci&oacute;n</strong></div></td>
            <td> <input type="text" name="fecha_apro" id="fecha_apro" size=12 readonly="readonly" value="<%

          if (detot.getAprobacion()!=null){
            out.print(sdf.format(detot.getAprobacion()));}
      %>"> &nbsp; <a href="javascript:cal5.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
              </a></td>
          </tr>
          <tr>
            <td colspan="2"> <div align="center">
                <input name="button" type="button" onClick="validarFechas(this.form);" value="Enviar"/>
              </div></td>
          </tr>
        </table>
		</td>
  </tr>
        </form>

</table>
<script language="JavaScript" type="text/javascript">

var cal1 = new calendar1(document.forms['form1'].elements['fecha_ot']);
cal1.year_scroll = true;
cal1.time_comp = false;


var cal2 = new calendar1(document.forms['form1'].elements['fecha_pejec']);
cal2.year_scroll = true;
cal2.time_comp = false;

var cal3 = new calendar1(document.forms['form1'].elements['fecha_susc']);
cal3.year_scroll = true;
cal3.time_comp = false;

var cal4 = new calendar1(document.forms['form1'].elements['fecha_tconc']);
cal4.year_scroll = true;
cal4.time_comp = false;

var cal5 = new calendar1(document.forms['form1'].elements['fecha_apro']);
cal5.year_scroll = true;
cal5.time_comp = false;
//-->
</script>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/iniciativas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
