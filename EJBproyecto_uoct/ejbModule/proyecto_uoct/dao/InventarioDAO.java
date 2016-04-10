package proyecto_uoct.dao;

import java.sql.*;
import java.util.*;

import proyecto_uoct.common.*;
import proyecto_uoct.inventario.VO.*;

public class InventarioDAO {
    private static InventarioDAO dao = null;


    public InventarioDAO() {
    }


  public static InventarioDAO getInstance() {
      if (dao == null) {
          dao = new InventarioDAO();
      }
      return dao;
  }

  public int regItem(ItemVO item)throws Exception{
      Connection conn=null;
      String q="Insert into inventario(id_item,nombre_item,idcategoria,id_unidad,cantidad)values"+
               "(s_inventario.nextval,'"+item.getNomItem()+
               "',"+item.getIdCategoria()+","+
               item.getIdUnidad()+","+
               item.getCantidadItem()+" )";
      try{
          conn=ConnectionPool.getInstance().getConnection();
          PreparedStatement ps=conn.prepareCall(q);
          ps.executeUpdate();

          q="SELECT s_inventario.CURRVAL FROM DUAL";
          ps=conn.prepareCall(q);
          ResultSet rs=ps.executeQuery();
          rs.next();
          int a=rs.getInt(1);
          conn.close();
          return a;
      }catch(Exception e){
          e.printStackTrace();
          conn.close();
          throw e;
      }

  }



  public void borrarItem(int idItem) throws Exception{
      Connection conn=null;
      String q="DELETE inventario WHERE id_item="+idItem;
      try{
          conn=ConnectionPool.getInstance().getConnection();
          PreparedStatement ps=conn.prepareCall(q);
          ps.executeUpdate();

      }catch(Exception e){
          e.printStackTrace();
          conn.close();
          throw e;
      }finally{
          conn.close();
      }

  }



  public void actualizarItem(ItemVO item) throws Exception{
      Connection conn=null;
      String q="UPDATE inventario SET idcategoria='"+item.getIdCategoria()+
               "',cantidad="+item.getCantidadItem()+
               ",id_unidad="+item.getIdUnidad()+
               " WHERE id_item="+item.getIdItem();
      try{
          conn=ConnectionPool.getInstance().getConnection();
          PreparedStatement ps=conn.prepareCall(q);
          ps.executeUpdate();
          conn.close();
      }catch(Exception e){
          e.printStackTrace();
          conn.close();
          throw e;
      }



  }

    /**
     * buscarItems
     *
     * @param bus busItemVO
     * @return List
     */
    public List buscarItems(BusItemVO bus) {
        Connection conn = null;
        List items = new LinkedList();
        try {
            String q ="SELECT id_item,nombre_item,cantidad,inventario.idcategoria,categoria_inventario.nombre,"+
                      " inventario.id_unidad,unidad_inventario.nombre FROM inventario ,categoria_inventario,unidad_inventario "+
                      " WHERE inventario.idcategoria=categoria_inventario.idcategoria "+
                      "  AND inventario.id_unidad = unidad_inventario.id_unidad ";

            boolean f = false;
            if (!bus.getPalClave().equals("")) {
                if (!f) {
                    q += " AND (UPPER(nombre_item) LIKE UPPER('%" +
                            bus.getPalClave() + "%') ";
                            f = true;
                } else {
                    q += " AND (UPPER(nombre_item) LIKE UPPER('%" + bus.getPalClave() +
                            "%')";
                }
            }

            if (bus.getIdCategoria().intValue()!=0) {
                if (!f) {
                    q += " AND inventario.idcategoria=" + bus.getIdCategoria();
                    f = true;
                } else {
                    q += " AND inventario.idcategoria=" + bus.getIdCategoria();
                }

            }
            conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = conn.prepareCall(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemVO item = new ItemVO();
                item.setIdItem(new Integer(rs.getInt(1)));
                item.setNomItem(rs.getString(2));
                item.setCantidadItem(new Integer(rs.getInt(3)));
                item.setIdCategoria(new Integer(rs.getInt(4)));
                item.setNomCategoria(rs.getString(5));
                item.setIdUnidad(new Integer(rs.getInt(6)));
                item.setUnidad(rs.getString(7));
                items.add(item);
            }
            conn.close();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException ex) {
                return items;
            }
            try {
                conn.close();
            } catch (SQLException ex1) {
                return items;

            }
            return items;
        }

    }


    public ItemVO detalleItem(Integer idItem){
        Connection conn=null;
        String q="SELECT id_item,nombre_item,cantidad,inventario.idcategoria,categoria_inventario.nombre,"+
                 " inventario.id_unidad,unidad_inventario.nombre FROM inventario ,categoria_inventario,unidad_inventario "+
                 " WHERE inventario.idcategoria=categoria_inventario.idcategoria "+
                 "  AND inventario.id_unidad = unidad_inventario.id_unidad "+
                 " AND inventario.id_item="+idItem;
        try{
            conn=ConnectionPool.getInstance().getConnection();
            PreparedStatement ps=conn.prepareCall(q);
            ResultSet rs=ps.executeQuery();
            rs.next();
            ItemVO item = new ItemVO();
                 item.setIdItem(new Integer(rs.getInt(1)));
                 item.setNomItem(rs.getString(2));
                 item.setCantidadItem(new Integer(rs.getInt(3)));
                 item.setIdCategoria(new Integer(rs.getInt(4)));
                 item.setNomCategoria(rs.getString(5));
                 item.setIdUnidad(new Integer(rs.getInt(6)));
                 item.setUnidad(rs.getString(7));

            conn.close();
            return item;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
