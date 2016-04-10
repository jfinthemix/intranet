<!DOCTYPE html>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="proyecto_uoct.proyecto.VO.DetalleProyectoVO,java.util.List,java.util.Iterator" errorPage="" %>
<%@ page import="proyecto_uoct.usuario.VO.UsuarioVO" %>
<%@ page import="proyecto_uoct.proyecto.VO.DocumentodeListaProyVO,proyecto_uoct.proyecto.VO.OTdeListaVO"%>
<%@ page import="proyecto_uoct.proyecto.VO.NLOdeListaVO,proyecto_uoct.documentacion.VO.DocumentoInVO" %>
<%@ page import="java.text.SimpleDateFormat,proyecto_uoct.common.util.UtilString" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
Integer edini = (Integer)request.getAttribute("edini");
Integer idSes = (Integer) request.getAttribute("idSes");
Integer regnlo=(Integer)request.getAttribute("regnlo");
Integer regot=(Integer)request.getAttribute("regot");
DetalleProyectoVO detproy= (DetalleProyectoVO) request.getAttribute("detalleProy");

UtilString util= new UtilString();

String mensaje=(String) request.getAttribute("mensaje");


List enc= detproy.getEquipo();



  if (enc !=null){
    Iterator ienc = enc.iterator();


    while (ienc.hasNext()){
      UsuarioVO u = (UsuarioVO) ienc.next();
      if(u.getIdUsu().equals(idSes)){
      detproy.setEsDelEquipo(true);
      }
    }
  }




%>


<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="jfanasco" >
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
	
	
 <table>
    <tr>

      <td>
        <table title="opcionesIniciativa">
          <tr><td>        <%if(edini.intValue()==1 && detproy.getIsActivo()&& detproy.getEsDelEquipo() && detproy.getIdEncargado().equals(idSes)){ %>

        <a href="proyectoAction.do?hacia=editarIni&idProy=<%=detproy.getIdProy()%>"> Editar Datos de la Iniciativa / </a>
<%} %>
</td><td><%if(edini.intValue()==1 && detproy.getIsActivo()&& detproy.getEsDelEquipo() && detproy.getIdEncargado().equals(idSes)){ %>

        <a href="proyectoAction.do?hacia=adminDocsIni&idProy=<%=detproy.getIdProy()%>&nomProy=<%=detproy.getNomProy()%>"> Admin. Documentos Anexos /</a>
<%} %></td>
<td>
<%if(regot.intValue()==1 && detproy.getIsActivo()&& detproy.getEsDelEquipo() && detproy.getIdEncargado().equals(idSes)){ %>

        <a href="proyectoAction.do?hacia=adminOTs&idProy=<%=detproy.getIdProy()%>"> Admin. OT /</a>
<%} %>
</td>
<td>
<%if(regnlo.intValue()==1 && detproy.getIsActivo()&& detproy.getEsDelEquipo() && detproy.getIdEncargado().equals(idSes)){ %>

        <a href="proyectoAction.do?hacia=adminNLOdelaIni&idProy=<%=detproy.getIdProy()%>"> Admin. LO de la iniciativa </a>
<%} %>
</td>

</tr>
        </table>
      </td>
    </tr>
  </table>
	
	
	
	
	
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
					<div class="col-sm-6 desarrollo">
					
						<h2><small>Iniciativa:</small><br/> <%=detproy.getNomProy() %></h2>
<br>

        <%if(mensaje!=null){ out.println("<h3><font style=\"mensaje\" color=\"red\" >"+mensaje+"</font></h3>");} %>
						
						
						
				 		<div class="box boxpost">
				 			<h4>Datos de Iniciativa</h4>
				 			<form class="form-horizontal">
				 				<div class="form-group">
    								<label for="inputInicio" class="col-sm-4 control-label">Fecha de inicio</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputInicio" value="<%= sdf.format(detproy.getFechaProy()) %>" disabled>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="selectEncargado" class="col-sm-4 control-label">Encargado</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><%= detproy.getEncargado() %></p>
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="selEquipo" class="col-sm-4 control-label">Equipo de trabajo</label>
    								<div class="col-sm-8">
    									<select multiple="" id="selEquipo" class="form-control" disabled>
    										
<%
  if (enc !=null){
    Iterator ienc = enc.iterator();

    while (ienc.hasNext()){
      UsuarioVO u = (UsuarioVO) ienc.next();
      if(u.getNombreUsu2()!=null){
        out.print("<option value=\""+ "1"+ "\">"+ u.getNombreUsu()+" "+u.getNombreUsu2()+" "+u.getApellUsu()+"</option>");
      }else{
        out.print("<option value=\"" + "1" +"\">"+ u.getNombreUsu()+" "+u.getApellUsu()+"</option>");
      }
    }
  }

 %>    										
    										
										</select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputEjecutor" class="col-sm-4 control-label">Ejecutor</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"> <%=detproy.getNomCliente() %></p>
      							</div>
  								</div>
    							<div class="form-group">
    								<label for="txtDescripcion" class="col-sm-4 control-label">Descripción de la Iniciativa</label>
    								<div class="col-sm-8">
      								<textarea class="form-control" id="txtDescripcion" rows="4" disabled><%
   if (detproy.getDescripcion()!=null && !detproy.getDescripcion().equals("")){
   out.println(detproy.getDescripcion());
   }
   else{out.println("&nbsp;");}
    %></textarea>
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="inputFondos" class="col-sm-4 control-label">Fondos totales</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputFondos" value="<%
   if (detproy.getFondosTotales().intValue()!=0){
   out.println("$"+ util.formateaPrecioPesos(detproy.getFondosTotales().toString()));
   }
   else{out.println("&nbsp;");}
    %> " disabled>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="txtAnuales" class="col-sm-4 control-label">Fondos anuales</label>
    								<div class="col-sm-8">
    									<textarea class="form-control" id="txtAnuales" rows="4" disabled><%
   if (detproy.getFondosAnuales()!=null){
   out.println(detproy.getFondosAnuales());
   }
   else{out.println("&nbsp;");}
    %> </textarea>
    								</div>
    							</div>
    							<div class="form-group">
    								<label class="col-sm-4 control-label">Estado de la iniciativa</label>
    								<div class="col-sm-8">
      								<p class="form-control-static">
      								<%
   if (detproy.getIsActivo()){
   out.println("Activa");
   }
   else{out.println("Cerrada");}
    %></p>
      							</div>
  								</div>
  								<div class="form-group">
    								<label for="inputFin" class="col-sm-4 control-label">Fecha de finalización</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputFin" value="<%
    if (detproy.getFechaFin()!=null){
   out.println(sdf.format(detproy.getFechaFin()));
   }
   else{out.println("---");}
    %>" disabled>
    								</div>
    							</div>
    							<div class="form-group">
    								<label class="col-sm-4 control-label">Documentos de la iniciativa</label>
    								<div class="col-sm-8">
    								
 <%
if(detproy.getDocumentos().size()!=0){
   request.setAttribute("docs",detproy.getDocumentos());
%>
          <tr>
            <td height="22" colspan="2"> <div align="center"> <display:table name="docs" class="table table-striped table-bordered table-hover" id="dt" >
                <display:column title="Documentos de la Iniciativa" property="str" href="javascript:Llamadalink2('?hacia=descargarDocIni','#')" paramId="idDoc" paramProperty="id">
                </display:column> </display:table> </div></td>
          </tr>
          <% }%>    								
    								
    								
      								
    								</div>

    							<!--  <div class="boxOpciones"> -->
    								<div class="form-group">
    									<div class="col-sm-12">
    									
    									
    									<%
    									
    // muestra estos botones solamente si el usuario de la sesión:
   
	    //tiene permiso para editar la iniciativa,
	    //el proyecto está activo
    //es parte del equipo de la iniciativa
   // es el encargado de la iniciativa.

  if (edini.intValue() == 1 && detproy.getIsActivo() && detproy.getEsDelEquipo()
&& detproy.getIdEncargado().equals(idSes)) {	
%>

        <a class="botoGris" href="javascript:Llamadalink2('?hacia=adminDocsIni','&idProy=<%=detproy.getIdProy()%>&nomProy=<%=detproy.getNomProy()%>')">Admin. Docs </a>

        <a class="botoGris" href="javascript:Llamadalink2('?hacia=editarIni','&idProy=<%=detproy.getIdProy()%>')">Edit. de la Inic.</a>
        
        <a class="botoGris" href="javascript:Llamadalink2('?hacia=adminOTs','&idProy=<%=detproy.getIdProy()%>')">Admin. OT</a>
        
        <a class="botoGris" href="javascript:Llamadalink2('?hacia=adminNLOdelaIni','&idProy=<%=detproy.getIdProy()%>')">Admin. LO de la Ini.</a>
        
        
<%} %>

<script>

function Llamadalink2(hacia, link) {
	
	link = link.replace('#', '');
	
	link='proyectos/proyectoAction.do'+hacia+link;
	
	LlamadaPagina(link);

}


		
function Llamadalink3(hacia, link) {
	
	link = link.replace('#', '');
	
	link='documentacion/documentoAction.do'+hacia+link;
	
	LlamadaPagina(link);

}
		

</script>

      								    							</div>
  									</div>
    							</div>
  							</form>
						
						
						
						
<%
 List ots=(List) detproy.getOTs();
 if(ots.size()!=0){
 request.setAttribute("otss",ots);
 %> 
   <display:table id="o" name="otss" pagesize="20" requestURI="proyectoAction.do" class="table table-striped table-bordered table-hover">
          <display:caption>Ordenes de Trabajo </display:caption>

          <display:column title="Nombre de la OT" property="str"   href="javascript:Llamadalink2('?hacia=detalleOT','#')" paramId="idOT" paramProperty="id">
          </display:column>
          <display:column title="Fecha Vencimiento" ><%= sdf.format(((OTdeListaVO)o).getFechaVencimiento()) %> </display:column>

          <display:column title="Estado" property="estadoOT">
          </display:column>

          <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
          <display:setProperty name="basic.empty.showtable" value="true"/>
          <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
          <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
          <display:setProperty name="paging.banner.placement" value="bottom"/>
          <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
          <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
          <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
          <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
          <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
          <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
          <display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
          <display:setProperty name="export.csv" value="false"/>
          <display:setProperty name="export.xml" value="false"/>
          <display:setProperty name="export.rtf" value="false"/>
          <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
          <display:setProperty name="export.pdf.filename" value="registroDocumentacion"/>
          <display:setProperty name="export.xml.filename" value="registroDocumentacion"/>
          <display:setProperty name="export.csv.filename" value="registroDocumentacion"/>
          <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
          <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
          <display:setProperty name="export.amount" value="list"/>

        </display:table>
        
        <%}%>				
     </div>   
        <br>
   
        
        <%
if(detproy.getNLOs().size()!=0){
request.setAttribute("nsot",detproy.getNLOs());

%>
<br>
<div class="box boxpost">
<div align="center">
  <display:table class="table table-striped table-bordered table-hover" name="nsot" id="ns" pagesize="20" requestURI="proyectoAction.do">
  <display:caption>NLO de la Iniciativa
  </display:caption>

  <display:column title="Fecha de la NLO"><%=sdf.format(((NLOdeListaVO)ns).getFechaNLO())%>
  </display:column>

  <display:column title="Descripción de la NLO" property="str">
  </display:column>

  <display:column title="Documento asociado a la NLO">
    <a href="javascript:Llamadalink3('?hacia=detalleDoc','&id_doc=<%=((NLOdeListaVO)ns).getDoc().getIdDoc()%>')"><%=((NLOdeListaVO)ns).getDoc().getTipoDoc()%>-<%=((NLOdeListaVO)ns).getDoc().getCodDoc()%></a>
  </display:column>

            <display:column title="Editar NLO"> <a href="javascript:Llamadalink2('?hacia=editarNLO','&idProy=<%=detproy.getIdProy()%>&idNLO=<%=((NLOdeListaVO)ns).getId()%>')">Editar NLO</a> </display:column>

	 <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
          <display:setProperty name="basic.empty.showtable" value="true"/> <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
          <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
          <display:setProperty name="paging.banner.placement" value="bottom"/>
          <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
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
          <display:setProperty name="export.amount" value="list"/>



</display:table>
</div>
</div>
        <div align="left">
          <%} %>
		
						
						
				 			
				 					 		
					
					</div>
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	</div>

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
    
    										<script type="text/javascript">
											function submitThisForm1() {



												var formulario = $('#form1');

												var action = 'proyectos/proyectoAction.do'
												SubmitFormulario(action,
														formulario);

											}
											
											
										</script>
    
    
  </body>
</html>
