package com.example.lab1_xo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private byte turn=0;
    private byte currentTurn=0;
    private ArrayList<Button> btns;
    private TextView text;
    private Button restart;
    final private byte X_TURN=0;
    final private byte MAX_TURN_COUNT=9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.textView);

        restart=(Button)findViewById(R.id.restart);
        View.OnClickListener do_restart=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Button btn:btns) {
                    btn.setText("");
                    btn.setEnabled(true);
                }
                text.setText("Крестики-нолики!");
                currentTurn=0;
            }
        };
        restart.setOnClickListener(do_restart);

        text.setTextSize(35);

        btns=new ArrayList<>();
        btns.add((Button)findViewById(R.id.button2));
        btns.add((Button)findViewById(R.id.button3));
        btns.add((Button)findViewById(R.id.button4));
        btns.add((Button)findViewById(R.id.button5));
        btns.add((Button)findViewById(R.id.button6));
        btns.add((Button)findViewById(R.id.button7));
        btns.add((Button)findViewById(R.id.button8));
        btns.add((Button)findViewById(R.id.button9));
        btns.add((Button)findViewById(R.id.button10));
        for (Button btn:btns) {
            btn.setText("");
        }
    }

    private void disableAllButtons(){
        for (Button btn:btns) {
            btn.setEnabled(false);
        }
    }

    private boolean isSetWin(String XorO,int ...poses){
        boolean flag=true;
        for (int pos:poses) {
            if (!btns.get(pos).getText().equals(XorO)) flag=false;
        }
        return flag;
    }

    //returns 1 if X win
    //return -1 if O win
    private int findWinner(){
        if (isSetWin("X",0,1,2)) return 1;
        if (isSetWin("X",3,4,5)) return 1;
        if (isSetWin("X",6,7,8)) return 1;
        if (isSetWin("X",0,3,6)) return 1;
        if (isSetWin("X",1,4,7)) return 1;
        if (isSetWin("X",2,5,8)) return 1;
        if (isSetWin("X",2,5,8)) return 1;
        if (isSetWin("X",0,4,8)) return 1;
        if (isSetWin("X",2,4,6)) return 1;

        if (isSetWin("O",0,1,2)) return -1;
        if (isSetWin("O",3,4,5)) return -1;
        if (isSetWin("O",6,7,8)) return -1;
        if (isSetWin("O",0,3,6)) return -1;
        if (isSetWin("O",1,4,7)) return -1;
        if (isSetWin("O",2,5,8)) return -1;
        if (isSetWin("O",2,5,8)) return -1;
        if (isSetWin("O",0,4,8)) return -1;
        if (isSetWin("O",2,4,6)) return -1;

        return 0;
    }

    @Override
    public void onClick(View v) {
        //switch turn
        turn=(byte)(1-turn);

        Button btn=(Button) v;

        if (X_TURN==turn){
            btn.setText("X");
            btn.setTextColor(Color.RED);
            text.setText("Ходит нолик");
        }else {
            btn.setText("O");
            btn.setTextColor(Color.BLUE);
            text.setText("Ходит крестик");
        }
        //check for winner
        int winner;

        if ((winner=findWinner())!=0){
            if (winner>0) text.setText("Крестик победил");
            else text.setText("Нолик победил");
            disableAllButtons();
        }else if (++currentTurn==MAX_TURN_COUNT){
            text.setText("Ничья!");
        }
        btn.setTextSize(25);
        btn.setEnabled(false);
    }


}
