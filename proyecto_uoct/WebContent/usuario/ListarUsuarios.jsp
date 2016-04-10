<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@ page import="java.sql.*,java.util.List,java.util.Iterator,proyecto_uoct.usuario.VO.UsuarioVO"%>

<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List listausu= (List) request.getAttribute("listausu");
int col=0;

//Gson gson = new Gson();

//String lala=gson.toJson(listausu).toString();

%>



<html>
<head>
<title>Usuarios</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>


<div class="box boxpost">
<h4>Usuarios de la Intranet</h4>





  <%if(listausu!=null){
Iterator i= listausu.iterator();
  request.setAttribute("usus",i);

  %>
        <display:table name="usus" class="table table-striped table-bordered table-hover tablesorter" requestURI="usuarioAction.do" id="usus">
        <display:column  title="Nombre" href="javascript:Llamadalink('verDatosUsuario','#')" paramId="id_usu" paramProperty="idUsu" >
        <% if(((UsuarioVO)usus).getNombreUsu2()!=null){%>
        <%=((UsuarioVO)usus).getNombreUsu()+" "+ ((UsuarioVO)usus).getNombreUsu2()+" "+((UsuarioVO)usus).getApellUsu()%>
        <%}else{ %>
        <%=((UsuarioVO)usus).getNombreUsu()+" "+((UsuarioVO)usus).getApellUsu()%>
        <%} %>
        </display:column>
        <display:column title="UserName"  > <%=((UsuarioVO)usus).getUsername() %> </display:column>
        <display:column title="Area" > <%=((UsuarioVO)usus).getArea() %> </display:column>
        <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
        <display:setProperty name="basic.empty.showtable" value="true"/>
        <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/> <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
         </display:table>
        <%} %>
      
  </div>
  

						
	<script>
function Llamadalink(hacia,link){
	link=link.replace('#','');
	link='usuario/usuarioAction.do'+link+'&hacia='+hacia;
	LlamadaPagina(link);
	
	
}



function CargadoPaginaInicial(){
	
	$('#usus').DataTable({
		"bFilter": false
	   ,"iDisplayLength": 10	
	   ,"bLengthChange": false
	   ,"language": {
		    "paginate": {
		        "previous": "<<"
		        ,"next":">>"
		        
		      }
			,"info":"Mostrando _START_ a _END_ de _TOTAL_",
		    }
	});
	
	
}
</script>

</body>
</html>
