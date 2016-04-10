<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List, java.util.Iterator,proyecto_uoct.EIV.VO.FlujoVO" errorPage="" %>
<%@ page import="java.text.SimpleDateFormat,proyecto_uoct.foro.VO.DocForoVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
List flujos= (List) request.getAttribute("listaFlujos");
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
		
	<script type="text/javascript">
	
	function submitThisForm1() {
		var formulario = $('#form1');
		var action = 'eiv/flujoAction.do';

		SubmitFormulario(action,formulario);
		}
	
	</script>		
		
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
				
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Buscar Flujos</h2>
						
				 		<div class="box boxpost">
				 			<h4>Datos de EISTU</h4>
				 			<form class="form-horizontal" name="form1" id="form1" method="post" action="eiv/flujoAction.do">
				 			 <input type="hidden" name="hacia" id="hacia" value="buscarFlujos">
				 			 <input type="hidden" name="Submit" id="Submit" value="buscarFlujos">
				 				<div class="form-group">
    								<label for="inputCodigo" class="col-sm-4 control-label">Por estudio</label>
    								<div class="col-sm-2 guionpost">
      								<p class="form-control-static">EISTU</p>
    								</div>
    								<div class="col-sm-6">
      								<input type="text" name="ideiv" class="form-control" id="inputCodigo" placeholder="Ingresa un número">
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="inputFecha" class="col-sm-4 control-label">Por fecha</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputFecha" name="fecha">
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputCalle" class="col-sm-4 control-label">Por calle involucrada</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputCalle" name="calle">
    								</div>
    							</div>
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<a href="javascript:void(0)" onclick="javascript:submitThisForm1();" class="botoVerde busca"><span class="glyphicons glyphicons-search"></span> Buscar</a>
      								</div>
  									</div>
    							</div>
  							</form>
						</div>
						
						

        					
    							
 <%if(flujos!=null){
   Iterator iflu=flujos.iterator();
  request.setAttribute("iflu",iflu);
  %>
 		<div class="box boxpost">
        <display:table  pagesize="15" name="iflu" requestURI="flujoAction.do" class="table table-striped table-bordered table-hover" id="flus" >

        <display:column sortable="true" title="EISTU" sortProperty="idEIV">
          <a href="javascript:LlamadaPagina('eiv/eivAction.do?hacia=detalleEIV&id_eiv=<%=((FlujoVO)flus).getIdEIV()%>')">EISTU-<%=((FlujoVO)flus).getIdEIV()%></a>
        </display:column>
        <display:column  title="Fecha de la Medición"><%=sdf.format(((FlujoVO)flus).getFecha())%> </display:column>
        <display:column  title="Intersección" ><%=((FlujoVO)flus).getInterseccion() %> </display:column>
        <display:column  title="Horas" ><%=((FlujoVO)flus).getHorasMed()%> </display:column>
        <display:column  title="Tipo de Día"><%=((FlujoVO)flus).getTipoDia() %> </display:column>


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
      </div>
  
        <% }%>    							
    							
 					</div>
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

      

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/jquery.tablesorter.js"></script> 
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
