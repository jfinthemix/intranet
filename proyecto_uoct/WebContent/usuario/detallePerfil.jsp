<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@page import="java.util.List,java.util.LinkedList,java.util.Iterator"  %>
<%@page import="proyecto_uoct.common.VO.ModuloVO,proyecto_uoct.common.VO.FuncionalidadVO"  %>
<%
List funs=(List)request.getAttribute("funcionalidades");
List mods=(List)request.getAttribute("modulos");
String nomPer= (String)request.getAttribute("nomPer");


Iterator im=mods.iterator();
int cont_smenu=0;
%>

<head>
<title>Detalle de Perfil:<%=nomPer %></title>
<link href="../util/stylb.css" rel="stylesheet" type="text/css" />
</head>

<body >

<form action="perfilAction.do" name="id_funcs" method="POST">
  <h3 align="left">Detalle del Perfil:<%=nomPer %>
    <input type="hidden" name="hacia" value="ingresarPerfil"/>
  </h3>

  <table width="450" border="0">
<%


while (im.hasNext()){                  // PARA CADA MODULO
ModuloVO m= (ModuloVO)im.next();

Iterator ifun=funs.iterator();

List temp=new LinkedList();              //CREA UNA LISTA TEMPORAL
int cont=0;

// PARA CADA FUNCIÓN , SI PERTENECE AL MODULO, LA GUARDA EN LA LISTA TEMPORAL CONTANDO CUANTAS VAN

while (ifun.hasNext()){
  FuncionalidadVO f=(FuncionalidadVO)ifun.next();
  if(f.getIdModulo().equals(m.getIdModulo())){
    temp.add(f);
    cont++;
  }
}


// SI EN LA LISTA TEMPORAL HAY FUNCIONALIDADES IMPRIME EL MODULO Y LAS FUNCIONALIDADES
if(cont!=0){
  cont_smenu++;
  out.println(
    "<tr>"+
    "<td colspan=\"2\"><h4>"+m.getNombreModulo()+"</h4></td>"+
    "</tr>");

  Iterator itemp=temp.iterator();
  while(itemp.hasNext()){
    FuncionalidadVO ftemp=(FuncionalidadVO)itemp.next();
out.println("    <tr>"+
"<td width=\"45\"><div align=\"center\">"+
          "&nbsp;"+
"</div></td>"+
 "<td width=\"395\">"+ftemp.getNombreFun()+"</td>"+
    "</tr>");
    }
  }
}
%>
 </table>
</form>
<hr>

  <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>
<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>

</body>
</html>

