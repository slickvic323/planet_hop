package com.example.vicnjake.planethop;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class GameplayActivity extends AppCompatActivity {

    private int screenSizeX;
    private int screenSizeY;

    GameplayView gameplayView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_gameplay);


        //Set screensize of device
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenSizeX = size.x;
        screenSizeY = size.y;

        // Hide the app title bar.
        getSupportActionBar().hide();

        // Make app full screen to hide top status bar.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Create our gameplay canvas
        gameplayView = new GameplayView(getApplicationContext(), screenSizeX, screenSizeY);

        //Set the content view to our new gameplay view
        setContentView(gameplayView);
    }
}
