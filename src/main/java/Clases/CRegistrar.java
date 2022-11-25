package Clases;

import Forms.FormMenuPrincipal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Steven Dual
 */
public class CRegistrar {
    
    public void RegistraUsuario(JTextField usuario, JPasswordField contrasenia){
    
//        try {
//            ResultSet rs=null;           
//            PreparedStatement ps= null;
//            
//            Clases.CConexion objetoConexion = new Clases.CConexion();
//            
//            String consulta="Insert into Usuarios (ingresoUsuario, ingresoContrasenia) values ('" + usuario +"', '" + contrasenia +"');";
//            ps=objetoConexion.estableceConexion().prepareStatement(consulta);
//            
//            String contra = String.valueOf(contrasenia.getPassword());
//            
//            ps.setString(1, usuario.getText());
//            ps.setString(2,contra);
//            
//            rs = ps.executeQuery();
//            
//            if (rs.next()) {
//                
//                JOptionPane.showMessageDialog(null,"Usuario registrado correctamente");
//                FormMenuPrincipal objetoMenu = new FormMenuPrincipal();
//                objetoMenu.setVisible(true);
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(null,"Registro INCORRECTO, VUELVA A INTENTAR");
//            }
//            
//      
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"ERROR: "+e.toString());
//        }
    }
    
}
