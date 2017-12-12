package br.com.frajolas;

/**
 * Created by 16254836 on 28/11/2017.
 */

public class Pizza  {

    private int id;
    private String nome;
    private Double preco;
    private String descricao;
    private String foto;
    private String categoria;
    private Double avaliacao;
    private int totalAvaliacao;


    public static Pizza create (int id,String nome,Double preco,String descricao,String foto,String categoria,Double avaliacao,int totalAvaliacao){

        Pizza p = new Pizza();
            p.setId(id);
            p.setNome(nome);
            p.setPreco(preco);
            p.setDescricao(descricao);
            p.setFoto(foto);
            p.setCategoria(categoria);
            p.setAvaliacao(avaliacao);
            p.setTotalAvaliacao(totalAvaliacao);
        return p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTotalAvaliacao() {
        return totalAvaliacao;
    }

    public void setTotalAvaliacao(int totalAvaliacao) {
        this.totalAvaliacao = totalAvaliacao;
    }

    public Double getAvaliacao() {
        return avaliacao;

    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
