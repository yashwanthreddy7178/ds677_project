package nigeriandailies.com.ng.coolgain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    TextInputLayout mFullName, mEmail, mPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        getSupportActionBar ().hide ();

        mFullName = findViewById (R.id.FullName);
        mEmail = findViewById (R.id.EmailReg);
        mPassword = findViewById (R.id.PasswordReg);

        
        mRegisterBtn = findViewById (R.id.RegistrationBtn);
        mLoginBtn = findViewById (R.id.createText1);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById (R.id.progressBar);

        if (fAuth.getCurrentUser () != null ){
            startActivity (new Intent (getApplicationContext (), MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText ().toString ().trim ();
                String password = mPassword.getText ().toString ().trim ();

                if (TextUtils.isEmpty (email)){
                    mEmail.setError ("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty (password)){
                    mPassword.setError ("Password is Required");
                    return;
            }

                if (password.length () < 6){
                    mPassword.setError ("password must be 6 or more characters");
                    return;
                }

                progressBar.setVisibility (View.VISIBLE);
                /* Register the user in firebase */

                fAuth.createUserWithEmailAndPassword (email,password).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful ()){
                            Toast.makeText (Register.this, "User Created", Toast.LENGTH_SHORT).show ();
                            startActivity (new Intent (getApplicationContext (), MainActivity.class));
                        }else {
                            Toast.makeText (Register.this, "Error ! " + task.getException ().getMessage (), Toast.LENGTH_SHORT).show ();
                        }
                    }
                });
        }

        });

        mLoginBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (getApplicationContext (), Login.class));
            }
        });
    }
}
