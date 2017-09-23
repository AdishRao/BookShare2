package lw.bookshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class displayUserBooks extends AppCompatActivity {

    ListView listViewBooks;
    List<existingBooks> existingBookses;
    private FirebaseAuth mAuth;
    FirebaseDatabase databaseViewbooks = FirebaseDatabase.getInstance();
    DatabaseReference myRef = databaseViewbooks.getReference();
    String bookids[] = new String[100];
    int i=0,f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_books);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        listViewBooks= (ListView) findViewById(R.id.listViewBooks);
        existingBookses = new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getUserbooks(dataSnapshot);
                checkBooks(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getUserbooks(DataSnapshot dataSnapshot) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        for(DataSnapshot ds : dataSnapshot.child("user").child(user.getUid()).child("books").getChildren())
        {
            bookids[i]=ds.getKey();
            i++;
            f=i;
        }
    }

    private void checkBooks(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.child("Books").getChildren()) {
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            existingBooks eBooks = new existingBooks();
             eBooks = ds.getValue(existingBooks.class);
             for(i=0;i<f;i++) {
                 if(bookids[i].equals(ds.getKey()))
                 existingBookses.add(eBooks);
             }
        }

        Booklist adapter = new Booklist(displayUserBooks.this,existingBookses);
        listViewBooks.setAdapter(adapter);
    }
}
