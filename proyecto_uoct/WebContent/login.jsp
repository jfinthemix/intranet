<html>
<head>
<script language="JavaScript" src="util/valid/gen_validatorv2.js" type="text/javascript"></script>
<title>
login
</title>
<link href="util/styla.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff" >
<form name="form1" method="post" action="indexAction.do">
  <p>
    <input type="hidden" name="hacia" value="inises" />
  </p>
  <table width="800" height="621" border="0" align="center" background="util/img/edificiouoctByN.jpg" cellpadding="0" cellspacing="0" >
  <tr>
      <td height="28"><div align="center"><img src="util/img/unidadoperativa.gif" width="500" height="30"> 
        </div></td>
  </tr>
    <tr>
	
      <td width="334" valign="top" ><table width="91%" border="0" align="center">
          <tr> 
            <td><div align="center"><strong><font color="#0080FF">Nombre de usuario:</font></strong></div></td>
          </tr>
          <tr> 
            <td><div align="center"> 
                <input type="text" name="nom">
              </div></td>
          </tr>
          <tr> 
            <td><div align="center"><strong><font color="#0080FF">Password:</font></strong></div></td>
          </tr>
          <tr> 
            <td><div align="center"> 
                <input type="password" name="psw">
              </div></td>
          </tr>
          <tr> 
            <td><div align="center"> 
                <input type="submit" name="Submit" value="Entrar">
              </div></td>
          </tr>
        </table></td>
    </tr>
  </table>
  <p>
    <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("form1");
frmvalidator.addValidation("nom","req","Debe ingresar el nombre");
frmvalidator.addValidation("nom","alnumhyphen","Sólo caracteres alfanuméricos [ A B C ...Z], [0 1 2 ..9],- y _");
frmvalidator.addValidation("nom","maxlen=15","Nombre no puede superar los 15 caracteres");

frmvalidator.addValidation("psw","req","Debe Ingresar Password");
frmvalidator.addValidation("psw","alnumhyphen","Sólo caracteres alfanumericos [ A B C ...Z], [0 1 2 ..9], - y _ ");
frmvalidator.addValidation("psw","maxlen=15","Password no puede superar los 15 caracteres");
</script>
  </p>
</form>
</body>
</html>
