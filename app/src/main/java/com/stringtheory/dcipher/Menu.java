package com.stringtheory.dcipher;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity{

    Button CaesarButton;
    Button VerhamButton;
    Button railfenceButton;
    Button VigenereButton;
    Button ChallengeButton;
    Button EmailCryptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        CaesarButton = (Button) findViewById(R.id.CaesarButton);
        VerhamButton = (Button) findViewById(R.id.VerhamButton);
        railfenceButton = (Button) findViewById(R.id.railFenceButton);
        VigenereButton = (Button) findViewById(R.id.VigenereCipher);
        ChallengeButton = (Button) findViewById(R.id.ChallengeQuestion);
        EmailCryptButton = (Button) findViewById(R.id.EmailCryButton);


        CaesarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeCaesar();
            }
        });
        VerhamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVerham();
            }
        });
        railfenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeRailFence();
            }
        });
        VigenereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeVigenere();
            }
        });
        ChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeChallenge();
            }
        });
        EmailCryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeEmail();
            }
        });

    }
    private void ChangeCaesar(){
        Intent newIntent = new Intent(this, Caesar.class);
        startActivity(newIntent);
    }
    private void changeVerham(){
        Intent newIntent = new Intent(this, Verham.class);
        startActivity(newIntent);
    }
    private void changeRailFence(){
        Intent newIntent = new Intent(this, RailFence.class);
        startActivity(newIntent);
    }
    private void changeVigenere(){
        Intent newIntent = new Intent(this, Vigenere.class);
        startActivity(newIntent);
    }
    private void changeChallenge(){
        Intent newIntent = new Intent(this, Challenge.class);
        startActivity(newIntent);
    }
    private void changeEmail(){
        Intent newIntent = new Intent(this, EmailEncryption.class);
        startActivity(newIntent);
    }
}
