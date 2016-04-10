<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,java.util.Date, java.util.Iterator,proyecto_uoct.EIV.VO.EIVVO,proyecto_uoct.EIV.VO.FlujoVO,proyecto_uoct.EIV.VO.EventoVO,java.lang.Boolean,java.text.SimpleDateFormat" errorPage=""%>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%

String mensaje=(String) request.getAttribute("mensaje");
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

EIVVO eiv = (EIVVO) request.getAttribute("detalleeiv");
List eventos= (List) request.getAttribute("eventos");

Integer regF=(Integer) request.getAttribute("regFlujos");
boolean regFlujos;
if(regF.intValue()==1){
regFlujos=true;
}else{
regFlujos=false;
}

Integer edB=(Integer)request.getAttribute("editarBitacora");
boolean editarBit;
if(edB.intValue()==1){
editarBit=true;
}else{
editarBit=false;
}


Integer em=(Integer)request.getAttribute("email");
boolean email;
if(em.intValue()==1){
email=true;
}else{
email=false;
}


Integer ed=(Integer)request.getAttribute("editarEIV");
boolean editar;
if(ed.intValue()==1){
editar=true;
}else{
editar=false;
}



Integer regBit=(Integer)request.getAttribute("regBit");
boolean regBit_b;
if(regBit.intValue()==1){
regBit_b=true;
}else{
regBit_b=false;
}

%>





<!DOCTYPE html>
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
		<link href="css/datepicker.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
			 		<div class="box boxpost">
				 		<%if(mensaje!=null){out.println(mensaje);} %>
				 			<h4>Datos de EISTU-<%=eiv.getIdEIV() %> : <%= eiv.getNomEiv()%></h4>
				 			<form class="form-horizontal">
				 				<div class="form-group">
    								<label for="inputCodigo" class="col-sm-4 control-label">Código de EISTU</label>
    								
    								<div class="col-sm-8">
      								<input type="text" class="form-control" readonly="readonly" readonly="readonly" id="inputCodigo" value="EISTU - <%=eiv.getIdEIV() %> ">
    								</div>
    							</div>
    							
    							<div class="form-group">
    								<label for="inputPalabra" class="col-sm-4 control-label">Título</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" readonly="readonly" id="inputPalabra" value="<%=eiv.getNomEiv() %>" >
    								</div>
    							</div>
				
    							
  								<div class="form-group">
    								<label for="inputPalabra" class="col-sm-4 control-label">Tipo de Estudio</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" readonly="readonly" id="inputPalabra" value="<%=eiv.getTipoEstudio() %>">
    								</div>
    							</div>
  								<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Fecha Presentación:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%= sdf.format(eiv.getFechaPresent()) %> ">
    								</div>
  								</div>
  								
  								<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Envío desde SEREMITT:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%= sdf.format(eiv.getFechaEnvioSeremitt()) %>">
    								</div>
  								</div>
  								
  								  								<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Fecha Ingreso:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%= sdf.format(eiv.getFechaIng()) %> ">
    								</div>
  								</div>
  								
  								  								<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Fecha de Vencimiento:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%= sdf.format(eiv.getFechaVenc()) %>  ">
    								</div>
  								</div>
  								
  								  								<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Estado del EISTU en UOCT:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%= eiv.getEstado() %>  ">
    								</div>
  								</div>  								
  								
  								
  															<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Estado del EISTU en SEREMITT:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%if (eiv.getEstadoSeremitt().intValue()==1){
             out.print("Aprobado por SEREMITT");}
            if (eiv.getEstadoSeremitt().intValue()==2){
             out.print("Rechazado por SEREMITT");}
             else{
             out.print("--");
           }
             %> ">
    								</div>
  								</div>  	
  								
  															<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Comuna:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%= eiv.getComuna() %> ">
    								</div>
  								</div>  	
  								
  															<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Dirección:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%=eiv.getDir() %> ">
    								</div>
  								</div>  	
  								
<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Redes Involucradas:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%
      List redes = eiv.getRedes();
      if (redes != null) {
        Iterator ir = redes.iterator();
        while (ir.hasNext()) {
          Integer red = (Integer) ir.next();
          out.println(red + " - ");
        }
      }
    %>">
    								</div>
  								</div>  	
  								
  								<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Nº de estacionamientos:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%= eiv.getEstacionamientos() %>">
    								</div>
  								</div>  	
  								
  								<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Consultor:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%= eiv.getNomCons() %>">
    								</div>
  								</div>  	
  								
  								<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Empresa Consultora:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%if(eiv.getEmpCons()!=null){out.print(eiv.getEmpCons());}else{out.print("&nbsp;");} %>">
    								</div>
  								</div>  	  								
  								
  									<div class="form-group ">
    								<label for="inputFecha" class="col-sm-4 control-label">Encargado del EISTU:</label>
    								<div class="col-sm-8">
    									<input type="text" class="form-control inputFecha pad2" readonly="readonly" id="inputDesde" value="<%
         if(eiv.getNom2Encarg()!=null){
           out.print(eiv.getNomEncarg()+ " "+ eiv.getNom2Encarg()+" "+ eiv.getApeEncarg());
         }else{
           out.print(eiv.getNomEncarg()+ " "+ eiv.getApeEncarg());
         }
         %>">
    								</div>
  								</div>
  								
								<div class="form-group">
    								<label for="inputFecha" class="col-sm-4 control-label">Oficio del EISTU:</label>
    								<div class="col-sm-8">
    									<label for="inputFecha" class="col-sm-10 control-label"><a href="javascript:LlamadaPagina('documentacion/documentoAction.do?hacia=detalleDoc&id_doc=<%=eiv.getIdOficio()%>')"><%=eiv.getNomDocumento() %></label>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputFecha" class="col-sm-4 control-label"></label>
    								<div class="col-sm-8">
    									<label for="inputFecha" class="col-sm-2 control-label"><a href="eiv/eivAction.do?hacia=detEIVyle&id_eiv=<%=eiv.getIdEIV()%>" target="_blank">Detalle del EISTU+Lista de bitácoras</a></label>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputFecha" class="col-sm-4 control-label"></label>
    								<div class="col-sm-8">
    									<label for="inputFecha" class="col-sm-10 control-label"><a href="eiv/eivAction.do?hacia=detEIVydetEventos&id_eiv=<%=eiv.getIdEIV()%>" target="_blank">Detalle del EISTU+bitácoras</a></label>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputFecha" class="col-sm-4 control-label"></label>
    								<div class="col-sm-8">
    									<label for="inputFecha" class="col-sm-10 control-label"><a href="eiv/eivAction.do?hacia=detEIVydetEventyflujos&id_eiv=<%=eiv.getIdEIV()%>" target="_blank">Detalle del EISTU+bitácoras+Flujos</a></label>
    								</div>
    							</div>
    							
    								 <%if (editar){ %>
    							<div class="form-group">
    								 <label for="inputFecha" class="col-sm-4 control-label"></label>
    								 <div class="col-sm-8">
    									<a href="javascript:LlamadaPagina('eiv/eivAction.do?hacia=editarEIV&id_eiv=<%=eiv.getIdEIV()%>&nomEIV=<%=eiv.getNomEiv()%>')" >Editar Datos del EISTU</a></label>
     								</div>
     							</div>
     								<%} %>
         							
    								<%if(email && (eiv.getIdEstado().intValue()==1 || eiv.getIdEstado().intValue()==2)&& eiv.getFechaVenc().compareTo(new Date())<=0){  %>
								<div class="form-group">
									<label for="inputFecha" class="col-sm-4 control-label"></label>
									<div class="col-sm-8">
    								
     									 <a href="javascript:LlamadaPagina('eiv/eivAction.do?hacia=aEnvioEmail&id_eiv=<%=eiv.getIdEIV() %>&nomEIV=<%=eiv.getNomEiv()%>')" >Enviar Email de Vencimiento</a>
									
    								</div>
    							</div>
        							<%}%>
  								</div> 
  								
 <div class="box boxpost"> 								
<h4 align="center">Flujos Vehiculares registrados para este EISTU</h4>
   <%
  List flujos = eiv.getFlujos();
  request.setAttribute("flujos",flujos);%>
      <display:table name="flujos" id="flu" requestURI="eivAction.do" class="table table-striped table-bordered table-hover">
      <display:column title="Fecha" sortable="true" sortProperty="fecha"> <%= sdf.format(((FlujoVO)flu).getFecha())%> </display:column> <display:column title="Tipo de Día" property="tipoDia" sortable="true">
      </display:column> <display:column title="Intersección" property="interseccion" sortable="true">
      </display:column> <display:column title="Intervalo horario" property="horasMed" >
      </display:column> <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
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
      <display:setProperty name="export.amount" value="list"/>
      </display:table>  								
      
       <% if ((boolean)regFlujos){ %>
        <a href="javascript:LlamadaPagina('eiv/flujoAction.do?hacia=cargarRegFlujo&id_eiv=<%=eiv.getIdEIV() %>&nomEIV=<%=eiv.getNomEiv()%>')">Admin. flujos </a>
        <%} %>
  								
  		
  		
  		<h4 align="center">Bitácora del EISTU</h4></td>
  
      <display:table name="eventos" requestURI="eivAction.do" class="table table-striped table-bordered table-hover" id="ev">
      <display:column title="Fecha" sortable="true" sortProperty="fechaEv"> <%=sdf.format(((EventoVO)ev).getFechaEv()) %> </display:column>
      <display:column title="Título" class="texto">
      <a href="javascript:LlamadaPagina('eiv/eivAction.do?hacia=detEvento&id_evento=<%=((EventoVO)ev).getIdEvento() %>&id_eiv=<%=eiv.getIdEIV() %>&nomEIV=<%=eiv.getNomEiv()%>')"><%=((EventoVO)ev).getTitulo()%></a></display:column>

      <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>

      <display:setProperty name="basic.empty.showtable" value="true"/>
      <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
      <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
      <display:setProperty name="paging.banner.placement" value="bottom"/> <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
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
    
    
    <%if (editarBit){ %>
        <a href="javascript:LlamadaPagina('eiv/eivAction.do?hacia=editarBitacora&id_eiv=<%= eiv.getIdEIV() %>&nomEIV=<%=eiv.getNomEiv()%>&id_estado=<%=eiv.getIdEstado() %>')">Admin. Bitácora</a>
        <% }else{ %>
        &nbsp;
        <% }%>
      </p>
      <% if (regBit_b){ %><a href="javascript:LlamadaPagina('eiv/eivAction.do?hacia=aAgregarBit&id_eiv=<%=eiv.getIdEIV() %>&nomEIV=<%=eiv.getNomEiv()%>')" >Agregar Bitácora</a>
        <%} %>			
  								
  								
  </div>						
				 			
				 	
				 		
					
					</div>
					</div>
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
