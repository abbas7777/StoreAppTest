package ir.ace.storeapptest.DataServer;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Banner;
import ir.ace.storeapptest.Models.BasketModel;
import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.Models.CommentModel;
import ir.ace.storeapptest.Models.Detail;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.NewProduct;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.Models.ProfileModel;
import ir.ace.storeapptest.Models.PropertiesItem;
import ir.ace.storeapptest.Models.ValuesItem;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("getcat.php")
    Single<List<CatsItem>> getCats();

    @POST("readamazing.php")
    Single<List<Product>> getProduct();

    @POST("readbanner.php")
    Single<List<Banner>> getBanner();

    @POST("newproduct.php")
    Single<List<NewProduct>> getNewProduct();

    @FormUrlEncoded
    @POST("loginbooktapACE.php")
    Single<String> checkLoginUser(@Field("phone") String phone, @Field("pass") String pass);

    @GET("getdetail.php")
    Single<List<Detail>> getDetails(@Query("id") String id, @Query("user") String phone);

    @GET("properties.php")
    Single<List<PropertiesItem>> getProperties();

    @GET("comments.php")
    Single<List<CommentModel>> getComments(@Query("id") String id);

    @GET("like.php")
    Single<MessageModel> setLike(@Query("id") String id);

    @GET("dislike.php")
    Single<MessageModel> setDissLike(@Query("id") String id);

    @GET("search.php")
    Single<List<Product>> getSearchProduct(@Query("search") String sSearch);

    @GET("gettab.php")
    Single<List<Product>> getFilterList(@Query("cat") String cat);

    @FormUrlEncoded
    @POST("addcommentsimple.php")
    Single<MessageModel> sendComment(@Field("user") String user, @Field("idp") String idp, @Field("title") String title, @Field("passage") String passage, @Field("suggested") String suggested);

    @FormUrlEncoded
    @POST("addtofavorlist.php")
    Single<MessageModel> addToFoverList(@Field("phone") String phone, @Field("idp") String idp);


    @GET("filter.php")
    Single<List<Product>> getSortProduct(@Query("cat") String cat, @Query("sort") int sort);

    @POST("sendfilter.php")
    Single<List<Product>> getFilterProduct(@Body List<ValuesItem> valuesItemList);

    @GET("editprofile.php")
    Single<String> updateProfileInfos(@Query("name") String name, @Query("phone") String phone, @Query("address") String address, @Query("codeposti") String code);

    @GET("getprofileinfo.php")
    Single<List<ProfileModel>> getProfile(@Query("phone") String phone);

    @GET("deletefav.php")
    Single<MessageModel> deleteFromFav(@Query("idp") String idp, @Query("phone") String phne);

    @POST("getfavoriteACE.php")
    Single<List<Product>> getFavorit(@Query("phone") String id);

    @GET("addbasketACE.php")
    Single<MessageModel> addToBasket(@Query("id") String id, @Query("phone") String phone);

    @GET("basketlist.php")
    Single<List<BasketModel>> getBasketList(@Query("phone") String phone);

    @GET("deletebasket.php")
    Single<MessageModel> deleteFromBasket(@Query("id") String id);

    @FormUrlEncoded
    @POST("signupACE.php")
    Single<MessageModel> signUp(@Field("name") String name, @Field("phone") String phone, @Field("pass") String pass);

}
