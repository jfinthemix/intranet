<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,proyecto_uoct.infoyrep.VO.ReportajeVO" errorPage="" %>
<%@page import="proyecto_uoct.documentacion.VO.TipoDocVO,java.util.List,java.util.Iterator,java.util.LinkedList" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List tsl=(List) request.getAttribute("tiposSalientes");

List lista= (List) request.getAttribute("lreps");
String mensaje=(String) request.getAttribute("mensaje");

%>
<html>
<head>
<title>Home-UOCT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" >
<meta http-equiv="Cache-Control" content="no-cache">
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<link href="util/styla.css" rel="stylesheet" type="text/css">

<style type="text/css">
#posi{
	position:absolute;
	top:0px;
	left:0px;
}

#tablaAncha{
heigth:600

}



</style>


<body>
<table  border="0" align="center">
  <tr>
    <td width="450"> <div align="left">
        <%if (lista!=null){
  Iterator lis=lista.iterator();
  request.setAttribute("lista",lis);%>

        <display:table id="reportaje" name="lista" class="its" requestURI="reportAction.do">

        <display:column style="tablaAncha"   title="Reportajes anteriores" href="info_instit_y_report/reportAction.do?hacia=detReportaje" maxWords="13" paramId="idRep" paramProperty="idRep">  <img alt="" src="util/img/news1.gif" border="0"/> <%=((ReportajeVO)reportaje).getTitRep() %> </display:column>

        <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
        <display:setProperty name="basic.empty.showtable" value="true"/>
        <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
      </display:table>
        <%       }  %>
      </div></td>
  </tr>
</table>
</div>
</body>
</html>
