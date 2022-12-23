package ir.ace.storeapptest.DataServer;

public class ApiProvider {
    public static ApiService apiService;

    public static  ApiService apiService(){
        if (apiService==null){
            apiService=ApiClient.getClient().create(ApiService.class);

        }
       return apiService;
    }
}
