## WORKSHOP DE SEMINÁRIO I

* API de Síntese e Reconhecimento de Voz

## PRIMEIRA PARTE (CONFIGURAÇÃO)

* [Download Android Studio](https://developer.android.com/studio/index.html)
* Instalação
* Configuração


## SEGUNDA PARTE 
(Download e abertura do projeto no Android Studio)

## TERCEIRA PARTE (STT)

* Falar.java
    
    * Declarar variáveis:
    
    ```java
            TextView tv;
            Button bTalk;
            private RadioGroup radioGroup;
            private RadioButton radioButton;
            private static final int REQ_CODE_SPEECH_INPUT = 100;
    ```
    
    * Usar findViewById (int) para ter acesso aos componentes da interface com os quais será preciso interagir:
    
    ```java
            bTalk= findViewById(R.id.bTalk);
            tv = findViewById(R.id.tvSpeech);
            radioGroup = (RadioGroup) findViewById(R.id.radio);
    ```
    
   * Verificar a escolha do radioButton relacionado com a lingua e chamar a função startVoiceInput com a língua escolhida:
    
    ```java
            bTalk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               int selectedId = radioGroup.getCheckedRadioButtonId();

               radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(MainActivity.this,
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

    ```
    
    * Função startVoiceInput:
    
    ```java
           private void startVoiceInput(String lang) {
                try {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                            lang);
                    startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

                } catch(ActivityNotFoundException e) {
                    String appPackageName = "com.google.android.googlequicksearchbox";
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            }
        }
    ```
    * Obter resultados:
    
    ```java
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    
                super.onActivityResult(requestCode, resultCode, data);

                    switch (requestCode) {
                        case REQ_CODE_SPEECH_INPUT: {

                            if (resultCode == RESULT_OK && null != data) {
                                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                                tv.setText(result.get(0));

                                if(result.get(0).equals("vamos ouvir")){
                                    Intent intent = new Intent(MainActivity.this, Ouvir.class);
                                    startActivity(intent);
                                }
                            }
                            break;
                        }
           }
    ```
    
    * Falar.java Final:
    
    ```java
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

            public class MainActivity extends AppCompatActivity {

                TextView tv;
                Button bTalk;
                private RadioGroup radioGroup;
                private RadioButton radioButton;
                private static final int REQ_CODE_SPEECH_INPUT = 100;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);

                    bTalk= findViewById(R.id.bTalk);
                    tv = findViewById(R.id.tvSpeech);
                    radioGroup = (RadioGroup) findViewById(R.id.radio);

                    bTalk.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            int selectedId = radioGroup.getCheckedRadioButtonId();
                            radioButton = (RadioButton) findViewById(selectedId);

                            Toast.makeText(MainActivity.this,
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
                                    Intent intent = new Intent(MainActivity.this, Ouvir.class);
                                    startActivity(intent);

                                }
                            }
                            break;
                        }

                    }
                }

            }

        }
    }

    ```
    
 

## QUARTA PARTE (TTS)

* Ouvir.java
    
    * Declarar variáveis:
    
    ```java
        TextToSpeech textToSpeech;
        EditText editText;
        Button bOuvir;
    ```
    
   * Usar findViewById (int) para ter acesso aos componentes da interface com os quais será preciso interagir:
    
    ```java
        editText=(EditText)findViewById(R.id.editText);
        bOuvir=(Button)findViewById(R.id.button);
    ```

   * Criar textToSpeech:
    
    ```java
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if(status != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(Locale.getDefault());

         for (Voice tmpVoice : textToSpeech.getVoices()) {
                    if (tmpVoice.getName().equals("en-us-x-sfg#male_1-local")) {
                        textToSpeech.setVoice(tmpVoice);
                        break;
                    }
                }
            }
        }
    });
    ```
    
   * Botão Para ouvir:
    
    ```java
       bOuvir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String toSpeak = editText.getText().toString();
            Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
            textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
        }
    });
    ```
    
   *  Ouvir.java Final:
    
    ```java
         package com.example.bruna.voiceapp;

        import android.os.Build;
        import android.os.Bundle;
        import android.speech.tts.TextToSpeech;
        import android.speech.tts.Voice;
        import android.support.annotation.RequiresApi;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import java.util.Locale;

        public class Ouvir extends AppCompatActivity {

            TextToSpeech textToSpeech;
            EditText editText;
            Button bOuvir;

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_ouvir);

                editText=(EditText)findViewById(R.id.editText);
                bOuvir=(Button)findViewById(R.id.button);

                textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            textToSpeech.setLanguage(Locale.getDefault());
                            for (Voice tmpVoice : textToSpeech.getVoices()) {
                                if (tmpVoice.getName().equals("en-us-x-sfg#male_1-local")) {
                                    textToSpeech.setVoice(tmpVoice);
                                    break;
                                }
                            }
                        }
                    }
                });

                bOuvir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String toSpeak = editText.getText().toString();
                        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
        }

        public void onPause(){
            if(textToSpeech !=null){
                textToSpeech.stop();
                textToSpeech.shutdown();
            }
            super.onPause();
        }
    }

    ```  