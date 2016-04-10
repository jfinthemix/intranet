<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,proyecto_uoct.infoyrep.VO.ReportajeVO" errorPage="" %>
<%@page import="proyecto_uoct.documentacion.VO.TipoDocVO,java.util.List,java.util.Iterator" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List tsl=(List) request.getAttribute("tiposSalientes");
ReportajeVO reportaje_izq= (ReportajeVO) request.getAttribute("reportaje_izq");
ReportajeVO reportaje_der= (ReportajeVO) request.getAttribute("reportaje_der");
List lista= (List) request.getAttribute("listaReps");


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
<title>Portada-UOCT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" >
<meta http-equiv="Cache-Control" content="no-cache">
</head>

<body>
	<h2>Reportajes</h2>
						
				 		<div class="box boxpost noticia">
				 			<h4><%=reportaje_izq.getTitRep()%></h4>
				 			
				 			<div class="clearfix">
    							<div class="col-md-5 imgNoticia">
    								<img src="info_instit_y_report/reportAction.do?hacia=getFoto&idRep=<%=reportaje_izq.getIdRep() %>" />
    							</div>
    							<div class="col-md-7 resNoticia">
    								<p><%=desc_izq %></p>

          					</div>
    						</div>
          				
    						<div class="boxOpciones">
    							<a href="javascript:void(0)" OnClick="LlamadaPagina('info_instit_y_report/reportAction.do?hacia=detReportaje&idRep=<%=reportaje_izq.getIdRep() %>')">Leer reportaje completo &raquo;</a>
    						</div>
  						</div>
  						
  						<div class="box boxpost noticia">
				 			<h4><%=reportaje_der.getTitRep()%>></h4>
				 			
				 			<div class="clearfix">
    							<div class="col-md-5 imgNoticia">
    								<img src="info_instit_y_report/reportAction.do?hacia=getFoto&idRep=<%=reportaje_der.getIdRep() %>" />
    							</div>
    							<div class="col-md-7 resNoticia">
    								<p><%=desc_der%></p>
          					</div>
    						</div>
          				
    						<div class="boxOpciones">
    							<a href="javascript:void(0)" OnClick="LlamadaPagina('info_instit_y_report/reportAction.do?hacia=detReportaje&idRep=<%=reportaje_der.getIdRep() %>')">Leer reportaje completo &raquo;</a>
    						</div>
  						</div>	
							
							
						<div class="verMas">
							<a OnClick="LlamadaPagina('info_instit_y_report/reportAction.do?hacia=reportajesAnteriores')" href="javascript:void(0)"><span class="glyphicon glyphicon-folder-open"></span> Reportajes anteriores</a>
						</div>
				 		
		</body>
</html>
