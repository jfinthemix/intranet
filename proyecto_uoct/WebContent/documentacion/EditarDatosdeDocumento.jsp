<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,java.util.Iterator,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.usuario.VO.UsuarioVO" errorPage="" %>
<%@ page import="proyecto_uoct.documentacion.VO.CliEntVO,proyecto_uoct.documentacion.VO.DocumentoVO" %>
<%@ page import="java.text.SimpleDateFormat,proyecto_uoct.documentacion.VO.DocumentodeListaVO,java.util.LinkedList" %>
<%

DocumentoVO detDoc = (DocumentoVO) request.getAttribute("detDoc");

List listausu=(List) request.getAttribute("listausu");

//--------------- separa una lista de responsables(usus_resp) y una lista de usuarios(usus)
List usus_resp = new LinkedList();
List usus = new LinkedList();

if (detDoc.getResponsableDoc()!=null && listausu!=null){
  List respons = detDoc.getResponsableDoc();//genera una lista con los responsables


  Iterator ilista = listausu.iterator();//un iterador para la lista completa de usuarios

  while(ilista.hasNext()){// para cada usuario
  boolean esR=false;
  UsuarioVO usu=(UsuarioVO)ilista.next();
  Iterator irespons= respons.iterator();//un iterador para la lista de responsables
  while (irespons.hasNext()){
    UsuarioVO r=(UsuarioVO)irespons.next();
    if (r.getIdUsu().intValue()==usu.getIdUsu().intValue()){
      usus_resp.add(usu);// si el usuario es responsable lo pone en una lista de responsables
      esR=true;
      break;
    }
  }
  if(!esR){
    usus.add(usu);// si no es responsable, lo pone en otra lista
  }
}
}










String mensaje =null;
if (request.getAttribute("mensaje")!=null){
mensaje = (String) request.getAttribute("mensaje");}

SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


%>
<html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- European format dd-mm-yyyy -->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script><!-- Date only with year scrolling -->
<!-- American format mm/dd/yyyy -->

<script language="JavaScript" src="calendar2.js" type="text/javascript"></script><!-- Date only with year scrolling -->


<script LANGUAGE="JavaScript" type="text/javascript">

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


<!-- agrega un doc ant a la lista-->
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



<!-- valida la agregacion de un doc Ant a la lista-->

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













</script>



<!-----   Script para marcar todos los items de un select multivariable        -------->


<script type="text/javascript">


function mover(){
var ntext =form_regDoc.id_tipoRel.options[form_regDoc.id_tipoRel.selectedIndex].text;
var nval = form_regDoc.id_tipoRel.options[form_regDoc.id_tipoRel.selectedIndex].value;

ntext = ntext +" - "+ form_regDoc.id_docRel.value;
nval = nval + "--" + form_regDoc.id_docRel.value;

var no = new Option();
no.value =nval;
no.text = ntext;
form_regDoc.listaRel.options[form_regDoc.listaRel.options.length] = no;

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
</script>

<script language="JavaScript" type="text/javascript">

function esEntrante(form){
form.id_tipoSaliente.disabled=true;
form.id_docSaliente.disabled=true;


form.id_tipoEntrante.disabled=false;
form.id_docEntrante.disabled=false;
}

function esSaliente(form){
form.id_tipoEntrante.disabled=true;
form.id_docEntrante.disabled=true;

form.id_tipoSaliente.disabled=false;
form.id_docSaliente.disabled=false;
}

// Script para el traspaso de variables desde pop-up
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

function confirmarEliminacion(f){
  borrar = window.confirm('Si elimina este documento se eliminaran todos los datos asociados a el, y si algun otro documento lo incluye como anterior, esta relación también será eliminada(pero no el doc. relacionado). ¿Desea continuar con la Eliminación de este Doc.?');
  (borrar)?f.submit():'return false';
}


</script>

<link href="../util/styla.css" rel="stylesheet" type="text/css">

<!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>


</head>

<body>
<table width="761" border="0">
  <tr>
    <td width="761" height="76" valign="top"><h3 align="center">Editar Datos de Documento
        <%
if (mensaje != null) {
%>
      </h3>
      <div align="center"><%=mensaje %>
        <%
}

%>
      </div></td>
  </tr>
  <form name="form_regDoc" action="documentoAction.do" method="post">
    <tr>
      <td> <div align="center">
          <input type="hidden" name="hacia" value="actualizarDoc" />
          <input type="hidden" name="idDoc" value="<%=detDoc.getIdDoc()%>" />
          <table width="650" border="1" align="center">
            <tr bgcolor="#CCCCCC">
              <td><div align="right"><strong>C&oacute;digo</strong></div></td>
              <td><h3><strong><font color="#000000"><%= detDoc.getTipoDoc() %> - <%= detDoc.getCodDoc() %></font> </strong></h3></td>
            </tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>Cliente</strong></div></td>
            <td><input type="text" name="nomCli" value="<%=detDoc.getNomCli()+" "+detDoc.getApeCli() %>" />
              <input type="hidden" name="idCli" value="<%=detDoc.getIdCli()%>" />
              &nbsp;<a href="clienteAction.do?hacia=busCli_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=1000,height=400, scrollbars=1'); return false;"  >cambiar
              el cliente</a></td>
            </tr>
            <tr>
              <td width="28%" height="54" bgcolor="#ADD8E4">
                <div align="right"><strong>Mat.</strong></div></td>
              <td> <div align="left">

                  <textarea name="materia" cols="50" rows="2" id="materia"><%=detDoc.getMateriaDoc() %></textarea>
                </div></td>
            </tr>
            <tr>
              <td width="28%" bgcolor="#ADD8E4"> <div align="right"><strong>Fecha
                  de Ingreso o Egreso</strong></div></td>
              <td> <div align="left">
                  <input name="fecha_IO" type="Text" readonly size="15" value="<%=sdf.format(detDoc.getFechaio()) %>" >
                  <a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date"></a>
                </div></td>
            </tr>
            <tr>
              <td width="28%" bgcolor="#ADD8E4"> <div align="right"><strong>Fecha
                  de Documento</strong></div></td>
              <td> <div align="left">
                  <input name="fecha_doc" type="Text" readonly size="15" value="<%=sdf.format(detDoc.getFechaDoc()) %>">
                  <a href="javascript:cal2.popup();"> <img src="img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date">
                  </a></div></td>
            </tr>
            <tr>
              <td width="28%" bgcolor="#ADD8E4"> <p align="right"><strong>Ant.</strong></p></td>
              <td> <div align="CENTER">
                  <table width="460" border="0">
                    <tr>
                      <td width="41%" rowspan="2"><input type="hidden" name="idDocAnt"/>
                        <input type="text"  size="2" name="tipoDocAnt"/>
                        -
                        <input name="codDocAnt" type="text" size="10" maxlength="20">
                        <a href="documentoAction.do?hacia=abuscarDocs_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=1000,height=400, scrollbars=1'); return false;"  >Buscar</a></td>
                      <td width="22%"><input type="button" name="Input" value="Agregar&gt;&gt;" onClick="validaAnt();"></td>
                      <td width="37%" rowspan="2"> <select name="listaRel" size="3" multiple id="select">
                          <%
                 if (detDoc.getDocsRelacionados()!=null){

                   List docsRel = detDoc.getDocsRelacionados();
                   Iterator irel = docsRel.iterator();
                   while (irel.hasNext()){
                   DocumentodeListaVO unDocRel = (DocumentodeListaVO) irel.next();

                   %>
                          <option value="<%= unDocRel.getIdDoc()%>"><%=unDocRel.getTipoDoc() %> - <%= unDocRel.getCodDoc()%></option>
                          <%

                   }

                 }

                  %>
                        </select></td>
                    </tr>
                    <tr>
                      <td><input type="button" name="Submit2" value="Borrar" onClick="borrar(document.form_regDoc.listaRel);"></td>
                    </tr>
                  </table>
                </div></td>
            </tr>
            <tr>
              <td width="28%" bgcolor="#ADD8E4"> <div align="right"><strong>Responsable</strong></div></td>
              <td><div align="left">
                  <table width="482" border="0">
                    <tr>
                      <td width="105"><select multiple size="4" name="list2">
                          <%
                  Iterator m = usus.iterator();
                  while (m.hasNext()){
                    UsuarioVO usu=(UsuarioVO) m.next();
                    if(usu.getIsActivo()){
                      if(usu.getNombreUsu2()!=null){
                        out.println("<option value="+usu.getIdUsu()+">"+usu.getNombreUsu()+" "+usu.getNombreUsu2()+" "+usu.getApellUsu()+"</option>");
                      }else{
                        out.println("<option value="+usu.getIdUsu()+">"+usu.getNombreUsu()+" "+usu.getApellUsu()+"</option>");
                      }
                    }
                  }
            %>
                        </select></td>
                      <td width="77"><input type="button" value="Agregar&gt;&gt;"
      onClick="move(this.form.list2,this.form.list1)" name="B2"> <br> <input type="button" value="&lt;&lt; Quitar"
      onClick="move(this.form.list1,this.form.list2)" name="B1"> </td>
                      <td width="248"><select multiple size="4" name="list1">
                          <%
                Iterator it = usus_resp.iterator();
                while (it.hasNext()){
                  UsuarioVO u = (UsuarioVO) it.next();
                  if(u.getNombreUsu2()!=null){
                    out.println("<option value="+u.getIdUsu()+">"+u.getNombreUsu()+" "+u.getNombreUsu2()+" "+u.getApellUsu()+"</option>");

                  }else{
                    out.println("<option value="+u.getIdUsu()+">"+u.getNombreUsu()+" "+u.getApellUsu()+"</option>");
                  }
                }
                %>
                        </select> </td>
                    </tr>
                  </table>
                </div></td>
            </tr>
            <!--
            <tr>
              <td width="28%" bgcolor="#ADD8E4"> <div align="right"><strong>Resumen
                  del Documento</strong></div></td>
              <td> <div align="left">
                  <textarea name="descrip" cols="50" rows="7" wrap="PHYSICAL"><%= detDoc.getResumen() %></textarea>
                </div></td>
            </tr>
            -->
          </table>
        </div></td>
    </tr>
    <tr>
      <td><div align="center">
          <input type="submit" name="Submit" value="Guardar" onClick="javascript:SelectAllList(document.form_regDoc.list1);">
          <script language="JavaScript" type="text/javascript">
    var frmvalidator  = new Validator("form_regDoc");
    frmvalidator.addValidation("idDoc","req","Falta el identificador del documento");

    frmvalidator.addValidation("nomCli","req","Debe ingresar el Cliente");
    frmvalidator.addValidation("idCli","req","Debe indicar el cliente");
    frmvalidator.addValidation("idCli","num");

    frmvalidator.addValidation("materia","req","Debe ingresar materia");
    frmvalidator.addValidation("materia","maxlen=1000","Nombre no puede superar los 1000 caracteres");

    frmvalidator.addValidation("fecha_IO","req","Debe Ingresar fecha de ingreso");
    frmvalidator.addValidation("fecha_doc","req","Debe Ingresar fecha del documento");
    frmvalidator.addValidation("list1","req","Debe Ingresar Responsable del documento");



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
        </div></td>
    </tr>
  </form>
  <tr>
    <td> <form action="documentoAction.do" name="adminDoc" method="POST">
        <div align="left">
          <input type="hidden" name="idDoc" value="<%=detDoc.getIdDoc()%>">
          <input type="hidden" name="hacia" value="adminArchivosDoc">
          <input type="hidden" name="tipoDoc" value="<%=detDoc.getTipoDoc()%>" />
          <input type="hidden" name="codDoc" value="<%=detDoc.getCodDoc()%>" />
          <input type="submit" name="Submit3" value="Admin. archivos de este documento">
        </div>
      </form></td>
  </tr>
  <tr>
    <td> <form action="documentoAction.do" name="borrarDoc" method="POST">
        <div align="left">
          <input type="hidden" name="id_doc" value="<%=detDoc.getIdDoc()%>">
          <input type="hidden" name="hacia" value="borrarDoc">
          <input type="button" value="Eliminar este documento" onclick="confirmarEliminacion(this.form);"/>
        </div>
      </form></td>
  </tr>

</table>
<hr>
 <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div><div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a> </div>
</body>
</html>
