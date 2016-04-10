package proyecto_uoct.inventario.model;

import javax.ejb.EJBLocalObject;
import proyecto_uoct.inventario.VO.ItemVO;
import java.util.List;
import proyecto_uoct.inventario.VO.BusItemVO;

public interface InventarioLocal extends EJBLocalObject {

    public boolean regItem(ItemVO item);

    public boolean actualizaItem(ItemVO item);

    public List buscarItem(BusItemVO bus);

    public boolean borrarItem(Integer idItem);

    public ItemVO detalleItem(Integer idItem);
}
