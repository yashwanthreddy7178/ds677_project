package com.ks.mygenie;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.stephentuso.welcome.WelcomeHelper;

/**
 * Created by DOLPHIN on 12-Oct-17.
 */

public class LoginActivity extends FragmentActivity{

    WelcomeHelper tutorialsWelcomeScreen;
    Button btnSignUp;
    Button btnSignIn;
    Button btnForgotP;
    Button btnGuest;

//    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tutorialsWelcomeScreen = new WelcomeHelper(this, TutorialsActivity.class);
        tutorialsWelcomeScreen.show(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        init();
    }

    private void init() {
        btnSignIn = (Button) findViewById(R.id.sign_in_btn);

        btnSignUp = (Button) findViewById(R.id.goto_reg_btn);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent generegistration  = new Intent(getApplicationContext(), GeneRegActivity.class);
                startActivity(generegistration);
            }
        });

        btnForgotP = (Button) findViewById(R.id.forgotP_btn);
        btnForgotP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoFP  = new Intent(getApplicationContext(), ForgotPassActivity.class);
                startActivity(gotoFP);
            }
        });

        btnGuest = (Button) findViewById(R.id.guest_sign_in_btn);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        tutorialsWelcomeScreen.onSaveInstanceState(outState);
    }
}
