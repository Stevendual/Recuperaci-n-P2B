package Clases;

import Forms.FormMenuPrincipal;
import Forms.ProyectoBiblioteca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Steven Dual
 */
public class CLogin {
    
    public void validaUsuario(JTextField usuario, JPasswordField contrasenia){
    
        try {
            ResultSet rs=null;           
            PreparedStatement ps= null;
            
            Clases.CConexion objetoConexion = new Clases.CConexion();
            
            String consulta="select * from Usuarios where Usuarios.ingresoUsuario =(?) and Usuarios.ingresoContrasenia=(?);";
            ps=objetoConexion.estableceConexion().prepareStatement(consulta);
            
            String contra = String.valueOf(contrasenia.getPassword());
            
            ps.setString(1, usuario.getText());
            ps.setString(2,contra);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                
                JOptionPane.showMessageDialog(null,"El Usuario es Correcto");
                ProyectoBiblioteca objetoProyecto = new ProyectoBiblioteca();
                objetoProyecto.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"El Usuario es INCORRECTO, VUELVA A INTENTAR");
            }
            
      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR: "+e.toString());
        }
    }
    
}
