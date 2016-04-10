<script type="text/javascript">

$(document).ready(function() {

	$('#aInicio').click(function(){
		LlamadaPagina("homeAction.do");
		});
	
	
	$('#aCumpleanos').click(function(){
		LlamadaPagina("index2Action.do?accion=cumpleanos");
		});
	$('#aAnexos').click(function(){
		LlamadaPagina("index2Action.do?accion=cuadroanexos");
		});
	
	$('#aAnexos').click(function(){
		LlamadaPagina("index2Action.do?accion=cuadroanexos");
		});
	
	$('#aDocumentosUOCT').click(function(){
		LlamadaPagina("index2Action.do?accion=infoinstit");
		});
	
	LlamadaPagina("homeAction.do");
});






/*

function popUp(href, target, features) {
	  res = window.open(href, target, features);
	  res.window.focus();
	}
*/
</script>






<div class="col-sm-3 menuLateral">
							<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  								
  								<div class="panel panel-default">
  									<a id="aInicio" class="list-group-item active collapsed" href="javascript:void(0)">
    									<span class="glyphicon glyphicon-home"></span> Inicio
  									</a>
  								</div>
  								
  								<div class="panel panel-default">
    								<a class="list-group-item panhead collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseUOCTinos" aria-expanded="false" aria-controls="collapseUOCTinos">
    									<span class="glyphicons glyphicons-group"></span> UOCTinos <b></b>
  									</a>
      							<div id="collapseUOCTinos" class="panel-collapse collapse" role="tabpanel">
      								<div class="list-group">
  											<a id="aCumpleanos" href="javascript:void(0)" class="list-group-item">Cumpleaños</a>
  											<a id="aAnexos" href="javascript:void(0)" class="list-group-item">Anexos</a>
  										</div>
      							</div>
      						</div>
      						
      						<div class="panel panel-default">
    								<a class="list-group-item panhead collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseContenidos" aria-expanded="false" aria-controls="collapseContenidos">
    									<span class="glyphicons glyphicons-book"></span> Contenidos <b></b>
  									</a>
      							<div id="collapseContenidos" class="panel-collapse collapse" role="tabpanel">
      								<div class="list-group">
  											<a id="aDocumentosUOCT" href="javascript:void(0)" class="list-group-item">Documentos UOCT</a>
  										</div>
      							</div>
      						</div>
      						
      						<div class="panel panel-default">
    								<a class="list-group-item panhead collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseUtil" aria-expanded="false" aria-controls="collapseUtil">
    									<span class="glyphicons glyphicons-cogwheel"></span> Utilitarios <b></b>
  									</a>
      							<div id="collapseUtil" class="panel-collapse collapse" role="tabpanel">
      								<div class="list-group">
  											<a href="javascript:void(0)" onClick=popUp('http://inti.uoct.cl:8080/intranet/pages/calculadora/calculo.html',this.target,'width=430,height=300') class="list-group-item">Calculadora de verdes</a>
  											<a href="javascript:void(0)" onClick=popUp('http://.cl:8080/intranet/pages/noticias.jsp',this.target,'width=1015,height=355') class="list-group-item">Noticias CNT</a>
  										</div>
      							</div>
      						</div>
      						
      						 
      					</div>
					</div>