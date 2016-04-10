<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="proyecto_uoct.usuario.VO.AreaVO,java.util.List,java.util.Iterator" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List areas =(List) request.getAttribute("areas");
String mensaje= (String) request.getAttribute("mensaje");
%>
<html>
<head>
<title>Areas de trabajo de UOCT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">



</head>

<body>
<script language="JavaScript" type="text/javascript">
function confirmarEliminacion(f){
  borrar = window.confirm('El área será eliminada solamente si no existe usuario asociado a ella.\r\n¿Seguro que desea eliminar el Area seleccionada?');
  (borrar)?f.submit():'return false';
}
</script>
<script language="JavaScript" type="text/javascript">
            var frmvalidator  = new Validator("form1");
            frmvalidator.addValidation("hacia","req","falta el indice de la acción que se debe hacer");

            frmvalidator.addValidation("nomArea","req","Debe indicar el nombre del área nueva");
            frmvalidator.addValidation("nomArea","alnumspace");
            frmvalidator.addValidation("nomArea","maxlen=20","área no puede superar los 20 caracteres");

          </script>
<h2>Administrar áreas de trabajo</h2>

<div class="box boxpost">
				 			<h4>Nueva &Aacute;rea</h4>
				 			<form name="form1" action="usuarioAction.do" class="form-horizontal">
				 			<input type="hidden" name="hacia" value="agregarArea" />
  								<div class="form-group">
    								<div class="col-sm-12">
      								<input name="nomArea" type="text" class="form-control" id="inputArea" placeholder="Nombre del área">
    								</div>
  								</div>
  								
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-offset-2 col-sm-10">
      									<button type="submit" class="botoVerde">Crear área</button>
    									</div>
  									</div>
    							</div>
  								
							</form>
						</div>
						
 <%if(areas!=null){
      Iterator ia=areas.iterator();
      request.setAttribute("areases",ia);
      %>

<div class="box">	
			<h4>Áreas de trabajo de UOCT</h4>
			<display:table id="areass" class="table table-striped table-bordered table-hover" name="areases" pagesize="15" requestURI="usuarioAction.do">
			<display:column title="Area">
      		<%=((AreaVO)areass).getArea() %> </display:column>
      		<display:column title="Eliminar">
      <form action="usuarioAction.do">
        
          <input type="hidden" name="hacia" value="eliminarArea"/>
          <input type="hidden" name="idArea" value="<%= ((AreaVO)areass).getIdArea()%>" />
          <button name="button" class="botoRojo botoMini" type="button" onClick="confirmarEliminacion(this.form);" value="eliminar"></span> Eliminar</button>
       
      </form>
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

<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
    