<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@ page import="proyecto_uoct.documentacion.VO.DocumentodeListaVO, java.util.List, java.util.Iterator" %>
<%@ page import="proyecto_uoct.EIV.VO.*,proyecto_uoct.usuario.VO.UsuarioVO,java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

Integer id_eiv= (Integer) request.getAttribute("id_eiv");
String id_estado = (String) request.getAttribute("id_estado");
String rmt=(String) request.getAttribute("rmt");
String mensaje = (String) request.getAttribute("mensaje");
String nomEIV = (String) request.getAttribute("nomEIV");
EIVVO eiv=(EIVVO)request.getAttribute("detEIV");
List usuarios = (List) request.getAttribute("listaIngenieros");
Iterator liu = usuarios.iterator();

UsuarioVO enc=null;

Iterator ienc=usuarios.iterator();

while(ienc.hasNext()){

  UsuarioVO u = (UsuarioVO) ienc.next();
  if(u.getIdUsu().intValue()==eiv.getIdEncargado().intValue()){
    enc=u;
    break;
  }
}


%>

<html>
<head>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="Unidad Operativa de Control de Tránsito">
		<link rel="icon" href="img/favicon.ico">
		
		<title>Unidad Operativa de Control de Tránsito</title>
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">



  

  <!-- validador -->


</head>

<body>
<script language="JavaScript" src="util/valid/gen_validatorv2.js" type="text/javascript"></script>

<script language="Javascript" type="text/javascript">
    function popUp(href, target, features) {
    reservar = window.open(href, target, features);
    reservar.window.focus();
  }

  /*
  function mover(){

    var ntext =form1.redRel.value;
    var no = new Option();
    no.value =ntext;
    no.text = ntext;
    formIngresarEIV.listaRel.options[formIngresarEIV.listaRel.options.length] = no;
    formIngresarEIV.redRel.value="";
  }*/


  function borrar(CONTROL){
    for (var i =0;i<CONTROL.length;i++){
      if (CONTROL.options[i].selected ==true){
        CONTROL.options[i]=null;
      }
    }
  }


  function move(fbox,tbox) {
    for(var i=0; i<fbox.options.length; i++) {
      if(fbox.options[i].selected && fbox.options[i].value != "") {
		if(tbox.value==""){
	        tbox.value=fbox.options[i].value;
        	fbox.options[i].value = "";
	        fbox.options[i].text = "";
		}
		else{
		if(tbox.value!=""){
	        tbox.value=tbox.value+","+ fbox.options[i].value;
    	    fbox.options[i].value = "";
        	fbox.options[i].text = "";
		}
		}


      }
    }
    BumpUp(fbox);
  }



  function BumpUp(box)  {
    for(var i=0; i<box.options.length; i++) {
      if(box.options[i].value == "")  {
        for(var j=i; j<box.options.length-1; j++)  {
          box.options[j].value = box.options[j+1].value;
          box.options[j].text = box.options[j+1].text;
        }
        var ln = i;
        break;
      }
    }
    if(ln < box.options.length)  {
      box.options.length -= 1;
      BumpUp(box);
    }
  }
  
  function submitThisForm1() {
		var formulario = $('#form1');
		var action = 'eiv/eivAction.do'
		SubmitFormulario(action,formulario);

	}



  </script>


		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					<div class="box">
					<h3>Env&iacute;o de Email de Aviso</h3>

<h4>EISTU - <%=id_eiv %>: <%=nomEIV %></h4>
        <%if (mensaje != null){%>
      
      <h4><div align="center"><%=mensaje %></div></h4>
        <%}%>
 
<form action="eivAction.do" name="form1" id="form1" method="POST">

          <input type="hidden" name="hacia" value="enviarEmail"/>
          <input type="hidden" name="id_eiv" value="<%= id_eiv%>" />
          
          
<div class="form-group">
          <label for="inputTitulo" class="col-sm-4 control-label">T&iacute;tulo del Email:</label>
          <label for="inputTitulo" class="col-sm-6 control-label">Aviso de Vencimiento de EISTU</label>
</div>

<div class="form-group">

<label for="inputTitulo" class="col-sm-4 control-label">Remitente:</label>
<label for="inputTitulo" class="col-sm-6 control-label"><%=rmt %><input type="hidden" name="rmt" value="<%=rmt%>"/></label>
            </div>
<div class="form-group">            
            <label for="inputTitulo" class="col-sm-4 control-label">Contenido:</label>
            <label for="inputTitulo" class="col-sm-6 control-label">Se comunica que el siguiente EIV permanece sin respuesta por parte de la UOCT</label>
</div>
             
                
                <table width="75%" border="1" class="table table-striped table-bordered table-hover">
                  <tr>
                    <td width="46%">C&oacute;digo</td>
                    <td width="54%">EISTU - <%=eiv.getIdEIV()%></td>
                  </tr>
                  <tr>
                    <td>Título</td>
                    <td><%=eiv.getNomEiv()%></td>
                  </tr>
                  <tr>
                    <td>Estado del EISTU</td>
                    <td><%=eiv.getEstado()%></td>
                  </tr>
                  <tr>
                    <td>Ubicaci&oacute;n</td>
                    <td><%=eiv.getDir() %></td>
                  </tr>
                  <tr>
                    <td>Revisado Por </td>
                    <td> <%if(enc.getNombreUsu2()!=null){
                 out.print(enc.getNombreUsu()+" "+enc.getNombreUsu2()+" "+enc.getApellUsu());}
                 else{
                 out.print(enc.getNombreUsu()+" "+enc.getApellUsu());}
                  %> </td>
                  </tr>
                  <tr>
                    <td>Fecha de Ingreso</td>
                    <td><%=sdf.format(eiv.getFechaIng()) %></td>
                  </tr>
                  <tr>
                    <td>Vencimiento</td>
                    <td><%=sdf.format(eiv.getFechaVenc()) %></td>
                  </tr>
                </table>
                <h4>Se solicita resolver a la brevedad</h4></td>
            
            
            <div class="form-group">
            <label for="inputTitulo" class="col-sm-4 control-label">Destinatarios del Email</label>
            </div>
             
             <div class="form-group">
             <table width="376" border="0" class="table table-striped table-bordered table-hover">
                  <tr>
                    <td width="94" rowspan="2"> 
                		<select multiple="multiple" name="list2" size="4">
                        <%
                    while (liu.hasNext()) {
                      UsuarioVO usu = (UsuarioVO) liu.next();
                      if(usu.getEmail()!=null){
                        if(usu.getNombreUsu2()!=null){
                          out.println("<option value=\"" + usu.getEmail() + "\">" + usu.getNombreUsu() + " " + usu.getNombreUsu2() + " " + usu.getApellUsu() + "</option>");
                        }else{
                          out.println("<option value=\"" + usu.getEmail() + "\">" + usu.getNombreUsu() +" " + usu.getApellUsu() + "</option>");
                        }
                      }
                    }
                      %>
                      </select> 
                      </td>
                    <td width="77"> <div align="center">
                        <input type="button" value="Agregar&gt;&gt;" name="B2" onclick="move(this.form.list2,this.form.emails)"/>
                      </div></td>
                    <td width="191" rowspan="2"> <input type="text" name="emails" size="20" />
                    </td>
                  </tr>
                </table>
                
                </div>
                

          			<input name="subject" type="hidden"  value="Aviso de Vencimiento de EISTU">

          			<%

                String q="Se comunica que el siguiente EISTU permanece sin respuesta por parte de UOCT: \n\r"+
                "Código: EISTU - "+eiv.getIdEIV()+"\r\n"+
                "Título:"+eiv.getNomEiv()+"\r\n"+
                "Estado del EISTU:"+eiv.getEstado()+"\r\n"+
                "Ubicación:"+eiv.getDir()+"\r\n"+
                "Revisado Por:";
                if(enc.getNombreUsu2()!=null){
                  q+=enc.getNombreUsu()+" "+enc.getNombreUsu2()+" "+enc.getApellUsu();
                }
                else{
                  q+= enc.getNombreUsu()+" "+enc.getApellUsu();
                }
                q+="\r\n"+"Fecha de Ingreso:"+sdf.format(eiv.getFechaIng()) +"\r\n"+
                "Vencimiento:"+sdf.format(eiv.getFechaVenc())+"\r\n"+"\r\n"+
                "Por favor Resolver a la brevedad";%>
          <input type="hidden" name="content" value="<%=q%>">
          <input type="hidden" name="enviar" value="Enviar">
          <div class="form-group" align="center">
          <div class="col-sm-8">
          <input type="button" onclick="javascript:submitThisForm1();" name="enviar1" value="Enviar">
          &nbsp;&nbsp;
          <input name="reset" type="reset" value="Restaurar Valores" />
          </div>
          </div>	
        


  </form>

  
<div class="form-group">

<label for="inputTitulo" class="col-sm-6 control-label">Usted también recibirá una copia de este correo.</label>
</div>
</div>
  <script language="JavaScript" type="text/javascript">
  var frmvalidator  = new Validator("form1");
  frmvalidator.addValidation("rmt","email","Su email no corresponde al formato de email, configurelo en 'Editar Datos Personales'");
  frmvalidator.addValidation("emails","req","Debe indicar al menos un destinatario del email");
</script>
</div>
</div>
</div>
</div>


</body>
</html>
