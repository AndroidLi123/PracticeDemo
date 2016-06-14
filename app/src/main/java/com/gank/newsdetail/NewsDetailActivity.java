package com.gank.newsdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gank.GankApplication;
import com.gank.R;
import com.gank.base.BaseActivity;
import com.gank.common.HtmlUtil;
import com.gank.common.ImageLoaderUtil;
import com.gank.common.MyImageLoader;
import com.gank.data.News;
import com.gank.newsdetail.model.NewsDetailModelImp;
import com.gank.newsdetail.presenter.NewsDetailPresenter;
import com.gank.newsdetail.presenter.NewsDetailPresenterImp;
import com.gank.newsdetail.view.NewsDetailView;
import com.gank.widget.MyLoadingView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsDetailActivity extends BaseActivity implements NewsDetailView {

    @Bind(R.id.iv_header)
    ImageView ivHeader;
    @Bind(R.id.tv_source)
    TextView tvSource;
    @Bind(R.id.wv_news)
    WebView wvNews;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.progress_view)
    MyLoadingView loadingView;
    @Bind(R.id.nested_view)
    NestedScrollView mNestedScrollView;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    private NewsDetailPresenter newsDetailPresenter;
    private Long newsid;
    public static final String NEWS_ID = "newsid";

    public static void start(Long newsid) {
        Intent intent = new Intent(GankApplication.getAppContext(), NewsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong(NEWS_ID, newsid);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        GankApplication.getAppContext().startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        initUI();
        newsid = (Long) getIntent().getExtras().get(NEWS_ID);
        newsDetailPresenter = new NewsDetailPresenterImp(new NewsDetailModelImp(this),this);
        loadNewsDetail();
    }

    private void initUI() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mNestedScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wvNews.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wvNews.getSettings().setLoadsImagesAutomatically(true);
        //设置 缓存模式
        wvNews.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        wvNews.getSettings().setDomStorageEnabled(true);
        //为可折叠toolbar设置标题
        mCollapsingToolbarLayout.setTitle(getString(R.string.hot_zhihu));
    }


    @Override
    public void showProgress() {
        loadingView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        loadingView.setVisibility(View.GONE);

    }

    @Override
    public void loadNewsDetail() {
        newsDetailPresenter.loadNewsDetail(newsid);

    }

    @Override
    public void setupDataToView(News news) {
        String htmlData = HtmlUtil.createHtmlData(news);
        ImageLoaderUtil.getInstance().loadImage(this, createBuilder(news).build());
        wvNews.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
        tvSource.setText(news.getImageSource());

    }

    @NonNull
    private MyImageLoader.Builder createBuilder(News news) {
        MyImageLoader.Builder builder = new MyImageLoader.Builder();
        builder.imgView(ivHeader);
        builder.url(news.getImage());
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        return builder;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
