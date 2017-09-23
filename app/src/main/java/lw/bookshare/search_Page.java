package lw.bookshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__page);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("user");
        FirebaseUser user = mAuth.getCurrentUser();
        getUserbook = (EditText) findViewById(R.id.getBookname); ///
        search =(Button) findViewById(R.id.searchButton);

        listViewUsers = (ListView) findViewById(R.id.listuser);
        existingUserss = new ArrayList<>();

        search.setOnClickListener(this);


    }

    protected void displayuser() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showUsers(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showUsers(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            existingUsers eBooks = new existingUsers();
            eBooks = ds.getValue(existingUsers.class);
            existingUserss.add(eBooks);
        }

        searched_page adapter = new searched_page(search_Page.this,existingUserss);
        listViewUsers.setAdapter(adapter);
        }

    @Override
    public void onClick(View view){

        if(view == search)
        {
            displayuser();
        }

    }
}



