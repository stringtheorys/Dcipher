package com.stringtheory.dcipher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Challenge extends Activity {

    Button MenuButton;

    TextView Question;
    TextView ExtraInfo;
    EditText AnswerBox;
    Button CheckAnswer;
    Button newQuestion;
    TextView Score;
    int score;

    String Answer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge);

        MenuButton = (Button) findViewById(R.id.button);
        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Question = (TextView) findViewById(R.id.Question);
        ExtraInfo = (TextView) findViewById(R.id.Information);
        AnswerBox = (EditText) findViewById(R.id.AnswerBox);
        CheckAnswer = (Button) findViewById(R.id.checkButton);
        newQuestion = (Button) findViewById(R.id.NewQuestion);
        Score = (TextView) findViewById(R.id.Score);

        newQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewQuestion();
            }
        });
        /*
        try{
            BufferedReader reader = new BufferedReader(new FileReader("QuestionsAnswered.txt"));
            score = Integer.parseInt(reader.readLine());
        } catch(IOException ex){

        }
        */
        Score.setText("Questions Completed - "+Integer.toString(score));

        CheckAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAnswer = AnswerBox.getText().toString();
                if (userAnswer == Answer){
                    score++;
                    Score.setText("Questions Completed - "+Integer.toString(score));
                    /*
                    try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter("QuestionsAnswered.txt"));
                        writer.write(score);
                    } catch(IOException ex){

                    }
                    */
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Challenge.this);
                    builder1.setMessage("Congrats your answer is correct");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Thanks",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    NewQuestion();
                } else {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Challenge.this);
                        builder1.setMessage("Sorry your answer is wrong");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Thanks",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                }
            }
        });

        NewQuestion();

    }
    private void NewQuestion(){
        Question newQuestion = new Question();
        Question.setText(newQuestion.getQuestion());
        ExtraInfo.setText(newQuestion.getExtraInformation());
        Answer = newQuestion.getAnswer();
    }
}



class Question {

    public Question() {
        generateQuestion();
    }

    private boolean encryptQ;
    private String plainText;
    private String cipherText;
    private final Random random = new Random();
    private int key;
    private final String[] sampleWords = {"code", "computer", "number", "key", "cipher", "alphabet", "hackathon", "program", "data", "encrypt"};

    private List<Integer> stringToIntegerList(String text) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            temp.add((int) text.charAt(i));
        }
        return temp;
    }

    private void encrypt() {
        List<Integer> convert = stringToIntegerList(plainText);
        for (int i = 0; i < convert.size(); i++) {
            convert.set(i, ((convert.get(i) - 97 + key) % 26) + 97);
        }
        StringBuilder sb = new StringBuilder();
        for (int num : convert) {
            sb.append((char) num);
        }
        cipherText = sb.toString();
    }

    public final void generateQuestion() {
        encryptQ = random.nextBoolean();
        plainText = sampleWords[random.nextInt(sampleWords.length)];
        key = random.nextInt(25) + 1;
        encrypt();
    }

    public boolean isEncryption() {
        return encryptQ;
    }

    public String getPlainText() {
        return plainText;
    }

    public String getCipherText() {
        return cipherText;
    }

    public String getQuestion() {
        if (encryptQ) {
            return "What is the ciphertext?";
        } else {
            return "What is the plaintext?";
        }
    }

    public String getExtraInformation() {
        if (encryptQ) {
            return "A plaintext word, \"" + plainText + "\", has been encrypted by a shift cipher with an alphabet of a-z, with a key of " + key + ".";
        } else {
            return "A plaintext word has been encrypted to the ciphertext \"" + cipherText + "\", by a shift cipher with an alphabet of a-z, with a key of " + key + ".";
        }
    }

    public String getAnswer(){
        if (encryptQ){
            return cipherText;
        } else {
            return plainText;
        }
    }

}