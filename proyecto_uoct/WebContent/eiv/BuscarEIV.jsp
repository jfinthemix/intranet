<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List,java.util.Iterator, proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.EIV.VO.EIVdeListaVO" errorPage="" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List listaingenieros= (List) request.getAttribute("listaIngenieros");
List listaestados= (List) request.getAttribute("listaEstados");
List listaeiv=(List) request.getAttribute("listaEIV");
List comunas=(List)request.getAttribute("comunas");
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
%><!DOCTYPE html>
<html lang="en">
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
	var formulario = $('#formBusEIV');
	//SelectAllList(formulario.listaRel);
	var action = 'eiv/eivAction.do';
	SubmitFormulario(action,formulario);

}
		</script>
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Buscar EIV</h2>
						
				 		<div class="box boxpost">
				 			<h4>Datos de EISTU</h4>
				 			<form class="form-horizontal" action="eiv/eivAction.do" name="formBusEIV" id="formBusEIV">
				 			<input type="hidden" name="hacia" value="buscarEIV" />
				 				<div class="form-group">
    								<label for="inputCodigo" class="col-sm-4 control-label">Código de EISTU</label>
    								<div class="col-sm-2 guionpost">
      								<p class="form-control-static">EISTU</p>
    								</div>
    								<div class="col-sm-6">
      								<input type="text" class="form-control" id="inputCodigo" name="ideiv">
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="inputPalabra" class="col-sm-4 control-label">Palabra clave</label>
    								<div class="col-sm-8">
      								<input type="text"  name="palClave" class="form-control" id="inputPalabra">
    								</div>
    							</div>
  								<div class="form-group input-daterange">
    								<label for="inputFecha" class="col-sm-4 control-label">Fecha de vencimiento</label>
    								<div class="col-sm-2">
    									<label class="control-label">Entre el:</label>
    								</div>
    								<div class="col-sm-2 noPadL">
      								<input type="text" class="form-control inputFecha pad2" id="inputDesde" name="fechaVencimiento">
    								</div>
    								<div class="col-sm-2">
      								<label class="control-label">Hasta el:</label>
    								</div>
    								<div class="col-sm-2 noPadL">
    									<input type="text" class="form-control inputFecha pad2" name="fechaVencimiento_b" id="inputHasta">
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="selectEstado" class="col-sm-4 control-label">Estado</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="selectEstado" name="estado">
      									<option value="">&nbsp;</option>
                  <%
        Iterator i =listaestados.iterator();
         while ( i.hasNext()){
           IdStrVO estado= (IdStrVO) i.next();
           out.println("<option value=\""+estado.getId()+"\">"+ estado.getStr()+"</option>" );
         }
         %>
      								</select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="selectEstadoS" class="col-sm-4 control-label">Estado en SEREMITT</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="selectEstadoS" name="estadoSeremitt">
      									 <option value="0" selected>Seleccionar</option>
      									 <option value="1">Aprobado</option>
      									 <option value="2">Rechazado</option>
      								</select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="selectIng" class="col-sm-4 control-label">Ingeniero encargado</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="selectIng" name="idIngeniero">
      									<option value="">seleccione ingeniero</option>
                  <%

        Iterator ii =listaingenieros.iterator();
         while ( ii.hasNext()){
           UsuarioVO ing= (UsuarioVO) ii.next();
           if(ing.getNombreUsu2()!=null){
             out.println("<option value=\""+ing.getIdUsu()+"\">"+ ing.getNombreUsu()+" "+ing.getNombreUsu2()+" "+ing.getApellUsu()+"</option>" );
           }else{
             out.println("<option value=\""+ing.getIdUsu()+"\">"+ ing.getNombreUsu()+" "+ing.getApellUsu()+"</option>" );
           }
         }


         %>
      								</select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="selectComuna" class="col-sm-4 control-label">Comuna</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="selectComuna" name="idComuna">
      									
      									 <option value="">Seleccionar</option>
                  <% if(comunas!=null){
            Iterator ic=comunas.iterator();
            while(ic.hasNext()){
              IdStrVO com=(IdStrVO) ic.next();
              %>
                  <option value="<%=com.getId()%>"><%=com.getStr() %></option>
                  <%
           }
         }
         %>
      								</select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputRedes" class="col-sm-4 control-label">Red involucrada</label>
    								<div class="col-sm-8">
    									<input type="text" id="inputRedes" class="form-control" name="red">
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputConsultor" class="col-sm-4 control-label">Consultor</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputConsultor" name="consultor" >
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
						
						
			
<%    if(listaeiv !=null){
  Iterator ieiv= listaeiv.iterator();
  request.setAttribute("eivses",ieiv);
  %>	<div class="box boxpost col-sm-15">	
        <display:table name="eivses" pagesize="15" id="eivs" requestURI="eivAction.do" class="table table-striped table-bordered table-hover" >
        <display:column  title="EISTU" href="eivAction.do?hacia=detalleEIV" sortable="true" sortProperty="idEIV">
        <a onclick="javascript:LlamadaPagina('eiv/eivAction.do?hacia=detalleEIV&id_eiv=<%=((EIVdeListaVO)eivs).getIdEIV()%>');" href="javascript:void(0);">EISTU-<%=((EIVdeListaVO)eivs).getIdEIV() %></a>
        </display:column>
        <display:column title="Nombre del EISTU"> <%=((EIVdeListaVO)eivs).getNomEiv() %>
        </display:column>
        <display:column title="Fecha de Vencimiento" sortable="true"><%=sdf.format(((EIVdeListaVO)eivs).getFechaVenc()) %>
        </display:column>
        <display:column title="Estado del EISTU" sortable="true"><%=((EIVdeListaVO)eivs).getEstado() %>
        </display:column>
        <display:column title="Ingeniero Encargado" sortable="true">
        <%
if(((EIVdeListaVO)eivs).getNom2Encarg()!=null){
out.print(((EIVdeListaVO)eivs).getNomEncarg()+" "+ ((EIVdeListaVO)eivs).getNom2Encarg()+" "+((EIVdeListaVO)eivs).getApeEncarg());
}else{
out.print(((EIVdeListaVO)eivs).getNomEncarg()+" "+((EIVdeListaVO)eivs).getApeEncarg());
}

%>
        </display:column>
        <display:column title="Consultor" sortable="true"><%=((EIVdeListaVO)eivs).getNomCons() %>
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
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
