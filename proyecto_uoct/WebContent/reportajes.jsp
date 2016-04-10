<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,proyecto_uoct.infoyrep.VO.ReportajeVO" errorPage="" %>
<%@page import="proyecto_uoct.documentacion.VO.TipoDocVO,java.util.List,java.util.Iterator,java.util.LinkedList" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
ReportajeVO reportaje_izq= (ReportajeVO) request.getAttribute("reportaje_izq");
ReportajeVO reportaje_der= (ReportajeVO) request.getAttribute("reportaje_der");



String mensaje=(String) request.getAttribute("mensaje");


String desc_izq=reportaje_izq.getDescRep();
if (desc_izq.length()>=550){
desc_izq= desc_izq.substring(0,550)+"...";
desc_izq=desc_izq.replaceAll("\r\n","<br>");
}
String desc_der=reportaje_der.getDescRep();

if (desc_der.length()>=550){
desc_der= desc_der.substring(0,550)+"...";
desc_der=desc_der.replaceAll("\r\n","<br>");
}

%>
<html>
<head>
<title>Home-UOCT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" >
<meta http-equiv="Cache-Control" content="no-cache">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<link href="util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript">

function reservar(form){
var id=rsv_form.idTipoDoc.options[rsv_form.idTipoDoc.selectedIndex].value;
var enlace="documentacion/documentoAction.do?hacia=aReservarDoc&idTipoDoc="+id;
popUp(enlace,this.target,"width=400,height=300, scrollbars=1");
}
var res=null;
function popUp(href, target, features) {
  res = window.open(href, target, features);
  res.window.focus();
}
</script>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>



<style type="text/css">
#posi{
	position:absolute;
	top:0px;
	left:0px;

}


</style>



</head>

<body>
  <div id="posi">
  <marquee border="0" align="left" scrollamount="2"  scrolldelay="25" behavior="scroll"  width="560" height="18">
<strong><em><font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=mensaje%></font></em></strong>
</marquee>
<br>

<table border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td> <table border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="18" height="18" background="util/img/esquinasuperiorder_2.jpg">&nbsp;</td>
          <td width="550"  valign="" background="util/img/horizontalSup_2.jpg">&nbsp;</td>
          <td width="20" background="util/img/esquinasuperiorIzq_2.jpg">&nbsp;</td>
        </tr>
        <tr>
          <td background="util/img/verticalIzq_2.jpg">&nbsp;</td>
          <td>
		  <table border="0">
              <tr valign="top">
                <td height="" colspan="2"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="info_instit_y_report/reportAction.do?hacia=detReportaje&idRep=<%=reportaje_izq.getIdRep()%>"><strong><%=reportaje_izq.getTitRep() %></strong></a></font></td>
              </tr>
              <tr valign="top">
                <td width="160" ><a href="info_instit_y_report/reportAction.do?hacia=detReportaje&idRep=<%=reportaje_izq.getIdRep()%>"><img src="info_instit_y_report/reportAction.do?hacia=getFoto&idRep=<%=reportaje_izq.getIdRep()%>" alt="click para ver el detalle del reportaje" width="150" height="120" border="0" /></a></td>
                <td width="353"><div align="justify"><font size="1" face="Verdana, Arial, Helvetica, sans-serif"><%= desc_izq %><font size="1"><a href="info_instit_y_report/reportAction.do?hacia=detReportaje&idRep=<%=reportaje_izq.getIdRep()%>">(ver
                    m&aacute;s)</a></font></font></div></td>
              </tr>
            </table></td>
          <td background="util/img/verticalDer_2.jpg">&nbsp;</td>
        </tr>
        <tr>
          <td width="18" height="20" background="util/img/esquinainferiorDer_2.jpg">&nbsp;</td>
          <td background="util/img/horizontalInf_2.jpg">&nbsp;</td>
          <td background="util/img/esquinainferiorIzq_2.jpg">&nbsp;</td>
        </tr>
      </table></td>

  </tr>
  <tr>
    <td>&nbsp;</td>

  </tr>
  <tr>
    <td colspan="2"><table border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="18" height="18" background="util/img/esquinasuperiorder_2.jpg">&nbsp;</td>
          <td width="550" valign="" background="util/img/horizontalSup_2.jpg">&nbsp;</td>
          <td width="20" background="util/img/esquinasuperiorIzq_2.jpg">&nbsp;</td>
        </tr>
        <tr>
          <td background="util/img/verticalIzq_2.jpg">&nbsp;</td>
          <td valign="top"><table border="0">
              <tr valign="top">
                <td colspan="2"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><a href="info_instit_y_report/reportAction.do?hacia=detReportaje&idRep=<%=reportaje_der.getIdRep()%>"><strong><%= reportaje_der.getTitRep() %></strong></a></font></td>
              </tr>
              <tr valign="top">
                <td width="160"><a href="info_instit_y_report/reportAction.do?hacia=detReportaje&idRep=<%=reportaje_der.getIdRep()%>"><img src="info_instit_y_report/reportAction.do?hacia=getFoto&idRep=<%=reportaje_der.getIdRep()%>" alt="click para ver el detalle del reportaje" width="150" height="120" border="0" /></a></td>
                <td width="353"><div align="justify"><font size="1" face="Verdana, Arial, Helvetica, sans-serif"><%= desc_der %><font size="1"><a href="info_instit_y_report/reportAction.do?hacia=detReportaje&idRep=<%=reportaje_der.getIdRep()%>">(ver
                    m&aacute;s)</a></font></font></div></td>
              </tr>
            </table></td>
          <td background="util/img/verticalDer_2.jpg">&nbsp;</td>
        </tr>
        <tr>
          <td width="18" height="20" background="util/img/esquinainferiorDer_2.jpg">&nbsp;</td>
          <td background="util/img/horizontalInf_2.jpg">&nbsp;</td>
          <td width="20" background="util/img/esquinainferiorIzq_2.jpg">&nbsp;</td>
        </tr>
      </table></td>
  </tr>
</table>

<br />
<div style="posi" align="left"><a href="index2Action.do?accion=reportajesAnteriores">Reportajes Anteriores</a></div>
</div>


</body>
</html>




<div class="col-sm-6 desarrollo">
					
						<h2>Reportajes</h2>
						
				 		<div class="box boxpost noticia">
				 			<h4></h4>
				 			
				 			<div class="clearfix">
    							<div class="col-md-5 imgNoticia">
    								<img src="img/demo_noticia1.jpg" />
    							</div>
    							<div class="col-md-7 resNoticia">
    								<p>Con el firme propósito de mejorar la conectividad de los chilenos y chilenas, asumió el Ministro de Transportes y Telecomunicaciones, Andrés Gómez-Lobo Echenique.</p>
          						<p>Ingeniero comercial de la Pontificia Universidad Católica de Chile, Doctor en Economía y Master of Science en Economía de los Recursos Naturales y el Medio Ambiente de la University College London, Gómez-Lobo tiene una amplia experiencia en el sector, destacando su rol como Jefe de Asesores en el Ministerio de Transportes y Telecomunicaciones y Director Metro de Santiago entre 2008...</p>
          					</div>
    						</div>
          				
    						<div class="boxOpciones">
    							<a href="javascript:void(0)">Leer reportaje completo &raquo;</a>
    						</div>
  						</div>
  						
  						<div class="box boxpost noticia">
				 			<h4>Cristián Bowen inicia sus funciones como Subsecretario de Transportes</h4>
				 			
				 			<div class="clearfix">
    							<div class="col-md-5 imgNoticia">
    								<img src="img/demo_noticia2.jpg" />
    							</div>
    							<div class="col-md-7 resNoticia">
    								<p>Cristián Bowen (DC) es ingeniero y economista de la Pontificia Universidad Católica y máster en Administración Pública de la Universidad de Harvard, sus áreas de interés son las Políticas Públicas, Planificación y Liderazgo Estratégico.</p>
    								<p>Durante 2005 Fue Director Ejecutivo de la Corporación Construyendo Futuro.</p>
    								<p>En 2006 y 2007 se desempeñó como Asesor del Ministerio de Transportes y Telecomunicaciones.</p>
    								<p>Trabajó como socio de Neoner, Empresa de Energías Renovables no Convencionales, como miembro del Directorio y asesor en materias...</p>
          					</div>
    						</div>
          				
    						<div class="boxOpciones">
    							<a href="javascript:void(0)">Leer reportaje completo &raquo;</a>
    						</div>
  						</div>	
							
							
						<div class="verMas">
							<a href="javascript:void(0)"><span class="glyphicon glyphicon-folder-open"></span> Reportajes anteriores</a>
						</div>
				 		
					
					</div>


