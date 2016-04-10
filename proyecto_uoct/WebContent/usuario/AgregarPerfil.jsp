<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@page import="java.util.List,java.util.LinkedList,java.util.Iterator"  %>
<%@page import="proyecto_uoct.common.VO.ModuloVO,proyecto_uoct.common.VO.FuncionalidadVO"  %>
<html>
<head>
<title>Agregar Perfil</title>
	<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
</head>

<body>


<%
List funs=(List)request.getAttribute("funcionalidades");
List mods=(List)request.getAttribute("modulos");
String sitemap="";
Iterator im=mods.iterator();
int cont_smenu=0;
%>



<h2>Agregar perfil</h2>
						
				 		<div class="box boxpost">
				 			<h4>Nuevo perfil</h4>
				 			
				 			<form class="form-horizontal" action="perfilAction.do">
  								<div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre del nuevo perfil</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputNombre">
    								</div>
  								</div>
  								
  								<hr>
  								
 <%

String html="";
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
  
  
  html+="<div class=\"form-group\">%n";
  html+="	<label class=\"col-sm-4 control-label\">"+m.getNombreModulo()+":</label>%n";
  html+="	<div class=\"col-sm-8\">%n";
 
  Iterator itemp=temp.iterator();
  while(itemp.hasNext()){
    FuncionalidadVO ftemp=(FuncionalidadVO)itemp.next();
  html+="		<div class=\"checkbox\">%n";
  html+="			<label>";  
  html+="				<input type=\"checkbox\" value=\""+ftemp.getIdFuncionalidad()+"\">%n";
  html+="						"+ftemp.getNombreFun()+"%n";
  html+="			</label>%n";
  html+="		</div>%n";
  }
  html+="		</div>%n";
  html+="	</div>%n";
}
}

%> 								
<%=String.format(html) %>
								
								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-offset-2 col-sm-10">
      									<button type="submit" class="botoVerde">Crear perfil</button>
    									</div>
  									</div>
    							</div>
  								
							</form>
          				
    					</div>
  						
  						<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>

						

</body>

</html>