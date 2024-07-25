package fatec.poo.control;

import fatec.poo.model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leomoraes
 */
public class DaoVendedor {
    private Connection conn;
    
    public DaoVendedor(Connection conn){
        this.conn = conn;
    }
    
    public Vendedor consultar(String cpf){
        Vendedor objVend = null;
        
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("SELECT * from tblVendedor where Cpf_Vendedor = ?");
        
        ps.setString(1, cpf);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
        objVend = new Vendedor(rs.getString("Cpf_Vendedor"),
                               rs.getString("Nome_Vendedor"),
                               rs.getDouble("SalarioBase_Vendedor"));
            objVend.setEndereco(rs.getString("Endereco_Vendedor"));
            objVend.setCidade(rs.getString("Cidade_Vendedor"));
            objVend.setUf(rs.getString("Uf_Vendedor"));
            objVend.setCep(rs.getString("Cep_Vendedor"));
            objVend.setDdd(rs.getString("Ddd_Vendedor"));
            objVend.setTelefone(rs.getString("Telefone_Vendedor"));
            objVend.setTaxaComissao(rs.getDouble("TaxaComissao_Vendedor"));       
            }
        }catch (SQLException ex){
            System.out.println(ex.toString());
        }
        return(objVend);  
    }
    
    public void inserir(Vendedor objVend){
    PreparedStatement ps;
    try{
    ps = conn.prepareStatement("Insert into tblVendedor(Cpf_Vendedor, Nome_Vendedor," +
                               "Endereco_Vendedor, Cidade_Vendedor, Uf_Vendedor," +
                               "Cep_Vendedor, Ddd_Vendedor, Telefone_Vendedor," +
                               "SalarioBase_Vendedor, TaxaComissao_Vendedor)" +
                               "values (?,?,?,?,?,?,?,?,?,?)");
    ps.setString(1, objVend.getCpf());
    ps.setString(2, objVend.getNome());
    ps.setString(3, objVend.getEndereco());
    ps.setString(4, objVend.getCidade());
    ps.setString(5, objVend.getUf());
    ps.setString(6, objVend.getCep());
    ps.setString(7, objVend.getDdd());
    ps.setString(8, objVend.getTelefone());
    ps.setDouble(9, objVend.getSalarioBase());
    ps.setDouble(10, objVend.getTaxaComissao());

    ps.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString() + " " + ex.getMessage());
        }
    }
    
    public void alterar(Vendedor vendedor){
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("Update tblVendedor set Nome_Vendedor = ?," +
                                   "Endereco_Vendedor = ?, Cidade_Vendedor = ?," +
                                   "Uf_Vendedor = ?, Cep_Vendedor = ?, Ddd_Vendedor = ?," +
                                   "Telefone_Vendedor = ?, SalarioBase_Vendedor = ?," +
                                   "TaxaComissao_Vendedor = ? where Cpf_Vendedor = ?");
    ps.setString(1, vendedor.getNome());
    ps.setString(2, vendedor.getEndereco());
    ps.setString(3, vendedor.getCidade());
    ps.setString(4, vendedor.getUf());
    ps.setString(5, vendedor.getCep());
    ps.setString(6, vendedor.getDdd());
    ps.setString(7, vendedor.getTelefone());
    ps.setDouble(8, vendedor.getSalarioBase());
    ps.setDouble(9, vendedor.getTaxaComissao());
    ps.setString(10, vendedor.getCpf());
    
    ps.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
    public void excluir(Vendedor vendedor){
        PreparedStatement ps;
        try{
        ps = conn.prepareStatement("DELETE FROM tblVendedor where Cpf_Vendedor = ?");
        
        ps.setString(1, vendedor.getCpf());
        
        ps.execute();
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
}
