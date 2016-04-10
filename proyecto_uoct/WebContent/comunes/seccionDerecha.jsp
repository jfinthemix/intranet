<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@ page import="proyecto_uoct.common.VO.DatosSesVO, java.util.List,java.util.Iterator, proyecto_uoct.usuario.VO.UsuarioVO,java.text.SimpleDateFormat,java.util.Calendar,java.util.Date" %>
<%@page import="java.util.List,proyecto_uoct.reservas.VO.RecursoVO,proyecto_uoct.reservas.VO.ReservaVO,java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%

SimpleDateFormat hora = new SimpleDateFormat("HH:mm");


List recursos= (List)request.getAttribute("RECURSOS");
Iterator rec1=recursos.iterator();


  /*
  <div class="media reservas">
	<div class="media-left">
		<span class="glyphicon glyphicon-blackboard"></span>
</div>
	<div class="media-body">
		<h6>Sala Reunion Proy</h6>
		<ul>
			<li>Desde:10:30-Hasta:11:30</li>
		</ul>
	</div>
</div>
*/
   
  
String ListadoReservas="";
while (rec1.hasNext()){
  RecursoVO rec=(RecursoVO) rec1.next();

  //out.print("<p>"+rec.getNombre()+"<br/>");
  List reservas=rec.getReservas();
  if(reservas!=null && reservas.size()!=0){
    Iterator ires=reservas.iterator();
    while(ires.hasNext()){
      ReservaVO reser=(ReservaVO)ires.next();
      //out.println("&nbsp;Reservado HOY Desde:"+hora.format(reser.getHoraDeInicio())+"-Hasta:"+hora.format(reser.getHoraDeFin())+"</p>");
      
      ListadoReservas=ListadoReservas+"<div class=\"media reservas\">%n";
      ListadoReservas=ListadoReservas+"		<div class=\"media-left\">%n";
      ListadoReservas=ListadoReservas+"			<span class=\"glyphicon glyphicon-blackboard\"></span>%n";
      ListadoReservas=ListadoReservas+"		</div>";
      ListadoReservas=ListadoReservas+"		<div class=\"media-body\">%n";
      ListadoReservas=ListadoReservas+"			<h6>"+rec.getNombre()+"</h6>%n";
      ListadoReservas=ListadoReservas+"				<ul>";
      ListadoReservas=ListadoReservas+"					<li>Desde:"+hora.format(reser.getHoraDeInicio())+"-Hasta:"+hora.format(reser.getHoraDeFin())+"</li>%n";
      ListadoReservas=ListadoReservas+"				</ul>%n";
      ListadoReservas=ListadoReservas+"		</div>%n";
      ListadoReservas=ListadoReservas+"</div>%n";
    }
  }/*else{
  //out.print("     &nbsp; Sin reservas para hoy</p>");}
 	  ListadoReservas=ListadoReservas+"<div class=\"media reservas\">%n";
 	  ListadoReservas=ListadoReservas+"		<div class=\"media-left\">%n";
  	  ListadoReservas=ListadoReservas+"			<span class=\"glyphicon glyphicon-blackboard\"></span>%n";
  	  ListadoReservas=ListadoReservas+"		</div>%n";
  	  ListadoReservas=ListadoReservas+"		<div class=\"media-body\">%n";
  	  ListadoReservas=ListadoReservas+"			<h6>Sin reservas para hoy</h6>%n";
  	  ListadoReservas=ListadoReservas+"		</div>%n";
  	  ListadoReservas=ListadoReservas+"</div>%n";
  
	}*/
}
	
	if(ListadoReservas==""){
		ListadoReservas=ListadoReservas+"<div class=\"media reservas\">%n";
	 	  ListadoReservas=ListadoReservas+"		<div class=\"media-left\">%n";
	  	  ListadoReservas=ListadoReservas+"			<span class=\"glyphicon glyphicon-blackboard\"></span>%n";
	  	  ListadoReservas=ListadoReservas+"		</div>%n";
	  	  ListadoReservas=ListadoReservas+"		<div class=\"media-body\">%n";
	  	  ListadoReservas=ListadoReservas+"			<h6>Sin reservas para hoy</h6>%n";
	  	  ListadoReservas=ListadoReservas+"		</div>%n";
	  	  ListadoReservas=ListadoReservas+"</div>%n";
		
	}

DatosSesVO usuarioSes=(DatosSesVO) request.getAttribute("usuario");
SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat sdf2= new SimpleDateFormat("dd");
SimpleDateFormat sdf3= new SimpleDateFormat("MM");
String[] montharray={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre","Enero"};
String[] diaarray={"","Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};

String ListadoCumpleanos=null;

Calendar hoyG=Calendar.getInstance();
java.util.Date hoyJ=new Date();

List cumpleaneros= (List) request.getAttribute("cumpleaneros");
Iterator i= cumpleaneros.iterator();

String cumpleanos=null;
String estemes= montharray[hoyG.get(Calendar.MONTH)];

int c=0;

while( i.hasNext()){
  UsuarioVO usu=(UsuarioVO) i.next();

  String cumpEsteAnoS=sdf.format(usu.getCumpleanos());
  String esteAnoS=sdf.format(new java.util.Date());
  esteAnoS=esteAnoS.substring(6);

  cumpEsteAnoS= cumpEsteAnoS.substring(0,6)+esteAnoS;


  java.util.Date cumpEsteAnoD= sdf.parse(cumpEsteAnoS);

  Calendar cumpEsteAnoC= Calendar.getInstance();
  cumpEsteAnoC.setTime(cumpEsteAnoD);

  int mes=Integer.parseInt(sdf3.format(new java.util.Date()));
  Calendar fechaG=Calendar.getInstance();

   if(ListadoCumpleanos!=null){

	  // <li>Lunes 2<br/> <strong>José Luis Gamboa</strong></li>

    if(usu.getNombreUsu2()!=null){


    	ListadoCumpleanos+="<li>"+diaarray[cumpEsteAnoC.get(Calendar.DAY_OF_WEEK)] +" "+cumpEsteAnoC.get(Calendar.DAY_OF_MONTH)+"<strong><br/> "+usu.getNombreUsu()+" "+usu.getNombreUsu2()+" "+usu.getApellUsu()+"</strong></li>%n";
    }else{
    	ListadoCumpleanos+="<li>"+diaarray[cumpEsteAnoC.get(Calendar.DAY_OF_WEEK)] +" "+cumpEsteAnoC.get(Calendar.DAY_OF_MONTH)+"<strong><br/> "+usu.getNombreUsu()+" "+usu.getApellUsu()+"</strong></li>%n";
    }
  }else{

    if(usu.getNombreUsu2()!=null){
    	ListadoCumpleanos="<li>"+diaarray[cumpEsteAnoC.get(Calendar.DAY_OF_WEEK)] +" "+cumpEsteAnoC.get(Calendar.DAY_OF_MONTH)+"<strong><br/> "+usu.getNombreUsu()+" "+usu.getNombreUsu2()+" "+usu.getApellUsu()+"</strong></li>%n";
    }else{
    	ListadoCumpleanos="<li>"+diaarray[cumpEsteAnoC.get(Calendar.DAY_OF_WEEK)] +" "+cumpEsteAnoC.get(Calendar.DAY_OF_MONTH)+"<strong><br/> "+usu.getNombreUsu()+" "+usu.getApellUsu()+"</strong></li>%n";
    }



  }
c++;
}

if(c==0){
	ListadoCumpleanos= "<li>No hay cumpleaños este mes</li>%n";
}


%>
			
					<div class="col-sm-3 sidebar">
					
						<div class="media fecha">
							<div class="media-left">
    							<span class="glyphicons glyphicons-calendar"></span>
      					</div>
  							<div id="hoy" class="media-body">
  							</div>
						</div>
						
					<!-- 	<div class="media comunicado">
							<div class="media-left">
    							<span class="glyphicons glyphicons-bookmark"></span>
      					</div>
  							<div class="media-body">
  								<p>Fundamenta tu valoración en tus esfuerzos y éxitos, no mas, pero tampoco menos.</p>
  							</div>
						</div>
				 -->
						<div class="box">
							<h5>Reservas para hoy</h5>
							
							<%=String.format(ListadoReservas)%>							
						</div><!-- /box -->
						
						<!--  <div class="media ieconomicos">
							<div class="media-left">
    							<span class="glyphicons glyphicons-usd"></span>
      					</div>
  							<div class="media-body">
  								<ul>
  									<li><strong>UF</strong> $ 23.637,37</li>
  									<li><strong>US (observado)</strong> $ 553,59</li>
  									<li><strong>UTM</strong> $ 41.469,00</li>
  								</ul>
  							</div>
						</div>
						-->
						
						<div class="box">
							<h5>Cumpleaños noviembre</h5>
							
							<div class="media cumples">
								<div class="media-left">
    								<span class="glyphicons glyphicons-birthday-cake"></span>
      						</div>
  								<div class="media-body">
  									<ul>
  										<%=String.format(ListadoCumpleanos)%>
  									</ul>
    							</div>
							</div>
							
						</div>
						   
					</div>
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->