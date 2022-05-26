import java.time.LocalDate;


public class Produto {

    private String codigo;
    private String nome;
    private Double valor;
    private Double valorTotalVenda;
    private int qtdEstoque;
    private LocalDate data;

    
    public Produto(){
        setCodigo(codigo);
        setNome(nome);
        setValor(valor);
        setqtdEstoque(qtdEstoque);
        setdata(data);

    }
    

    
    public Double getValorTotalVenda() {
        return valorTotalVenda;
    }



    public void setValorToalVenda(Double valorToalVenda) {
        this.valorTotalVenda = valorToalVenda;
    }



    public String getcodigo(){
        return this.codigo;
    }
    public String getnome(){
        return this.nome;
    }
    public Double getvalor(){
        return this.valor;
    }
    public int getqtdEstoque(){
        return this.qtdEstoque;
    }
    public LocalDate getdata(){
        return this.data;
    }
   

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setqtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    public void setdata(LocalDate data){
        this.data = data;
    }
    

    

    @Override
    public String toString(){
        return "codigo: "  + codigo + ", Nome: " + nome + ", Valor:R$ " + valor + ", Quatidade em estoque: " + qtdEstoque + "Media valor total das vendas: " + valorTotalVenda;
    }
    
    
}
