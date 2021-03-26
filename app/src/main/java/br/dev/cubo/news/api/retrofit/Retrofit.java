package br.dev.cubo.news.api.retrofit;

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

import br.dev.cubo.news.api.service.NewsService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

//CONNECTION CLASS | USING RETROFIT
public class Retrofit {

    private static NewsService newsService;

    public static NewsService getInstance() {
        if (newsService == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/Infoglobo/desafio-apps/master/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            newsService = retrofit.create(NewsService.class);
        }

        return newsService;
    }

}
