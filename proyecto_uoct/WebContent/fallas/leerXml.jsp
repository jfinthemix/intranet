<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,proyecto_uoct.fallas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%@page buffer="30kb"%>
<%
List vieneNodo                 = (List)  request.getAttribute("nodo"); //viene del fallasAction.java
List nodoXml             = new LinkedList();

List lista_dispositivo         = (List) request.getAttribute("lista_dispositivo");
String dispo                   = "";
String probl                   = "";
Integer id                     = new Integer(0);

int bandera                    = 1;
int banderaAnterior            = 0;
Integer sistemaParaIngresar    = new Integer(3);
Integer subsistemaParaIngresar = new Integer(5);

String nombre_sistema     = "";
String nombre_subsistema  = "";
String nombre_dispositivo = "";
%>
<html>
<head>
<title>Leer Xml</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript">
function valida_envia(){
  document.form_xml.submit();
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000">
  <h2>Leer Xml</h2>
  <br>
<form name="form_xml" method="post" action="fallasAction.do">
<%

Iterator i   = vieneNodo.iterator();
int indice = 1;
FallaVO nodoX   = null;
while (i.hasNext()) {
    FallaVO nodo = (FallaVO) i.next();
    nodoX = new FallaVO();
    if(nodo.getDispositivoXml()!= null) {
      dispo=nodo.getDispositivoXml();
     }
    else if(nodo.getCodigoXml()!= null) {
      probl = nodo.getCodigoXml();
      Iterator ii = lista_dispositivo.iterator();
      while (ii.hasNext()) {
        FallaVO dispositivo = (FallaVO) ii.next();
        if(dispositivo.get_nom_dispositivo().equals(dispo)){//dispo de la tabla dispositivo es igual a dispo de la lista de xml
           id = dispositivo.get_id_dispositivo();
           nombre_sistema     = "CENSO";
           nombre_subsistema  = "URTDs";
           nombre_dispositivo = dispositivo.get_nombre_dispositivo();
         }
       }
       //hacia=Reclamar
       nodoX.setDispositivoXml(dispo);
       //System.out.println("-"+nodoX.getDispositivoXml());
       nodoX.setCodigoXml(probl);
       //System.out.print("<<"+nodoX.getCodigoXml());
       nodoXml.add(nodoX);
     }
   }

/***************************/
/***************************/

int inicio = 1;
%>

<%

String codi = "";

if(nodoXml!=null){
  Iterator ilf=nodoXml.iterator();
  request.setAttribute("nodoXml",ilf);
  %>

  <display:table  name="nodoXml" export="true" class="its" id="lf" pagesize="40" requestURI="fallasAction.do">
    <display:column media="html excel pdf" title="n" >
      <%=inicio %>
    </display:column>
    <display:column media="html excel pdf" title="dispositivo"  sortable="false" >
      <%=(((FallaVO)lf).getDispositivoXml())%>
      <% dispo=((FallaVO)lf).getDispositivoXml(); %>
    </display:column>
    <display:column media="html excel pdf" title="codigo falla" sortable="false" sortProperty="CodigoXml">
      <% out.print(((FallaVO)lf).getCodigoXml());
         codi = (((FallaVO)lf).getCodigoXml());%>
      <% probl = ((FallaVO)lf).getCodigoXml(); %>
    </display:column>
    <display:column  media="html" title="reclamar" sortable="false" >
      <%
      Iterator iv = lista_dispositivo.iterator();
      while (iv.hasNext()) {
        FallaVO dispositivo = (FallaVO) iv.next();
        if(dispositivo.get_nom_dispositivo().equals(dispo)){//dispo de la tabla dispositivo es igual a dispo de la lista de xml
          id = dispositivo.get_id_dispositivo();
          nombre_sistema     = "CENSO";
          nombre_subsistema  = "URTDs";
          nombre_dispositivo = dispositivo.get_nombre_dispositivo();
        }
      }
      //hacia=Reclamar
      out.print("<a href='fallasAction.do?hacia=ingresar_falla&sistema="+ sistemaParaIngresar +"&nomSistema=" + nombre_sistema+ "&subsistema="+ subsistemaParaIngresar +"&nomSubsistema=" + nombre_subsistema+ "&dispositivo="+ id + "&nomDispositivo="+ dispo + "&problema="+ probl + "&vieneDe=ingresar_fallas'>Reclamar</a>");
      inicio ++;
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
    <display:setProperty name="export.excel.filename" value="Fallas URTD.xls"/>
    <display:setProperty name="export.pdf.filename" value="Fallas URTD.pdf"/>
    <display:setProperty name="export.xml.filename" value="Fallas URTD.xml"/>
    <display:setProperty name="export.csv.filename" value="Fallas URTD.csv"/>
    <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10' border='0'>"/>
    <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10' border='0'>"/>
    <display:setProperty name="export.amount" value="list"/>
    </display:table>
    <%
  }


%>
</form>
</body>
</html>
