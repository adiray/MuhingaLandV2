package com.example.dell.muhingalandv2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PlayMusic extends AppCompatActivity {

    //miscellaneous objects
    String songTitle, artistName, songFileReference, albumName, coverImage;

    //initialize the views
    TextView songTitleTv, artistNameTv;
    ImageButton playButton, downloadButton, pauseButton, stopButton;
    ImageView coverImageImgView;

    //Media player objects
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);


        //Get the intent that started this activity
        Intent intent = getIntent();
        artistName = intent.getStringExtra(SongsView.EXTRA_PLAY_SONG_ARTIST_NAME);
        songTitle = intent.getStringExtra(SongsView.EXTRA_PLAY_SONG_TITLE);
        songFileReference = intent.getStringExtra(SongsView.EXTRA_PLAY_SONG_FILE_REFERENCE);
        albumName = intent.getStringExtra(SongsView.EXTRA_PLAY_SONG_ALBUM_NAME);
        coverImage = intent.getStringExtra(SongsView.EXTRA_PLAY_SONG_COVER_IMAGE);


        //initialize the views
        songTitleTv = findViewById(R.id.play_music_song_title);
        artistNameTv = findViewById(R.id.play_music_artist_name);
        playButton = findViewById(R.id.play_music_play_button);
        pauseButton = findViewById(R.id.play_music_pause_button);
        stopButton = findViewById(R.id.play_music_stop_button);
        downloadButton = findViewById(R.id.play_music_download_button);
        coverImageImgView = findViewById(R.id.play_music_song_cover_image);

        //assign the text to the textViews
        songTitleTv.setText(songTitle);
        artistNameTv.setText(artistName);

        //set the cover image
        Glide.with(this).load(coverImage).into(coverImageImgView);

        //set onClickListeners to the control buttons
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSong();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseSong();
            }
        });


        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSong();
            }
        });

    }


    public void playSong() {


        playButton.setBackgroundColor(getResources().getColor(R.color.my_color_alternative_shade));

        mediaPlayer = new android.media.MediaPlayer();
        mediaPlayer.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC);
        try {

            mediaPlayer.setDataSource(songFileReference);

        } catch (IllegalArgumentException | SecurityException | IllegalStateException e) {

            android.widget.Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", android.widget.Toast.LENGTH_LONG).show();

        } catch (java.io.IOException e) {

            e.printStackTrace();

        }

        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                mediaPlayer.start();


            }
        });


    }


    public void pauseSong() {


        mediaPlayer.pause();

    }


    public void stopSong() {


        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;


    }


}
