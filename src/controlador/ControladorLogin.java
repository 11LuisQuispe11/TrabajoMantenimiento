/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JOptionPane;

import modelo.dao.UsuarioDAO;
import modelo.vo.Usuario;
import vista.VentanaLogin;
import vista.VentanaPrincipal;

public class ControladorLogin {
    private VentanaLogin miVentanaLogin;
    private VentanaPrincipal miVentanaPrincipal;
    private UsuarioDAO miUsuarioDAO;
    private Usuario miUsuario;

    public void setVentanaLogin(VentanaLogin miVentanaLogin) {
        this.miVentanaLogin = miVentanaLogin;
    }

    public void setVentanaPrincipal(VentanaPrincipal miVentanaPrincipal) {
        this.miVentanaPrincipal = miVentanaPrincipal;
    }

    public void setUsuario(Usuario miUsuario) {
        this.miUsuario = miUsuario;
    }

    public void setUsuarioDAO(UsuarioDAO miUsuarioDAO) {
        this.miUsuarioDAO = miUsuarioDAO;
    }

    public void logearUsuario() {
        String username = miVentanaLogin.getUsername();
        String password = miVentanaLogin.getPassword();
        miUsuario = buscarUsuario(username);
        if (miUsuario != null) {
            if (validarCredenciales(password)) {
                iniciarSesion();
            } else {
                mostrarMensaje("La contraseña proporcionada es incorrecta");
            }
        } else {
            mostrarMensaje("Usuario no registrado");
        }
        limpiarCampos();
    }

    private Usuario buscarUsuario(String username) {
        return miUsuarioDAO.buscarUsuarioUsername(username);
    }

    private boolean validarCredenciales(String password) {
        return password.equals(miUsuario.getPassword());
    }

    private void iniciarSesion() {
        miVentanaLogin.setVisible(false);
        miVentanaPrincipal.setVisible(true);
        miVentanaPrincipal.panelHome.setVisible(true);
        validarPermisoUsuario();
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void cerrarAplicacion() {
        System.exit(0);
    }

    public void validarPermisoUsuario() {
        String rol = miUsuario.getRol();
        if (rol.equalsIgnoreCase("VENDEDOR")) {
            miVentanaPrincipal.btnUsuarios.setVisible(false);
            setLayoutBotonesVentanaPrincipal(5, 35);
        } else {
            miVentanaPrincipal.btnUsuarios.setVisible(true);
            setLayoutBotonesVentanaPrincipal(5, 24);
        }
    }

    private void setLayoutBotonesVentanaPrincipal(int ver, int hor) {
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, ver, hor);
        flowLayout1.setAlignOnBaseline(true);
        miVentanaPrincipal.panLatBtns.setLayout(flowLayout1);
    }

    private void limpiarCampos() {
        miVentanaLogin.limpiarCampos();
    }
}
