package com.example.izual.studentftk;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class VkontakteActivity extends Activity {

    WebView webview;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        //Получаем элементы
        webview = (WebView) findViewById(R.id.web);


        webview.getSettings().setJavaScriptEnabled(true);
        webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);
        webview.clearCache(true);

        //Чтобы получать уведомления об окончании загрузки страницы
        webview.setWebViewClient(new VkWebViewClient());

        String url = "https://oauth.vk.com/authorize?client_id=4601196&redirect_uri=https://studentspbstu.tk/vk/oauth&v=5.25&response_type=code";
        webview.loadUrl(url);
        webview.setVisibility(View.VISIBLE);
    }

    class VkWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            parseUrl(url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if( url.startsWith("http://oauth.vk.com/authorize") || url.startsWith("http://oauth.vk.com/oauth/authorize") ) {

            }
        }
    }

    private void parseUrl(String url) {
        try {
            if( url == null ) {
                return;
            }
            if( url.startsWith("https://studentspbstu.tk/vk/oauth&v=5.25&response_type=code") ) {
                if( !url.contains("error") ) {
                    String[] auth = VKUtils.parseRedirectUrl(url);
                    webview.setVisibility(View.GONE);


                    //Строим данные
                    Intent intent = new Intent();
                    intent.putExtra("token", auth[1]);
                    intent.putExtra("uid", auth[0]);

                    //Возвращаем данные
                    setResult(Activity.RESULT_OK, intent);
                } else {
                    setResult(RESULT_CANCELED);
                    finish();
                }
            } else if( url.contains("error?err") ) {
                setResult(RESULT_CANCELED);
                finish();
            }
        } catch( Exception e ) {
            e.printStackTrace();
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}