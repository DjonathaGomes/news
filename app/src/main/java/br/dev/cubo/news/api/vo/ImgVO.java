package br.dev.cubo.news.api.vo;

/**
 *
 *
 * @company CUBO
 * @site www.cubo.dev.br
 * @phone +55 11 9-7727-8055
 * @department development/support and design UI & UX
 *
 * @author D.M.G.
 * @since create at 25 Mar 2021
 *
 *
 */

public class ImgVO {

    private String autor;
    private String fonte;
    private String legenda;
    private String url;

    public ImgVO(String autor, String fonte, String legenda, String url) {
        this.autor = autor;
        this.fonte = fonte;
        this.legenda = legenda;
        this.url = url;
    }

    public String getAutor() {
        return autor;
    }

    public String getFonte() {
        return fonte;
    }

    public String getLegenda() {
        return legenda;
    }

    public String getUrl() {
        return url;
    }

}
