package br.dev.cubo.news.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.dev.cubo.news.R;
import br.dev.cubo.news.business.model.NewsVO;

/**
 * @author D.M.G.
 * @company CUBO
 * @site www.cubo.dev.br
 * @phone +55 11 9-7727-8055
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{

    private Context context;
    private List<NewsVO> news;

    private OnItemClickListener onItemClickListener;

    public NewsAdapter(Context context, List<NewsVO> news) {
        this.context = context;
        this.news = news;
    }

    public void setNewsAdapter(List<NewsVO> newsVOS){
        this.news = newsVOS;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_news, parent, false);
        return new NewsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.bind(news.get(position));

        holder.title.setText(news.get(position).getTitulo());
        holder.origin.setText(news.get(position).getOrigen());
        holder.date.setText(news.get(position).getData());

        if (news.get(position).getImg() != null && !news.get(position).getImg().isEmpty()){
            Glide.with(context).load(news.get(position).getImg()).into(holder.img);
        }else {
            holder.img.setImageResource(R.drawable.ic_notfound);
        }
    }

    @Override
    public int getItemCount() {
        if (news != null){
            return news.size();
        }else {
            return 0;
        }
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView title, origin, date;
        private ImageView img;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            this.mView = itemView;

            title = itemView.findViewById(R.id.title);
            origin = itemView.findViewById(R.id.origin);
            date = itemView.findViewById(R.id.date);
            img = itemView.findViewById(R.id.img);
        }

        public void bind(NewsVO item){
            mView.setOnClickListener(v -> {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(item);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(NewsVO item);
    }
}
