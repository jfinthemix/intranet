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
List usuarios = (List) request.getAttribute("listaUsuarios");
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
<title>Env&iacute;o de Email</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
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



  </script>

  <!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="center">Env&iacute;o de Email de Aviso</h3></td>
  </tr>
  <tr>
    <td><h4 align="center">EISTU - <%=id_eiv %>: <%=nomEIV %></h4>
        <%if (mensaje != null){%>
      <div align="center"><font color="red"><%=mensaje %></font></div>
        <%}%>
      </td>
  </tr>
  <form action="eivAction.do" name="form1" method="POST" >
    <tr>
      <td> <div align="center">
          <input type="hidden" name="hacia" value="enviarEmailIngreso" />
          <input type="hidden" name="ingresoEIV" value="ingresoEIV" />
          <input type="hidden" name="id_eiv" value="<%= id_eiv%>" />
            <input type="hidden" name="nomEIV" value="<%=nomEIV%>" />
          <table width="539" border="1" align="center">
            <tr>
              <td width="124" bgcolor="#ADD8E4"><strong>Asunto del Email:</strong></td>
              <td width="399">Aviso de ingreso EISTU</td>
            </tr>

            <tr>
              <td width="124" bgcolor="#ADD8E4"><strong>Remitente</strong></td>
              <td width="399"><%=rmt %><input type="hidden" name="rmt" value="<%=rmt%>"/></td>
                <input type="hidden" name="passw" value="xxxx"/>
            </tr>


            <tr>
              <td bgcolor="#ADD8E4"><strong>Contenido</strong></td>
              <td><p>Se informa el ingreso del siguiente EISTU para ser estudiado
                  por UOCT</p>
                <table width="83%" border="1">
                  <tr>
                    <td width="52%">C&oacute;digo</td>
                    <td width="48%">EISTU - <%=eiv.getIdEIV()%></td>
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
                    <td>Encargados de la revisión</td>
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
                <p>Se solicita resolver a la brevedad</p></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><strong>Destinatarios del Email</strong></td>
              <td> <table width="376" border="0">
                  <tr>
                    <td width="94" rowspan="2"> <select multiple="multiple" name="list2" size="4">
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
                      </select> </td>
                    <td width="77"> <div align="center">
                        <input type="button" value="Agregar&gt;&gt;" name="B2" onclick="move(this.form.list2,this.form.emails)"/>
                      </div></td>
                    <td width="191" rowspan="2"> <input type="text" name="emails" size="20" />
                    </td>
                  </tr>
                </table></td>
            </tr>
          </table>
        </div></td>
    </tr>
    <tr>
      <td> <div align="center">
          <input name="subject" type="hidden"  value="Aviso de ingreso de EIV">
          <%

                String q="Se informa el ingreso del siguiente EIV para ser estudiado por UOCT: \n\r"+
                "Código: EISTU - "+eiv.getIdEIV()+"\r\n"+
                "Título: "+eiv.getNomEiv()+"\r\n"+
                "Estado del EISTU: "+eiv.getEstado()+"\r\n"+
                "Ubicación: "+eiv.getDir()+"\r\n"+
                "Revisado Por: ";
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
          <input type="submit" name="enviar" value="Enviar">
        </div></td>
    </tr>
    <tr>
    <td align="center">
      <font color="red">
        <strong> Usted también recibirá una copia de este mismo correo,</strong>
      </font>
    </td>
  </tr>

  </form>
<br>

  <script language="JavaScript" type="text/javascript">
  var frmvalidator  = new Validator("form1");
  frmvalidator.addValidation("rmt","email","Su email no corresponde al formato de email, configurelo en 'Editar Datos Personales'");
  frmvalidator.addValidation("passw","req","Indique la contraseña de su cuenta de email");
  frmvalidator.addValidation("emails","req","Debe indicar al menos un destinatario del email");
</script>
</table>

<hr>
 <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div> <div align="right"><a href="../ayuda/eiv.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
