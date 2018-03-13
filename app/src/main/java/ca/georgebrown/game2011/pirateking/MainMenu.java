package ca.georgebrown.game2011.pirateking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
*
*                                                                                           MainMenu.java
*                                                                              Marco Giardina - Felix Pham - Boris Merlo
*                                                                                 101025550   - 101035471  -  101081263
*                                                                      03-11-18 - © 2018 Straw Hat Games.  All rights reserved.
*
*                                                                                 version 0.1 -> added reel functionality, images, and main game mechanics
*                                                                                             -> added buttons to ensure game mechanics functionality (bets, spin, reset)
*
*                                                                                 version 0.5 -> added various checks to ensure a functional game loop
*                                                                                             -> added an options menu, added UI elements, tested win conditions
*
*                                                                                 version 1.0 -> added background to main game window
*                                                                                             -> added various UI elements to main game window
*                                                                                             -> polished main game window UI elements, and tested win conditions, as well as jackpot
*                                                                                             -> polished main menu, added text elements, and updated version number
*
*                                                          This script will allow the user to navigate through the main menu of the application.
*                                                          The user will be able to choose if they want to start a new instance of the game, go to the options screen, or quit.
**/

public class MainMenu extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final Button quitBtn;
        {
            quitBtn = findViewById(R.id.quitBtn);
            quitBtn.setOnClickListener(new View.OnClickListener()
                                       {
                                           @Override
                                           public void onClick(View v)
                                           {
                                               //finish();
                                               finishAffinity();
                                           }
                                       }
            );
        }

        final Button playBtn;
        {
            playBtn = findViewById(R.id.playBtn);
            playBtn.setOnClickListener(new View.OnClickListener()
                                       {
                                           @Override
                                           public void onClick(View v)
                                           {
                                               startPlay();
                                           }
                                       }
            );
        }

        final Button optionsBtn;
        {
            optionsBtn = findViewById(R.id.optionsBtn);
            optionsBtn.setOnClickListener(new View.OnClickListener()
                                          {
                                              @Override
                                              public void onClick(View v)
                                              {
                                                  optionsMenu();
                                              }
                                          }
            );
        }
    }

    public void optionsMenu()
    {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    private void startPlay()
    {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //Toast.makeText(this, "onStart completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //Toast.makeText(this, "onResume completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //Toast.makeText(this, "onPause completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        //Toast.makeText(this, "onRestart completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        //Toast.makeText(this, "onStop completed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //Toast.makeText(this, "onDestroy completed", Toast.LENGTH_LONG).show();
    }
}
