package bwie.com.textone.mvp.presenter;

import bwie.com.textone.MainActivity;
import bwie.com.textone.api.Api;
import bwie.com.textone.mvp.model.IModel;
import bwie.com.textone.mvp.model.Model;

public class Presenter implements IPresenter {
    MainActivity mainActivity;
    private Model model;

    public Presenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        model = new Model();
    }

    @Override
    public void ShowPre() {
        model.Show(Api.Show, new IModel.Callback() {
            @Override
            public void Success(String data) {
                mainActivity.ShowView(data);
            }
        });
    }
}
