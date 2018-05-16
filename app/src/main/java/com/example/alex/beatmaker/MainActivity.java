package com.example.alex.beatmaker;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Oggetto per la riproduzione di suoni brevi, quindi adatto a un launchpad
    private SoundPool pool;

    // ArrayList per contenere gli id di ogni traccia audio caricata
    private ArrayList<Integer> sounds;

    // Boolean per controllare se tutte le tracce audio sono state caricate
    private boolean mediaLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sounds = new ArrayList<>();
        mediaLoaded = false;

        // Inizializza gli attributi per la SoundPool
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        // Inizializza la SoundPool
        pool = new SoundPool.Builder()
                .setMaxStreams(9)
                .setAudioAttributes(audioAttributes)
                .build();

        // Listener per caricamento completo di tutte le tracce audio
        pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                System.out.println("Sound " + sampleId + " loading operation returned " + status);
                mediaLoaded = true;
            }
        });

        // Caricamento delle tracce audio (si trovano nella cartella "res/raw"
        sounds.add(pool.load(this, R.raw.sound1, 1));
        sounds.add(pool.load(this, R.raw.sound2, 1));
        sounds.add(pool.load(this, R.raw.sound3, 1));
        sounds.add(pool.load(this, R.raw.sound4, 1));
        sounds.add(pool.load(this, R.raw.sound5, 1));
        sounds.add(pool.load(this, R.raw.sound6, 1));
        sounds.add(pool.load(this, R.raw.sound7, 1));
        sounds.add(pool.load(this, R.raw.sound8, 1));
        sounds.add(pool.load(this, R.raw.sound9, 1));
    }

    public void onPadButtonClick(View v) {
        Button button = findViewById(v.getId());
        String tag = v.getTag().toString();

        if(mediaLoaded) {

            // Acquisizione del valore del volume per impostarlo nelle tracce audio
            AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            float volume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) / (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            // Selezione della traccia da riprodurre in base al tag del tasto premuto
            switch (tag) {
                case "pad1":
                    pool.play(sounds.get(0), volume, volume, 1, 0, 1f);
                    break;
                case "pad2":
                    pool.play(sounds.get(1), volume, volume, 1, 0, 1f);
                    break;
                case "pad3":
                    pool.play(sounds.get(2), volume, volume, 1, 0, 1f);
                    break;
                case "pad4":
                    pool.play(sounds.get(3), volume, volume, 1, 0, 1f);
                    break;
                case "pad5":
                    pool.play(sounds.get(4), volume, volume, 1, 0, 1f);
                    break;
                case "pad6":
                    pool.play(sounds.get(5), volume, volume, 1, 0, 1f);
                    break;
                case "pad7":
                    pool.play(sounds.get(6), volume, volume, 1, 0, 1f);
                    break;
                case "pad8":
                    pool.play(sounds.get(7), volume, volume, 1, 0, 1f);
                    break;
                case "pad9":
                    pool.play(sounds.get(8), volume, volume, 1, 0, 1f);
                    break;
            }
        }
    }

    public void onPackSelectionClick(View v) {
        Button button = findViewById(v.getId());
        String tag = v.getTag().toString();

        sounds.clear();

        switch(tag) {
            case "pack1":
                sounds.add(pool.load(this, R.raw.sound1, 1));
                sounds.add(pool.load(this, R.raw.sound2, 1));
                sounds.add(pool.load(this, R.raw.sound3, 1));
                sounds.add(pool.load(this, R.raw.sound4, 1));
                sounds.add(pool.load(this, R.raw.sound5, 1));
                sounds.add(pool.load(this, R.raw.sound6, 1));
                sounds.add(pool.load(this, R.raw.sound7, 1));
                sounds.add(pool.load(this, R.raw.sound8, 1));
                sounds.add(pool.load(this, R.raw.sound9, 1));
                break;
            case "pack2":
                sounds.add(pool.load(this, R.raw.sound10, 1));
                sounds.add(pool.load(this, R.raw.sound11, 1));
                sounds.add(pool.load(this, R.raw.sound12, 1));
                sounds.add(pool.load(this, R.raw.sound13, 1));
                sounds.add(pool.load(this, R.raw.sound14, 1));
                sounds.add(pool.load(this, R.raw.sound15, 1));
                sounds.add(pool.load(this, R.raw.sound16, 1));
                sounds.add(pool.load(this, R.raw.sound17, 1));
                sounds.add(pool.load(this, R.raw.sound18, 1));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pool.release();
        pool = null;
    }
}
