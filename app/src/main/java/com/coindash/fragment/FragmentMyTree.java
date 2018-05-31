package com.coindash.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.coindash.Constants.AdvancedWebView;
import com.coindash.Constants.AppGlobal;
import com.coindash.Constants.WsConstant;
import com.coindash.MainActivity;
import com.coindash.R;


@SuppressWarnings("ALL")
public class FragmentMyTree extends Fragment {
    private AdvancedWebView webView;

    public FragmentMyTree() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_webview, container, false);
        MainActivity.mainTitle.setText("My Tree");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View v) {
        webView = (AdvancedWebView) v.findViewById(R.id.webView_MyCrew);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient());

        String url = WsConstant.URL + "MyTree.php?TokenData=" + AppGlobal.getId(getActivity())
                + "&RegisterID=" + AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_REGID)
                + "&EmailID=" + AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_EMAIL)
                + "&UserName=" + AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_USERNAME);

        if (AppGlobal.isNetwork(getActivity())) {
            webView.loadUrl(url);
        } else {
            Toast.makeText(getActivity(), getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        }
    }

    public class MyWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            AppGlobal.showProgressDialog(getActivity());
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            AppGlobal.hideProgressDialog(getActivity());
        }
    }
}