package proyecto_uoct.documentacion.model;

import java.util.*;

import javax.ejb.*;
import proyecto_uoct.documentacion.VO.ClienteVO;
import proyecto_uoct.documentacion.VO.EntExtVO;

public interface ClienteLocal extends EJBLocalObject {


    public boolean agregarCli(ClienteVO cliente);

    public boolean registrarEntExt(EntExtVO entidad);

    public List getCliEntporPal(String palClave, boolean activo);

    public ClienteVO getDatosCli(Integer id_cli);

    public EntExtVO getDatosEnt(Integer id_ent);

    public List getCliPartic(boolean activo);

    public void updateDatosCli(ClienteVO cliente);

    public List getEntidadesExternas();

    public boolean actualizarEntExt(EntExtVO entidad);

    public List buscarEntExt(String nomEnt);

    public List getAgendaCli();

    public boolean cambiarEstadoCli(Integer idCli);

    public boolean cambiarEstadoEntExt(Integer idEntExt);
}
