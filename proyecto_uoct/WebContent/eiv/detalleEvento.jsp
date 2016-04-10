<%@page import="proyecto_uoct.EIV.VO.EventoVO,java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

EventoVO evento=(EventoVO) request.getAttribute("evento");
String id_eiv=(String) request.getAttribute("id_eiv");
String nomEIV=(String) request.getAttribute("nomEIV");
String mensaje =(String) request.getAttribute("mensaje");
%>
<html>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="jfanasco" >
		<link rel="icon" href="img/favicon.ico">
		
		<title>Unidad Operativa de Control de Tránsito</title>
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
<body bgcolor="#ffffff">

<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-5 desarrollo box boxpost">
					
			 	


<h3 align="center"><strong>Detalle de Bitácora</strong></h3>


        <% if (mensaje != null){ %>      
      <h3><font color="red"><%=mensaje %></font></h3>
        <%}%>
      
     
      <div class="form-group col-sm-12">
    	<label for="inputPalabra" class="col-sm-12 control-label">EISTU - <%=id_eiv %>: <%=nomEIV %></label>
    	</div>
    	<br>
    	<div class="form-group col-sm-12">
    		<label for="inputPalabra" class="col-sm-4 control-label">Título bitácora:</label>
              <label for="inputPalabra" class="col-sm-6 control-label"><%=evento.getTitulo()%></label>
        </div>
        <br>
        <div class="form-group col-sm-12">
            <label for="inputPalabra" class="col-sm-4 control-label">Fecha:</label>
            <label for="inputPalabra" class="col-sm-6 control-label"><%=sdf.format(evento.getFechaEv()) %></label>
         </div>     
            <br>  
            
           <div class="form-group col-sm-12">  
            <label for="inputPalabra" class="col-sm-4 control-label">Descripción:</label>
         <label for="inputPalabra" class="col-sm-6 control-label"> <%=evento.getDescEv() %></label>
            </div>
            
            <%if(evento.getIdDocRel().intValue()!=0){ %>
            <div class="form-group col-sm-12">
            <label for="inputPalabra" class="col-sm-4 control-label">Documento Relacionado</label>
             <label for="inputPalabra" class="col-sm-6 control-label"><a href="documentacion/documentoAction.do?hacia=detalleDoc&id_doc=<%=evento.getIdDocRel() %>"><%=evento.getTipoDoc() %>-<%=evento.getCodDoc() %></a></label>
                  </div>
            <%}%>
         
</div>
</div>
</div>
</div>


</body>
</html>
