package lw.bookshare;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adish on 22/09/17.
 */

public class Booklist extends ArrayAdapter<existingBooks> {
private Activity context;
    private List<existingBooks> booksList;

    public Booklist (Activity context, List<existingBooks> booksList){
        super(context, R.layout.my_lib,booksList);
                this.context=context;
                this.booksList=booksList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.my_lib,null,true);

        TextView bookTitle = (TextView) listViewItem.findViewById(R.id.bookTitle);
        TextView bookAuthor = (TextView) listViewItem.findViewById(R.id.bookAuthor);

        existingBooks existingBookss = booksList.get(position);
        bookTitle.setText(existingBookss.getTitle());
        bookAuthor.setText(existingBookss.getAuthor());

        return listViewItem;
    }
}
