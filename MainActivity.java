package com.ceylonux.www.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = (TextView) findViewById(R.id.tvText);
    }

     public void clickMic(View v){
         if(v.getId()==R.id.btnMic){

             promptSpeechInput();
         }
     }

    private void promptSpeechInput() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Somthing!");


        try {
            startActivityForResult(i,100);
        }
        catch(ActivityNotFoundException a) {
            Toast.makeText(MainActivity.this ,"Sorry! Your device dosen't support speech language!",Toast.LENGTH_LONG).show();
        }
    }
    public  void onActivityResult(int requestCode, int resultCode,Intent i){
        super.onActivityResult(requestCode,resultCode,i);
        switch (requestCode)
        {
            case 100: if(resultCode==RESULT_OK&& i!=null){
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                resultText.setText(result.get(0));
            }
            break;
        }
    }

}
