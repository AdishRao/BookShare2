package lw.bookshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button Register_Button;
private EditText email_New;
private EditText password_New;
private TextView existing_user_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      Register_Button = (Button) findViewById(R.id.Register_Button);
      email_New = (EditText) findViewById(R.id.email_New);
      password_New = (EditText) findViewById(R.id.password_New);
      existing_user_signin = (TextView) findViewById(R.id.existing_user_signin);

      Register_Button.setOnClickListener(this);
      existing_user_signin.setOnClickListener(this);

    }
    private void registerUser(){
        String email =
    }




    public void onClick(View view){
       if(view==Register_Button) {
           registerUser();
       }
       if(view==existing_user_signin) {

       }
    }
}
