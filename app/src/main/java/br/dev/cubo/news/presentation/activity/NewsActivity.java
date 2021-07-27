package br.dev.cubo.news.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;

import br.dev.cubo.news.R;
import br.dev.cubo.news.business.model.NewsVO;
import br.dev.cubo.news.databinding.ActivityNewsBinding;

/**
 * @author D.M.G.
 * @company CUBO
 * @site www.cubo.dev.br
 * @phone +55 11 9-7727-8055
 */
public class NewsActivity extends AppCompatActivity {
    private ActivityNewsBinding binding;
    public static final String EXTRA_OBJ = "EXTRA_OBJ";
    private NewsVO news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);

        Bundle bundle = getIntent().getExtras();
        news = (NewsVO) bundle.getSerializable("EXTRA_OBJ");

        customScreen();
        prepareUi();
    }

    private void prepareUi() {

        if (news != null) {
            setupNews();
        }else {
            binding.error.setVisibility(View.VISIBLE);
            binding.content.setVisibility(View.GONE);

            binding.error.setOnClickListener(v -> {
                onBackPressed();
            });
        }

    }

    private void setupNews() {
        binding.btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        if (news.getImg() != null && !news.getImg().isEmpty()){
            Glide.with(this).load(news.getImg()).into(binding.imgNews);
        }else {
            binding.imgNews.setImageResource(R.drawable.ic_notfound);
        }

        binding.titleNews.setText(news.getTitulo());
        binding.SubTitleNews.setText(news.getDesc());
        binding.txtNews.setText(news.getNoticia());

    }

    private void customScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        overridePendingTransition(R.anim.enter_out, R.anim.exit_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.enter_in, R.anim.exit_in);
    }
}