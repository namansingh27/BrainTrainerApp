package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button0,button1,button2,button3,playAgainBtn;
    TextView sumText,textCorrect,textScore,textTimer;
    int locationOfCorrectAns;

    ArrayList<Integer> answer= new ArrayList<>(); //This array list will hold all 4 options for us

    int score=0;
    int numberOfGamesPlayed=0;

    public void chooseAns(View view){     //creating a method to choose an answer

       if(String.valueOf(locationOfCorrectAns).equals(view.getTag().toString())){
           textCorrect.setText("Correct!!");
           score++;
       }
       else
       {
           textCorrect.setText("Wrong Answer");
       }

       numberOfGamesPlayed++;

       textScore.setText(score + "/" + numberOfGamesPlayed);

       newQuestion();

    }

    public void playAgain(View view){

        score=0;
        numberOfGamesPlayed=0;
        textTimer.setText("20s");
        textScore.setText(score + "/" + numberOfGamesPlayed);

        newQuestion();
        playAgainBtn.setVisibility(View.INVISIBLE);

        CountDownTimer countDownTimer=new CountDownTimer(20100,1000) {
            @Override
            public void onTick(long millisUntilFinished) { // represents current time
                textTimer.setText(String.valueOf(millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {
                textCorrect.setText("Over!");
                playAgainBtn.setVisibility(View.VISIBLE);

            }
        }.start();


    }

    public void newQuestion(){
        Random rand=new Random(); //creating new random class to generate random number

        int a= rand.nextInt(41);
        int b=rand.nextInt(41);

        sumText.setText(a + "+" + b);


        locationOfCorrectAns= rand.nextInt(4); //this will generate the location of our answer randomly

        answer.clear(); //To clear array list after every new question is formed

        for(int i=0;i<4;i++){

            if(i==locationOfCorrectAns){
                answer.add(a+b);
            }
            else{
                int wrongAns= rand.nextInt(41);

                while(wrongAns==a+b){
                    wrongAns= rand.nextInt(41);
                }
                answer.add(wrongAns);
            }

        }

        button0.setText(String.valueOf(answer.get(0))); // here button 0 will hold the value of 0th index element of array list
        button1.setText(String.valueOf(answer.get(1)));// here button 1 will hold the value of 1st index element of array list
        button2.setText(String.valueOf(answer.get(2)));// here button 2 will hold the value of 2nd index element of array list
        button3.setText(String.valueOf(answer.get(3)));// here button 3 will hold the value of 3rd index element of array list
        //One of them produces correct answer




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sumText=findViewById(R.id.sumText);//initialise
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgainBtn=findViewById(R.id.playAgainBtn);

        textCorrect=findViewById(R.id.textCorrect);
        textScore=findViewById(R.id.textScore);
        textTimer=findViewById(R.id.textTimer);

      playAgain(findViewById(R.id.textCorrect));


    }
}