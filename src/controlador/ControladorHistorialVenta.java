package controlador;

import modelo.dao.CategoriaDAO;
import modelo.dao.ClienteDAO;
import modelo.dao.PedidoDAO;
import modelo.dao.ProductoDAO;
import modelo.dao.UsuarioDAO;
import vista.PanelHistorialVentas;

public class ControladorHistorialVenta {

    // Atributos de clase
    private PanelHistorialVentas miPanelHistorialVentas;
    private CategoriaDAO miCategoriaDAO;
    private ProductoDAO miProductoDAO;
    private UsuarioDAO miUsuarioDAO;
    private ClienteDAO miClienteDAO;
    private PedidoDAO miPedidoDAO;
    private int idVentaSeleccionado;
    private String dniUsuarioSelecionado;
    private String dniClienteSeleccionado;

    // Enlace vista
    public void setPanelHistorialVentas(PanelHistorialVentas miPanelHistorialVentas) {
        this.miPanelHistorialVentas = miPanelHistorialVentas;
    }

    // Configuración de los DAOs
    public void setUsuarioDAO(UsuarioDAO miUsuarioDAO) {
        this.miUsuarioDAO = miUsuarioDAO;
    }

    public void setProductoDAO(ProductoDAO miProductoDAO) {
        this.miProductoDAO = miProductoDAO;
    }

    public void setClienteDAO(ClienteDAO miClienteDAO) {
        this.miClienteDAO = miClienteDAO;
    }

    public void setPedidoDAO(PedidoDAO miPedidoDAO) {
        this.miPedidoDAO = miPedidoDAO;
    }

    public void setCategoriaDAO(CategoriaDAO miCategoriaDAO) {
        this.miCategoriaDAO = miCategoriaDAO;
    }

    // Método principal para cargar los pedidos seleccionados
    public void cargarPedidosSeleccionados() {
        // Obtener los valores seleccionados desde la vista
        idVentaSeleccionado = miPanelHistorialVentas.idVentaSeleccionado();
        dniUsuarioSelecionado = miPanelHistorialVentas.dniUsuarioSeleccionado();
        dniClienteSeleccionado = miPanelHistorialVentas.dniClienteSeleccionado();

        // Desempaquetar datos de usuario y cliente
        desempaquetarDatosUsuario(); // Extract Method: Long Method
        desempaquetarDatosCliente(); // Extract Method: Long Method

        // Configurar la tabla de pedidos
        configurarTablaPedidos(); // Extract Method: Long Method

        // Enfocar la tabla de pedidos
        miPanelHistorialVentas.tblPedidos.requestFocus();
    }

    // Método para desempaquetar los datos del usuario
    private void desempaquetarDatosUsuario() {
        miPanelHistorialVentas.desempaquetarDatosUsuario(miUsuarioDAO.buscarUsuario(dniUsuarioSelecionado));
    }

    // Método para desempaquetar los datos del cliente
    private void desempaquetarDatosCliente() {
        miPanelHistorialVentas.desempaquetarDatosCliente(miClienteDAO.buscarCliente(dniClienteSeleccionado));
    }

    // Método para configurar la tabla de pedidos
    private void configurarTablaPedidos() {
        miPanelHistorialVentas.SetTablaPedidos(
                miPedidoDAO.listarCarritoPedidos(idVentaSeleccionado),
                miProductoDAO.listarProductos(),
                miCategoriaDAO.listarCategorias()
        );
    }
}
