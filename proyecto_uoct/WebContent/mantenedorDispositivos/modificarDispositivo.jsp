
<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,proyecto_uoct.mantenedorDispositivos.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje                  = (String) request.getAttribute("mensaje");

LinkedList listaSubsistema      = new LinkedList();
listaSubsistema                 = (LinkedList) request.getAttribute("listaSubsistema");
LinkedList listaEmpresa         = new LinkedList();
listaEmpresa                    = (LinkedList) request.getAttribute("listaEmpresa");
LinkedList listaTipoDispositivo = new LinkedList();
listaTipoDispositivo            = (LinkedList) request.getAttribute("listaTipoDispositivo");
Integer id_dispositivo          = (Integer) request.getAttribute("id_dispositivo");
String nombre                   = (String)  request.getAttribute("nombre");
String descripcion              = (String)  request.getAttribute("descripcion");
Integer id_subsistema           = (Integer) request.getAttribute("id_subsistema");
String nombre_subsistema        = (String)  request.getAttribute("nombre_subsistema");
Integer id_empresa              = (Integer) request.getAttribute("id_empresa");
String empresa                  = (String)  request.getAttribute("empresa");
Integer id_tipo_dispositivo     = (Integer) request.getAttribute("id_tipo_dispositivo");
String tipo_dispositivo         = (String)  request.getAttribute("tipo_dispositivo");


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
<link href="css/datepicker.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->


<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
function valida_envia(){
  document.formMantenedorDispositivo.submit();
}
</script>



</head>
<body onload="valida_cambioInicial();">
<br>
<div class="main">
<div class="container">
<div class="row clearfix">
<div class="col-sm-3 menuLateral">
<div class="panel-group" id="accordion" role="tablist"  aria-multiselectable="true"><br>
</div>
</div>
<div class="col-sm-6 desarrollo">
<h2>Editar Dispositivo</h2>
<div class="box boxpost">

  <%
        if (mensaje != null){
          out.print("<br>"+mensaje+"<br>");
        }
        %>

<h4>Datos del Dispositivo</h4>
<form class="form-horizontal" name="formMantenedorDispositivo" method="post" action="../mantenedorDispositivos/dispositivoAction.do">
<div class="form-group"> <label for="inputNombre"
class="col-sm-4 control-label">Nombre</label>
<div class="col-sm-8"> <input class="form-control" id="inputNombre"  name="nombre" type="text" value="<% out.print(nombre); %>" size="50" maxlength="50"> </div>
</div>
<div class="form-group"> <label for="inputDescripcion"
class="col-sm-4 control-label">Descripcion</label>
<div class="col-sm-8"> <input class="form-control" id="inputDescripcion" name="descripcion" type="text" value="<% out.print(descripcion); %>" size="50" maxlength="50"> </div>
</div>
<div class="form-group"> <label for="selectSubsistema"
class="col-sm-4 control-label">Subsistema</label>
<div class="col-sm-8">
<select class="form-control" id="selectSubsistema" name="subsistema">
               <%
                Iterator i = listaSubsistema.iterator();
                while (i.hasNext()) {
                  DispositivoVO subsistema = (DispositivoVO) i.next();
                  if(subsistema.getIdSubsistema().equals(id_subsistema)){
                    out.print("<option value=\"" + subsistema.getIdSubsistema()+ "\" selected>" + subsistema.getNombreSubsistema() + "</option>");
                   }
                  else{
                    out.print("<option value=\"" + subsistema.getIdSubsistema()+ "\">" + subsistema.getNombreSubsistema() + "</option>");
                  }
                }
                %>
           
</select>
</div>
</div>
<div class="form-group"> <label for="selectEmpresa"
class="col-sm-4 control-label">Empresa mantenedora</label>
<div class="col-sm-8">
<select class="form-control" id="selectEmpresa" name="empresaMantenedora">
                <%
                Iterator ii = listaEmpresa.iterator();
                while (ii.hasNext()) {
                  DispositivoVO empresam = (DispositivoVO) ii.next();
                  if(empresam.getIdEmpresa().equals(id_empresa)){
                    out.print("<option value=\"" + empresam.getIdEmpresa()+ "\" selected>" + empresam.getNombreEmpresa() + "</option>");
                   }
                  else{
                    out.print("<option value=\"" + empresam.getIdEmpresa()+ "\">" + empresam.getNombreEmpresa() + "</option>");
                  }
                }
                %>
</select>
</div>
</div>
<div class="form-group"> <label for="selectTipo"
class="col-sm-4 control-label">Tipo dispositivo</label>
<div class="col-sm-8">
<select class="form-control" id="selectTipo" name="tipoDispositivo">
               
                <%
                Iterator iii = listaTipoDispositivo.iterator();
                while (iii.hasNext()) {
                  DispositivoVO tipoDispositivo = (DispositivoVO) iii.next();
                  if(tipoDispositivo.getIdTipoDispositivo().equals(id_tipo_dispositivo)){
                    out.print("<option value=\"" + tipoDispositivo.getIdTipoDispositivo()+ "\" selected>" + tipoDispositivo.getTipoDispositivo() + "</option>");
                   }
                  else{
                    out.print("<option value=\"" + tipoDispositivo.getIdTipoDispositivo()+ "\">" + tipoDispositivo.getTipoDispositivo() + "</option>");
                  }
                }
                %>
</select>
</div>
</div>
<div class="boxOpciones">
<div class="form-group">
<div class="col-sm-12"> <button type="reset" class="botoGris"><span
class="glyphicons glyphicons-undo"></span> Reestablecer valores</button>
<button type="submit" class="botoVerde" name="hacia" value="Grabar">
<span class="glyphicons glyphicons-disk-save"></span> Guardar</button> 
</div>
<input type="hidden" name="id_dispositivo" value="<% out.print(id_dispositivo);%>">
                <input type="hidden" name="accion" value="-1">
</div>
</div>
</form>
</div>
<div class="verMas"> <a href="javascript:history.back()"><span
class="glyphicons glyphicons-undo"></span> Volver</a> <a
href="javascript:void(0)" class="pull-right"><span
class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
</div>
</div>
<br>
</div>
<!-- /row --> </div>
<!-- /container --> </div>
<!-- /main -->
<div class="container"> <footer> </footer>
<div class="row">
<div class="col-sm-12">
<p>Unidad Operativa de Control de Tránsito, <span id="pie"></span></p>
</div>
</div>
</div>
<!-- /container -->
<script
src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.es.min.js"></script>
<script src="js/moment.js"></script>
<script src="js/truncate.js"></script>
<script src="js/fullcalendar.min.js"></script>
<script src="js/uoct.js"></script>
<script src="js/uoct_falla1.js"></script>
</body>
</html>

