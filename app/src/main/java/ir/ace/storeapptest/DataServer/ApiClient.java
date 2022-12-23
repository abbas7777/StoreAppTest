package ir.ace.storeapptest.DataServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public  static Retrofit retrofit=null;
    public  static final String BASE_URL="http://192.168.43.127/app/";

    public static Retrofit getClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }
}
