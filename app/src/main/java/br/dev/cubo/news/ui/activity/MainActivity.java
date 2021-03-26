package br.dev.cubo.news.ui.activity;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.dev.cubo.news.R;
import br.dev.cubo.news.api.retrofit.Retrofit;
import br.dev.cubo.news.api.vo.ImgVO;
import br.dev.cubo.news.api.vo.ListNewsVO;
import br.dev.cubo.news.api.vo.NewsVO;
import br.dev.cubo.news.ui.recyclerview.NewsRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView_news;
    private List<NewsVO> LstNewsVO;
    private ListNewsVO listNewsVO;
    
    private NewsRecyclerAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);//  set limits screen

        //METHODS
        getComponents();
        getListNews();

        LstNewsVO = new ArrayList<>();

    }

    //GET LIST
    private void getListNews() {
        Retrofit.getInstance().allNews().enqueue(new Callback<List<ListNewsVO>>() {
            @Override
            public void onResponse(Call<List<ListNewsVO>> call, Response<List<ListNewsVO>> response) {
                if (response.isSuccessful()){
                    listNewsVO = response.body().get(0);
                    for (NewsVO newsVO : listNewsVO.getConteudos()){ LstNewsVO.add(newsVO); }

                    //CONFIG LIST METHOD
                    configList(LstNewsVO);
                }
            }

            @Override
            public void onFailure(Call<List<ListNewsVO>> call, Throwable t) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alerta");
                builder.setIcon(R.drawable.ic_erro);
                builder.setMessage("Nao foi possivel carregar os arquivos");
                builder.setCancelable(false);

                builder.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    //METHOD CONFIG LIST
    private void configList(List<NewsVO> listNewsVOS) {
        setOnClickListner();
        NewsRecyclerAdapter adapter = new NewsRecyclerAdapter(this, listNewsVOS, listener);
        recyclerView_news.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_news.setAdapter(adapter);
    }

    //SEND DATE ACTIVITY [ NEWS_ACTIVITY ]
    private void setOnClickListner() {
        listener = new NewsRecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                intent.putExtra("title",LstNewsVO.get(position).getTitulo());
                intent.putExtra("sub_title",LstNewsVO.get(position).getSubTitulo());
                intent.putExtra("txt_main",LstNewsVO.get(position).getTexto());

                List<ImgVO> erro = LstNewsVO.get(position).getImagens();

                if (erro != null && !erro.isEmpty()){
                    intent.putExtra("url",LstNewsVO.get(position).getImagens().get(0).getUrl());
                }else {
                    String error = null;
                    intent.putExtra("url", error);
                }

                startActivity(intent);
            }
        };
    }

    //COMPONENTS
    private void getComponents() { recyclerView_news = findViewById(R.id.list_news_recyclerView); }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
