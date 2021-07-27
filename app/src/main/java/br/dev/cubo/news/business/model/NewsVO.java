package br.dev.cubo.news.business.model;

import java.io.Serializable;

/**
 * @author D.M.G.
 * @company CUBO
 * @site www.cubo.dev.br
 * @phone +55 11 9-7727-8055
 */
public class NewsVO implements Serializable {
    private String titulo;
    private String desc;
    private String data;
    private String origen;
    private AutorVO autor;
    private String noticia;
    private String img;

    public NewsVO(String titulo, String desc, String data, String origen, AutorVO autor, String noticia, String img) {
        this.titulo = titulo;
        this.desc = desc;
        this.data = data;
        this.origen = origen;
        this.autor = autor;
        this.noticia = noticia;
        this.img = img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public AutorVO getAutor() {
        return autor;
    }

    public void setAutor(AutorVO autor) {
        this.autor = autor;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
