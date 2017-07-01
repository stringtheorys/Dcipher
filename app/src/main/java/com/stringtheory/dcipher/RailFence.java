package com.stringtheory.dcipher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RailFence extends Activity{
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
        setContentView(R.layout.railfence);

        TextView CipherDescription = (TextView) findViewById(R.id.CipherDescription);
        CipherDescription.setText("A railfence cipher is a type of transposition cipher that encodes text by mapping it in a zig-zag onto a table, then taking the length-ways output.");

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
                Encrpytion(plaintext, keyText);

                Decryption(plaintext, keyText);
            }
        });
    }

    private void Encrpytion(String plainText, int keyText){

        CipherText.setText("Cipher Text : "+Encryptor.encryptRailFence(plainText,keyText));
    }
    private void Decryption(String plainText, int keyText){

        CipherText.setText("Plain Text : "+Encryptor.decryptRailFence(plainText,keyText));
    }

    private void changeMenu(){
        onBackPressed();
    }

}

