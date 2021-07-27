package br.dev.cubo.news.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

import br.dev.cubo.news.R;
import br.dev.cubo.news.business.model.NewsVO;
import br.dev.cubo.news.databinding.ActivityMainBinding;
import br.dev.cubo.news.presentation.adapter.NewsAdapter;
import br.dev.cubo.news.presentation.viewmodel.MainActivityViewModel;

/**
 * @author D.M.G.
 * @company CUBO
 * @site www.cubo.dev.br
 * @phone +55 11 9-7727-8055
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private List<NewsVO> news;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        customScreen();
        initViewModel();
    }

    private void prepareUi() {
        setupRecyclerView(news);

        binding.openMenu.setOnClickListener(v -> {

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);

        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getNewsObserver().observe(this, new Observer<List<NewsVO>>() {
            @Override
            public void onChanged(List<NewsVO> newsVOS) {
                if (newsVOS != null){
                    news = newsVOS;
                    adapter.setNewsAdapter(news);
                }else {
                    error();
                }
            }
        });

        viewModel.apiCall();
        prepareUi();
    }

    private void setupRecyclerView(List<NewsVO> news) {
        adapter = new NewsAdapter(this, news);
        binding.rvNews.setLayoutManager(new LinearLayoutManager(this));
        binding.rvNews.setAdapter(adapter);
        adapter.setOnItemClickListener(this::goToNewsScreen);
    }

    private void goToNewsScreen(NewsVO news) {
        if (news == null){
            Log.e("erro2", "erro");
        }else {
            Intent intent = new Intent(this, NewsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(NewsActivity.EXTRA_OBJ, news);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private void error() {

        binding.rvNews.setVisibility(View.GONE);
        binding.error.setVisibility(View.VISIBLE);

        binding.error.setOnClickListener(v -> {
            onBackPressed();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void customScreen() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);//  set limits screen
        overridePendingTransition(R.anim.enter_out, R.anim.exit_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
