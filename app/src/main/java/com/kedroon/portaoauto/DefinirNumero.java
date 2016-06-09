package com.kedroon.portaoauto;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class DefinirNumero extends AppCompatActivity {
    String FILENAME = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definir_numero);
        final Button button = (Button) findViewById(R.id.numero);
        final EditText numero = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = String.valueOf(numero.getText().toString());
                //Toast.makeText(getApplicationContext(), number, Toast.LENGTH_SHORT).show();
                String FILENAME = "number";
                if (!number.matches("")){
                    try {
                        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        fos.write(number.getBytes());
                        fos.close();
                    }
                    catch (Exception err){

                        err.printStackTrace();
                    }

                    try{
                        FileInputStream fos = openFileInput(FILENAME);
                        InputStreamReader inputStreamReader = new InputStreamReader(fos);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            sb.append(line);
                        }
                        fos.close();
                        Toast.makeText(getApplicationContext(), "O número: "+sb+" foi salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception err){

                        err.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Por favor insira um número!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        try{
            FileInputStream fos = openFileInput(FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fos);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            fos.close();
            numero.setText(sb);
            //Toast.makeText(getApplicationContext(), "O número: "+sb+" foi salvo com sucesso!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception err){
            numero.setText("");
            err.printStackTrace();
        }

    }
}
