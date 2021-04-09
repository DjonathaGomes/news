# App-News
Journalistic demo application - app O Globo demo

### API
https://raw.githubusercontent.com/Infoglobo/desafio-apps/master/capa.jsonv

### Used
Retrofit | Recyclerview | Glide

### Retrofit
public class Retrofit {
    private static final String URL_BASE = "https://raw.githubusercontent.com/Infoglobo/desafio-apps/master/";
    private static NewsService newsService;
    public static NewsService getInstance() {
    
        if (newsService == null) {
            OkHttpClient client = configuraClient();
            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);
        }
        
        return newsService;
        
    }
    
    private static OkHttpClient configuraClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
}

### Service
public interface NewsService {
    @GET("capa.json")
    Call <List<ListNewsVO>> allNews();
}
    
### Object
public class NewsVO {
    private final List<String> autores;
    private final Boolean informePublicitario;
    private final String subTitulo;
    private final String texto;
    private final List<String> videos;
    private final String atualizadoEm;
    private final int id;
    private final String publicadoEm;
    private final SectionVO section;
    private final String tipo;
    private final String titulo;
    private final String url;
    private final String urlOriginal;
    private final List<ImgVO> imagens;
}

public class SectionVO {
    private String nome;
    private String url;
}

public class ListNewsVO {
    private final List<NewsVO> conteudos;
    private final String produto;
}

public class ImgVO {
    private String autor;
    private String fonte;
    private String legenda;
    private String url;
}

### RecyclerView
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
    
### Activity

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

