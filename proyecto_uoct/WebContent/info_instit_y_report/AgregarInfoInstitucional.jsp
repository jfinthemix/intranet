<%@page language="java"%>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.infoyrep.VO.ArchivoInfoVO"%>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%

List lista=(List) request.getAttribute("lista");
String mensaje=(String) request.getAttribute("mensaje");
%>
<html>
<head>
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
 function valLargoFile()
{
  var frm = document.forms['form1'];// reemplazar nombre del form
  archivo=frm.unArchivo.value; //reemplazar nombre del file
  largo=50; //reemplazar la longitud del campo
  while(archivo.indexOf('\\') !=-1){
  archivo=archivo.slice(archivo.indexOf('\\')+1);
  }
  if(archivo.length>largo){
  alert("nombre del archivo demasiado largo");
  return false;
  }else{
  return true;
  }

}

function eliminarInfo(id){
  form2.idFile.value=id;
elim=window.confirm('�Est� seguro de eliminar este archivo?');
(elim)?form2.submit():'return false';
}

function submitThisForm1() {


	var formulario = $('#form1');
	var action = 'info_instit_y_report/infoinstitAction.do';
	SubmitFormulario(action,formulario);

}


</script>



<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo box boxpost">


<h3>Publicar Archivo</h3>
          <%if(mensaje!=null){%>
        
<div class="col-sm-8"><h3><strong><%=mensaje %></strong></h3></div> <%} %>
 
 
	  <html:form  action="info_instit_y_report/infoinstitAction.do" styleId="form1"  enctype="multipart/form-data" name="form1" type="proyecto_uoct.infoyrep.controller.fileup1ActionForm" >
	  <input type="hidden" name="hacia" value="agregarArchivo">
       
       <div class="form-group">

				<label for="inputTitulo" class="col-sm-6 control-label"><strong>Seleccione el Archivo:</strong></label>
            	<div class="col-sm-12"><html:file property="unArchivo" styleId="unArchivo" /></div>
       </div>
           <div class="form-group">  
           <label for="inputTitulo" class="col-sm-6 control-label">Descripci&oacute;n:</label>
       		</div>
      	<div class="form-group">
      		<div class="col-sm-8"> <textarea name="descripcion" cols="45" rows="5"></textarea></div>
       </div>
       
       <div class="boxOpciones">
       <input type="hidden" name="Submit" value="Guardar">
        <input type="button" onclick="javascript:submitThisForm1();" name="Submit1" value="Guardar"> </div>

</html:form>

</div>

	<script language="JavaScript" type="text/javascript">
	var frmvalidator  = new Validator("form1");
	frmvalidator.addValidation("unArchivo","req","Debe indicar el archivo");
	frmvalidator.addValidation("descripcion","req","Debe indicar una descripci�n del archivo");
	frmvalidator.addValidation("descripcion","maxlen=300","La descripci�n no puede superar los 300 carcteres");
	frmvalidator.addValidation("descripcion","alnumspace");
	</script>

    <script language="JavaScript" type="text/javascript">
    var frmvalidator  = new Validator("form1");

    frmvalidator.setAddnlValidationFunction("valLargoFile");

    frmvalidator.addValidation("UnArchivo","req","Debe ingresar el archivo");
    frmvalidator.addValidation("descripcion","req","Debe Ingresar Descripci�n");
    frmvalidator.addValidation("descripcion","maxlen=300","Descripci�n no puede superar los 300 caracteres");
  </script>
  
  
          <%if (lista!=null){
  Iterator lis=lista.iterator();
  request.setAttribute("lista",lis);%>
<div class="box boxpost col-sm-8">  
 <form action="infoinstitAction.do" name="form2" id="form2">

         <display:table id="archivos" name="lista" class="table table-striped table-bordered table-hover" requestURI="infoinstitAction.do">
          <display:caption><h3>Informaci&oacute;n Institucional Publicada</h3></display:caption>
          <display:column title="Nombre"> <%=((ArchivoInfoVO)archivos).getNomArchivo() %> </display:column>
          <display:column title="Descripci�n" class="texto">
          <%=((ArchivoInfoVO)archivos).getDescripcion() %> </display:column>
          <display:column title="Descargar" href="infoinstitAction.do?hacia=descargarArchivo" paramId="idFile" paramProperty="idFile" >
          Descargar </display:column>
          <display:column  title="Eliminar">
          <a href="#" onclick="eliminarInfo(<%=((ArchivoInfoVO)archivos).getIdFile()%>)">Eliminar</a>
        </display:column>


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
          <display:setProperty name="export.amount" value="list"/> </display:table>
        
          <input type="hidden" name="hacia" value="eliminarArchivo" />
   <input type="hidden" name="idFile" value="" />
 </form>
   </div>
        
          <%} %>
        



 

</div>
</div>
</div>

</body>
</html>
