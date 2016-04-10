package proyecto_uoct.documentacion.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import java.util.List;

import proyecto_uoct.dao.ClienteDAO;
import proyecto_uoct.documentacion.VO.ClienteVO;
import proyecto_uoct.documentacion.VO.EntExtVO;

public class ClienteBean implements SessionBean {
    SessionContext sessionContext;
    public void ejbCreate() throws CreateException {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }


    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }


    public boolean agregarCli(ClienteVO cliente) {

        boolean insertado = false;
        ClienteDAO cli = ClienteDAO.getInstance();
        insertado = cli.agregarCli(cliente);

        return insertado;
    }

//----------------------------------------------------------------
    public boolean registrarEntExt(EntExtVO entidad) {

        boolean insertado = false;
        ClienteDAO cli = ClienteDAO.getInstance();
        insertado = cli.registrarEntExt(entidad);
        return insertado;
    }

//----------------------------------------------------------------

    public List getCliEntporPal(String palClave,boolean activo) {
        List clientes = null;
        ClienteDAO cli = ClienteDAO.getInstance();
        clientes = cli.getCliEntporPal(palClave,activo);
        return clientes;
    }


//-------------------------------------------------------
    public ClienteVO getDatosCli(Integer id_cli) {
        ClienteVO cli = null;
        ClienteDAO clidao = ClienteDAO.getInstance();
        cli = clidao.getDatosCli(id_cli);

        return cli;
    } //-----------------------------------------------------------
    public EntExtVO getDatosEnt(Integer id_ent) {
        EntExtVO entidad = null;
        ClienteDAO clidao = ClienteDAO.getInstance();
        entidad = clidao.getDatosEnt(id_ent);
        return entidad;
    }


//-----------------------------------------------------------
    public List  getCliPartic(boolean activo) {
        List clientes = null;
        ClienteDAO cli = ClienteDAO.getInstance();
        clientes = cli.getCliPartic(activo);
        return clientes;
    }

    public void updateDatosCli(ClienteVO cliente) {

        ClienteDAO cli = ClienteDAO.getInstance();
        cli.updateDatosCli(cliente);
    }


    public List  getEntidadesExternas() {
        ClienteDAO cli = ClienteDAO.getInstance();
        return cli.getEntidadesExternas();
    }

    public boolean  actualizarEntExt(EntExtVO entidad) {
        ClienteDAO cli = ClienteDAO.getInstance();
        return cli.actualizarEntExt(entidad);
    }

    public List  buscarEntExt(String nomEnt) {
        ClienteDAO cli = ClienteDAO.getInstance();
        return cli.buscarEntExt(nomEnt);

    }

    public List  getAgendaCli() {
        ClienteDAO cli = ClienteDAO.getInstance();

        return cli.getAgendaCli();
    }

    public boolean  cambiarEstadoCli(Integer idCli) {
        ClienteDAO cli = ClienteDAO.getInstance();
        try{
            Integer isactivo = cli.getEstadoUsu(idCli);
            if (isactivo.intValue() == 1) {
                cli.desactivarCli(idCli);
                return true;
            }
            if (isactivo.intValue() != 1) {
                cli.activarCli(idCli);
                return true;
            } else {
                return false;
            }
        }catch(Exception e){
            sessionContext.setRollbackOnly();
            return false;
        }

    }

    public boolean  cambiarEstadoEntExt(Integer idEntExt) {
        ClienteDAO cli = ClienteDAO.getInstance();
            try{
                Integer isactivo = cli.getEstadoEntExt(idEntExt);
                if (isactivo.intValue() == 1) {
                    cli.desactivarEntExt(idEntExt);
                    return true;
                }
                if (isactivo.intValue() != 1) {
                    cli.activarEntExt(idEntExt);
                    return true;
                } else {
                    return false;
                }
            }catch(Exception e){
                sessionContext.setRollbackOnly();
                return false;
        }
    }
}
