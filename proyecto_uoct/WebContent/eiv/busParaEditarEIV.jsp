<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.Date,java.util.List,java.util.Iterator, proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.EIV.VO.EIVdeListaVO" errorPage="" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List listaingenieros= (List) request.getAttribute("listaIngenieros");
List listaestados= (List) request.getAttribute("listaEstados");
List listaeiv=(List) request.getAttribute("listaEIV");
List comunas=(List)request.getAttribute("comunas");
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");



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


Integer regBit=(Integer)request.getAttribute("regBit");
boolean regBit_b;
if(regBit.intValue()==1){
regBit_b=true;
}else{
regBit_b=false;
}

Integer ed=(Integer)request.getAttribute("editarEIV");
boolean editar;
if(ed.intValue()==1){
editar=true;
}else{
editar=false;
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

<script type="text/javascript">

function submitThisForm1() {
	var formulario = $('#formBusEIV');
	var action = 'eiv/eivAction.do';
	SubmitFormulario(action,formulario);

}
function Llamadalink(hacia, link) {
	link = link.replace('#', '');
	link = 'eiv/eivAction.do'+ '?hacia=' + hacia + link ;
	alert(link);
	LlamadaPagina(link);

}

</script>

		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Editar EISTU</h2>
						
				 		<div class="box boxpost">
				 			<h4>Datos de EISTU</h4>
				 			
				 			 <form class="form-horizontal" action="eiv/eivAction.do" name="formBusEIV" id="formBusEIV" method="POST">
				 			 <input type="hidden" name="hacia" value="busParaEditarEIV" />
				 				<div class="form-group">
    								<label for="inputCodigo" class="col-sm-4 control-label">Código de EISTU</label>
    								<div class="col-sm-2 guionpost">
      								<p class="form-control-static">EISTU</p>
    								</div>
    								<div class="col-sm-6">
      								<input type="text" name="ideiv" size="5" maxlength="5" class="form-control" id="ideiv">
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="inputPalabra" class="col-sm-4 control-label">Palabra clave</label>
    								<div class="col-sm-8">
      								<input name="palClave" maxlength="50" type="text" class="form-control" id="palClave">
    								</div>
    							</div>
  								<div class="form-group input-daterange">
    								<label for="inputFecha" class="col-sm-4 control-label">Fecha de vencimiento</label>
    								<div class="col-sm-2">
    									<label class="control-label">Entre el:</label>
    								</div>
    								<div class="col-sm-2 noPadL">
      								<input type="text" name="fechaVencimiento" size=8 class="form-control inputFecha pad2" id="fechaVencimiento">
    								</div>
    								<div class="col-sm-2">
      								<label class="control-label">Hasta el:</label>
    								</div>
    								<div class="col-sm-2 noPadL">
    									<input type="text" name="fechaVencimiento_b" class="form-control inputFecha pad2" id="fechaVencimiento_b">
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="selectEstado" class="col-sm-4 control-label">Estado</label>
    								<div class="col-sm-8">
      								<select name="estado" class="form-control" id="estado">
      									
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
      									 <option value="0" selected disabled>Seleccionar</option>
      									 <option value="1">Aprobado</option>
                						 <option value="2">Rechazado</option>
               					</select>
      								
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="selectIng" class="col-sm-4 control-label">Ingeniero encargado</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="selectIng" name="idIngeniero">
      									<option value="">&nbsp;</option>
                  <%

        Iterator ii =listaingenieros.iterator();
         while ( ii.hasNext()){
           UsuarioVO ing= (UsuarioVO) ii.next();
           if(ing.getNombreUsu2()!=null){
             out.println("<option value=\""+ing.getIdUsu()+"\">"+ ing.getNombreUsu()+" "+ing.getNombreUsu2()+" "+ing.getApellUsu()+"</option>" );
           }
           else{
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
      									 <option value="">&nbsp;</option>
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
    									<input type="text" id="inputRedes" class="form-control" name="red" maxlength="3">
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputConsultor" class="col-sm-4 control-label">Consultor</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputConsultor" name="consultor" maxlength="50" size="30">
    								</div>
    							</div>
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<a href="javascript:void(0)" class="botoVerde busca" onclick="javascript:submitThisForm1();"><span class="glyphicons glyphicons-search"></span> Buscar</a>
      								</div>
  									</div>
    							</div>
  							</form>
						</div>

<%

if (listaeiv != null){

%>
						<div class="box boxpost">
				 			<h4>Lista de EISTU</h4>
				 			<display:table name="listaEIV" id="le" class="table table-striped table-bordered table-hover col-sm-8" requestURI="eivAction.do" pagesize="15">


     <display:column  title="EISTU" sortable="true" sortProperty="idEIV" >
         <%
         out.println("EISTU-"+((EIVdeListaVO)le).getIdEIV());
         %>
     </display:column>

     <display:column  title="Título EISTU" property="nomEiv" maxLength="15">
     </display:column>


     <display:column  title="Editar EISTU" >
       <% if (editar ){
         out.println("<a onclick=\"javascript:LlamadaPagina('eiv/eivAction.do?hacia=editarEIV&id_eiv="+((EIVdeListaVO)le).getIdEIV()+"\');\" href=\"javascript:void(0);\">Editar</a>");
       }else{out.print("No permitido");}
         %>
     </display:column>

     <display:column title="Admin. Flujos">
     <%if (regFlujos){
        out.println("<a href=\"javascript:void(0);\" onclick=\"javascript:LlamadaPagina('eiv/flujoAction.do?hacia=cargarRegFlujo&id_eiv="+((EIVdeListaVO)le).getIdEIV()+"&nomEIV="+((EIVdeListaVO)le).getNomEiv()+"');\">Admin. Flujos</a>");
        }else{out.print("No permitido</div>");}
        %>
     </display:column>

     <display:column title="Admin. Bitácora">
       <%
      if (editarBit){
        out.println("<a href=\"javascript:void(0);\" onclick=\"javascript:LlamadaPagina('eiv/eivAction.do?hacia=editarBitacora&id_eiv="+((EIVdeListaVO)le).getIdEIV()+"&nomEIV="+((EIVdeListaVO)le).getNomEiv()+"')\">Admin Bitácora</a>");
        }else{out.print("No permitido");}

       %>
     </display:column>

     <display:column title="Agregar Bitácora">
       <%         if (regBit_b){
         out.println("<a  href=\"javascript:void(0);\" onclick=\"javascript:LlamadaPagina('eiv/eivAction.do?hacia=aAgregarBit&id_eiv="+((EIVdeListaVO)le).getIdEIV()+"&nomEIV="+((EIVdeListaVO)le).getNomEiv()+"')\">Agregar Bitácora</a>");
       }else{out.print("No permitido");}
       %>

     </display:column>

   <display:column title="Email de Vencimiento">
     <%
     if(email && (((EIVdeListaVO)le).getIdEstado().intValue()==1 || ((EIVdeListaVO)le).getIdEstado().intValue()==2)&&((EIVdeListaVO)le).getFechaVenc().compareTo(new Date())<=0){
       out.println("<a  href=\"javascript:void(0);\" onclick=\"javascript:LlamadaPagina('eiv/eivAction.do?hacia=aEnvioEmail&id_eiv="+((EIVdeListaVO)le).getIdEIV()+"&nomEIV="+((EIVdeListaVO)le).getNomEiv()+"')\">Enviar Email</a>");
     }else{out.print("No permitido");}

     %>

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
  						<%} %>
				
				 		
					
					</div>
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

 <!-- /container -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
