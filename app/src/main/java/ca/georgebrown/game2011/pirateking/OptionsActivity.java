package ca.georgebrown.game2011.pirateking;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 *
 *                                                                                           OptionsActivity.java
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
 *                                                          This script will allow the user to activate and de-activate sound effects and background music.
 *                                                          It uses toggle buttons to allow the enable the user to change these settings.
 **/

public class OptionsActivity extends AppCompatActivity
{
    public ToggleButton toggleSFXObj, toggleMusicObj;

    public void onCreate(Bundle savedInstanceState)//, PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState);//, persistentState);

        setContentView(R.layout.activity_options);
        //toggleSFXObj = findViewById(R.id.toggleSFX);

       /* toggleMusicObj.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(toggleSFXObj.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "SFX WORKING ON", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "SFX WORKING OFF", Toast.LENGTH_SHORT).show();
                }
                if(toggleMusicObj.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "MUSIC WORKING ON", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "MUSIC WORKING OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    public void onToggleMusicClick(View view)
    {
    }

    public void onToggleSFXClick(View view)
    {
    }
}