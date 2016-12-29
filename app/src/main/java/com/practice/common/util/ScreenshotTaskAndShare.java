package com.practice.common.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.widget.NestedScrollView;
import android.webkit.WebView;

public class ScreenshotTaskAndShare extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private ProgressDialog dialog;
    private WebView webView;
    private int windowWidth;
    private float contentHeight;
    private Bitmap bitmap;
    private NestedScrollView scrollView;
    public ScreenshotTaskAndShare(Context context, WebView webView, NestedScrollView scrollView) {
        this.context = context;
        this.dialog = null;
        this.webView = webView;
        this.windowWidth = 0;
        this.contentHeight = 0f;
        this.scrollView = scrollView;

    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("请稍候");
        dialog.show();
        windowWidth = ViewUnit.getWindowWidth(context);
        contentHeight = webView.getContentHeight() * ViewUnit.getDensity(context);
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        bitmap = CommonUtils.getBitmapByView(scrollView);
        return bitmap != null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        dialog.dismiss();
        if (bitmap != null) {
            ShareUtils.shareImage(context, CommonUtils.getImageUri(context, bitmap),
                    "分享文章至");
        }

    }
}