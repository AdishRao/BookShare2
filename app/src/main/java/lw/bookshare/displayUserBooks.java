package lw.bookshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class displayUserBooks extends AppCompatActivity {

    ListView listViewBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_books);



        listViewBooks= (ListView) findViewById(R.id.listViewBooks);

    }
}
