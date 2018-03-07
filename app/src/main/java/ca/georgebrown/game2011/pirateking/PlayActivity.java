package ca.georgebrown.game2011.pirateking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PlayActivity extends AppCompatActivity
{

    private static int[] images = {R.drawable.bomb, R.drawable.berries, R.drawable.marines, R.drawable.sunny, R.drawable.meramera, R.drawable.mugiwara};

    public int playerCredits = 1000;
    public int pBetCounter = 0;
    public double pAmountWon = 0;

    public int bomb = 0;
    public int berries = 0;
    public int marines = 0;
    public int sunny = 0;
    public int meramera = 0;
    public int mugiwara = 0;

    public ImageView top1, top2, top3;
    public ImageView mid1, mid2, mid3;
    public ImageView bot1, bot2, bot3;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable()
    {
        @SuppressLint("InlinedApi")
        @Override
        public void run()
        {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable()
    {
        @Override
        public void run()
        {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
            {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent)
        {
            if (AUTO_HIDE)
            {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.spinBtn).setOnTouchListener(mDelayHideTouchListener);

        mid1 = findViewById(R.id.middle1);
        mid1.setImageResource(images[0]);

        mid2 = findViewById(R.id.middle2);
        mid2.setImageResource(images[1]);

        mid3 = findViewById(R.id.middle3);
        mid3.setImageResource(images[2]);

        TextView pCredits = findViewById(R.id.pCreditAmount);
        pCredits.setText(playerCredits+"");

        TextView pBet = findViewById(R.id.pBetAmount);
        pBet.setText(pBetCounter+"");

        final Button spinBtn;
        {
            spinBtn = findViewById(R.id.spinBtn);
            spinBtn.setOnClickListener(new View.OnClickListener()
                                       {
                                           @Override
                                           public void onClick(View v)
                                           {
                                               if(pBetCounter != 0)
                                                   spinReel();
                                               pBetCounter = 0;
                                               UpdateUI();
                                           }
                                       }
            );
        }

        final Button betBtn1;
        {
            betBtn1 = findViewById(R.id.betBtn1);
            betBtn1.setOnClickListener(new View.OnClickListener()
                                       {
                                           @Override
                                           public void onClick(View v)
                                           {
                                               if(playerCredits >= 5)
                                                   setBetAmount(5);
                                           }
                                       }
            );
        }

        final Button betBtn2;
        {
            betBtn2 = findViewById(R.id.betBtn2);
            betBtn2.setOnClickListener(new View.OnClickListener()
                                       {
                                           @Override
                                           public void onClick(View v)
                                           {
                                               if(playerCredits >= 10)
                                                   setBetAmount(10);
                                           }
                                       }
            );
        }

        final Button betBtn3;
        {
            betBtn3 = findViewById(R.id.betBtn3);
            betBtn3.setOnClickListener(new View.OnClickListener()
                                       {
                                           @Override
                                           public void onClick(View v)
                                           {
                                               if(playerCredits >= 30)
                                                   setBetAmount(30);
                                           }
                                       }
            );
        }

        final Button menuBtn;
        {
            menuBtn = findViewById(R.id.menuBtn);
            menuBtn.setOnClickListener(new View.OnClickListener()
                                       {
                                           @Override
                                           public void onClick(View v)
                                           {
                                               backToMenu();
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

        final Button resetCreditsBtn;
        {
            resetCreditsBtn = findViewById(R.id.resetBtn);
            resetCreditsBtn.setOnClickListener(new View.OnClickListener()
                                               {
                                                   @Override
                                                   public void onClick(View v)
                                                   {
                                                       resetCredits();
                                                   }
                                               }
            );
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle()
    {
        if (mVisible)
        {
            hide();
        }
        else
        {
            show();
        }
    }

    private void hide()
    {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show()
    {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis)
    {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void spinReel()
    {
        int randSpin = (int) Math.floor((Math.random() * 50) + 1);
        if (randSpin >= 1 && randSpin <= 15) {
            mid1.setImageResource(images[0]);
            bomb++;
        } else if (randSpin >= 16 &&randSpin <= 28) {
            mid1.setImageResource(images[1]);
            berries++;
        } else if (randSpin >= 29 && randSpin <= 38) {
            mid1.setImageResource(images[2]);
            marines++;
        } else if (randSpin >= 39 && randSpin <= 44) {
            mid1.setImageResource(images[3]);
            sunny++;
        } else if (randSpin >= 45 && randSpin <= 48) {
            mid1.setImageResource(images[4]);
            meramera++;
        } else if (randSpin >= 49) {
            mid1.setImageResource(images[5]);
            mugiwara++;
        }

        int randSpin2 = (int) Math.floor((Math.random() * 50) + 1);
        if (randSpin2 >= 1 && randSpin2 <= 15) {
            mid2.setImageResource(images[0]);
            bomb++;
        } else if (randSpin2 >= 16 &&randSpin2 <= 28) {
            mid2.setImageResource(images[1]);
            berries++;
        } else if (randSpin2 >= 29 && randSpin2 <= 38) {
            mid2.setImageResource(images[2]);
            marines++;
        } else if (randSpin2 >= 39 && randSpin2 <= 44) {
            mid2.setImageResource(images[3]);
            sunny++;
        } else if (randSpin2 >= 45 && randSpin2 <= 48) {
            mid2.setImageResource(images[4]);
            meramera++;
        } else if (randSpin2 >= 49) {
            mid2.setImageResource(images[5]);
            mugiwara++;
        }

        int randSpin3 = (int) Math.floor((Math.random() * 50) + 1);
        if (randSpin3 >= 1 && randSpin3 <= 15) {
            mid3.setImageResource(images[0]);
            bomb++;
        } else if (randSpin3 >= 16 &&randSpin3 <= 28) {
            mid3.setImageResource(images[1]);
            berries++;
        } else if (randSpin3 >= 29 && randSpin3 <= 38) {
            mid3.setImageResource(images[2]);
            marines++;
        } else if (randSpin3 >= 39 && randSpin3 <= 44) {
            mid3.setImageResource(images[3]);
            sunny++;
        } else if (randSpin3 >= 45 && randSpin3 <= 48) {
            mid3.setImageResource(images[4]);
            meramera++;
        } else if (randSpin3 >= 49) {
            mid3.setImageResource(images[5]);
            mugiwara++;
        }

        if (mugiwara == 3)
        {
            playerCredits += (pBetCounter * 10);
            pAmountWon = pBetCounter * 10;
            showEarnings();
            //TODO: add jackpot message
        }
        else if (meramera == 3)
        {
            playerCredits += (pBetCounter * 6);
            pAmountWon = pBetCounter * 6;
            showEarnings();
        }
        else if (sunny == 3)
        {
            playerCredits += (pBetCounter * 4);
            pAmountWon = pBetCounter * 4;
            showEarnings();
        }
        else if (marines == 3)
        {
            playerCredits += (pBetCounter * 3);
            pAmountWon = pBetCounter * 3;
            showEarnings();
        }
        else if (berries == 3)
        {
            playerCredits += (pBetCounter * 2);
            pAmountWon = pBetCounter * 2;
            showEarnings();
        }
        else if (marines == 2)
        {
            playerCredits += (pBetCounter * 1.5);
            pAmountWon = pBetCounter * 1.5;
            showEarnings();
        }
        else if (berries == 2)
        {
            playerCredits += (pBetCounter * 1.2);
            pAmountWon = (pBetCounter * 1.2);
            showEarnings();
        }
        else
        {
            TextView pWinAmount = findViewById(R.id.winAmountText);
            pWinAmount.setVisibility(View.INVISIBLE);
        }

        UpdateUI();
        resetReel();
    }

    public void setBetAmount(int betAmount)
    {
        pBetCounter += betAmount;
        playerCredits -= betAmount;
        UpdateUI();
    }

    public void UpdateUI()
    {
        TextView pBet = findViewById(R.id.pBetAmount);
        pBet.setText(pBetCounter+"");
        TextView pCredits = findViewById(R.id.pCreditAmount);
        pCredits.setText(playerCredits+"");
    }

    public void resetCredits()
    {
        if(playerCredits <= 4)
        {
            playerCredits = 1000;
            UpdateUI();
        }
    }

    public void showEarnings()
    {
        TextView pWinAmount = findViewById(R.id.winAmountText);
        pWinAmount.setText("You won: "+pAmountWon+"!");
        pWinAmount.setVisibility(View.VISIBLE);
    }

    public void backToMenu()
    {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

   public void optionsMenu()
    {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void resetReel()
    {
        bomb = 0;
        berries = 0;
        marines = 0;
        meramera = 0;
        mugiwara = 0;
        sunny = 0;
    }
}
