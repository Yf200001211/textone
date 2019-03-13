package bwie.com.textone.mvp.model;

import java.io.IOException;

import bwie.com.textone.network.OkHttp;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Model implements IModel {
    @Override
    public void Show(String url, final Callback callback) {
        OkHttp.okHttpGet(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            callback.Success(response.body().string());
            }
        });
    }
}
