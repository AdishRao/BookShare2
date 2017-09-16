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
import static lw.bookshare.R.id.signIn_Button;
import static lw.bookshare.R.id.signUpText;

public class Details_Page extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText getUsername;
    EditText getPhone;
    Button addDetails;
    Spinner getLoc;
    FirebaseDatabase databaseUsers = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__page);
        mAuth = FirebaseAuth.getInstance();
        getUsername =(EditText)findViewById(R.id.getUsername);
        getPhone =(EditText) findViewById(R.id.getPhone);
        getLoc = (Spinner) findViewById(R.id.getLoc);


        addDetails.setOnClickListener(this);


    }
    private void addUser(){
        String username =getUsername.getText().toString().trim();
        String phone = getPhone.getText().toString().trim();
        String location = getLoc.getSelectedItem().toString();
        DatabaseReference myRef = databaseUsers.getReference("user");


        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(phone))
        {
            Users users = new Users(username, phone, location);
            FirebaseUser user= mAuth.getCurrentUser();
            myRef.child(user.getUid()).setValue(users);
            Toast.makeText(this,"Info Saved", Toast.LENGTH_LONG).show();

        }

        else
        {
            Toast.makeText(this,"Fill in details", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View view){
        if(view == addDetails)
        {
            addUser();
            finish();
            startActivity(new Intent(this, edit_Library.class));
        }
    }
}
