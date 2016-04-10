<%@taglib uri="/WEB-INF/lib/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/lib/struts-nested.tld" prefix="nested"%>
<%@page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.usuario.VO.UsuarioVO" errorPage=""%>
<%@page import="proyecto_uoct.documentacion.VO.*,proyecto_uoct.proyecto.VO.DetalleProyectoVO"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%

List tiposDoc = (List) request.getAttribute("tiposDoc");

SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

DetalleProyectoVO detproy= (DetalleProyectoVO) request.getAttribute("detalleProy");

Iterator ii = tiposDoc.iterator();
String mensaje = null;
if (request.getAttribute("mensaje") != null) {
  mensaje = (String) request.getAttribute("mensaje");
}
%>
<html>
<head>
<title>Registrar OT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">



<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
  <!-- European format dd-mm-yyyy -->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script>  <!-- Date only with year scrolling -->

<!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<script LANGUAGE="JavaScript" type="text/javascript">

<!--------- Script para habilitar y deshabilitar los File-->
  function cambiaDoc(file,chk){
    if(chk.checked==false){
      file.disabled=true;
    }
    if(chk.checked==true){
      file.disabled=false;
    }

  }


<!-- Script para el traspaso de variables
sortitems = 1;

function move(fbox,tbox) {
for(var i=0; i<fbox.options.length; i++) {
if(fbox.options[i].selected && fbox.options[i].value != "") {
var no = new Option();
no.value = fbox.options[i].value;
no.text = fbox.options[i].text;
tbox.options[tbox.options.length] = no;
fbox.options[i].value = "";
fbox.options[i].text = "";
   }
}
BumpUp(fbox);
if (sortitems) SortD(tbox);
}


function BumpUp(box)  {
for(var i=0; i<box.options.length; i++) {
if(box.options[i].value == "")  {
for(var j=i; j<box.options.length-1; j++)  {
box.options[j].value = box.options[j+1].value;
box.options[j].text = box.options[j+1].text;
}
var ln = i;
break;
   }
}
if(ln < box.options.length)  {
box.options.length -= 1;
BumpUp(box);
   }
}







function SortD(box)  {
var temp_opts = new Array();
var temp = new Object();
for(var i=0; i<box.options.length; i++)  {
temp_opts[i] = box.options[i];
}
for(var x=0; x<temp_opts.length-1; x++)  {
for(var y=(x+1); y<temp_opts.length; y++)  {
if(temp_opts[x].text > temp_opts[y].text)  {
temp = temp_opts[x].text;
temp_opts[x].text = temp_opts[y].text;
temp_opts[y].text = temp;
temp = temp_opts[x].value;
temp_opts[x].value = temp_opts[y].value;
temp_opts[y].value = temp;
      }
   }
}
for(var i=0; i<box.options.length; i++)  {
box.options[i].value = temp_opts[i].value;
box.options[i].text = temp_opts[i].text;
   }
}

// -->


<!----  -   Script para marcar todos los items de un select multivariable        -------->


<!--- agrega un doc ant a la lista-->
function agregarAnt(){
 var  iddoc=form1.idDocAnt.value;
 var tipodoc=form1.tipoDocAnt.value;
 var coddoc=form1.codDocAnt.value;
var ntext =tipodoc+"-"+coddoc;
var nval =iddoc;


form1.idDocAnt.value="";
form1.tipoDocAnt.value="";
form1.codDocAnt.value="";

var no = new Option();
no.value =nval;
no.text = ntext;
form1.listaRel.options[form1.listaRel.options.length] = no;
}



<!-- valida la agregacion de un doc Ant a la lista-->

function validaAnt(){
 var  iddoc=form1.idDocAnt.value;
  for (i=0;i<form1.listaRel.options.length;i++){
    if(iddoc==form1.listaRel.options[i].value){
      alert("ya existe en la lista");
      return false;
    }
  }
    if(iddoc==""){ alert("Buscar Documento para agregar");return false;}
  agregarAnt();
}


function borrar(CONTROL){
for (var i =0;i<CONTROL.length;i++){
if (CONTROL.options[i].selected ==true){
CONTROL.options[i]=null;
}
}
}


function SelectAllList(CONTROL){
for(var i = 0;i < CONTROL.length;i++){
CONTROL.options[i].selected = true;
SelectAllList2(document.form1.listaRel);
}
}

function SelectAllList2(CONTROL){
for(var i = 0;i < CONTROL.length;i++){
CONTROL.options[i].selected = true;
}
}


<!--   Para el traspaso de variables entre Ventanas -->
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

  function pasaDoc(idDoc,codDoc,tipoDoc){
    form1.idDocAnt.value = idDoc;
    form1.codDocAnt.value = codDoc;
    form1.tipoDocAnt.value = tipoDoc;

    otra.window.close();
  }


 function valLargoFile(arch)
{
  archivo=arch.value; //reemplazar nombre del file
  largo=30; //reemplazar la longitud del campo
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


</script>


</head>

<body>
<html:form action="/proyectos/proyectoAction.do" name="form1"  method="post" type="proyecto_uoct.proyecto.controller.AgregarDocProy_fb" enctype="multipart/form-data">
<table width="750" border="0">
  <tr>
    <td><h3>Registro de NLO asociada a Iniciativa</h3></td>
  </tr>
  <tr>
    <td><input type="hidden" name="hacia" value="registrarNLO" /> <input type="hidden" name="idProy" value="<%=detproy.getIdProy() %>" />
      <table width="567" border="1" align="left">
        <!--DWLayoutTable-->
        <tr>
          <td width="31%" height="23" bgcolor="#ADD8E4"><strong>Iniciativa asociada:*</strong></td>
          <td width="69%"><%=detproy.getNomProy() %> <input type="hidden" name="idCli" value="<%=detproy.getIdCliente() %>" />
          </td>
        </tr>
        <tr>
          <td height="23" bgcolor="#ADD8E4"><strong>Fecha de la NLO*:</strong></td>
          <td><div align="left">
              <input name="fechaDoc" type="Text" readonly size="15" >
              <a href="javascript:cal2.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
              </a> </div></td>
        </tr>
        <tr>
          <td height="23" bgcolor="#ADD8E4"><strong>Descripci&oacute;n de la NLO*:</strong></td>
          <td><textarea name="nota" cols="60" rows="5" wrap="ON" ></textarea></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td><h4>Registro del Documento asociado</h4></td>
  </tr>
  <tr>
    <td><table width="594" border="1" align="left">
        <tr bgcolor="#CCCCCC">
          <td width="184" bgcolor="#ADD8E4"> <div align="right"><strong> C&oacute;digo
              del Documento* </strong> </div></td>
          <td width="335"> <div align="left">
              <select name="idTipoDoc">
                <%
                  while (ii.hasNext()) {
                    TipoDocVO tipo = (TipoDocVO) ii.next();
                    out.println("<option value=" + tipo.getIdTipo() + ">" + tipo.getTipo() + "</option>");
                  }
                  %>
              </select>
              <input type="text" maxlength="100" name="codDoc" size="20"/>
            </div></td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4"> <div align="right"><strong>Personas a Cargo:*</strong></div></td>
          <td> <table width="357" border="0">
              <tr>
                <td width="68" rowspan="2"><select multiple size="4" name="list2" >
                    <%if (detproy.getEquipo()!=null){
                Iterator ie=detproy.getEquipo().iterator();
                while(ie.hasNext()){
                UsuarioVO u=(UsuarioVO)ie.next();
                if(u.getNombreUsu2()!=null){
                %>
                    <option value="<%=u.getIdUsu() %>"><%=u.getNombreUsu()+" "+u.getNombreUsu2()+" "+ u.getApellUsu() %></option>
                    <%}
             else{%>
                    <option value="<%=u.getIdUsu() %>"><%=u.getNombreUsu()+" "+ u.getApellUsu() %></option>
                    <%}
              } }%>
                  </select> </td>
                <td width="93" height="29"> <input type="button" value=" Agregar &gt;&gt;  "
              onClick="move(this.form.list2,this.form.list1)" name="B2"/> </td>
                <td width="182" rowspan="2"> <select multiple size="4" name="list1">
                  </select> </td>
              </tr>
              <tr>
                <td><input type="button" value="&lt;&lt;  Quitar "
            onClick="move(this.form.list1,this.form.list2)" name="B1"> </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td width="184" bgcolor="#ADD8E4"> <div align="right"><strong>Mat.*:</strong>
            </div></td>
          <td> <div align="left">
              <textarea cols="50" rows="2" name="materia"><%=detproy.getNomProy() %></textarea>
            </div></td>
        </tr>
        <tr>
          <td width="184" bgcolor="#ADD8E4"> <div align="right"><strong>Fecha
              de Recepción/Despacho*</strong> </div></td>
          <td> <div align="left">
              <input name="fecha_IO" type="Text" readonly size="15" >
              <a href="javascript:cal3.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
              </a> </div></td>
        </tr>
        <tr>
          <td width="184" bgcolor="#ADD8E4"> <p align="right"> <strong>Ant.:</strong>
            </p></td>
          <td> <div align="CENTER">
              <table width="400" border="0">
                <tr>
                  <td width="202" rowspan="2"> <input type="hidden" name="idDocAnt"/>
                    <input type="text" size="3" name="tipoDocAnt" readonly="readonly">
                    -
                    <input type="text"  maxlength="20" name="codDocAnt" size="15" readonly="readonly"/>
                    <a href="../documentacion/documentoAction.do?hacia=abuscarDocs_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=1000,height=400, scrollbars=1'); return false;"  >Buscar
                    </a></td>
                  <td width="77"> <div align="center">
                      <input type="button" value="Agregar&gt;&gt;" name="Input" onClick="validaAnt();"/>
                    </div></td>
                  <td width="97" rowspan="2"> <select multiple="multiple" name="listaRel" size="3" >
                    </select> </td>
                </tr>
                <tr>
                  <td> <div align="center">
                      <input type="button" value="Borrar" name="Submit2" onClick="borrar(document.form1.listaRel);"/>
                    </div></td>
                </tr>
              </table>
            </div></td>
        </tr>
        <tr>
          <td width="184" bgcolor="#ADD8E4"> <div align="right"><strong>Resumen
              del Documento*</strong> </div></td>
          <td> <div align="left">
              <textarea name="descrip" cols="50" rows="7" wrap="ON"></textarea>
            </div></td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4"> <div align="right"><strong>Subir documento</strong>
            </div></td>
          <td><table border="0">
              <tr>
                <td> <html:file property="eldoc" disabled="true" onblur="valLargoFile(this.form.eldoc)"/>
                </td>
                <td> <input type="checkbox" name="eldoc_chk"  onClick="cambiaDoc(this.form.eldoc,this.form.eldoc_chk)" />
                </td>
              </tr>
              <tr>
                <td> <html:file property="eldoc1" disabled="true" onblur="valLargoFile(this.form.eldoc1)"/>
                </td>
                <td> <input type="checkbox" name="eldoc1_chk" onClick="cambiaDoc(this.form.eldoc1,this.form.eldoc1_chk)" />
                </td>
              </tr>
              <tr>
                <td> <html:file property="eldoc2" disabled="true" onblur="valLargoFile(this.form.eldoc2)"/>
                </td>
                <td> <input type="checkbox" name="eldoc2_chk" onClick="cambiaDoc(this.form.eldoc2,this.form.eldoc2_chk)"/>
                </td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td><input type="submit" value="Guardar" name="regNLOIni_submit" onClick="javascript:SelectAllList(document.form1.list1);"/></td>
  </tr>
</table>
</html:form>
<script language="JavaScript" type="text/javascript">
    var frmvalidator  = new Validator("form1");
    frmvalidator.addValidation("fechaDoc","req","Debe indicar fecha del documento");
    frmvalidator.addValidation("nota","req","Debe indicar la descripción de la NLO");
    frmvalidator.addValidation("nota","maxlen=500","Descripción de la NLO no puede superar los 500 caracteres");

    frmvalidator.addValidation("idCli","req","Debe indicar el Ejecutor de la Orden de Trabajo");

    frmvalidator.addValidation("list1","req","Debe indicar el(los) responsable(s) de la NLO");

    frmvalidator.addValidation("idTipoDoc","req","Debe indicar el tipo de documento de la OT");

    frmvalidator.addValidation("codDoc","req","Debe indicar el código del documento de la OT");
    frmvalidator.addValidation("codDoc","maxlen=1000","El código del documento no puede superar los 100 caracteres");


    frmvalidator.addValidation("materia","req","Debe ingresar materia del documento ");
    frmvalidator.addValidation("materia","maxlen=1000","Materia no puede superar los 1000 caracteres");


    frmvalidator.addValidation("fecha_IO","req","Debe indicar fecha de ingreso del documento");


    frmvalidator.addValidation("descrip","req","Debe indicar el resumen del documento");
    frmvalidator.addValidation("descrip","maxlen=1000","La descripción no puede superar los 1000 caracteres");

  </script>
<script language="JavaScript" type="text/javascript">

var cal2 = new calendar1(document.forms['form1'].elements['fechaDoc']);
cal2.year_scroll = true;
cal2.time_comp = false;

var cal3 = new calendar1(document.forms['form1'].elements['fecha_IO']);
cal3.year_scroll = true;
cal3.time_comp = false;

//-->
</script>

<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/iniciativas.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>

