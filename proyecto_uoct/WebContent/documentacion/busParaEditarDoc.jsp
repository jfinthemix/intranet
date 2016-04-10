<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List,java.util.Iterator,proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.documentacion.VO.DocumentodeListaVO" errorPage=""%>
<%@ page import="java.text.SimpleDateFormat,proyecto_uoct.documentacion.VO.TipoDocVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

  List listausu = (List) request.getAttribute("listausu");
  List tiposentrantes = (List) request.getAttribute("tiposentrantes");
  List tipossalientes = (List) request.getAttribute("tipossalientes");
  List listadocs = (List) request.getAttribute("listadocs");
  Iterator iv = tiposentrantes.iterator();
  Iterator iiv = tipossalientes.iterator();

  Integer id_tipousu= null;
  if (request.getAttribute("id_tipousu")!= null){
  id_tipousu= (Integer)request.getAttribute("id_tipousu");
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
function habilitaEnIni(){
buscarDocs.enIni.disabled=false;
}
function deshabilitaEnIni(){
buscarDocs.enIni.disabled=true;
}
</script>

</head>
<body>
<table width="770" border="0">
  <tr>
    <td><h3 align="center">Editar Documentos</h3></td>
  </tr>
  <tr>
    <td> <form action="documentoAction.do" name="buscarDocs">
        <div align="center">
          <input type="hidden" name="hacia" value="buscarParaEditarDoc"/>
          <table width="494" border="1" align="center">
            <tr bgcolor="#F7FBC4">
              <td bgcolor="#FAA564" colspan="2">
                <label>
                <input name="tipoBus" type="radio" value="1" checked onClick="deshabilitaEnIni();">
                <strong>Por c&oacute;digo</strong></label> </td>
              <td colspan="2"> <select name="id_tipoDoc">
                  <option value="0" selected></option>
                  <%
                    while (iv.hasNext()) {
                      TipoDocVO tipo = (TipoDocVO) iv.next();
                      if(tipo.getIsActivo()){
                      out.println("<option value=" + tipo.getIdTipo() + ">" + tipo.getTipo() + "</option>");
                    }}
                  %>
                  <%
                    while (iiv.hasNext()) {
                      TipoDocVO tipo = (TipoDocVO) iiv.next();
                      out.println("<option value=" + tipo.getIdTipo() + ">" + tipo.getTipo() + "</option>");
                    }
                  %>
                </select>
                -
                <input type="text"  maxlength="20" name="codDoc" size="15"/></td>
            </tr>
            <tr bgcolor="#33FF00">
              <td height="18" colspan="2" bgcolor="#FAA564">
                <input type="radio" name="tipoBus" value="2" onClick="habilitaEnIni();">
                <strong>Sentido</strong></td>
              <td colspan="2" bgcolor="#ADD8E4">
                <label>
                <input type="radio" name="id_sentido" value="1" checked>
                Entrante </label> <label>
                <input type="radio" name="id_sentido" value="2">
                Saliente </label> </td>
            </tr>
            <tr>
              <td colspan="2" bgcolor="#ADD8E4"><div align="right"><strong>Materia</strong></div></td>
              <td colspan="2"> <input type="text" name="materia"> </td>
            </tr>
            <tr>
              <td colspan="2" rowspan="2" bgcolor="#ADD8E4"><div align="right"><strong>Fecha</strong></div></td>
              <td width="10%">Desde:</td>
              <td width="62%"> <input type="Text" name="fecha_ini" readonly> <a href="javascript:cal1.popup();">
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
              <td colspan="2" bgcolor="#ADD8E4"><div align="right"><strong>Palabra
                  Clave(**)</strong></div></td>
              <td colspan="2"><input name="palClave" type="text" id="palClave"></td>
            </tr>
            <tr>
              <td colspan="2" bgcolor="#ADD8E4"><div align="right"><strong>Encargado</strong></div></td>
              <td colspan="2"> <select name="id_usuario">
                  <option selected value="0">nombre del usuario</option>
                  <%
        Iterator ius = listausu.iterator();
        while (ius.hasNext()) {
          UsuarioVO usu = (UsuarioVO) ius.next();
          if(usu.getNombreUsu2()!=null){
            out.println("<option value=" + usu.getIdUsu() + ">" + usu.getNombreUsu() + " " + usu.getNombreUsu2() + " " + usu.getApellUsu() + "</option>");
          }else{
            out.println("<option value=" + usu.getIdUsu() + ">" + usu.getNombreUsu() + " " + usu.getApellUsu() + "</option>");
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
              <td colspan="4"> <div align="center">
                  <input type="submit" name="gobuscardoc" value="Buscar">
                </div></td>
            </tr>
          </table>
        </div>
      </form>
      <div align="center">
        <script language="JavaScript" type="text/javascript">
				var cal1 = new calendar1(document.forms['buscarDocs'].elements['fecha_ini']);
				cal1.year_scroll = true;
				cal1.time_comp = false;
				var cal2 = new calendar1(document.forms['buscarDocs'].elements['fecha_fin']);
				cal2.year_scroll = true;
                                cal2.time_comp = false;
		</script>
      </div></td>
  </tr>
  <tr>
    <td><div align="center">(**) palabra que se buscar&aacute; en el nombre del
        Cliente, apellido del Cliente, Mat. y Resumen del doc. </div></td>
  </tr>
  <tr>
    <td> <div align="center">
        <%if (listadocs != null) {
   Iterator idocs = listadocs.iterator();
   request.setAttribute("docses",idocs);
   %>
        <display:table class="its" name="docses" pagesize="15" requestURI="documentoAction.do" id="docss">
        <display:caption>Documentos Encontrados </display:caption>
        <display:column title="Codigo(Editar)" sortable="true" href="documentoAction.do?hacia=aeditarDoc" paramId="id_doc" paramProperty="idDoc">
        <%=((DocumentodeListaVO)docss).getTipoDoc()+"-"+((DocumentodeListaVO)docss).getCodDoc()%> </display:column>

        <display:column title="Materia" property="materiaDoc" class="texto"></display:column>

        <display:column title="Fecha de Ingreso o Egreso" sortable="true" sortProperty="fechaio" maxLength="14"><%=sdf.format(((DocumentodeListaVO)docss).getFechaio())%> </display:column>

        <display:column title="Estado" sortable="true" sortProperty="idEstado">
        <% if (((DocumentodeListaVO)docss).getIdEstado()){
        out.println("Respondido");
      }else{
        out.println("Pendiente");
      }
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
        <%} %>
      </div></td>
  </tr>
</table>

<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div><div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a> </div>

   </body>
</html>

