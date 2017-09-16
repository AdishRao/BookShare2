package lw.bookshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static lw.bookshare.R.id.Register_Button;
import static lw.bookshare.R.id.addDetails;
import static lw.bookshare.R.id.existing_user_signin;
import static lw.bookshare.R.id.getLoc;
import static lw.bookshare.R.id.getPhone;
import static lw.bookshare.R.id.getUsername;
import static lw.bookshare.R.id.signIn_Button;
import static lw.bookshare.R.id.signUpText;

public class Details_Page extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__page);
    }





    @Override
    public void onClick (View view){

    }
}
