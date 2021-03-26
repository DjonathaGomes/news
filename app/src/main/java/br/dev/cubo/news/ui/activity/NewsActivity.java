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

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.dev.cubo.news.R;
import br.dev.cubo.news.api.vo.ListNewsVO;
import br.dev.cubo.news.api.vo.NewsVO;

public class NewsActivity extends AppCompatActivity {

    private ImageView imgNews, btnBack;
    private TextView titleNews, SubTitleNews, txtNews;
    private String titleNew, subTitle, textNews, imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //CONFIG STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //METHODS
        getComponents();
        configDate();

        //BTN BACK HOME
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(NewsActivity.this, MainActivity.class);
                startActivity(open);
                finish();
            }
        });

    }

    //CONFIG AND GET DATE
    private void configDate() {

        //GET DATE
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            titleNew = bundle.getString("title");
            subTitle = bundle.getString("sub_title");
            textNews = bundle.getString("txt_main");
            imgUrl = bundle.getString("url");

        }

        titleNews.setText(titleNew);
        SubTitleNews.setText(subTitle);
        txtNews.setText(textNews);

        //ERROR TREATMENT IMAGES
        if(imgUrl == null){
            imgNews.setImageResource(R.drawable.ic_notfound);
        }else {
            Glide.with(this).load(imgUrl).into(imgNews);
        }
    }

    //GET COMPONENTS
    private void getComponents() {

        titleNews = findViewById(R.id.titleNews);
        SubTitleNews = findViewById(R.id.SubTitleNews);
        txtNews = findViewById(R.id.txtNews);
        imgNews = findViewById(R.id.imgNews);
        btnBack = findViewById(R.id.btnBack);

    }
}