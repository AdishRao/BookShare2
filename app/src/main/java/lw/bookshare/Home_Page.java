package lw.bookshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home_Page extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
 Button btn_search;
    Button btn_edit;

    private Button buttonLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        btn_search =(Button) findViewById(R.id.btn_search);
        btn_edit=(Button) findViewById(R.id.btn_edit);
        btn_search.setOnClickListener(this);
        btn_edit.setOnClickListener(this);

        buttonLogout =(Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);


    }
    @Override
    public void onClick(View view){
        if(view == buttonLogout)
        {
            mAuth.signOut();
            finish();
            startActivity(new Intent(this,Login_Page.class));
        }
        if(view==btn_search)
        {
            finish();
            startActivity(new Intent(this,edit_Library.class));
        }

        if(view==btn_edit)
        {
            finish();
            startActivity(new Intent(this,edit_Library.class));
        }

    }
}
