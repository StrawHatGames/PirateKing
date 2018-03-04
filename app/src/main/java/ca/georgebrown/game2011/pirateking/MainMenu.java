package ca.georgebrown.game2011.pirateking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
