package lw.bookshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class search_Page extends AppCompatActivity implements View.OnClickListener {

    ListView listViewUsers;
    List <existingUsers> existingUserss;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private EditText getUserbook;
    private Button search;
    private ListView mListView;
    String Bid;
    String uidss[] = new String[100];
    int i=0,f;
    int found=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__page);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        getUserbook = (EditText) findViewById(R.id.getBookname); ///
        search =(Button) findViewById(R.id.searchButton);
        listViewUsers = (ListView) findViewById(R.id.listuser);
        existingUserss = new ArrayList<>();

        search.setOnClickListener(this);


    }


    protected void displayuser() {
        myRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                getBid(dataSnapshot);
                if(found==1)
                {
                    getUid(dataSnapshot);
                }
                showUsers(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getBid(DataSnapshot dataSnapshot) {
        String bookTitle = getUserbook.getText().toString().trim();

        for (DataSnapshot ds : dataSnapshot.child("Books").getChildren()) {
            existingBooks eBooks = new existingBooks();
            eBooks = ds.getValue(existingBooks.class);
            String title = eBooks.getTitle();
            if (title.equals(bookTitle)) {
                found = 1;
                Bid = ds.getKey();
            }

        }
    }

    private void getUid(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.child("Books").child(Bid).child("users").getChildren())
        {
            uidss[i]=ds.getKey();
            i++;
            f=i;
        }

    }


    private void showUsers(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds : dataSnapshot.child("user").getChildren()){
            i=0;
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            existingUsers eBooks = new existingUsers();
            eBooks = ds.getValue(existingUsers.class);
            for(i=0;i<f;i++) {
                if (ds.getKey().equals(uidss[i]))
                    existingUserss.add(eBooks);
            }


        }
        if(found==0) {
            Toast.makeText(this, "Book Not Found", Toast.LENGTH_SHORT).show();
        }
        searched_page adapter = new searched_page(search_Page.this,existingUserss);
        listViewUsers.setAdapter(adapter);
        for(i=0;i<f;i++)
        {
            uidss[i]="";
        }
        }

    @Override
    public void onClick(View view){

        if(view == search)
        {   f=0;
            existingUserss.clear();
            listViewUsers.setAdapter(null);
            displayuser();
        }
    }
}



