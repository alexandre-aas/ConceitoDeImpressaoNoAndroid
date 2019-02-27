package com.s.d.a.a.conceitodeimpressaonoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.content.Context;
import android.webkit.WebResourceRequest;



public class Impressao extends AppCompatActivity {

    private WebView mainWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressao);

        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                criarTrabalhoDeImpressaoWeb(view);
                mainWebView = null;
            }
        });

        String documentoHTML =
                "<html><body><h1>Teste de impressão no Android API 21</h1><p>"
                        + "Conceito de Impressao no Android API 21 Lollipop.</p></body></html>";

        webView.loadDataWithBaseURL(null, documentoHTML,
                "text/HTML", "UTF-8", null);

        mainWebView = webView;
    }

    private void criarTrabalhoDeImpressaoWeb(WebView webView) {

        PrintManager gerenciadorDeImpressao = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        PrintDocumentAdapter printAdapter =
                webView.createPrintDocumentAdapter("DocumentoParaImpressao");

        String jobName = getString(R.string.app_name) + " Teste de Impressão";

        gerenciadorDeImpressao.print(jobName, printAdapter, new PrintAttributes.Builder().build());
    }
}
