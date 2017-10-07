package com.madscientists.algoliademo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.madscientists.algoliademo.R;
import com.madscientists.algoliademo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Activity_WebView extends AppCompatActivity {

    public static final String KEY_TITLE="title";
    public static final String KEY_URL = "url";

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.webView)WebView webView;
    @BindView(R.id.webViewProgressBar)ProgressBar webViewProgressBar;
    private String url;
    private String title;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public static void initAndStart(Context context, String pageTitle, String pageURL){
        Intent intent = new Intent(context,Activity_WebView.class);
        intent.putExtra(Activity_WebView.KEY_URL, pageURL);
        intent.putExtra(Activity_WebView.KEY_TITLE,pageTitle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setDefaultFontForActivity(this);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        title = getIntent().getStringExtra(KEY_TITLE);
        url = getIntent().getStringExtra(KEY_URL);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String urlStr = url;
        if (!TextUtils.isEmpty(urlStr)) {
            if (!urlStr.startsWith("http://") && !urlStr.startsWith("https://")) {
                urlStr = "http://" + urlStr;
            }
        }
//        Log.i("webview", "onViewCreated: "+urlStr);
        if (!TextUtils.isEmpty(urlStr)) {
            webView.setWebViewClient(new InAppBrowser());
//            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLoadsImagesAutomatically(true);
//            webView.getSettings().setDomStorageEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(urlStr);

        }
    }

    private class InAppBrowser extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            view.setVisibility(View.GONE);
            webViewProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webViewProgressBar.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
