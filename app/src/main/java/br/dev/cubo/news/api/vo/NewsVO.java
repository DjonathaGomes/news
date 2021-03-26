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

import java.util.List;

public class NewsVO {

    private final List<String> autores;
    private final Boolean informePublicitario;
    private final String subTitulo;
    private final String texto;
    private final String[] videos;
    private final String atualizadoEm;
    private final int id;
    private final String publicadoEm;
    private final SectionVO section;
    private final String tipo;
    private final String titulo;
    private final String url;
    private final String urlOriginal;
    private final List<ImgVO> imagens;

    public NewsVO(List<String> autores, Boolean informePublicitario,
                  String subTitulo, String texto, String[] videos,
                  String atualizadoEm, int id, String publicadoEm,
                  SectionVO section, String tipo, String titulo,
                  String url, String urlOriginal, List<ImgVO> imagens) {
        this.autores = autores;
        this.informePublicitario = informePublicitario;
        this.subTitulo = subTitulo;
        this.texto = texto;
        this.videos = videos;
        this.atualizadoEm = atualizadoEm;
        this.id = id;
        this.publicadoEm = publicadoEm;
        this.section = section;
        this.tipo = tipo;
        this.titulo = titulo;
        this.url = url;
        this.urlOriginal = urlOriginal;
        this.imagens = imagens;
    }

    public List<String> getAutores() {
        return autores;
    }

    public Boolean getInformePublicitario() {
        return informePublicitario;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public String getTexto() {
        return texto;
    }

    public String[] getVideos() {
        return videos;
    }

    public String getAtualizadoEm() {
        return atualizadoEm;
    }

    public int getId() {
        return id;
    }

    public String getPublicadoEm() {
        return publicadoEm;
    }

    public SectionVO getSection() {
        return section;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public List<ImgVO> getImagens() {
        return imagens;
    }

}
