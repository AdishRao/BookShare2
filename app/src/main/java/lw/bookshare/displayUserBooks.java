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
    DatabaseReference myRef = databaseViewbooks.getReference("Books");

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
                checkBooks(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void checkBooks(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String bid=ds.getKey();
            existingBooks eBooks = new existingBooks();
            eBooks = ds.getValue(existingBooks.class);

            existingBookses.add(eBooks);
        }

        Booklist adapter = new Booklist(displayUserBooks.this,existingBookses);
        listViewBooks.setAdapter(adapter);
    }
}
