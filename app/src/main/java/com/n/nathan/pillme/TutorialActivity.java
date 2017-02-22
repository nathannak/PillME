package com.n.nathan.pillme;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class TutorialActivity extends AppCompatActivity {

    VideoView videoView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //
        videoView = (VideoView) findViewById(R.id.vid_tut);
        videoView.setVideoURI(Uri.parse("android.resource://com.n.nathan.pillme/" + R.raw.pillmetutorial));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });
        //

        btn = (Button) findViewById(R.id.continue_button);

        //
    }
    //

    //
    public void goto_main (View view)
    {
        finish();
    }
    //

    //
}
