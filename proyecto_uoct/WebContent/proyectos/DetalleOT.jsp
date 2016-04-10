<!DOCTYPE html>


<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page import= "java.util.List,java.util.Iterator,proyecto_uoct.proyecto.VO.DetalleProyectoVO" %>
<%@ page import= "proyecto_uoct.usuario.VO.UsuarioVO" %>
<%@ page import= "proyecto_uoct.proyecto.VO.DetalleOTVO" %>
<%@ page import= "proyecto_uoct.proyecto.VO.NLOVO,proyecto_uoct.documentacion.VO.DocumentoInVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
Integer regnlo= (Integer)request.getAttribute("regnlo");
Integer editarFechasOT= (Integer)request.getAttribute("editarFechasOT");
Integer idSes= (Integer)request.getAttribute("idSes");
DetalleProyectoVO detproy=(DetalleProyectoVO) request.getAttribute("detproy");
DetalleOTVO detot= (DetalleOTVO) request.getAttribute("detalleOT");


String mensaje=(String)request.getAttribute("mensaje");

%>



<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="Unidad Operativa de Control de Tránsito">
		<link rel="icon" href="img/favicon.ico">
		
		<title>Unidad Operativa de Control de Tránsito</title>
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
				 <table title="opcionesIniciativa">
          <tr>
<td>
<%if(regnlo.intValue()==1 && detproy.getIsActivo() && detproy.getIdEncargado().equals(idSes)){ %>
        <a href="proyectoAction.do?hacia=editarOT&idOT=<%=detot.getIdOT() %>&idProy=<%=detproy.getIdProy()%>"> Editar OT /</a>
<%} %>
</td>


<td>
<%if(regnlo.intValue()==1 && detproy.getIsActivo() && editarFechasOT.intValue()==1){ %>
        <a href="proyectoAction.do?hacia=editarFechasOT&idOT=<%=detot.getIdOT() %>&idProy=<%=detproy.getIdProy()%>"> Editar Fechas OT /</a>
<%} %>
</td>


<td>
<%if(regnlo.intValue()==1 && detproy.getIsActivo() && detproy.getIdEncargado().equals(idSes)){ %>

        <a href="proyectoAction.do?hacia=aRegNLOOT&idOT=<%=detot.getIdOT()%>"> Agregar LO / </a>
<%} %>
</td>
<td>
<%if(regnlo.intValue()==1 && detproy.getIsActivo() && detproy.getIdEncargado().equals(idSes)){ %>

        <a href="proyectoAction.do?hacia=adminNLOdelaOT&idOT=<%=detot.getIdOT()%>"> Admin. LO </a>
<%} %>
</td>


</tr>
        </table>
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Orden de Trabajo</h2>
						
 <%if(mensaje!=null){ out.println("<h3><font style=\"mensaje\" color=\"red\" >"+mensaje+"</font></h3>");} %>						
						
						
				 		<div class="box">
				 			<h4>Datos de la OT</h4>
				 			<form class="form-horizontal">
				 				<div class="form-group">
    								<label for="inputNombreOT" class="col-sm-4 control-label">Nombre de la OT</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputNombreOT" value="<%= detot.getNomOT() %>" disabled>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputDoc" class="col-sm-4 control-label">Documento de la OT</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><a href="javascript:Llamadalink('detalleDoc','&id_doc=<%= detot.getDocumento().getIdDoc()%>')"><%= detot.getDocumento().getTipoDoc()+"-"+detot.getDocumento().getCodDoc() %></a></p>
    								</div>
    							</div>
    							<div class="form-group">
    								<label class="col-sm-4 control-label">Estado de la OT</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><%= detot.getEstadoOT() %></p>
      							</div>
  								</div>
  								<div class="form-group">
    								<label class="col-sm-4 control-label">Iniciativa a la que pertenece</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><%= detot.getDetProy().getNomProy() %></p>
      							</div>
  								</div>
  								<div class="form-group">
    								<label class="col-sm-4 control-label">Ejecutor de la OT</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><%= detot.getCli().getNomCli()+" "+detot.getCli().getApeCli() %></p>
      							</div>
  								</div>
  								<div class="form-group">
    								<label class="col-sm-4 control-label">Plazo de ejecución</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><%if(detot.getPlazo()!=null && detot.getPlazo().intValue()!=0){
        out.print(detot.getPlazo());}else{out.print("no definido");} %></p>
      							</div>
  								</div>
  								<div class="form-group">
    								<label class="col-sm-4 control-label">Estado de pago</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><%if(detot.getEP()!=null && !detot.getEP().equals("")){
        out.print(detot.getEP());}else{out.print("&nbsp;");} %></p>
      							</div>
  								</div>
  								<div class="form-group">
    								<label for="selEquipo" class="col-sm-4 control-label">Encargados de la OT</label>
    								<div class="col-sm-8">
    									<select multiple="" id="selEquipo" class="form-control" disabled>
    										 <%

      List encargados= detot.getEncargados();
      if(encargados!=null)
      {
        Iterator ienc=encargados.iterator();
        while (ienc.hasNext()){
          UsuarioVO usu=(UsuarioVO) ienc.next();
          if(usu.getNombreUsu2()!=null){ 
              
            out.println("<option value=\""+usu.getIdUsu()+"\">"+usu.getNombreUsu()+" "+usu.getNombreUsu2()+" "+ usu.getApellUsu()+"</option>");
          }else{
            out.println("<option value=\""+usu.getIdUsu()+"\">"+usu.getNombreUsu()+" "+usu.getApellUsu()+"</option>");
          }
        }
      }
      %>
										</select>
    								</div>
    							</div>
				 			</form>
				 		</div>
				 		
				 		<div class="box boxpost">
				 			<h4>Fechas de la OT</h4>
				 			<form class="form-horizontal">
				 				<div class="form-group">
    								<label for="inputInicio" class="col-sm-4 control-label">Fecha de la OT</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputInicio" value="<%

      if (detot.getFechaOT()!=null){
         out.print(sdf.format(detot.getFechaOT()));}
        else{out.print("&nbsp;");
        }

       %>" disabled>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputVto" class="col-sm-4 control-label">Fecha de vencimiento</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputVto" value="<%
       if (detot.getVencimiento()!=null){
         out.print(sdf.format(detot.getVencimiento()));}
        else{out.print("&nbsp;");
        }


      %> " disabled>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputSus" class="col-sm-4 control-label">Fecha de suscripción</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputSus" value=" <% if (detot.getSuscrip()!=null){
          out.print(sdf.format(detot.getSuscrip()));}
          else{out.print("&nbsp;");
        } %> " disabled>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputFin" class="col-sm-4 control-label">Fecha de finalización de tareas</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputFin" value=" <% if (detot.getFinTareas()!=null){
            out.print(sdf.format(detot.getFinTareas()));}
        else{out.print("&nbsp;");
      } %> " disabled>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputAprobacion" class="col-sm-4 control-label">Aprobación</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputAprobacion" value="<%if (detot.getAprobacion()!=null){
            out.print(sdf.format(detot.getAprobacion()));}
        else{out.print("&nbsp;");
      } %>" disabled>
    								</div>
    							</div>
    							
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<a href="javascript:void(0)" class="botoVerde">Editar fechas OT</a>
      								</div>
  									</div>
    							</div>
  							</form>
						</div>
						
						
						
						
 <%
   List nlos=detot.getNLOs();
   if ( nlos!=null){
   request.setAttribute("nlos",nlos);
   %>
   <div class="box boxpost">
        <display:table name="nlos" id="n" pagesize="15" class="table table-striped table-bordered table-hover tablesorter" requestURI="proyectoAction.do">
        <display:column title="Fecha de la NLO"><%=sdf.format(((NLOVO)n).getFechaNLO())%> </display:column>

        <display:column title="Descripci&oacute;n NLO" property="str">
        </display:column>

        <display:column title="Documento asociado a la NLO">
        <a href="javascript:Llamadalink('?hacia=detalleDoc','&id_doc=<%=((NLOVO)n).getDocumento().getIdDoc()%>')"><%=((NLOVO)n).getDocumento().getTipoDoc()%>-<%=((NLOVO)n).getDocumento().getCodDoc()%></a> </display:column>


   <display:column title="Editar NLO"> <a href="javascript:Llamadalink2('?hacia=editarNLO','&idOT=<%=detot.getIdOT() %>&idNLO=<%=((NLOVO)n).getId()%>')">Editar
  NLO</a> </display:column>

		 <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
        <display:setProperty name="basic.empty.showtable" value="true"/> <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/> <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
        <display:setProperty name="export.csv" value="false"/> <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.rtf" value="false"/> <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.xml.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.csv.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/> </display:table>
        </div>
        <%}  %>

        					<div class="boxOpciones">
    							<div class="form-group">
    								<div class="col-sm-12 text-left">
    									<p><strong>No se encontraron elementos para mostrar</strong></p>
      								</div>
  								</div>
    						</div>
						</div>
				 			
				 				 		
					

			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	
	<script type="text/javascript">
	
	function Llamadalink(hacia, link) {
		
		link = link.replace('#', '');
		
		link = 'documentacion/documentoAction.do' + hacia + link ;

		
		LlamadaPagina(link);

												//clienteAction.do?hacia=detalleEntExt
	}
	
	function Llamadalink2(hacia, link) {
		
		link = link.replace('#', '');
	
		link = 'proyectos/proyectoAction.do' + hacia + link ;
		

		
		LlamadaPagina(link);

												//clienteAction.do?hacia=detalleEntExt
	}
	
	</script>

      <div class="container">
			<footer>
				<div class="row">
					<div class="col-sm-12">
						<p>Unidad Operativa de Control de Tránsito. <span id="pie"></span></p>
					</div>
				</div>
        	</footer>
		</div> <!-- /container -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
