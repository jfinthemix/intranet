package proyecto_uoct.inventario.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import proyecto_uoct.inventario.VO.ItemVO;
import java.util.List;
import proyecto_uoct.inventario.VO.BusItemVO;
import proyecto_uoct.dao.InventarioDAO;
import proyecto_uoct.inventario.VO.BusItemVO;

public class InventarioBean implements SessionBean {
    SessionContext sessionContext;
    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }


    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public void ejbCreate() throws CreateException {
    }

    public boolean regItem(ItemVO item) {
        InventarioDAO d=InventarioDAO.getInstance();
        try {
            int nuevo=d.regItem(item);
            return true;
        } catch (Exception ex) {
            sessionContext.setRollbackOnly();
            return false;
        }

    }

    public boolean  actualizaItem(ItemVO item) {
        InventarioDAO d=InventarioDAO.getInstance();
        try {
            d.actualizarItem(item);
            return true;
        } catch (Exception ex) {
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public List  buscarItem(BusItemVO bus) {
        InventarioDAO d=InventarioDAO.getInstance();
        return d.buscarItems(bus);
    }



    public boolean  borrarItem(Integer idItem) {
        InventarioDAO d=InventarioDAO.getInstance();
        try {
            d.borrarItem(idItem.intValue());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }

    public ItemVO  detalleItem(Integer idItem) {
        InventarioDAO d=InventarioDAO.getInstance();
        return d.detalleItem(idItem);
    }
}
