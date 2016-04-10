<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage=""%>
<%@page import="proyecto_uoct.EIV.VO.FlujoVO,java.text.SimpleDateFormat" %>
<%@page import="java.util.List,java.util.Iterator" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
Integer id_eiv = (Integer) request.getAttribute("id_eiv");
String nomEIV = (String) request.getAttribute("nomEIV");
FlujoVO flujo_ant=(FlujoVO) request.getAttribute("flujo_ant");
List flujos = (List) request.getAttribute("flujos");
String mensaje=(String)request.getAttribute("mensaje");
%>

<html>
<head>
<title>Ingreso de Flujos</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="Unidad Operativa de Control de Tránsito">
		<link rel="icon" href="img/favicon.ico">

		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->


</head>
<body>
<script type="text/javascript">

function submitThisForm1() {
	var formulario = $('#formFlujo');
	var action = 'eiv/eivAction.do'
	SubmitFormulario(action,	formulario);

}

</script>


		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					<div class="col-sm-6 desarrollo box">


<h3 align="center">Flujos Vehiculares de EISTU:</h3>

  	
<h4 align="center">EISTU-<%=id_eiv %>: <%=nomEIV %>
        <%if(mensaje!=null){ %>
      </h4>
      <div align="center"><font color="red"><%=mensaje%></font></div>
        <%}%>
      

  <form action="flujoAction.do" class="form-horizontal" name="formFlujo" id="formFlujo">
   
          <input type="hidden" name="hacia" value="ingresarFlujo"/>
          <input type="hidden" name="id_eiv" value="<%=id_eiv%>" />
          <input type="hidden" name="nomEIV" value="<%=nomEIV%>"/>
  <div class="form-group">
  <p></p>
  <p></p>
         <label for="inputTitulo" class="col-sm-4 control-label"><h5>Fecha  del flujo:</h5></label>
            <div class="col-sm-8">
				<input type="text" class="form-control"  name="fechaflujo" value="<% if (flujo_ant!=null){  if (!flujo_ant.getFecha().equals("")){ out.print(sdf.format(flujo_ant.getFecha()));}}%>" >
                
   </div>
   </div>         
           <div class="form-group">
 <label for="inputTitulo" class="col-sm-4 control-label"><h5>Tipo de día:</h5></label>
 <div class="col-sm-8">
           <select name="tipodia">
                  <option value="1" <%if (flujo_ant!=null){  if (flujo_ant.getIdTipoDia().equals("1")){out.print("selected");} }%>>Laboral</option>
                  <option value="2"<%if (flujo_ant!=null){  if (flujo_ant.getIdTipoDia().equals("2")){out.print("selected");} }%>>
                  S&aacute;bado </option>
                  <option value="3"<%if (flujo_ant!=null){  if (flujo_ant.getIdTipoDia().equals("3")){out.print("selected");} }%>>Domingo</option>
                </select>
                </div>
                </div>
       <div class="form-group">
           <label for="inputTitulo" class="col-sm-4 control-label"> <h5>Calle 1:</h5> </label>
       
            <div class="col-sm-8">
              <input class="form-control"  type="text" name="calle1" value="<%if (flujo_ant!=null){  if(!flujo_ant.getCalle1().equals("null")){ out.print(flujo_ant.getCalle1());}} %>"></td>
			 </div>
			 </div>
       <div class="form-group">
			<label for="inputTitulo" class="col-sm-4 control-label"><h5>Calle 2:</h5> </label>
       
       <div class="col-sm-8">
<input type="text" class="form-control"  name="calle2" value="<%if (flujo_ant!=null){  if(!flujo_ant.getCalle2().equals("null")){ out.print(flujo_ant.getCalle2());}} %>"></td>
           </div>
           </div>
           
             <div class="form-group">
<label for="inputTitulo" class="col-sm-4 control-label"><h5>Horas de las Mediciones:</h5></label>
           <div class="col-sm-8">
              <input class="form-control" name="horasMed" maxlength="200" type="text" size="80" value="<% if (flujo_ant!=null){  if(!flujo_ant.getHorasMed().equals("null")){ out.print(flujo_ant.getHorasMed()); }}%>">
                (ej:06:00-07:00; 18:30-19:00) 
           </div>
           </div>
<div class="col-sm-12"> 

          <a href="javascript:void(0)" onclick="javascript:submitThisForm1();" class="botoVerde"><span class="glyphicons glyphicons-envelope"></span> Guardar</a>
<p></p>
</div>          
       
        
  </form>
  <br>
 
  
  
        <table width="650" border="1" align="center" class="col-sm-12">
          <tr bgcolor="">
            <td width="72" bgcolor="#AAAAAA"> <div align="center"><strong>Fecha</strong></div></td>
            <td width="69" bgcolor="#AAAAAA"> <div align="center"><strong>Tipo
                de d&iacute;a</strong></div></td>
            <td width="90" bgcolor="#AAAAAA"> <div align="center"><strong>Calle
                1</strong></div></td>
            <td width="87" bgcolor="#AAAAAA"> <div align="center"><strong>Calle
                2</strong></div></td>
            <td width="226" bgcolor="#AAAAAA"> <div align="center"><strong>Horas
                de Mediciones</strong></div></td>
            <td width="66" bgcolor="#AAAAAA"> <div align="center"><strong>Borrar</strong></div></td>
          </tr>
          <% if (flujos!=null){
    Iterator i =flujos.iterator();
    while(i.hasNext()){
    FlujoVO flujo = (FlujoVO)i.next();
    %>
          <tr>
            <td><%=sdf.format(flujo.getFecha()) %></td>
            <td><%=flujo.getTipoDia()%></td>
            <td><%=flujo.getCalle1()%></td>
            <td><%if(flujo.getCalle2()!=null){
           out.print(flujo.getCalle2());}
           else{
             out.print("&nbsp;");
           }
             %></td>
            <td><%=flujo.getHorasMed()%></td>
            <td width="66"> <div align="center"><strong><a href="javascript:LlamadaPagina('eiv/flujoAction.do?hacia=eliminarFlujo&id_eiv=<%=id_eiv %>&nomEIV=<%=nomEIV %>&idFlujo=<%=flujo.getIdFlujo()%>')">Borrar</a></strong></div></td>
          </tr>
          <%
    }
    }%>
        </table>
</td>
  </tr>
  <tr><td><a href="javascript:LlamadaPagina('eiv/eivAction.do?hacia=detalleEIV&id_eiv=<%=id_eiv%>')">Ir al detalle del EISTU</a></td></tr>
</table>
<br />
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/eiv.html" target="_blank">Ayuda</a>
  </div>
  </div> <!--  sm-desarrollo -->

  </div>
  </div>
  </div>
  </div>

</body>
</html>
