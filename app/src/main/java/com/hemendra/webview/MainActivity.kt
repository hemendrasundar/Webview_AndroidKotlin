package com.hemendra.webview

import android.app.ProgressDialog
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.webkit.*;

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webview.settings.javaScriptEnabled = true
        webview.settings.builtInZoomControls = true
        btn_fb.setOnClickListener(this)
        btn_file.setOnClickListener(this)
        btn_insta.setOnClickListener(this)
        btn_youtube.setOnClickListener(this)
        btn_search.setOnClickListener {
            webview.loadUrl("https://${et_url.text.toString()}")
        }

        webview.addJavascriptInterface(this@MainActivity,"myinterface")
        webview.webViewClient = object:WebViewClient(){

            var progressdialog = ProgressDialog(this@MainActivity)

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressdialog.setTitle("Loading...")
                progressdialog.setMessage("please wait while loading...")
                progressdialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressdialog.dismiss()
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return super.shouldOverrideUrlLoading(view, url)
                Toast.makeText(this@MainActivity,url,Toast.LENGTH_SHORT).show()
            }
        }



    }

    override fun onClick(view: View?) {

        when(view!!.id)
        {
            R.id.btn_youtube -> webview.loadUrl("https://www.youtube.com")
            R.id.btn_fb -> webview.loadUrl("https://www.facebook.com")
            R.id.btn_insta -> webview.loadUrl("https://www.instagram.com")
            R.id.btn_file ->webview.loadUrl("file:///android_asset/img/test.html")

        }



    }

    @JavascriptInterface
    fun callfromjs(name:String,password:String,url:String)
    {
        Toast.makeText(this@MainActivity,"Username:$name\n password:$password",Toast.LENGTH_LONG).show()
         et_url.setText(url)
    }


}