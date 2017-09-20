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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.author;
import static lw.bookshare.R.id.buttonLogout;

public class edit_Library extends AppCompatActivity implements View.OnClickListener {
    Button add_Book;
    Button rmv_Book;
    Button view_Lib;
    EditText btitle;
    EditText bauthor;
    private FirebaseAuth mAuth;
    FirebaseDatabase databaseAddbooks = FirebaseDatabase.getInstance();
    String UserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__library);
            mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        add_Book =(Button) findViewById(R.id.add_Book);
            rmv_Book =(Button) findViewById(R.id.rmv_Book);
            view_Lib =(Button) findViewById(R.id.view_Lib);
            btitle =(EditText) findViewById(R.id.bTitle);
            bauthor =(EditText) findViewById(R.id.bAuthor);
            add_Book.setOnClickListener(this);
            rmv_Book.setOnClickListener(this);
            view_Lib.setOnClickListener(this);

        DatabaseReference myRef = databaseAddbooks.getReference("Books");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                checkBooks(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void addBook() {
        String title =btitle.getText().toString().trim();
        String author= bauthor.getText().toString().trim();
        DatabaseReference myRef = databaseAddbooks.getReference("Books");


        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(author))
        {
            FirebaseUser user = mAuth.getCurrentUser();
            String bid= myRef.push().getKey();
            userBooksAdd books = new userBooksAdd(title, author, "true");
            myRef.child(bid).setValue(books);
            myRef.child(bid).child("users").child("UID").setValue(user.getUid());
            Toast.makeText(this,"Added to library",Toast.LENGTH_LONG).show();
        }


        else
        {
            Toast.makeText(this,"Enter title and author",Toast.LENGTH_LONG).show();
        }

    }

    private void checkBooks(DataSnapshot dataSnapshot) {
        String title =btitle.getText().toString().trim();
        String author= bauthor.getText().toString().trim();
        FirebaseUser user = mAuth.getCurrentUser();
        for (DataSnapshot ds: dataSnapshot.getChildren() ){
              existingBooks eBooks = new existingBooks();
             String bid=ds.getKey();
            eBooks.setAuthor(ds.child(bid).getValue(existingBooks.class).getAuthor()); //Gets Author
             eBooks.setAuthor(ds.child(bid).getValue(existingBooks.class).getTitle()); //Gets Title
             Toast.makeText(this,eBooks.getAuthor(),Toast.LENGTH_SHORT).show();
             Toast.makeText(this,eBooks.getTitle(),Toast.LENGTH_SHORT).show();



            // if(eBooks.getAuthor()==author && eBooks.getTitle()== title){
               // Map<String, Object> childUpdates = new HashMap<>();
                //childUpdates.put("UID", user.getUid());
                //myRef.child(ds.getKey()).child("users").child("UID").updateChildren(childUpdates);
           // }

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
