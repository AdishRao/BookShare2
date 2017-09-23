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
 * Created by ashwi on 22-09-2017.
 */

public class searched_page extends ArrayAdapter<existingUsers> {

    private Activity context;
    private List<existingUsers> userList;

    public searched_page (Activity context, List<existingUsers> userList){
        super(context, R.layout.my_lib,userList);
        this.context=context;
        this.userList=userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.my_lib,null,true);

        TextView username = (TextView) listViewItem.findViewById(R.id.bookTitle);
        TextView phone = (TextView) listViewItem.findViewById(R.id.bookAuthor);

        existingUsers existingUserss = userList.get(position);
        username.setText(existingUserss.getUsername());
        phone.setText(existingUserss.getPhone());

        return listViewItem;
    }
}
