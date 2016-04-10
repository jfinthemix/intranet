<!DOCTYPE html>

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List,java.util.Iterator,proyecto_uoct.proyecto.VO.DetalleProyectoVO" errorPage="" %>
<%@ page import="proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.proyecto.VO.ProyectodeListaVO " %>
<%@ page import="java.text.SimpleDateFormat,java.util.Date" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

List proyectosaCargo=(List) request.getAttribute("proyectosaCargo");
List proyectosEnEquipo=(List) request.getAttribute("proyectosEnEquipo");
List otrosProyectos=(List) request.getAttribute("otrosProyectos");
Integer edini=(Integer) request.getAttribute("edini");

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
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Buscar iniciativas</h2>
						
				 		<div class="box boxpost">
				 			<h4>Datos de Iniciativa</h4>
				 			<form id="form1" name="form1" class="form-horizontal" action="proyectoAction.do" method="POST">
				 			<input type="hidden" name="hacia" value="buscarIni" />
				 				<div class="form-group">
    								<label for="selectInicio" class="col-sm-4 control-label">Inicio del proyecto</label>
    								<div class="col-sm-8">
    								
<select name="ano" >
            <option value="0">Todos</option>
            <option value="<%=ano.intValue()+1%>"><%=ano.intValue()+1%></option>
            <%   for (int ia=0;ia<=5;ia++){


              if(ia==0){%>

            <option value="<%=ano.intValue()-ia%>" selected="selected"><%=ano.intValue()-ia%></option>
            <%}else{
            %>
            <option value="<%=ano.intValue()-ia%>"><%=ano.intValue()-ia%></option>
           <%}} %>
          </select>
    								
    								</div>
    							</div>
  								<div class="form-group">
    								<div class="col-sm-8 col-sm-offset-4">
      								<input type="checkbox" id="soloActivos" name="soloActivos"/ > Buscar solamente activos
    								</div>
    							</div>
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    									<input type="hidden" name="buscarProyecto" value="Buscar" />
    										<a OnClick="submitThisForm1();" href="javascript:void(0)"
											class="botoVerde"> Buscar</a>
    										
										<script type="text/javascript">
											function submitThisForm1() {



												var formulario = $('#form1');

												var action = 'proyectos/proyectoAction.do'
												SubmitFormulario(action,
														formulario);

											}
											
											function Llamadalink(hacia, link) {
												link = link.replace('#', '');
												link = 'proyectos/proyectoAction.do' + link + '&hacia=' + hacia;
												//alert(link+' ' +param);
												LlamadaPagina(link);

												//clienteAction.do?hacia=detalleEntExt
											}
										</script>    										
    										
    										
      								</div>
  									</div>
    							</div>
  							</form>
						</div>
						
						<div class="box boxpost">
						
						

<%if (proyectosaCargo!=null){
%>
        <display:table name="proyectosaCargo" id="inis" pagesize="15" class="table table-striped table-bordered table-hover" requestURI="proyectoAction.do" >
          <display:caption class="box"> <strong><h4>Iniciativas a mi cargo</h4></strong>  </display:caption>
        <display:column title="Nombre de la Iniciativa" property="nomProy" href="javascript:Llamadalink('detalleIni','#')" paramProperty="idProy" paramId="idProy">
        </display:column>

        <display:column title="Encargado" class="col-sm-4" property="encargado"><% %>
        </display:column>

        <display:column title="Fecha de Inicio" class="col-sm-2"><%=sdf.format(((ProyectodeListaVO)inis).getFechaProy()) %> </display:column>

        <display:column title="Estado" class="col-sm-2">
        <%if(((ProyectodeListaVO)inis).getIsActivo()){out.print("Activo");}else{out.print("Inactivo");} %>
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
        <%} %>



						
		 <%if (proyectosEnEquipo!=null){
%>
<br>
        <display:table name="proyectosEnEquipo" id="inis" pagesize="15" class="table table-striped table-bordered table-hover" requestURI="proyectoAction.do">
          <display:caption class="box"><h4><strong>Iniciativas en que participo </strong></h4></display:caption>
        <display:column title="Nombre de la Iniciativa" class="col-sm-4" property="nomProy" href="javascript:Llamadalink('detalleIni','#')"  paramProperty="idProy" paramId="idProy">
        </display:column>

        <display:column title="Encargado"  class="col-sm-2" property="encargado">
        </display:column>

        <display:column title="Fecha de Inicio" class="col-sm-2"><%=sdf.format(((ProyectodeListaVO)inis).getFechaProy()) %> </display:column>

        <display:column title="Estado" class="col-sm-2">
        <%if(((ProyectodeListaVO)inis).getIsActivo()){out.print("Activo");}else{out.print("Inactivo");} %>
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
        <%} %>
					
    							
				 			<%if (otrosProyectos!=null){
%>
        <display:table name="otrosProyectos" id="inis" pagesize="15" class="table table-striped table-bordered table-hover" requestURI="proyectoAction.do">
          <display:caption class="box"><strong><h4> Otros Proyectos</h4></strong></display:caption>
        <display:column title="Nombre de la Iniciativa" class="col-sm-4" property="nomProy" href="javascript:Llamadalink('detalleIni','#')" paramProperty="idProy" paramId="idProy">
        </display:column>

        <display:column title="Encargado" property="encargado" class="col-sm-2">
        </display:column>

        <display:column title="Fecha de Inicio" class="col-sm-2"><%=sdf.format(((ProyectodeListaVO)inis).getFechaProy()) %> </display:column>

        <display:column title="Estado" class="col-sm-2">
        <%if(((ProyectodeListaVO)inis).getIsActivo()){out.print("Activo");}else{out.print("Inactivo");} %>
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
						<p>Unidad Operativa de Control de Tránsito, <span id="pie"></span></p>
					</div>
				</div>
        	</footer>
		</div> <!-- /container -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="recuros/js/bootstrap.min.js"></script>
    <script src="recuros/js/bootstrap-datepicker.min.js"></script>
    <script src="recuros/js/bootstrap-datepicker.es.min.js"></script>
    <script src="recuros/js/moment.js"></script>
    <script src="recuros/js/truncate.js"></script>
    <script src="recuros/js/fullcalendar.min.js"></script>
    <script src="recuros/js/uoct.js"></script>
    

    
  </body>
</html>
