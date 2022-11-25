package Clases;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Steven Dual
 */
public class consultas {
    // Declaramos la conexion a mysql
    public static Connection con;
    // Declaramos los datos de conexion a la bd
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="Dual1020";
    private static final String url="jdbc:mysql://localhost:3306/login?characterEncoding=utf8";
    // Funcion que va conectarse a mi bd de mysql
    public Connection conectar(){
      con = null;
      try{
          con = (Connection) DriverManager.getConnection(url, user, pass);
          if(con!=null){
          }
      }
      catch(SQLException e)
      {
          JOptionPane.showMessageDialog(null,"Error" + e.toString());
      }
      return con;
    }
    
    public void RellenaLaTablaConDatosMySQL(String tabla, JTable visor)
    {
        String sql = "Select * from " + tabla;
        Statement st;
        Connection conexion = conectar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Libro");
        model.addColumn("Título");
        model.addColumn("Editorial");
        model.addColumn("Año");
        
        visor.setModel(model);
        String [] dato = new String[7];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {      
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                model.addRow(dato);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void leerLectores(String tabla, JTable visor)
    {
        String sql = "Select * from " + tabla;
        Statement st;
        Connection conexion = conectar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Lector");
        model.addColumn("Nombre");
        model.addColumn("Direccion");
        model.addColumn("Telefono");
        model.addColumn("Ciudad");
        model.addColumn("Correo");
        visor.setModel(model);
        String [] dato = new String[6];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {      
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                dato[5] = rs.getString(6);
                model.addRow(dato);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void leerSalidas(String tabla, JTable visor)
    {
        String sql = "Select * from " + tabla;
        Statement st;
        Connection conexion = conectar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Salida");
        model.addColumn("Fecha préstamo");
        model.addColumn("Fecha regreso");
        model.addColumn("Observación");
        model.addColumn("FK_Libro");
        model.addColumn("FK_Usuario");
        visor.setModel(model);
        String [] dato = new String[6];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {      
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                dato[5] = rs.getString(6);
                model.addRow(dato);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void leerPrestamos(JTable visor)
    {
        String sql = "select nombre as 'Prestador', titulo as 'Nombre del libro'," +
"fecha_salida as 'Fecha de préstamo', fecha_regreso as 'Fecha devolución', observarcion as 'Observaciones'" +
"from libro inner join usuario on libro.id_libro = usuario.id_usuario left join salida on salida.id_salida = usuario.id_usuario";
        Statement st;
        Connection conexion = conectar();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Prestador");
        model.addColumn("Nombre del libro");
        model.addColumn("Fecha de préstamo");
        model.addColumn("Fecha devolución");
        model.addColumn("Observaciones");
        visor.setModel(model);
        String [] dato = new String[6];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {      
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                model.addRow(dato);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    public void InsertarLibro(JTextField titulo, JTextField editorial, JTextField anio)
    {
        try{
            Connection conecta = conectar();
            CallableStatement proc = conecta.prepareCall(" CALL SP_INSERTALIBRO(?,?,?)");
            proc.setString(1, titulo.getText());
            proc.setString(2, editorial.getText());
            proc.setString(3, anio.getText());
            proc.execute();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void InsertarUsuario(JTextField nombre, JTextField direccion, JTextField telefono, JTextField ciudad, JTextField correo)
    {
        try{
            Connection conecta = conectar();
            CallableStatement proc = conecta.prepareCall(" CALL SP_INSERTAUSUARIO(?,?,?,?,?)");
            proc.setString(1, nombre.getText());
            proc.setString(2, direccion.getText());
            proc.setString(3, telefono.getText());
            proc.setString(4, ciudad.getText());
            proc.setString(5, correo.getText());
            proc.execute();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void EliminaRegistro(String id, String tabla, String id_name)
    {
        String sql = "delete from " + tabla + " where " + id_name + " = " + id;
        Statement st;
        Connection conexion = conectar();
        try
        {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public void ActualizarLibro(JTextField titulo, JTextField editorial, JTextField anio, String id)
    {
        String sql = "update libro set titulo = '" + titulo.getText() +"', editorial = '" + editorial.getText()+"', anio = '" + anio.getText()+ "' where id_libro = " + id;
        Statement st;
        Connection conexion = conectar();
        try
        {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Libro Actualizado");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public void ActualizarUsuario(JTextField nombre, JTextField direccion, JTextField telefono, JTextField ciudad, JTextField correo, String id)
    {
        String sql = "update usuario set nombre = '" + nombre.getText() +"', direccion = '" + direccion.getText()+"', telefono = '" + telefono.getText()+"', ciudad = '" + ciudad.getText()+ "', correo = '" + correo.getText() + "' where id_usuario = " + id;
        Statement st;
        Connection conexion = conectar();
        try
        {
            st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Lector Actualizado");
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}
