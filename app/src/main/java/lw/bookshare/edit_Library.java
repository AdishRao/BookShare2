package lw.bookshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static lw.bookshare.R.id.buttonLogout;

public class edit_Library extends AppCompatActivity implements View.OnClickListener {
    Button add_Book;
    Button rmv_Book;
    Button view_Lib;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__library);


            add_Book =(Button) findViewById(R.id.add_Book);
            rmv_Book =(Button) findViewById(R.id.rmv_Book);
            view_Lib =(Button) findViewById(R.id.view_Lib);
            add_Book.setOnClickListener(this);
            rmv_Book.setOnClickListener(this);
            view_Lib.setOnClickListener(this);




    }




    @Override
    public void onClick(View view){
        if(view == add_Book)
        {
            // Fill
        }
        if(view == rmv_Book)
        {
            // fill
        }
        if(view == view_Lib)
        {
            // fill
        }
    }
}
