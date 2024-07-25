package fatec.poo.model;

import java.util.ArrayList;

/**
 *
 * @author leomoraes
 */
public class Cliente extends Pessoa {
    
    private double limiteCredito;
    private double limiteDisponivel;
    private ArrayList<Pedido> pedidos;
    
    public Cliente(String cpf, String nome, double limiteCredito){
        super(cpf, nome);
        this.limiteCredito = limiteCredito;
        this.limiteDisponivel = limiteCredito;        
        pedidos = new ArrayList<Pedido>();
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }
    
    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }
    
          
    public void setLimiteDisponivel(double limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    } 
    
    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;        
    } 
        
    
    public void addPedido(Pedido p){
        pedidos.add(p);               
    }
    
    public void removerPedido(Pedido p){
        pedidos.remove(p);
        p.setCliente(null);        
    } 
    
    
}
