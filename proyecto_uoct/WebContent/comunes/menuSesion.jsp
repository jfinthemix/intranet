<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="proyecto_uoct.Constantes.IconoModulo"%>
<%@page import="java.util.List,java.util.LinkedList,java.util.Iterator"  %>
<%@page import="proyecto_uoct.common.VO.ModuloVO,proyecto_uoct.common.VO.FuncionalidadVO"  %>

<script type="text/javascript">

$(document).ready(function(){
	
	LlamadaPagina("homeAction.do");
	
});



/*

function popUp(href, target, features) {
	  res = window.open(href, target, features);
	  res.window.focus();
	}
*/
</script>



<%
List funs=(List)request.getAttribute("funs");
List mods=(List)request.getAttribute("mods");
String sitemap="";
Iterator im=mods.iterator();
int cont_smenu=0;
while (im.hasNext()){                  // PARA CADA MODULO
ModuloVO m= (ModuloVO)im.next();

Iterator ifun=funs.iterator();

List temp=new LinkedList();              //CREA UNA LISTA TEMPORAL
int cont=0;

// PARA CADA FUNCIÓN DEL PERFIL DEL USUARIO, SI PERTENECE AL MODULO, LA GUARDA EN LA LISTA TEMPORAL CONTANDO CUANTAS VAN

while (ifun.hasNext()){
  FuncionalidadVO f=(FuncionalidadVO)ifun.next();
  if(f.getIdModulo().equals(m.getIdModulo())){
    temp.add(f);
    cont++;
  }
}


if(cont!=0){
  cont_smenu++;
   sitemap=sitemap+"<div class=\"panel panel-default\">%n";
   sitemap=sitemap+"	<a class=\"list-group-item panhead collapsed\" role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"+m.getNombreModulo().replaceAll("\\s","").replaceAll("\\.", "") +"\" aria-expanded=\"false\" aria-controls=\"collapse"+m.getNombreModulo().replaceAll("\\s","").replaceAll("\\.", "")+"\">%n";
   sitemap=sitemap+"		<span class=\"glyphicons "+IconoModulo.ObtieneIconoModulo(m.getIdModulo())+"\"></span>"+m.getNombreModulo()+"<b></b>%n";
   sitemap=sitemap+"	</a>%n";
   sitemap=sitemap+"	<div id=\"collapse"+m.getNombreModulo().replaceAll("\\s","").replaceAll("\\.", "")+"\" class=\"panel-collapse collapse\" role=\"tabpanel\">%n";
   sitemap=sitemap+"		<div class=\"list-group\">%n";
   
  Iterator itemp=temp.iterator();
  while(itemp.hasNext()){
    FuncionalidadVO ftemp=(FuncionalidadVO)itemp.next();

    if(ftemp.getPopup().intValue()==0){
      //sitemap=sitemap+  "<li><a href=\""+ftemp.getURLFun()+"\" Target=\"mainFrame\">-"+ftemp.getNombreFun()+"</a></li>";
    	sitemap=sitemap+"		<a href=\"javascript:void(0)\" onClick=\"LlamadaPagina('"+ftemp.getURLFun()+"')\" class=\"list-group-item\">"+ftemp.getNombreFun()+"</a>%n";
    }else{
    //+ "<li><a href=\"#\" onClick=popUp('"+ftemp.getURLFun()+"',this.target,'"+ftemp.getParams_popup()+"')>-"+ftemp.getNombreFun()+"</a></li>";
    }

    }
  sitemap=sitemap+"			</div>%n";
  sitemap=sitemap+"		</div>%n";
  sitemap=sitemap+"</div>%n";
  }
 // sitemap = sitemap+"</ul> </dd>";

}

 //sitemap=sitemap+ "</ul>";
%>


<div class="col-sm-3 menuLateral">
	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<div class="panel panel-default">
  									<a href="javascript:void(0)" class="list-group-item active collapsed" onClick="LlamadaPagina('homeAction.do');" >
    									<span class="glyphicon glyphicon-home"></span> Inicio
  									</a>
  								</div>
		<%=String.format(sitemap)%>
	</div>
</div>
