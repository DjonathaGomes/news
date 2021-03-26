# App-News
Journalistic demo application [ aplicativo de demonstração jornalístico ]

####API
https://raw.githubusercontent.com/Infoglobo/desafio-apps/master/capa.jsonv

####Use
Retrofit 2
Recyclerview
Glide

####Retrofit
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

####Service
public interface NewsService {

    @GET("capa.json")
    Call <List<ListNewsVO>> allNews();
}



