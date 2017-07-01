package com.stringtheory.dcipher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Verham extends Activity{
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
        setContentView(R.layout.verham);

        TextView CipherDescription = (TextView) findViewById(R.id.CipherDescription);
        CipherDescription.setText("The Vernam cipher is a cipher that uses a one-time keyword with an XOR applied to the plaintext.This has made the cipher is only 100% mathatically secure cipher");

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
                String keyText = KeyText.getText().toString();
                if (plaintext.length() != keyText.length()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Verham.this);
                    builder1.setMessage("Your key and plain text must be the same length");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                Encrpytion(plaintext, keyText);
            }
        });
        DecrpytButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plaintext = PlainText.getText().toString();
                String keyText = KeyText.getText().toString();
                if (plaintext.length() != keyText.length()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Verham.this);
                    builder1.setMessage("Your key and plain text must be the same length");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                Decryption(plaintext, keyText);
            }
        });
    }

    private void Encrpytion(String plainText, String keyText){

        CipherText.setText("Cipher Text : " + Encryptor.encryptVernam(plainText,keyText));
    }
    private void Decryption(String plainText, String keyText){

        CipherText.setText("Plain Text : " + Encryptor.decryptVernam(plainText, keyText));
    }

    private void changeMenu(){
        onBackPressed();
    }

}

