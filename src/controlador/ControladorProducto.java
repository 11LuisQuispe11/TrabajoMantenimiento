/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.dao.CategoriaDAO;
import modelo.vo.Producto;
import modelo.dao.ProductoDAO;
import modelo.dao.ProveedorDAO;
import vista.PanelProducto;

/**
 *
 * @author AlexTprog
 */
public class ControladorProducto {

    // ATRIBUTOS DE CLASE
    private PanelProducto miPanelProducto;
    private ProductoDAO miProductoDAO;
    private CategoriaDAO miCategoriaDAO;
    private ProveedorDAO miProveedorDAO;
    private Producto miProducto;
    private int idProducto;

    // ENLACE VISTA
    public void setPanelProducto(PanelProducto miPanelProducto) {
        this.miPanelProducto = miPanelProducto;
    }

    // ENLACE MODELO
    // Vo
    public void setProducto(Producto miProducto) {
        this.miProducto = miProducto;
    }

    // Dao
    public void setProductoDAO(ProductoDAO miProductoDAO) {
        this.miProductoDAO = miProductoDAO;
    }
    
    public void setCategoriaDAO(CategoriaDAO miCategoriaDAO) {
        this.miCategoriaDAO = miCategoriaDAO;
    }
    
    public void setProveedorDAO(ProveedorDAO miProveedorDAO) {
        this.miProveedorDAO = miProveedorDAO;
    }

    // MÉTODOS DE CLASE
    // ProductoDAO
    public void registrarProducto() {
        miProducto = miPanelProducto.empaquetarDatosProducto();
        miProductoDAO.registrarProducto(miProducto);
        actualizarTablaProductos();
        miPanelProducto.limpiarCampos();
    }
    
    public void buscarProducto() {
        idProducto = Integer.parseInt(miPanelProducto.txtIdProducto.getText());
        miPanelProducto.desempaquetarDatosProducto(miProductoDAO.buscarProducto(idProducto));
    }
    
    public void modificarProducto() {
        miProducto = miPanelProducto.empaquetarDatosProducto();
        miProductoDAO.modificarProducto(miProducto);
        actualizarTablaProductos();
        miPanelProducto.limpiarCampos();
    }
    
    public void eliminarProducto() {
        idProducto = Integer.parseInt(miPanelProducto.txtIdProducto.getText());
        miProductoDAO.eliminarProducto(idProducto);
        actualizarTablaProductos();
        miPanelProducto.limpiarCampos();
    }
    
    public void cargarProductoSeleccionado() {
        idProducto = Integer.parseInt(miPanelProducto.idProductoSeleccionado());
        miPanelProducto.txtIdProducto.setText(idProducto + "");
        miPanelProducto.desempaquetarDatosProducto(miProductoDAO.buscarProducto(idProducto));
        miPanelProducto.txtIdProducto.requestFocus();
    }

    private void actualizarTablaProductos() {
        miPanelProducto.setTablaProductos(miProductoDAO.listarProductos(),
        miCategoriaDAO.listarCategorias(), miProveedorDAO.listarProveedores());
    }
}
