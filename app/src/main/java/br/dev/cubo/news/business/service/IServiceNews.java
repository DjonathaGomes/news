package br.dev.cubo.news.business.service;

import java.util.List;

import br.dev.cubo.news.business.model.NewsVO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author D.M.G.
 * @company CUBO
 * @site www.cubo.dev.br
 * @phone +55 11 9-7727-8055
 */
public interface IServiceNews {

    @GET("df5b4b53b491f2f4a9eb48fde84b1d29/raw/e40f3b6712258d9607ce3348acd4583317df85d9/news.json")
    Call<List<NewsVO>> getListNews();
}
