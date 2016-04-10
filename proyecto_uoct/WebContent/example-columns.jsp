<jsp:root version="1.2" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:display="urn:jsptld:/displaytag_12">
  <jsp:directive.page contentType="text/html; charset=UTF8" />
   <jsp:directive.page import="java.util.Date,java.util.List,java.util.Iterator,java.text.SimpleDateFormat,proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.common.util.UtilString" />
  <jsp:include page="inc/header.jsp" flush="true" />
  
   <jsp:scriptlet>
  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
  List usuarios= (List) request.getAttribute("usuarios");
  String cumple="";
  UtilString us=new UtilString();

</jsp:scriptlet>

<div align="left">
   <jsp:scriptlet>
   if(usuarios!=null){
Iterator iusus=usuarios.iterator();
request.setAttribute("ususes",iusus);
</jsp:scriptlet>

<h3>Cumpleaños del Personal</h3>

  <display:table requestURI="index2Action.do" name="ususes" id="usus" class="its">

  <display:column title="Nombre" sortable="true" sortProperty="nombreUsu">
 <jsp:scriptlet>
 if(((UsuarioVO)usus).getNombreUsu2()!=null)
 	{
 		out.println(((UsuarioVO)usus).getNombreUsu()+" "+((UsuarioVO)usus).getNombreUsu2()+" "+((UsuarioVO)usus).getApellUsu());
 	}
	 else{
	 	out.println(((UsuarioVO)usus).getNombreUsu()+" "+((UsuarioVO)usus).getApellUsu());
	 } 
 	</jsp:scriptlet>
  
  
  
  </display:column>

  <display:column title="Cumpleaños" sortProperty="cumpleanos" sortable="true">
   <jsp:scriptlet> if(((UsuarioVO)usus).getCumpleanos()!=null){
      cumple=us.formatoFecha(sdf.format(((UsuarioVO)usus).getCumpleanos()));
      out.println(cumple);
  		}else{
	  	out.println("&nbsp;");
  		}
  </jsp:scriptlet>
  </display:column>

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
  <display:setProperty name="export.pdf.label" value="<img src='util/img/pdf.gif' width='10' height='10'>"/>
  <display:setProperty name="export.excel.label" value="<img src='util/img/excel.gif' width='10' height='10'>"/>
  <display:setProperty name="export.amount" value="list"/>

</display:table>
   <jsp:scriptlet> } </jsp:scriptlet>
</div>


  
</jsp:root>


