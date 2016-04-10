<%@taglib uri="/WEB-INF/lib/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/lib/struts-nested.tld" prefix="nested"%>
<%@page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.usuario.VO.UsuarioVO" errorPage=""%>
<%@page import="proyecto_uoct.documentacion.VO.*,proyecto_uoct.proyecto.VO.*"%>
<%@page import="java.text.SimpleDateFormat,java.util.LinkedList"%>

<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%

SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

DetalleProyectoVO detproy= (DetalleProyectoVO) request.getAttribute("detalleProy");
DetalleOTVO ot=(DetalleOTVO) request.getAttribute("detalleOT");
List estados=(List)request.getAttribute("estados");
String mensaje = null;
if (request.getAttribute("mensaje") != null) {
  mensaje = (String) request.getAttribute("mensaje");
}



//--------------- separa una lista de responsables(usus_resp) y una lista de usuarios(usus)
List usus_resp = new LinkedList();
List usus = new LinkedList();

if (ot.getEncargados()!=null && detproy.getEquipo()!=null){
  Iterator ilista = detproy.getEquipo().iterator();//un iterador para la lista completa de usuarios

  while(ilista.hasNext()){// para cada usuario
  boolean esR=false;
  UsuarioVO usu=(UsuarioVO)ilista.next();
  Iterator irespons= ot.getEncargados().iterator();//un iterador para la lista de responsables
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




</script>


</head>

<body>
<html:form action="/proyectos/proyectoAction.do" name="form1"  method="post" type="proyecto_uoct.proyecto.controller.AgregarDocProy_fb" enctype="multipart/form-data">
<table width="750" border="0">
  <tr>
    <td><h3>Edicion de Orden de Trabajo </h3></td>
  </tr>
  <tr>
    <td>

      <input type="hidden" name="hacia" value="actualizarOT" />
      <input type="hidden" name="idProy" value="<%=detproy.getIdProy() %>" />
      <input type="hidden" name="idOT" value="<%=ot.getIdOT()%>" />
      <table width="600" border="1" align="left">
        <!--DWLayoutTable-->
        <tr>
          <td width="36%" bgcolor="#ADD8E4"><div align="right"><strong>Nombre
              OT:</strong></div></td>
          <td width="64%"><%=ot.getNomOT() %></td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4"><div align="right"><strong>Iniciativa asociada:</strong></div></td>
          <td><%=detproy.getNomProy() %></td>
        </tr>
        <tr>
          <td bgcolor="#A6F7BA"> <div align="right"><strong>Ejecutor
              de la OT)*:</strong> </div></td>
          <td> <input type="hidden" name="idCli" value="<%=ot.getCli().getIdCli() %>" />
            <input type="text" name="nomCli" readonly="readonly" value="<%=ot.getCli().getNomCli()+" "+ot.getCli().getApeCli() %>"/>
            &nbsp;<a href="../documentacion/clienteAction.do?hacia=busCli_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=1000,height=400, scrollbars=1'); return false;"  >Buscar
            Ejecutor</a> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4"><div align="right"><strong>Personas a Cargo:*</strong></div></td>
          <td> <table width="186" border="0">
              <tr>
                <td width="16" rowspan="2"><select multiple size="4" name="list2" >
                    <%if (usus!=null){
                Iterator ie=usus.iterator();
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
                <td width="107"> <input type="button" value=" Agregar &gt;&gt;  "
              onClick="move(this.form.list2,this.form.list1)" name="B2"/> </td>
                <td width="49" rowspan="2"> <select multiple size="4" name="list1">
                    <%if (usus_resp!=null){
                Iterator ie2=usus_resp.iterator();
                while(ie2.hasNext()){
                UsuarioVO u2=(UsuarioVO)ie2.next();
                if(u2.getNombreUsu2()!=null){
                %>
                    <option value="<%=u2.getIdUsu() %>"><%=u2.getNombreUsu()+" "+u2.getNombreUsu2()+" "+ u2.getApellUsu() %></option>
                    <%}
             else{%>
                    <option value="<%=u2.getIdUsu() %>"><%=u2.getNombreUsu()+" "+ u2.getApellUsu() %></option>
                    <%}
              } }%>
                  </select> </td>
              </tr>
              <tr>
                <td><input type="button" value="&lt;&lt;  Quitar "
            onClick="move(this.form.list1,this.form.list2)" name="B1"> </td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4"><div align="right"><strong>Fecha de la OT:*</strong></div></td>
          <td><font color="#FFFFFF">
            <input type="text" name="fechaOT" id="fechaOT" size=12 readonly="readonly" value="<%=sdf.format(ot.getFechaOT())%>">
            <a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar fecha">
            </a> </font> </td>
        </tr>
        <tr bgcolor="#FF0000">
          <td><div align="right"><font color="#FFFFFF"><strong>Plazo de Ejecuci&oacute;n:*</strong></font></div></td>
          <td><font color="#FFFFFF">
            <input name="plazo" type="text" size="10" value="<%=ot.getPlazo()%>">
            d&iacute;as</font> </td>
        </tr>
        <tr bgcolor="#FF0000">
          <td><div align="right"><font color="#FFFFFF"><strong>Fecha de Plazo
              de Ejecuci&oacute;n:*</strong></font></div></td>
          <td> <div align="left"> <font color="#FFFFFF">
              <input type="text" name="fecha_pejec" id="fecha_pejec" size=12 readonly="readonly" value="<%=sdf.format(ot.getVencimiento()) %>">
              <a href="javascript:cal2.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date">
              </a> </font></div></td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4"><div align="right"><strong>Cobro EP:</strong></div></td>
          <td><input type="text" name="ep" value="<%if(ot.getEP()!=null){out.print(ot.getEP());}%>" ></td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4"><div align="right"><strong>Estado de la OT:</strong></div></td>
          <td><select name="idEstado">
              <%if(estados!=null){
              Iterator i=estados.iterator();
              while (i.hasNext()){
                IdStrVO es=(IdStrVO) i.next();
                %>
              <option value="<%=es.getId()%>" <%if(es.getId().equals(ot.getIdEstado())){out.print(" selected ");}%>>
              <%=es.getStr()%></option>
              <%}
              }
              %>
            </select></tr>
      </table>
    </td>
  </tr>
  <tr>
    <td><input type="submit" value="Guardar" name="Submit" onClick="javascript:SelectAllList(document.form1.list1);"/>
      &nbsp; <input name="reset" type="reset" value="Reestablecer valores"></td>
  </tr>
</table>
</html:form>

      <script language="JavaScript" type="text/javascript">
    var frmvalidator  = new Validator("form1");
    frmvalidator.addValidation("idCli","req","Debe indicar el cliente de la Orden de Trabajo");

    frmvalidator.addValidation("list1","req","Debe indicar el(los) responsable(s) de la OT");

    frmvalidator.addValidation("fechaOT","req","Debe indicar la fecha de la OT");

    frmvalidator.addValidation("plazo","req","Debe indicar la cantidad de días de plazo para la ejecución de la OT");
    frmvalidator.addValidation("plazo","num");

    frmvalidator.addValidation("fecha_pejec","req","Debe indicar la fecha de vencimiento de la OT");

    frmvalidator.addValidation("ep","maxlen=10","El estado de pago no puede superar los 10 caracteres");
    frmvalidator.addValidation("ep","alnumspace");

  </script>
       <script language="JavaScript" type="text/javascript">
var cal1 = new calendar1(document.forms['form1'].elements['fechaOT']);
cal1.year_scroll = true;
cal1.time_comp = false;
var cal2 = new calendar1(document.forms['form1'].elements['fecha_pejec']);
cal2.year_scroll = true;
cal2.time_comp = false;
//-->
</script>

<input type="button" value="Volver atrás" onclick="history.back()" style="font-family: Verdana; font-size: 12 pt">

<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/iniciativas.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>

