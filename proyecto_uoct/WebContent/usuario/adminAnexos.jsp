<%@page import="java.util.List,java.util.Iterator" %>
<%@page import="proyecto_uoct.usuario.VO.AnexoVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List anexos=(List)request.getAttribute("anx");
%>
<html>
<head>
<title>
Cuadro de Anexos
</title>


<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>


</head>
<body>


<h2>Administrar anexos telefónicos</h2>
<div class="box boxpost">
<h4>Anexos telefónicos<br> <small><strong>No</strong> pertenecen a usuarios</small></h4>
  <%
        if(anexos!=null){
          Iterator inx=anexos.iterator();
          request.setAttribute("anexoses",inx);
          %>
           <display:table name="anexoses" id="anx" pagesize="15" class="table table-striped table-bordered table-hover" requestURI="usuarioAction.do">
              <display:caption>Anexos de UOCT</display:caption> <display:column title="Nombre del Anexo"><%=((AnexoVO)anx).getNomAnexo() %>
              </display:column> <display:column title="Anexo"><%=((AnexoVO)anx).getAnexo() %>
              </display:column> <display:column title="Editar"  paramId="idAnx" paramProperty="idAnexo">
              <a href="usuarioAction.do?hacia=aeditarAnexo" class="botoGris botoMini">Editar</a> </display:column>


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
        <display:setProperty name="export.amount" value="boxOpciones"/>

            </display:table> 
            <%} %>
 
 </div>

 
 <div class="box boxpost">
		<h4>Agregar nuevo anexo <br> <small>Anexo <strong>no</strong> relacionado a un usuario</small></h4>
			<form name="form1" action="usuarioAction.do" class="form-horizontal">
  								<div class="form-group">
    								<label for="inputNombreAnexo" class="col-sm-4 control-label">Nombre</label>
    								<div class="col-sm-8">
      								<input name="nomAnx" type="text" class="form-control" id="inputNombreAnexo">
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputAnexo"  name="anx" class="col-sm-4 control-label">Anexo</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputAnexo">
    								</div>
  								</div>
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
      									<button  name="submit" type="submit" class="botoVerde">Agregar</button>
    									</div>
  									</div>
    							</div>
  							</form>
</div>
 
  <div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
  <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("form1");
frmvalidator.addValidation("nomAnx","req","Indique el nombre del anexo");
frmvalidator.addValidation("nomAnx","maxlen=30","Nombre del anexo no puede superar los 30 caracteres");
frmvalidator.addValidation("nomAnx","alnumspace");

frmvalidator.addValidation("anx","req","Debe Ingresar el anexo telefónico nuevo");
frmvalidator.addValidation("anx","maxlen=10","Anexo no puede superar los 10 caracteres");
frmvalidator.addValidation("anx","num");
</script>
</body>
</html>
