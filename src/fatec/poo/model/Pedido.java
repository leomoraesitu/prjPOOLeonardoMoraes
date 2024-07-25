package fatec.poo.model;

/**
 *
 * @author leomoraes
 */
public class Pedido {
    private String numero;
    private String dataEmissao;
    private double valor;
    private Cliente cliente;
    private Vendedor vendedor;
    
    public Pedido(String numero){
        this.numero = numero;
    }
    
    public String getNumero(){
        return numero;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    
}
