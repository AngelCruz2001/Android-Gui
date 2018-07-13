package com.microsoft.cognitiveservices.luis.sample;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.cognitiveservices.luis.clientlibrary.LUISClient;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener{

    private RecyclerView recyclerView;
    private AdapterMensajes adapter;
    String  Mensaje,Respuesta;
    String Array [];
    String AppID="1500f094-435a-4b2d-8b7f-5aeab9fb74c7";
    String AppKey="dfdc1c531aea42298eb62105fdb6d52a";
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;
    private TextToSpeech textToSpeech;
    LUISClient client = new LUISClient(this.AppID, this.AppKey, true);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech = new TextToSpeech( this,this );



        recyclerView=(RecyclerView)findViewById(R.id.lista);
        adapter=new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(adapter);


        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });


    }
    private void setScrollbar(){
        recyclerView.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.
                                    EXTRA_RESULTS);
                    final String strSpeech2Text = speech.get(0);
                    adapter.addMensaje(new Mensaje("User",strSpeech2Text));
                    final CargarLUIS LUIS = new CargarLUIS ();

                    LUIS.FuncionarLUIS (strSpeech2Text, client);
                    Mensaje=strSpeech2Text;

                    textToSpeech.setLanguage( new Locale ( "spa", "MX" ) );
                    //Toast.makeText (context,"Mensaje: "+Leer,Toast.LENGTH_SHORT).show ();
                    speak(Mensaje );


                    //Hey LUIS

                    Respuesta="El intento es  " +LUIS.getIntencion ()+"\n"+
                                    "y las Entidades son ";



                                    for (int i = 0; i < LUIS.Longitud (); i++) {
                                        String Respuesta1 = LUIS.getEntidades (i) + "\n";
                                        Respuesta += Respuesta1;
                                    }

                            adapter.addMensaje(new Mensaje("User",Respuesta));
                            speak (Respuesta);

                            Toast.makeText(getApplicationContext (), strSpeech2Text, Toast.LENGTH_SHORT).show();

                        }


                break;
            default:

                break;
        }
    }

    public void onClickImgBtnHablar(View v) {

        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

// Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void onInit( int status )
    {
        if ( status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED )
        {
            Toast.makeText( this, "ERROR LANG_MISSING_DATA | LANG_NOT_SUPPORTED", Toast.LENGTH_SHORT ).show();
        }
    }

    private void speak( String str )
    {
        textToSpeech.speak( str, TextToSpeech.QUEUE_FLUSH, null );
        textToSpeech.setSpeechRate( 0.0f );
        textToSpeech.setPitch( 0.0f );
    }

    @Override
    protected void onDestroy()
    {
        if ( textToSpeech != null )
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

}