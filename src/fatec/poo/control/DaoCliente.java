package fatec.poo.control;

import fatec.poo.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leomoraes
 */
public class DaoCliente {
    
    private Connection conn;
    
    public DaoCliente(Connection conn){
        this.conn = conn;
    }
    
    public Cliente consultar(String cpf){
        Cliente objCli = null;
        
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("SELECT * from tblCliente where Cpf_Cliente = ?");
        
        ps.setString(1, cpf);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
        objCli = new Cliente(rs.getString("Cpf_Cliente"),
                             rs.getString("Nome_Cliente"),
                             rs.getDouble("LimiteCredito_Cliente"));
            objCli.setEndereco(rs.getString("Endereco_Cliente"));
            objCli.setCep(rs.getString("Cep_Cliente"));
            objCli.setCidade(rs.getString("Cidade_Cliente"));
            objCli.setUf(rs.getString("Uf_Cliente"));
            objCli.setDdd(rs.getString("Ddd_Cliente"));
            objCli.setTelefone(rs.getString("Telefone_Cliente"));
            objCli.setLimiteDisponivel(rs.getDouble("LimiteDisponivel_Cliente"));
            }        
        }catch (SQLException ex){
            System.out.println(ex.toString());
        }
        return(objCli);
    }
    
    public void inserir(Cliente objCli){
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("Insert into tblCliente(Cpf_Cliente, Nome_Cliente," +
                                   "Endereco_Cliente, Cidade_Cliente, Uf_Cliente," +
                                   "Cep_Cliente, Ddd_Cliente, Telefone_Cliente," +
                                   "LimiteCredito_Cliente, LimiteDisponivel_Cliente) values(?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, objCli.getCpf());
        ps.setString(2, objCli.getNome());
        ps.setString(3, objCli.getEndereco());
        ps.setString(4, objCli.getCidade());
        ps.setString(5, objCli.getUf());
        ps.setString(6, objCli.getCep());
        ps.setString(7, objCli.getDdd());
        ps.setString(8, objCli.getTelefone());
        ps.setDouble(9, objCli.getLimiteCredito());
        ps.setDouble(10, objCli.getLimiteDisponivel());
        
        ps.execute();
        }catch (SQLException ex){
            System.out.println(ex.toString() + " " + ex.getMessage()); 
        }
    }
    
    public void alterar(Cliente cliente){
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("Update tblCliente set Nome_Cliente = ?, " +
                                   "Endereco_Cliente = ? , Cidade_Cliente = ?, Uf_Cliente = ?, " +
                                   "Cep_Cliente = ?, Ddd_Cliente = ?, Telefone_Cliente = ?, " +
                                   "LimiteCredito_Cliente = ?, LimiteDisponivel_Cliente = ? " +
                                   "where Cpf_Cliente = ?");
        
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getEndereco());
        ps.setString(3, cliente.getCidade());
        ps.setString(4, cliente.getUf());
        ps.setString(5, cliente.getCep());
        ps.setString(6, cliente.getDdd());
        ps.setString(7, cliente.getTelefone());
        ps.setDouble(8, cliente.getLimiteCredito());
        ps.setDouble(9, cliente.getLimiteDisponivel());
        ps.setString(10, cliente.getCpf());
        
        ps.execute();
        }catch (SQLException ex){
            System.out.println(ex.toString());
        }        
    }
    
    public void excluir(Cliente cliente){
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("DELETE FROM tblCliente where Cpf_Cliente = ?");
        
        ps.setString(1, cliente.getCpf());
        
        ps.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString()); 
        }
    }
    
}

