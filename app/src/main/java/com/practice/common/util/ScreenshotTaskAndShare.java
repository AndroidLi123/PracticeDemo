package com.practice.common.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.webkit.WebView;

/**
 * Created by LiXiaoWang
 */
public class ScreenshotTaskAndShare extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private ProgressDialog dialog;
    private WebView webView;
    private int windowWidth;
    private float contentHeight;
    private Bitmap bitmap;

    public ScreenshotTaskAndShare(Context context, WebView webView) {
        this.context = context;
        this.dialog = null;
        this.webView = webView;
        this.windowWidth = 0;
        this.contentHeight = 0f;
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
        bitmap = ViewUnit.capture(webView, windowWidth, contentHeight, false, Bitmap.Config.ARGB_8888);
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
