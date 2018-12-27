package com.example.tao.imagefeeddemo.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.tao.imagefeeddemo.R;
import com.example.tao.imagefeeddemo.adapter.ImgFeedRecyclerAdapter;
import com.example.tao.imagefeeddemo.model.ImgInfo;
import com.example.tao.imagefeeddemo.persenter.ImgFeedPresenter;
import com.example.tao.imagefeeddemo.view.ImgFeedView;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ImgFeedView, SwipeRefreshLayout.OnRefreshListener{
    private ImgFeedPresenter presenter;
    private ProgressWheel progressWheel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ImgFeedRecyclerAdapter adapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.layout_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.view_recycler);
        adapter = new ImgFeedRecyclerAdapter(this);
         staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        presenter = new ImgFeedPresenter(this);
        presenter.attachView(this);
        presenter.startLoadTask();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                staggeredGridLayoutManager.invalidateSpanAssignments();
            }
        });
    }

    @Override
    public void onRefresh() {
        presenter.startLoadTask();
    }

    @Override
    public void showLoading() {
        progressWheel.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressWheel.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false); // close refresh animator
    }

    @Override
    public void showError(String msg) {
        //show toast
    }

    @Override
    public void showResult(List<ImgInfo> imgInfos) {
        adapter.setImgInfos(imgInfos);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }
}
