package com.practice.module.newsdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.practice.PracticeApplication;
import com.practice.R;
import com.practice.common.base.BaseActivity;
import com.practice.common.data.News;
import com.practice.common.util.HtmlUtil;
import com.practice.common.util.ImageLoader;
import com.practice.common.util.ImageLoaderUtil;
import com.practice.common.util.ScreenshotTaskAndShare;
import com.practice.common.widget.MyLoadingView;
import com.practice.module.newsdetail.model.NewsDetailModelImp;
import com.practice.module.newsdetail.presenter.NewsDetailPresenter;
import com.practice.module.newsdetail.presenter.NewsDetailPresenterImp;
import com.practice.module.newsdetail.view.NewsDetailView;

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
    public static final String NEWS_ID = "newsid";

    public static void start(Long newsid) {
        Intent intent = new Intent(PracticeApplication.getAppContext(), NewsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong(NEWS_ID, newsid);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PracticeApplication.getAppContext().startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        initUI();
        newsDetailPresenter = new NewsDetailPresenterImp(new NewsDetailModelImp(this),this);
        loadNewsDetail((Long) getIntent().getExtras().get(NEWS_ID));
    }

    private void initUI() {
        setUpToolbar(mToolbar);
        mNestedScrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        setUpWebView(wvNews);
        mCollapsingToolbarLayout.setTitle(getString(R.string.hot_zhihu));
    }

    private void setUpWebView(WebView wvNews) {
        wvNews.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wvNews.getSettings().setLoadsImagesAutomatically(true);
        wvNews.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wvNews.getSettings().setDomStorageEnabled(true);
        wvNews.getSettings().setSupportZoom(true);
        wvNews.getSettings().setBuiltInZoomControls(true);

    }

    private void setUpToolbar(Toolbar mToolbar) {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
    public void loadNewsDetail(long newsid) {
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
    private ImageLoader.Builder createBuilder(News news) {
        ImageLoader.Builder builder = new ImageLoader.Builder();
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
            case R.id.id_share:
                shareNewsDetail();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareNewsDetail() {
        ScreenshotTaskAndShare screenshotTaskAndShare = new ScreenshotTaskAndShare(this,wvNews);
        screenshotTaskAndShare.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newsdetail, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
