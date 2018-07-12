package com.microsoft.cognitiveservices.luis.sample;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.microsoft.cognitiveservices.luis.clientlibrary.LUISClient;
import com.microsoft.cognitiveservices.luis.clientlibrary.LUISDialog;
import com.microsoft.cognitiveservices.luis.clientlibrary.LUISEntity;
import com.microsoft.cognitiveservices.luis.clientlibrary.LUISIntent;
import com.microsoft.cognitiveservices.luis.clientlibrary.LUISResponse;
import com.microsoft.cognitiveservices.luis.clientlibrary.LUISResponseHandler;

import java.io.Console;
import java.util.List;

public class CargarLUIS {
    String AppID="1500f094-435a-4b2d-8b7f-5aeab9fb74c7";
    String AppKey="dfdc1c531aea42298eb62105fdb6d52a";
    String TextPredict;
    String Intencion;
    String []Entidades;
    LUISResponse previousResponse = null;

    public String getIntencion() {
        return Intencion;
    }



    public String[] getEntidades() {
        return Entidades;
    }

    public void setTextPredict(String textPredict) {
        TextPredict = textPredict;
    }


    public void FuncionarLUIS(){
        try {
            LUISClient client = new LUISClient(this.AppID, this.AppKey, true);
            client.predict(this.TextPredict, new LUISResponseHandler() {
                @Override
                public void onSuccess(LUISResponse response) {
                    processResponse(response);
                }

                @Override
                public void onFailure(Exception e) {
                    String Error=e.getMessage ();
                    Log.e ("Error: ",Error);
                }
            });
        } catch (Exception e) {
            String Error=e.getMessage ();
            Log.e ("Error: ",Error);
        }
    }

    public void processResponse(LUISResponse response) {
        previousResponse = response;
        LUISIntent topIntent = response.getTopIntent();
        this.Intencion=topIntent.getName ();
        List<LUISEntity> entities = response.getEntities();
        for (int i = 0; i < entities.size(); i++) {
            this.Entidades[i]=entities.get(i).getName ();
        }
        LUISDialog dialog = response.getDialog();
        if (dialog != null) {
            if (!dialog.isFinished()) {
            }
        }
    }



}
