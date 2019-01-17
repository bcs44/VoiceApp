## WORKSHOP DE SEMINÁRIO I

* API de Síntese e Reconhecimento de Voz

## PRIMEIRA PARTE (CONFIGURAÇÃO)

* [Download Android Studio](https://developer.android.com/studio/index.html)
* Instalação
* Configuração


## SEGUNDA PARTE 
* Caso não queiram seguir os passos da segunda e terceira parte, podem fazer o download do projeto [BSVoiceApp](https://github.com/bcs44/BSVoiceApp).

* 1 

![1](https://user-images.githubusercontent.com/23102822/51327642-ac881b00-1a69-11e9-8e04-107f2e5f23ca.png)

* 2

![2](https://user-images.githubusercontent.com/23102822/51327644-aeea7500-1a69-11e9-81e1-e134545621b1.png)

* 3

![3](https://user-images.githubusercontent.com/23102822/51327654-b4e05600-1a69-11e9-99a4-fb3d01039db4.png)

* 4

![4](https://user-images.githubusercontent.com/23102822/51327680-bad63700-1a69-11e9-9059-928736cd4e54.png)

* activity_falar.xml:


 ```xml
            <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context=".Falar"
            android:orientation="vertical">

            <RadioGroup
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="2"
                android:id="@+id/radio">

                <RadioButton
                    android:id="@+id/radioButton"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Português" />

                <RadioButton
                    android:id="@+id/radioButton2"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Inglês" />

                <RadioButton
                    android:id="@+id/radioButton3"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Default" />
            </RadioGroup>

            <Button
                android:id="@+id/bTalk"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="0"
                android:text="Falar" />

            <TextView
                android:id="@+id/tvSpeech"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_weight="3"
                android:scrollbars="vertical"
                android:text="Name" />
        </LinearLayout>
  ```
## TERCEIRA PARTE (CRIAÇÃO DOS FICHEIROS NECESSÁRIOS) 
* Caso não queiram seguir os passos da segunda e terceira parte, podem fazer o download do projeto [BSVoiceApp](https://github.com/bcs44/BSVoiceApp).

* 1

![5](https://user-images.githubusercontent.com/23102822/51327878-25877280-1a6a-11e9-9d88-8d720be26ec7.png)

* 2

![6](https://user-images.githubusercontent.com/23102822/51327884-27e9cc80-1a6a-11e9-9707-3d984690506e.png)

* 3

![7](https://user-images.githubusercontent.com/23102822/51327887-28826300-1a6a-11e9-8d12-611544cf8090.png)

* 4

![8](https://user-images.githubusercontent.com/23102822/51327890-29b39000-1a6a-11e9-8182-d215cd525e99.png)

* activity_ouvir.xml:

  ```xml
     <?xml version="1.0" encoding="utf-8"?>
   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       tools:context=".Ouvir"
       android:orientation="vertical">

       <EditText
           android:id="@+id/editText"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:ems="10"
           android:inputType="textPersonName"
           android:text="O que deseja Ouvir?" />

       <Button
           android:id="@+id/button"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:text="Button" />
   </LinearLayout>

  ```


  
## QUARTA PARTE (STT)

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
    
 

## QUINTA PARTE (TTS)

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
