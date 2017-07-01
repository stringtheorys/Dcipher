package com.stringtheory.dcipher;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Caesar extends Activity{
    Button MenuButton;

    EditText PlainText;
    EditText KeyText;
    Button EncryptButton;
    Button DecrpytButton;
    Button BruteForceButton;
    TextView CipherText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ceasar);

        TextView CipherDescription = (TextView) findViewById(R.id.CipherDescription);
        CipherDescription.setText("A shift cipher is a type of substitution cipher where all the letters are replaced by letters further down the alphabet/ASCII table. Our cipher uses ASCII codes between 32 and 126.");

        MenuButton = (Button) findViewById(R.id.MenuButton);
        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMenu();
            }
        });

        PlainText = (EditText) findViewById(R.id.PlainTextET);
        KeyText = (EditText) findViewById(R.id.KeyET);

        EncryptButton = (Button) findViewById(R.id.encrypButton);
        DecrpytButton = (Button) findViewById(R.id.decryptButton);
        BruteForceButton = (Button) findViewById(R.id.BruteForceDecrypt);

        CipherText = (TextView) findViewById(R.id.CipherTextTV);

        EncryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plaintext = PlainText.getText().toString();
                int keyText = Integer.parseInt(KeyText.getText().toString());
                Encrpytion(plaintext, keyText);
            }
        });
        DecrpytButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plaintext = PlainText.getText().toString();
                int keyText = Integer.parseInt(KeyText.getText().toString());

                Decryption(plaintext, keyText);
            }
        });
    }

    private void Encrpytion(String plainText, int keyText){

        CipherText.setText("Cipher Text : "+Encryptor.encryptShift(plainText,keyText));
    }
    private void Decryption(String plainText, int keyText){

        CipherText.setText("Plain Text : "+Encryptor.decryptShift(plainText,keyText));
    }

    private void changeMenu(){
        onBackPressed();
    }

}
