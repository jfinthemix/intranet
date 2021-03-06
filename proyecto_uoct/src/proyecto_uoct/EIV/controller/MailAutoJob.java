package proyecto_uoct.EIV.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import proyecto_uoct.EIV.VO.EIVVO;
import proyecto_uoct.EIV.VO.EIVdeListaVO;
import proyecto_uoct.EIV.model.EIVLocal;
import proyecto_uoct.common.LocalizadorServicios;
import proyecto_uoct.common.LocalizadorServiciosException;
import proyecto_uoct.usuario.VO.UsuarioVO;
import proyecto_uoct.usuario.model.UsuarioLocal;

public class MailAutoJob implements Job {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

	try {

	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    UsuarioLocal usuLoc = LocalizadorServicios.getInstance().getUsuarioLocal();
	    List vencenHoy = eivLocal.buscaEIVxFechaVenc(new Date());
	    Iterator i = vencenHoy.iterator();
	    while (i.hasNext()) {
		EIVdeListaVO eivdelista = (EIVdeListaVO) i.next();
		EIVVO eiv = eivLocal.getDetalleEIV(eivdelista.getIdEIV());
		UsuarioVO usu = usuLoc.getDatosUsu(eiv.getIdEncargado());
		String emailEncargado = usu.getEmail();
		String para = "eiv@uoct.cl";
		if (emailEncargado != null) {
		    para += "," + emailEncargado;
		}
		String contenido = "Se comunica que el siguiente EISTU permanece sin respuesta por parte de UOCT:\n\r"
			+ "Código: EISTU - " + eiv.getIdEIV() + "\r\n" + "Título: " + eiv.getNomEiv() + "\r\n"
			+ "Estado del EIV: " + eiv.getEstado() + "\r\n" + "Ubicación: " + eiv.getDir() + "\r\n"
			+ "Revisado Por: ";
		if (usu.getNombreUsu2() != null) {
		    contenido += usu.getNombreUsu() + " " + usu.getNombreUsu2() + " " + usu.getApellUsu();
		} else {
		    contenido += usu.getNombreUsu() + " " + usu.getApellUsu();
		}
		contenido += "\r\n" + "Fecha de Ingreso:" + sdf.format(eiv.getFechaIng()) + "\r\n" + "Vencimiento:"
			+ sdf.format(eiv.getFechaVenc()) + "\r\n" + "\r\n" + "Por favor Resolver a la brevedad";

		eivLocal.alertaEmail("sistema@uoct.cl", "", para, "Aviso de Vencimiento de EISTU", contenido);
		System.out.print("Email enviado");
	    }

	} catch (LocalizadorServiciosException ex) {
	} catch (Exception e) {
	    System.out.print("Error en el envío de Email el día de vencimiento");
	    e.printStackTrace();

	}

    }

}
