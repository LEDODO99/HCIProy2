package com.example.proyectohci;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class EjerciciosFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final LayoutInflater inf = inflater;
        View view = inflater.inflate(R.layout.fragment_ejecicios, container, false);
        
        ImageButton btnSquats=(ImageButton) view.findViewById(R.id.imageButtonSquats);
        btnSquats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo("<html><body><iframe width=\"420\" height=\"300\" src=\"https://www.youtube.com/embed/C_hgb3lLy0U\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\" mozallowfullscreen=\"mozallowfullscreen\" msallowfullscreen=\"msallowfullscreen\" oallowfullscreen=\"oallowfullscreen\" webkitallowfullscreen=\"webkitallowfullscreen\"></iframe></body></html>", inf);
            }
        });

        ImageButton btnAbs=(ImageButton) view.findViewById(R.id.imageButtonAbs);
        btnAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo("<html><body><iframe width=\"420\" height=\"300\" src=\"https://www.youtube.com/embed/Qy4veWr0Cq8\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\" mozallowfullscreen=\"mozallowfullscreen\" msallowfullscreen=\"msallowfullscreen\" oallowfullscreen=\"oallowfullscreen\" webkitallowfullscreen=\"webkitallowfullscreen\"></iframe></body></html>", inf);
            }
        });

        ImageButton btnBiceps=(ImageButton) view.findViewById(R.id.imageButtonBiceps);
        btnBiceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo("<html><body><iframe width=\"420\" height=\"300\" src=\"https://www.youtube.com/embed/q10Vm7YVpLM\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\" mozallowfullscreen=\"mozallowfullscreen\" msallowfullscreen=\"msallowfullscreen\" oallowfullscreen=\"oallowfullscreen\" webkitallowfullscreen=\"webkitallowfullscreen\"></iframe></body></html>", inf);
            }
        });

        ImageButton btnLunges=(ImageButton) view.findViewById(R.id.imageButtonLunges);
        btnLunges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo("<html><body><iframe width=\"420\" height=\"300\" src=\"https://www.youtube.com/embed/VXU58ctZ-qI\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\" mozallowfullscreen=\"mozallowfullscreen\" msallowfullscreen=\"msallowfullscreen\" oallowfullscreen=\"oallowfullscreen\" webkitallowfullscreen=\"webkitallowfullscreen\"></iframe></body></html>", inf);
            }
        });
        return view;
    }

    private void playVideo(String url, LayoutInflater inflater){

        View dialogLayout = inflater.inflate(R.layout.dialog_info_video, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());

        final AlertDialog dialog = builder.create();
        dialog.setView(dialogLayout);
        dialog.show();
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        String frameVideo = url;

        WebView displayYoutubeVideo = (WebView) dialog.findViewById(R.id.mWebView);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");

    }
}
