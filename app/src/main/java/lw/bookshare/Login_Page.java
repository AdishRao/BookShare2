package lw.bookshare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.attr.start;
import static lw.bookshare.R.id.Register_Button;
import static lw.bookshare.R.id.existing_user_signin;

public class Login_Page extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button signIn_Button;
    private EditText email_Old;
    private EditText password_Old;
    private TextView signUpText;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in START ACTIVITY    CHECK THIS LINE OF CODE!!!
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        signIn_Button= (Button) findViewById(R.id.signIn_Button);
        email_Old = (EditText) findViewById(R.id.email_Old);
        password_Old = (EditText) findViewById(R.id.password_Old);
        signUpText = (TextView) findViewById(R.id.signUpText);
        progressDialog = new ProgressDialog(this);

        signIn_Button.setOnClickListener(this);
        signUpText.setOnClickListener(this);
    }

        private void  userLogin(){
            String email = email_Old.getText().toString().trim();
            String password= password_Old.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
                return;
            }

            progressDialog.setMessage("Registering");
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                //START USER ACTIVITY
                            }

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Toast.makeText(Login_Page.this,"Failed",Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }

    @Override
    public void onClick(View view){
        if(view == signIn_Button)
        {
            userLogin();
        }
        if( view == signUpText)
        {   finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
