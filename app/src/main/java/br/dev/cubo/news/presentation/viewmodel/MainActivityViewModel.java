package br.dev.cubo.news.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.dev.cubo.news.business.model.NewsVO;
import br.dev.cubo.news.business.service.IServiceNews;
import br.dev.cubo.news.network.Connection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author D.M.G.
 * @company CUBO
 * @site www.cubo.dev.br
 * @phone +55 11 9-7727-8055
 */
public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<NewsVO>> listpNews;

    public MainActivityViewModel() {
        listpNews = new MutableLiveData<>();
    }

    public MutableLiveData<List<NewsVO>> getNewsObserver(){
        return listpNews;
    }

    public void apiCall(){
        IServiceNews api = Connection.getConnectClient().create(IServiceNews.class);
        Call<List<NewsVO>> call = api.getListNews();
        call.enqueue(new Callback<List<NewsVO>>() {
            @Override
            public void onResponse(Call<List<NewsVO>> call, Response<List<NewsVO>> response) {

                listpNews.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NewsVO>> call, Throwable t) {

                listpNews.postValue(null);
            }
        });
    }
}
