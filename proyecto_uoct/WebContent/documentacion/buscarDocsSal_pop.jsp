<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,java.util.Iterator,proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.documentacion.VO.DocumentodeListaVO" errorPage=""%>
<%@ page import="java.text.SimpleDateFormat,proyecto_uoct.documentacion.VO.TipoDocVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

  List listausu = (List) request.getAttribute("listausu");
  List tipossalientes = (List) request.getAttribute("tipossalientes");
  List listadocs = (List) request.getAttribute("listadocs");
  Iterator iiv = tipossalientes.iterator();

  String mensaje=(String) request.getAttribute("mensaje");

    if (listadocs != null && listadocs.size()==0) {
      mensaje="No hay documentos coincidentes con los parámetros ingresados";
    }

%>
<html>
<head>
<title>Buscar documento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- European format dd-mm-yyyy -->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script><!-- Date only with year scrolling -->
<!-- American format mm/dd/yyyy -->
<script language="JavaScript" src="calendar2.js" type="text/javascript"></script><!-- Date only with year scrolling -->
<link href="../util/styla.css" rel="stylesheet" type="text/css">


<script type="text/javascript">
function pasaDoc(idDoc,codDoc,tipoDoc){
	window.opener.pasaDoc(idDoc,codDoc,tipoDoc);
}


function habilitaEnIni(){
buscarDocs.enIni.disabled=false;
}
function deshabilitaEnIni(){
buscarDocs.enIni.disabled=true;
}
</script>

</head>
<body>
<table width="750" border="0">
  <tr>
    <td><div align="center">
        <h3>Buscar Documentos Salientes</h3>
      </div></td>
  </tr>
  <tr>
    <td> <div align="center">
        <% if (mensaje!=null){out.println(mensaje);} %>
      </div>
      <form action="documentoAction.do" name="buscarDocs">
        <div align="center">
          <input type="hidden" name="hacia" value="buscarDocsSal_pop"/>
          <table width="56%" border="1" align="center">
            <tr bgcolor="#F7FBC4">
              <td width="34%" bgcolor="#FAA564">
<p>
                  <label>
                  <input name="tipoBus" type="radio" value="1" checked onclick="deshabilitaEnIni();">
                  <strong>Por c&oacute;digo</strong> </label>
                </p></td>
              <td colspan="3"> <label></label> <select name="idTipoDoc">
                  <option value="0" selected></option>
                  <%
                    while (iiv.hasNext()) {// lista los tipos de documentos salientes
                      TipoDocVO tipo = (TipoDocVO) iiv.next();
                      if(tipo.getIsActivo()){
                      out.println("<option value=" + tipo.getIdTipo() + ">" + tipo.getTipo() + "</option>");
                    }}
                  %>
                </select>
                -
                <input type="text"  maxlength="50" name="codigoDoc" size="20"/></td>
            </tr>
            <tr bgcolor="#33FF00">
              <td width="34%" bgcolor="#FAA564"> <input type="radio" name="tipoBus" value="2" onclick="habilitaEnIni();"> 
                <strong>Sentido</strong> </td>
              <td colspan="3" bgcolor="#ADD8E4">
<label>
                <input name="id_sentido" type="radio" value="2" checked>
                Saliente </label></td>
            </tr>
            <tr>
              <td width="34%" bgcolor="#ADD8E4"><div align="right"><strong>Materia</strong></div></td>
              <td colspan="2"> <input type="text" name="materia"> </td>
            </tr>
            <tr>
              <td rowspan="2" bgcolor="#ADD8E4"><div align="right"><strong>Fecha</strong></div></td>
              <td width="10%">Desde:</td>
              <td width="56%"> <input type="Text" name="fecha_ini" readonly> <a href="javascript:cal1.popup();">
                <img src="img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date">
                </a> </td>
            </tr>
            <tr>
              <td>Hasta:</td>
              <td> <input type="Text" name="fecha_fin" readonly> <a href="javascript:cal2.popup();">
                <img src="img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date">
                </a> </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Palabra Clave(**):</strong></div></td>
              <td colspan="2"><input type="text" name="palClave"></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Encargado</strong></div></td>
              <td colspan="2"> <select name="id_usuario">
                  <option selected value="0">nombre del usuario</option>
                  <%
        Iterator ius = listausu.iterator();
        while (ius.hasNext()) {
          UsuarioVO usu = (UsuarioVO) ius.next();
          if(usu.getNombreUsu2()!=null){
            out.println("<option value=" + usu.getIdUsu() + ">" + usu.getNombreUsu() + " " + usu.getNombreUsu2() + " " + usu.getApellUsu() + "</option>");
          }else{
            out.println("<option value=" + usu.getIdUsu() + ">" + usu.getNombreUsu() + " " +usu.getApellUsu() + "</option>");
          }
        }
      %>
                </select> </td>
            </tr>
            <tr>
              <td colspan="4"> <div align="center">
                  <input type="checkbox" name="enIni" value="true" disabled="disabled">
                  Incluir Documentos de Iniciativas de Inversi&oacute;n</div></td>
            </tr>
            <tr>
              <td colspan="3"> <div align="center">
                  <input type="submit" name="Submit63" value="Buscar">
                  &nbsp;&nbsp;&nbsp;
                  <input type="reset" name="Submit" value="Restablecer">
                </div></td>
            </tr>
          </table>
        </div>
      </form>
      <div align="center">
        <script language="JavaScript" type="text/javascript">
<!-- // create calendar object(s) just after form tag closed
				 // specify form element as the only parameter (document.forms['formname'].elements['inputname']);
				 // note: you can have as many calendar objects as you need for your application
				var cal1 = new calendar1(document.forms['buscarDocs'].elements['fecha_ini']);
				cal1.year_scroll = true;
				cal1.time_comp = false;
				var cal2 = new calendar1(document.forms['buscarDocs'].elements['fecha_fin']);
				cal2.year_scroll = true;
                                cal2.time_comp = false;
//-->
</script>
      </div></td>
  </tr>
  <tr>
    <td><div align="center">(**) palabra que se buscar&aacute; en el nombre del
        Cliente, apellido del Cliente, Mat. y Resumen del doc.</div></td>
  </tr>
  <tr>
    <td> <div align="center">
        <%if (listadocs != null) {
   Iterator idocs = listadocs.iterator();
   request.setAttribute("docses",idocs);
   %>
        <display:table class="its" name="docses" pagesize="15" requestURI="documentoAction.do" id="docss">
        <display:caption>Documentos Encontrados </display:caption>


				        <display:setProperty name="basic.empty.showtable" value="true"/> <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/> <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]
        {0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty> 
        <display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}
        [Sgte/Ultimo]"></display:setProperty>
         <display:setProperty name="export.csv" value="false"/>
        <display:setProperty name="export.xml" value="false"/> <display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.xml.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.csv.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/>





		 <display:column title="Codigo(seleccionar)">
        <a href="#" onClick="return pasaDoc('<%=((DocumentodeListaVO)docss).getIdDoc()%>','<%=((DocumentodeListaVO)docss).getCodDoc()%>','<%=((DocumentodeListaVO)docss).getTipoDoc()%>')"><%=((DocumentodeListaVO)docss).getTipoDoc()+"-"+((DocumentodeListaVO)docss).getCodDoc()%></a> </display:column> <display:column title="Materia"><%=((DocumentodeListaVO)docss).getMateriaDoc()%> </display:column> <display:column title="Fecha de Ingreso o Egreso"><%=sdf.format(((DocumentodeListaVO)docss).getFechaio())%> </display:column> <display:column title="Estado">
        <% if (((DocumentodeListaVO)docss).getIdEstado()){
        out.println("Respondido");
      }else{
        out.println("Pendiente");
      }
%>
        </display:column> </display:table>
        <%} %>
      </div></td>
  </tr>
</table>
<hr>
<div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a> </div>
</body>
</html>

