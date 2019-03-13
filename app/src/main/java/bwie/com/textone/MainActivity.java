package bwie.com.textone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import bwie.com.textone.adapter.MyAdapter;
import bwie.com.textone.bean.ShowBean;
import bwie.com.textone.mvp.presenter.Presenter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycle;
    private Presenter presenter;
    private GridLayoutManager gridLayoutManager;
    private RefreshLayout refreshLayout;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = findViewById(R.id.recycle);
        refreshLayout = findViewById(R.id.refreshLayout);
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                myAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        //加载
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                myAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();

            }
        });
        refreshLayout.setEnableRefresh(true);//启用刷新
        refreshLayout.setEnableLoadmore(true);//启用加载
        //关闭下拉
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();

        gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recycle.setLayoutManager(gridLayoutManager);
        presenter = new Presenter(this);
        presenter.ShowPre();
    }

    public void ShowView(final String data) {
        runOnUiThread(new Runnable() {


            private List<ShowBean.ResultBean> list;

            @Override
            public void run() {
                Gson gson = new Gson();
                ShowBean showBean = gson.fromJson(data, ShowBean.class);
                list = showBean.getResult();
                myAdapter = new MyAdapter(MainActivity.this,list);
                recycle.setAdapter(myAdapter);
            }
        });
    }
}
