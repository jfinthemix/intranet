package proyecto_uoct.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import proyecto_uoct.EIV.model.EIVLocal;
import proyecto_uoct.common.LocalizadorServicios;
import proyecto_uoct.common.LocalizadorServiciosException;
import proyecto_uoct.proyecto.VO.BusOTVO;
import proyecto_uoct.proyecto.VO.DetalleOTVO;
import proyecto_uoct.proyecto.model.ProyectoLocal;
import proyecto_uoct.usuario.VO.UsuarioVO;
import proyecto_uoct.usuario.model.UsuarioLocal;

public class MailAutoVenceOT implements Job {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

	try {

	    ProyectoLocal proyLocal = LocalizadorServicios.getInstance().getProyectoLocal();
	    EIVLocal eivLocal = LocalizadorServicios.getInstance().getEIVLocal();
	    UsuarioLocal usuLoc = LocalizadorServicios.getInstance().getUsuarioLocal();

	    BusOTVO paramBusOT = new BusOTVO();
	    paramBusOT.setFechaVenc(sdf.format(new Date()));
	    List vencenHoy = proyLocal.buscarOT(paramBusOT);
	    Iterator i = vencenHoy.iterator();
	    while (i.hasNext()) {
		DetalleOTVO detalleOT = (DetalleOTVO) i.next();
		List encargadosOT = proyLocal.getEncargadosOT(detalleOT.getIdOT());

		Iterator ienc = encargadosOT.iterator();
		String para = null;
		while (ienc.hasNext()) {
		    UsuarioVO encargado = (UsuarioVO) ienc.next();
		    if (para == null) {
			para = encargado.getEmail();
		    } else {
			para += "," + encargado.getEmail();
		    }

		}

		String contenido = "Se comunica que la siguiente OT vence el día de hoy:\n\r" + "Nombre OT: "
			+ detalleOT.getNomOT() + "\r\n" + "Proyecto: " + detalleOT.getDetProy().getNomProy() + "\r\n";

		eivLocal.alertaEmail("sistema@uoct.cl", "", para, "Aviso de Vencimiento de OT", contenido);
		System.out.print("Email enviado");
	    }

	} catch (LocalizadorServiciosException ex) {
	} catch (Exception e) {
	    System.out.print("Error en el envío de Email el día de vencimiento de OT");
	    e.printStackTrace();

	}

    }
}
