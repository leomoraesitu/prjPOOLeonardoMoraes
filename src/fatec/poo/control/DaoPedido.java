package fatec.poo.control;

import fatec.poo.model.Cliente;
import fatec.poo.model.Pedido;
import fatec.poo.model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leomoraes
 */
public class DaoPedido {
    private Connection conn;   
    
    
    public DaoPedido(Connection conn){
        this.conn = conn;
       
    }    
   
    
    public Pedido consultar(String numero){
        Pedido objPed = null;
        
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("SELECT * from tblPedido where Numero_Pedido = ?");
        
        ps.setString(1, numero);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            objPed = new Pedido(rs.getString("Numero_Pedido"));
            objPed.setDataEmissao(rs.getString("Data_Pedido"));
            objPed.setValor(rs.getDouble("Valor_Pedido")); 
            
            Cliente c = new DaoCliente(conn).consultar(rs.getString("Cpf_Cliente"));
            c.addPedido(objPed);
            objPed.setCliente(c);
            
            Vendedor v = new DaoVendedor(conn).consultar(rs.getString("Cpf_Vendedor"));
            v.addPedido(objPed);
            objPed.setVendedor(v);
            }
        }catch (SQLException ex){
            System.out.println(ex.toString());
        }
        return(objPed);        
    }
    
    public void inserir(Pedido objPed){
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("Insert into tblPedido(Numero_Pedido, Data_Pedido, Valor_Pedido, Cpf_Cliente, Cpf_Vendedor)" +
                                   "values(?,?,?,?,?)");
        ps.setString(1, objPed.getNumero());
        ps.setString(2, objPed.getDataEmissao());
        ps.setDouble(3, objPed.getValor());
        ps.setString(4, objPed.getCliente().getCpf());
        ps.setString(5, objPed.getVendedor().getCpf());
        
        ps.execute();
        }catch (SQLException ex){
            System.out.println(ex.toString() + " " + ex.getMessage());
        }
    }
    
    public void alterar(Pedido pedido){
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("Update tblPedido set Data_Pedido = ?, Valor_pedido = ?, Cpf_Cliente = ?, Cpf_Vendedor = ?" +
                                   "where Numero_Pedido = ?");
        ps.setString(1, pedido.getDataEmissao());
        ps.setDouble(2, pedido.getValor());        
        ps.setString(3, pedido.getCliente().getCpf());
        ps.setString(4, pedido.getVendedor().getCpf());
        ps.setString(5, pedido.getNumero());
        
        ps.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }        
    }
    
    public void excluir(Pedido pedido){
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("DELETE FROM tblPedido where Numero_Pedido = ?");
        
        ps.setString(1, pedido.getNumero());
        
        ps.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }    
    }
}
