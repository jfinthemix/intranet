<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@ page import="java.util.List,java.util.Iterator,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.ventas.VO.VtaVO,java.text.SimpleDateFormat,proyecto_uoct.ventas.VO.CliVtaVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%

SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

List estados=(List)request.getAttribute("estados");
List ventas=(List)request.getAttribute("ventas");%>


<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="Unidad Operativa de Control de Tr�nsito">
		<link rel="icon" href="img/favicon.ico">
		
		<title>Unidad Operativa de Control de Tr�nsito</title>
		
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
var otra=null;
function popUp(href, target, features) {
    otra = window.open(href, target, features);
    otra.window.focus();
  }


function pasaCli(nomCli, idCli){
    form1.nomCli.value = nomCli;
    form1.idCli.value = idCli;
    otra.window.close();
  }
  
function submitThisForm1() {
	var formulario = $('#form1');
	var action = 'ventas/ventasAction.do';
	SubmitFormulario(action,	formulario);

}

  
</script>	
	
				
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Buscar Ventas</h2>
						
						<div class="box boxpost">
				 			<h4>Datos de b�squeda</h4>
				 			<form class="form-horizontal" action="ventas/ventasAction.do" name="form1" id="form1">
				 			<input type="hidden" name="accion" value="buscarVta" />
				 				<div class="form-group input-daterange">
    								<label for="inputFecha" class="col-sm-4 control-label">Fecha de inicio</label>
    								<div class="col-sm-2">
    									<label class="control-label">Entre el:</label>
    								</div>
    								<div class="col-sm-2 noPadL">
      								<input type="text" class="form-control inputFecha pad2" id="inputDesde" name="fechaPresentacion" size=8>
    								</div>
    								<div class="col-sm-2">
      								<label class="control-label">Hasta el:</label>
    								</div>
    								<div class="col-sm-2 noPadL">
    									<input type="text" class="form-control inputFecha pad2" id="inputHasta" name="fechaPresentacion2" size=8>
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputCliente" class="col-sm-4 control-label">Cliente</label>
    								<div class="col-sm-5">
    								<input type="hidden" name="idCli" value="0"/>
      								<input type="text" class="form-control" id="inputCliente" name="nomCli">
    								</div>
    								<div class="col-sm-3">
      								<a href="ventas/ventasAction.do?accion=busCliVta_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=350,height=500, scrollbars=1'); return false;" class="botoGris botoMini noMarg"><span class="glyphicons glyphicons-search"></span> Buscar</a>
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="selectEstado" class="col-sm-4 control-label">Estado</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="selectEstado" name="idEstado">

<option value="0" selected></option>
                  <% if(estados!=null){
  Iterator ie=estados.iterator();
  while(ie.hasNext()){
  IdStrVO e=(IdStrVO)ie.next();
  %>
                  <option value="<%=e.getId()%>"><%=e.getStr() %></option>
                  <%
}
}
%>
                </select>

    							</div>
    							<div class="form-group">
    								<label for="inputPalabra" class="col-sm-4 control-label">Palabra clave</label>
    								<div class="col-sm-8">
      								<input type="text" name="palClave" maxlength="30" class="form-control" id="inputPalabra">
      								<span id="helpBlock" class="help-block">Detalle de la venta.</span>
      							</div>
    							</div>
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<a href="javascript:void(0)" onclick="submitThisForm1();" class="botoVerde busca"><span class="glyphicons glyphicons-search"></span> Buscar</a>
      								</div>
  									</div>
    							</div>
  							</form>
						</div>
						
						
						
						
						
<%if(ventas!=null){%>

<div class="box boxpost encuentra">

        <display:table class="table table-striped table-bordered table-hover tablesorter" name="ventas" id="vs" requestURI="ventasAction.do">
        <display:caption><h4>Ventas Encontradas</h4></display:caption>


        <display:column title="Cod. de Venta" property="codVenta" sortable="true" sortProperty="codVenta" href="ventasAction.do?accion=detalleVenta" paramId="idVenta" paramProperty="idVenta" maxLength="40">
        </display:column>

        <display:column title="Fecha de Inicio"><%=sdf.format(((VtaVO)vs).getFechaRecepcion()) %> </display:column>

        <display:column title="Cliente"><%=(((VtaVO)vs).getCliente()).getNomCli() %> </display:column>

        <display:column title="Estado" property="estado" sortable="true" sortProperty="estado">
        </display:column>

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
        <%} %>						
						











					
				 		
					
					
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	</div>

      


		
		


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/jquery.tablesorter.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
