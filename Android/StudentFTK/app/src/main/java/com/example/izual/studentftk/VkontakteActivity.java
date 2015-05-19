package com.example.izual.studentftk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.izual.studentftk.Network.Settings;
import com.example.izual.studentftk.FragmentManagement.TopLevelFragmentManager;


public class VkontakteActivity extends Activity {

    WebView webview;
    ProgressBar progress;
    String vk_redirect_url = Settings.FULL_SITE_NAME + "vk/oauth&v=5.25";

    String vkClientID = "4858987";
    //old 4601196

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webview = (WebView) findViewById(R.id.web);
        progress = (ProgressBar) findViewById(R.id.progress);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);
        webview.clearCache(true);

        webview.setWebViewClient(new VkWebViewClient());

        String url = "http://oauth.vk.com/authorize?client_id=" + vkClientID
                            + "&redirect_uri="+ vk_redirect_url + "&response_type=code";
        webview.loadUrl(url);
        webview.setVisibility(View.VISIBLE);
    }

    class VkWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progress.setVisibility(View.VISIBLE);
            if( url.startsWith("https://" + Settings.SITE_NAME) ) {
                Intent intent = new Intent(VkontakteActivity.this, TopLevelFragmentManager.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        }



        public void onReceivedSslError(final WebView view, final SslErrorHandler handler, SslError error) {

            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if( url.startsWith("http://oauth.vk.com/authorize") || url.startsWith("http://oauth.vk.com/oauth/authorize") ) {
                progress.setVisibility(View.GONE);
            }
        }
    }
}



