package com.example.bruna.voiceapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Falar extends AppCompatActivity {

    TextView tv;
    Button bTalk;
    private RadioGroup radioGroup;
    private RadioButton radioButton;


    private static final int REQ_CODE_SPEECH_INPUT = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falar);


        bTalk= findViewById(R.id.bTalk);
        tv = findViewById(R.id.tvSpeech);
        radioGroup = (RadioGroup) findViewById(R.id.radio);


        bTalk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                int selectedId = radioGroup.getCheckedRadioButtonId();


                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(Falar.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();


                if(radioButton.getText().equals("Português")){
                    startVoiceInput("pt-PT");
                }
                else if(radioButton.getText().equals("Inglês")){
                    startVoiceInput("en-US");
                }
                else if(radioButton.getText().equals("Default")){
                    startVoiceInput(String.valueOf(Locale.getDefault()));
                }


            }

        });



    }

    private void startVoiceInput(String lang) {
        try {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                    lang);
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

//ACTION_RECOGNIZE_SPEECH - Inicia uma atividade que solicitará ao utilizador a fala e envia os resultados para o Activity.onActivityResult

//EXTRA_LANGUAGE_MODEL - informa ao reconhecedor qual modelo de fala prefere ao executar ACTION_RECOGNIZE_SPEECH.

//LANGUAGE_MODEL_FREE_FORM - Use um modelo de idioma baseado no reconhecimento de fala de forma livre. Este é o valor a ser usado para EXTRA_LANGUAGE_MODEL.

//EXTRA_LANGUAGE - Esta tag informa ao reconhecedor para realizar o reconhecimento de fala em um idioma diferente daquele definido no Locale.getDefault ().


        } catch(ActivityNotFoundException e) {
            String appPackageName = "com.google.android.googlequicksearchbox";
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tv.setText(result.get(0));
                    if(result.get(0).equals("vamos ouvir")){
                        Intent intent = new Intent(Falar.this, Ouvir.class);
                        startActivity(intent);

                    }
                }
                break;
            }

        }
    }

}
