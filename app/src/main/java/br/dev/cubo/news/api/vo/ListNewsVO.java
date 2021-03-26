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

public class ListNewsVO {

    private final List<NewsVO> conteudos;
    private final String produto;

    public ListNewsVO(List<NewsVO> conteudos, String produto) {
        this.conteudos = conteudos;
        this.produto = produto;
    }

    public List<NewsVO> getConteudos() {
        return conteudos;
    }

    public String getProduto() {
        return produto;
    }

}
