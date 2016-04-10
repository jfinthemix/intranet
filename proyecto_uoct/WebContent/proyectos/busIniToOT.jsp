<!DOCTYPE html>


<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List,java.util.Iterator,proyecto_uoct.proyecto.VO.DetalleProyectoVO" errorPage="" %>
<%@ page import="proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.proyecto.VO.ProyectodeListaVO,proyecto_uoct.documentacion.VO.ClienteVO " %>
<%@ page import="java.text.SimpleDateFormat,java.util.Date" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

List proyectos=(List) request.getAttribute("proyectos");
Date hoy = new Date();
SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
Integer ano = Integer.valueOf(sdf2.format(hoy));

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
		
		
		<script type="text/javascript">
											function submitThisForm1() {
												
												var formulario = $('#form_proy');

												var action = 'proyectos/proyectoAction.do'
												SubmitFormulario(action,
														formulario);

											}
										</script>
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Registrar OT</h2>
						
						<div class="box boxpost">
				 			<h4>Seleccione la iniciativa para el registro de OT</h4>
				 			<form id="form_proy" name="form_proy" class="form-horizontal" action="proyectoAction.do" method="POST">
				 				<div class="form-group">
    								<label for="selectInicio" class="col-sm-4 control-label">Año de inicio de la Iniciativa</label>
    								<div class="col-sm-8">
  									<input type="hidden" name="hacia" value="buscarIniToOT" />
        <select name="ano" id="ano" class="form-control">
          <option value="<%=ano.intValue()+1%>"><%=ano.intValue()+1%></option>
          <%   for (int ia=0;ia<=5;ia++){%>
          <option value="<%=ano.intValue()-ia%>"><%=ano.intValue()-ia%></option>
          <%}%>
        </select>    								
    								
      								
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
						
						
  <%if (proyectos!=null){
%>
<div class="box boxpost">
<display:table name="proyectos" id="inis" pagesize="15" class="table table-striped table-bordered table-hover" requestURI="proyectoAction.do">
  <display:caption>Iniciativas de Inversión </display:caption>

  <display:column title="Nombre de la Iniciativa" property="nomProy">
  </display:column>

  <display:column title="Encargado" property="encargado">
  </display:column>


  <display:column title="Fecha de Inicio"><%=sdf.format(((ProyectodeListaVO)inis).getFechaProy()) %>
  </display:column>

  <display:column title="Estado"><%if(((ProyectodeListaVO)inis).getIsActivo()){out.print("Activo");}else{out.print("Inactivo");} %>
  </display:column>

<display:column title="Reg. OT">
<%if(((ProyectodeListaVO)inis).getEsDelEquipo()){%>
  <a href="javascript:Llamadalink('aRegOT','&idProy=<%=((ProyectodeListaVO)inis).getIdProy()%>')" >Reg.ot</a>
  <%}else{ %>
  No tienes permiso
  <%} %>
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
        <display:setProperty name="export.amount" value="list"/>




  </display:table>

  </div>
      
        <%} %>
    						

						
				 			
				 		
				 		
					
					</div>
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

      <div class="container">
			<footer>
				<div class="row">
					<div class="col-sm-12">
						<p>Unidad Operativa de Control de Tránsito. <span id="pie"></span></p>
					</div>
				</div>
        	</footer>
		</div> <!-- /container -->
<script>
	function Llamadalink(hacia, link) {
												
		link = link.replace('#', '');
		link = 'proyectos/proyectoAction.do'+ '?hacia=' + hacia + link ;
		LlamadaPagina(link);

												//clienteAction.do?hacia=detalleEntExt
	}
	
	</script>

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
