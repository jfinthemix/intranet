<%
Float uf=(Float)request.getAttribute("uf");
String ufS=uf.toString();%>
<html>
<head>
<title>
Actualizar UF
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>


<script language="JavaScript" type="text/javascript">
function validaUF(formulario){
	var tiene=formulario.uf.value.search("[^0-9,]");
	if(tiene>=0){
	alert("El valor de la UF sólo puede ser expresado usando dígitos y ','");
	return false;
	}
	if(validaComa(formulario.uf.value)){
			alert("El formato de la uf es: <digitos enteros>,<digitos decimales>");
			formulario.uf.focus();
			return false;
	}
	else{
	ufForm.submit();
	}
}

function validaComa(valor){
	var i=0;
	var v=0;
	for(i=0;i<valor.length;i++){
		if(valor[i]==','){
		v++;
		}
	}
	if(v == 1){
	return false;
	}
	else{
	return true;
	}
}

</script>


</head>
<body bgcolor="#ffffff">
<form action="ventasAction.do" method="POST" name="ufForm">
  <table width="265" border="0">
    <tr>
      <td><h3 align="center">Actualizar valor de la UF</h3></td>
    </tr>
    <tr>
      <td> <div align="center">
          <input type="hidden" name="accion" value="actualizarUF" />
          <input type="text" name="uf" value="<%=ufS.replace('.',',') %>" maxlength="15" />
        </div></td>
    </tr>
    <tr>
      <td><div align="center">
                  <input type="button" name="s" value="Actualizar el valor" onClick="validaUF(this.form)"/>
        </div></td>
    </tr>
  </table>
</form>
<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
