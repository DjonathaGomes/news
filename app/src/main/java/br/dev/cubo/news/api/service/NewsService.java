package br.dev.cubo.news.api.service;

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

import br.dev.cubo.news.api.vo.ListNewsVO;
import retrofit2.Call;
import retrofit2.http.GET;

//PARAMETER AND CALLS
public interface NewsService {

    @GET("capa.json")
    Call <List<ListNewsVO>> allNews();
}
