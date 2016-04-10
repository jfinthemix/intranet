package proyecto_uoct.ventas.model;

import javax.ejb.EJBLocalObject;
import java.util.List;
import proyecto_uoct.ventas.VO.InfoVtaVO;
import proyecto_uoct.ventas.VO.CliVtaVO;
import proyecto_uoct.ventas.VO.VtaVO;
import proyecto_uoct.ventas.VO.BusVtaVO;

public interface VentasLocal extends EJBLocalObject {


    public List getTiposInfo(boolean activo);

    public boolean regInfoVta(InfoVtaVO info);

    public InfoVtaVO getDetalleInfoVta(Integer idInfo);

    public boolean actualizarInfoVta(InfoVtaVO info);

    public boolean regClienteVta(CliVtaVO cli);

    public List buscarClienteVta(String palClave, boolean activo);

    public CliVtaVO getDetalleCliente(Integer idCli);

    public List exportarAgenda();

    public boolean actualizarCliente(CliVtaVO cli);

    public boolean regProcesoVenta(VtaVO vta);

    public List getEstadosVta();

    public List buscarVentas(BusVtaVO bus);

    public VtaVO detalleVta(Integer idVenta);

    public float getUF();

    public boolean regFechaCotizacion(String fecha, Integer idVenta);

    public boolean regFechaPago(String fecha, Integer idVenta, Float uf);

    public boolean regFechaEntrega(String fecha, Integer idVenta);

    public boolean anularVenta(Integer idVenta);

    public boolean actualizaUF(String uf);

    public void actualizarVenta(VtaVO venta) throws Exception;

    public boolean regFechaFin(String fecha, String codFact, Integer idVta);

    public Boolean correoCotizacion(Integer id_venta);
}
