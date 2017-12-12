package br.com.frajolas.app;

/**
 * Created by 16254826 on 28/11/2017.
 */

public class Produto {

    private int id;
    private String nome;
    private String preco;
    private String descricao;
    private String foto;


    public static Produto create(int id,  String nome, String preco, String descricao, String foto){

        Produto c = new Produto();
        c.setId(id);
        c.setNome(nome);
        c.setPreco(preco);
        c.setDescricao(descricao);
        c.setFoto(foto);

        return c;
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

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
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
}

