package com.example.administrator.speechtotext;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class naman extends AppCompatActivity {
    public Button generateqrcode;
    public Button scanqrcode;
    public void callbtn1(){
        generateqrcode= findViewById(R.id.generateqrcode);
generateqrcode.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(naman.this,gps.class);
        startActivity(intent);
    }
});


    }
    public void callbtn2(){
        scanqrcode= findViewById(R.id.scanqrcode);
        scanqrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(naman.this,detectqrcode.class);
                startActivity(intent);
            }
        });


    }


    private TextView txvresult;
    private TextToSpeech mTTs;
    private SeekBar mseekbarpitch;
    private SeekBar mseekbarspeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naman);
        callbtn1();
        callbtn2();
        txvresult= findViewById(R.id.txvResult);
        mseekbarpitch=findViewById(R.id.seek_bar_pitch);
        mseekbarspeed=findViewById(R.id.seek_bar_speed);
        mTTs= new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = mTTs.setLanguage(Locale.ENGLISH);


                    if (result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA){
                        Log.e("TTS","Langvage not supported");
                    }
                }else{
                    Log.e("TTS","Intilization Failed");};

            }
        });

    }




    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);

        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    float pitch= (float) mseekbarpitch.getProgress() / 50;
                    if (pitch<0.1)pitch=0.1f;
                    float speed= (float) mseekbarspeed.getProgress() / 50;
                    if (speed<0.1)speed=0.1f;
                    mTTs.setPitch(pitch);
                    mTTs.setSpeechRate(speed);
                    txvresult.setText(result.get(0));
                    mTTs.speak(String.valueOf(result),TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
        }





























    }
}
