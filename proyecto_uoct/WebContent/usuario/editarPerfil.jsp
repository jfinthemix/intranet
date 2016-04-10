<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@page import="java.util.List,java.util.LinkedList,java.util.Iterator"  %>
<%@page import="proyecto_uoct.common.VO.ModuloVO,proyecto_uoct.common.VO.FuncionalidadVO"  %>
<%
String nomPer = (String)request.getAttribute("nomPer");
Integer idPer=(Integer) request.getAttribute("idPer");
List fp= (List)request.getAttribute ("FdelP");
List f= (List)request.getAttribute ("f");
List m = (List)request.getAttribute("modulos");



%>

<head>
<title>Edici&oacute;n de Perfil:<%=nomPer %></title>
<link href="../util/stylb.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript">
function confirmarEnvio(f){
borrar = window.confirm('El perfil será eliminado solo si no existen usuarios asociados a él.\r\n ¿Seguro que desea eliminar el perfil');
(borrar)?f.submit():'return false';
}
</script>

</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="left">Perfil:<%=nomPer %></h3></td>
  </tr>
  <tr>
    <td><form action="perfilAction.do" name="id_funcs" method="POST">
        <div align="left">
          <input type="hidden" name="hacia" value="actualizarPerfil"/>
          <input type="hidden" name="id_perfil" value="<%=idPer %>" />
        </div>
        <div align="left">
          <table width="450" border="0">
            <%

Iterator im=m.iterator();

while (im.hasNext()){                  // PARA CADA MODULO
ModuloVO mod= (ModuloVO)im.next();



List tempF=new LinkedList();              //CREA UNA LISTA TEMPORAL
int contF=0;

// PARA CADA FUNCIÓN , SI PERTENECE AL MODULO, LA GUARDA EN LA LISTA TEMPORAL CONTANDO CUANTAS VAN

Iterator iF=f.iterator();

while (iF.hasNext()){
  FuncionalidadVO fun=(FuncionalidadVO)iF.next();
  if(fun.getIdModulo().equals(mod.getIdModulo())){
    tempF.add(fun);
    contF++;
  }
}


// SI EN LA LISTA TEMPORAL HAY FUNCIONALIDADES IMPRIME EL MODULO Y LAS FUNCIONALIDADES
if(contF!=0){
  out.println(
    "<tr>"+
    "<td colspan=\"2\"><h2>"+mod.getNombreModulo()+"</h2></td>"+
    "</tr>");

    Iterator itempF=tempF.iterator();

    while(itempF.hasNext()){

      FuncionalidadVO ftemp=(FuncionalidadVO)itempF.next();

      String chk="";

      Iterator iFP=fp.iterator();

      while(iFP.hasNext()){
        FuncionalidadVO ftempFP=(FuncionalidadVO)iFP.next();
        if(ftemp.getIdFuncionalidad().equals(ftempFP.getIdFuncionalidad())){
        chk=" checked ";
        break;
        }
      }
        out.println("    <tr>"+
        "<td width=\"45\"><div align=\"center\">"+
        "<input type=\"checkbox\" "+chk+" name=\"id_funcionalidad\" value=\""+ftemp.getIdFuncionalidad()+"\" />"+
        "&nbsp;"+
        "</div></td>"+
        "<td width=\"395\">"+ftemp.getNombreFun()+"</td>"+
        "</tr>");

      }
    }
  }
  %>
          </table>
          <input type="submit" name="Submit2" value="Actualizar" />
        </div>
      </form></td>
  </tr>
  <tr>
    <td><form action="perfilAction.do" method="POST" name="elim" >
        <input type="hidden" name="hacia" value="eliminarPerfil"/>
        <input type="hidden" name="id_perfil" value="<%=idPer %>" />
        <div align="right">
          <input type="button" name="Submit" value="Eliminar Este Perfil" onClick="confirmarEnvio(this.form);">
        </div>
      </form></td>
  </tr>
</table>
<br>
<hr>
  <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>
<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>

</body>
</html>


