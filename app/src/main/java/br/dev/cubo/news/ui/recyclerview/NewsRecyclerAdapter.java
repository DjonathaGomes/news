package br.dev.cubo.news.ui.recyclerview;

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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;

import java.util.List;

import br.dev.cubo.news.R;
import br.dev.cubo.news.api.vo.ImgVO;
import br.dev.cubo.news.api.vo.NewsVO;
import br.dev.cubo.news.ui.activity.NewsActivity;

//ADAPTER FOR RECYCLERVIEW CUSTOMIZATION
public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<NewsVO> list;
    private RecyclerViewClickListener listener;

    public NewsRecyclerAdapter(Context context, List<NewsVO> list, RecyclerViewClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v =layoutInflater.inflate(R.layout.item_list_home, parent, false);
        return new NewsRecyclerAdapter.MyViewHolder(v);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //POPULATING ATTRIBUTES LIST
        holder.title.setText(list.get(position).getTitulo());
        holder.date.setText(list.get(position).getAtualizadoEm());

        //ERROR TREATMENT AUTHORS
        List<String> strings = list.get(position).getAutores();

        if (strings != null && !strings.isEmpty()){
            holder.origin.setText(list.get(position).getAutores().get(0));
        }else {
            holder.origin.setText("desconhecido");
        }

        //ERROR TREATMENT IMAGES
        List<ImgVO> imgVOList = list.get(position).getImagens();

        if (imgVOList != null && !imgVOList.isEmpty()){
            Glide.with(context).load(list.get(position).getImagens().get(0).getUrl()).into(holder.imgList);
        }else {
            holder.imgList.setImageResource(R.drawable.ic_notfound);
        }

    }

    @Override
    public int getItemCount() { return list.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView origin, title, date;
        ImageView imgList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            origin = itemView.findViewById(R.id.origin);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            imgList = itemView.findViewById(R.id.imgList);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            listener.onClick(v, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

}
