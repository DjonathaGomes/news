package br.dev.cubo.news.business.model;

import java.io.Serializable;

/**
 * @author D.M.G.
 * @company CUBO
 * @site www.cubo.dev.br
 * @phone +55 11 9-7727-8055
 */
public class AutorVO implements Serializable {
    private String nome;
    private String desc;

    public AutorVO(String nome, String desc) {
        this.nome = nome;
        this.desc = desc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
