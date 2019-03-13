package bwie.com.textone.network;


import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttp {

    private static OkHttpClient okHttpClient;
    private static Request request;
    private static OkHttpClient client;
    private static RequestBody build;
    private static Request requestBuild;

    public static void okHttpGet(String url, final Callback callback){
        okHttpClient = new OkHttpClient();
        request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        okHttpClient.newCall(request).enqueue(callback);

    }
    public static void okHttpPost(String url,String name,String pwd,Callback callback){
        client = new OkHttpClient();
        build = new FormBody.Builder().add("phone", name).add("pwd", pwd).build();
        requestBuild = new Request.Builder().url(url).post(OkHttp.build).build();
        client.newCall(requestBuild).enqueue(callback);
    }
}
