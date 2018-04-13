package com.example.samiksha_2.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity
{
    Handler h=new Handler ( );
    static int userturnscore = 0, usertotalscore = 0, computerturnscore = 0, computertotalscore = 0;
    static int final_score = 100;
    static TextView textView2, textview4;
    static Button button, button1, button2;
    static ImageView imageView;
    static int diceFaces[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    private static Random r = new Random ();
    int val;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        textView2 = ( TextView ) findViewById ( R.id.textView2 );
        textview4 = ( TextView ) findViewById ( R.id.textView4 );
        button = ( Button ) findViewById ( R.id.button );
        button1 = ( Button ) findViewById ( R.id.button2 );
        button2 = ( Button ) findViewById ( R.id.button3 );
        imageView = ( ImageView ) findViewById ( R.id.imageView );
        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                ROLL ();
                if (val!=1)
                userturnscore = val + userturnscore;
                else{
                    userturnscore = 0;
                    // updatescore ();
                    //button.setEnabled ( false );
                    compturn ();
                }

            }
        } );
        button1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                HOLD ();
            }
        } );
        button2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                RESET ();
            }
        } );

    }

    public void ROLL()
    {
        val = r.nextInt ( 6 ) + 1;
        if (val != 1) {
            imageView.setImageResource ( diceFaces[val - 1] );

        } else
        {
            imageView.setImageResource ( diceFaces[val - 1] );

        }
    }

    Runnable runfun=new Runnable () {
        @Override
        public void run()
        {
            ROLL ();
            if (val == 1)
            {
                computerturnscore = 0;
                updatescore ();
                button.setEnabled ( true );
                button1.setEnabled ( true );
            } else {
                computerturnscore += val;
                if (r.nextBoolean ())
                h.postDelayed ( this,500 );
                //updatescore ();
                else{
                    computertotalscore += computerturnscore;
                    computerturnscore = 0;
                    updatescore ();
                }

            }

        }
    };

    public void HOLD()
    {
        usertotalscore = usertotalscore + userturnscore;
        userturnscore = 0;
        updatescore ();
        if (usertotalscore >= final_score)
        {
            button.setEnabled ( false );
            button1.setEnabled ( false );
            return;
        }
        compturn ();
    }

    public void RESET()
    {
        imageView.setImageResource ( diceFaces[0] );
        usertotalscore = userturnscore = 0;
        computerturnscore = computertotalscore = 0;
        updatescore ();
        button.setEnabled ( true );
        button1.setEnabled ( true );
    }

    public void updatescore()
    {
        usertotalscore += userturnscore;
        textView2.setText (String.valueOf (usertotalscore) );
        computertotalscore += computerturnscore;
        textview4.setText ( String.valueOf ( computertotalscore ));
        if(usertotalscore>=100)
        {
            Toast.makeText ( getApplicationContext (),"User won",Toast.LENGTH_LONG ).show ();
        }
        if(computertotalscore>=100)
        {
            Toast.makeText ( getApplicationContext (),"Computer won",Toast.LENGTH_LONG ).show ();
        }

    }

    public void compturn()
    {
        button.setEnabled ( false );
        button1.setEnabled ( false );
        boolean flag=true;

        h.postDelayed ( runfun,500 );
        if (computertotalscore >= final_score)
        {
            button.setEnabled ( false );
            button1.setEnabled ( false );
            return ;
        }
        button.setEnabled ( true );
        button1.setEnabled ( true );
    }


}







