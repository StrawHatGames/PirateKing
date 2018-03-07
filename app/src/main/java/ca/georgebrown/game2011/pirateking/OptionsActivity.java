package ca.georgebrown.game2011.pirateking;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

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