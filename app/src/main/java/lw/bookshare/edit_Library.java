package lw.bookshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static lw.bookshare.R.id.buttonLogout;

public class edit_Library extends AppCompatActivity implements View.OnClickListener {
    Button add_Book;
    Button rmv_Book;
    Button view_Lib;
    EditText btitle;
    EditText bauthor;
    private FirebaseAuth mAuth;
    FirebaseDatabase databaseAddbooks = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__library);
            mAuth = FirebaseAuth.getInstance();
            add_Book =(Button) findViewById(R.id.add_Book);
            rmv_Book =(Button) findViewById(R.id.rmv_Book);
            view_Lib =(Button) findViewById(R.id.view_Lib);
            btitle =(EditText) findViewById(R.id.bTitle);
            bauthor =(EditText) findViewById(R.id.bAuthor);
            add_Book.setOnClickListener(this);
            rmv_Book.setOnClickListener(this);
            view_Lib.setOnClickListener(this);
    }

    private void addBook() {
        String title =btitle.getText().toString().trim();
        String author= bauthor.getText().toString().trim();

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(author))
        {
            DatabaseReference myRef = databaseAddbooks.getReference("Books");
            FirebaseUser user = mAuth.getCurrentUser();
           String bid= myRef.push().getKey();

            userBooksAdd books = new userBooksAdd(title, author);
            myRef.child(bid).setValue(books);
            myRef.child(bid).child("Users").setValue(user.getUid());
            Toast.makeText(this,"Added to library",Toast.LENGTH_LONG).show();

        }


        else
        {
            Toast.makeText(this,"Enter title and author",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view){
        if(view == add_Book)
        {
            addBook();
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
