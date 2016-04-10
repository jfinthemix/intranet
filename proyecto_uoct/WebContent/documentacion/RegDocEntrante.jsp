<%@taglib uri="/WEB-INF/lib/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/lib/struts-nested.tld" prefix="nested"%>
<%@page language="java" import="java.sql.*,java.util.List,java.util.Iterator,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.usuario.VO.UsuarioVO" errorPage=""%>
<%@page import="proyecto_uoct.documentacion.VO.CliEntVO,proyecto_uoct.documentacion.VO.TipoDocVO"%>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%
  List listausu = (List) request.getAttribute("listausu");
  Iterator liu = listausu.iterator();
  List tiposentrantes = (List) request.getAttribute("tiposentrantes");
  Iterator i = tiposentrantes.iterator();
  List tipossalientes = (List) request.getAttribute("tipossalientes");
  String mensaje = null;
  if (request.getAttribute("mensaje") != null) {
    mensaje = (String) request.getAttribute("mensaje");
  }
%>
<html>
  <head>
    <title>  Ingreso de documentación</title>
    <meta http-equiv="Content-Type"  content="text/html; charset=iso-8859-1">
      <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">

        <!-- European format dd-mm-yyyy -->
        <script language="JavaScript" src="calendar1.js" type="text/javascript"></script>  <!-- Date only with year scrolling -->


        <!-- validador -->
        <script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

        <script LANGUAGE="JavaScript" type="text/javascript">

//<!--------- Script para habilitar y deshabilitar los File-->
  function cambiaDoc(file,chk){
    if(chk.checked==false){
      file.disabled=true;
    }
    if(chk.checked==true){
      file.disabled=false;
    }

  }


//<!--- agrega un doc ant a la lista-->
function agregarAnt(){
 var  iddoc=form_regDoc.idDocAnt.value;
 var tipodoc=form_regDoc.tipoDocAnt.value;
 var coddoc=form_regDoc.codDocAnt.value;
var ntext =tipodoc+"-"+coddoc;
var nval =iddoc;


form_regDoc.idDocAnt.value="";
form_regDoc.tipoDocAnt.value="";
form_regDoc.codDocAnt.value="";

var no = new Option();
no.value =nval;
no.text = ntext;
form_regDoc.listaRel.options[form_regDoc.listaRel.options.length] = no;
}



//<!-- valida la agregacion de un doc Ant a la lista-->

function validaAnt(){
 var  iddoc=form_regDoc.idDocAnt.value;
  for (i=0;i<form_regDoc.listaRel.options.length;i++){
    if(iddoc==form_regDoc.listaRel.options[i].value){
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
SelectAllList2(document.form_regDoc.listaRel);
}
}

function SelectAllList2(CONTROL){
for(var i = 0;i < CONTROL.length;i++){
CONTROL.options[i].selected = true;
}
}


//<!--   Para el traspaso de variables entre Ventanas -->
var otra=null;
function popUp(href, target, features) {
    otra = window.open(href, target, features);
    otra.window.focus();
  }


function pasaCli(nomCli, idCli){
    form_regDoc.nomCli.value = nomCli;
    form_regDoc.idCli.value = idCli;
    otra.window.close();
  }

  function pasaDoc(idDoc,codDoc,tipoDoc){
    form_regDoc.idDocAnt.value = idDoc;
    form_regDoc.codDocAnt.value = codDoc;
    form_regDoc.tipoDocAnt.value = tipoDoc;

    otra.window.close();
  }

</script>


<script type="text/javascript">

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



<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="750" border="0">
  <tr>
    <td width="760" ><h3 align="center">Ingreso de documentación
        <%if (mensaje != null) {%>
      </h3>
      <div align="left"><font color="red"><%=mensaje %></font></div>
        <%}%>
      </td>
  </tr>
  <html:form action="/documentacion/documentoAction.do" name="form_regDoc"  method="post" type="proyecto_uoct.documentacion.controller.RegDocFormBean" enctype="multipart/form-data">
  <tr>
    <td> <div align="center">
        <input type="hidden"  name="hacia" value="ingresarDocumento"/>
        <table width="750" border="1" align="center">
          <tr bgcolor="#CCCCCC">
            <td width="143"> <div align="right"><strong>
                <input type="hidden" value="1" name="sentido" />
                C&oacute;digo * </strong> </div></td>
            <td width="591"> <div align="left">
                <select name="idTipoDoc">
                  <option value="0">----</option>
                  <%
              while (i.hasNext()) {
                TipoDocVO tipo = (TipoDocVO) i.next();
                if(tipo.getIsActivo() && !tipo.getEnIniciativas()){
                out.println("<option value=" + tipo.getIdTipo() + ">" + tipo.getTipo() + "</option>");
        	    }}
            	%>
                </select>
                <input type="text" maxlength="100" name="codDoc" size="30"/>
              </div></td>
          </tr>
          <tr>
            <td bgcolor="#C0FACF">
              <div align="right"><strong>Remitente(cliente)*</strong></div></td>
            <td><input type="hidden" name="idCli" value="" /> <input type="text" name="nomCli" readonly="readonly" />
              &nbsp;<a href="clienteAction.do?hacia=busCli_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=1000,height=400, scrollbars=1'); return false;"  >Buscar
              Cliente</a> </td>
          </tr>
          <tr>
            <td width="143" bgcolor="#C0FACF">
              <div align="right"><strong>Mat.*:</strong>
              </div></td>
            <td> <div align="left">
                <textarea cols="60" rows="3" name="materia"></textarea>
              </div></td>
          </tr>
          <tr>
            <td width="143" height="28" bgcolor="#C0FACF">
              <div align="right"><strong>Fecha
                de Ingreso*</strong> </div></td>
            <td> <div align="left">
                <input name="fecha_IO" type="Text" readonly size="15" >
                <a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date">
                </a> </div></td>
          </tr>
          <tr>
            <td width="143" bgcolor="#C0FACF">
              <div align="right"><strong>Fecha
                de Documento*</strong> </div></td>
            <td> <div align="left">
                <input name="fecha_doc" type="Text" readonly size="15" >
                <a href="javascript:cal2.popup();"> <img src="img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date"></a>
              </div></td>
          </tr>
          <tr>
            <td width="143" bgcolor="#C0FACF">
              <p align="right"> <strong>Ant.:</strong>
              </p></td>
            <td> <div align="CENTER">
                <table width="602" border="0" align="left">
                  <tr>
                    <td width="49%" rowspan="2"> <input type="hidden" name="idDocAnt"/>
                      <input type="text" size="5" name="tipoDocAnt" readonly="readonly">
                      -
                      <input type="text"  maxlength="20" name="codDocAnt" size="15" readonly="readonly"/>
                      <a href="documentoAction.do?hacia=abuscarDocs_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=1000,height=400, scrollbars=1'); return false;"  >Buscar</a></td>
                    <td width="14%"> <div align="center">
                        <input type="button" value="Agregar&gt;&gt;" name="Input" onClick="validaAnt();"/>
                      </div></td>
                    <td width="37%" rowspan="2"> <select multiple="multiple" name="listaRel" size="3" >
                      </select></td>
                  </tr>
                  <tr>
                    <td> <div align="center">
                        <input type="button" value="Borrar" name="Submit2" onClick="borrar(document.form_regDoc.listaRel);"/>
                      </div></td>
                  </tr>
                </table>
              </div></td>
          </tr>
          <tr>
            <td width="143" bgcolor="#C0FACF">
              <div align="right"><strong>Responsable*</strong>
              </div></td>
            <td> <div align="left">
                <table width="601" border="0">
                  <tr>
                    <td width="141" rowspan="2"> <select multiple="multiple" name="list2" size="4">
                        <%
	                    while (liu.hasNext()) {
                              UsuarioVO usu = (UsuarioVO) liu.next();
                              if(usu.getIsActivo()==true){
                                if(usu.getNombreUsu2()!=null){
                                  out.println("<option value=" + usu.getIdUsu() + ">" + usu.getNombreUsu() + " " + usu.getNombreUsu2() + " " + usu.getApellUsu() + "</option>");
                                }else{
                                  out.println("<option value=" + usu.getIdUsu() + ">" + usu.getNombreUsu() + " " + usu.getApellUsu() + "</option>");
                                }
                              }
                            }
        	          %>
                      </select> </td>
                    <td width="77"> <div align="center">
                        <input type="button" value="Agregar&gt;&gt;" name="B2" onClick="move(this.form.list2,this.form.list1)"/>
                      </div></td>
                    <td width="369" rowspan="2"> <select multiple="multiple" name="list1" size="4">
                      </select> </td>
                  </tr>
                  <tr>
                    <td> <div align="center">
                        <input type="button" value="&lt;&lt;Quitar" name="B1" onClick="move(this.form.list1,this.form.list2)"/>
                      </div></td>
                  </tr>
                </table>
              </div></td>
          </tr>
         <!-- <tr>
            <td width="143" bgcolor="#C0FACF">
              <div align="right"><strong>Resumen*</strong>
              </div></td>
            <td> <div align="left">
                <textarea name="descrip" cols="60" rows="5" wrap="PHYSICAL"></textarea>
              </div></td>
          </tr>
          -->
          <tr>
            <td height="72" bgcolor="#C0FACF">
              <div align="right"><strong>Subir
                documento</strong> </div></td>
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
        </table>
      </div></td>
  </tr>
  <tr>
    <td> <div align="center">
        <input type="submit" value="Guardar" name="Submit" onClick="javascript:SelectAllList(document.form_regDoc.list1);"/>
      </div></td>
  </tr>
  </html:form>
  <script language="JavaScript" type="text/javascript">

  var frmvalidator  = new Validator("form_regDoc");

  frmvalidator.addValidation("idTipoDoc","dontselect=0","Debe ingresar el tipo de documento");

  frmvalidator.addValidation("codDoc","req","Debe ingresar el código del documento");
  frmvalidator.addValidation("codDoc","maxlen=100","El código del documento no puede superar los 100 caractéres");

  frmvalidator.addValidation("idCli","req","Debe indicar el cliente");
  frmvalidator.addValidation("idCli","num");

  frmvalidator.addValidation("materia","req","Debe ingresar materia");
  frmvalidator.addValidation("materia","maxlen=1000","Nombre no puede superar los 1000 caracteres");

  frmvalidator.addValidation("fecha_IO","req","Debe Ingresar fecha de ingreso");

  frmvalidator.addValidation("fecha_doc","req","Debe Ingresar fecha del documento");

  frmvalidator.addValidation("list1","req","Debe Ingresar Responsable del documento");

<!--//  frmvalidator.addValidation("descrip","req","Debe Ingresar descripción del documento");
//  frmvalidator.addValidation("descrip","maxlen=3500","La descrición no puede superar los 3500 caracteres");
//-->
  </script>
  <script language="JavaScript" type="text/javascript">
<!-- // create calendar object(s) just after form tag closed
// specify form element as the only parameter (document.forms['formname'].elements['inputname']);
// note: you can have as many calendar objects as you need for your application
var cal1 = new calendar1(document.forms['form_regDoc'].elements['fecha_IO']);
cal1.year_scroll = true;
cal1.time_comp = false;
var cal2 = new calendar1(document.forms['form_regDoc'].elements['fecha_doc']);
cal2.year_scroll = true;
cal2.time_comp = false;
//-->
</script>
</table>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div><div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a> </div></p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

</body>

</html>

