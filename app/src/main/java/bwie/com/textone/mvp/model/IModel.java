package bwie.com.textone.mvp.model;

public interface IModel {
    void Show(String url, Callback callback);
    interface Callback{
        void Success(String data);
    }
}
