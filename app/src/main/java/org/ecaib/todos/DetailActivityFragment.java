package org.ecaib.todos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ecaib.todos.provider.NotesProvider;
import org.ecaib.todos.provider.notes.NotesColumns;
import org.ecaib.todos.provider.notes.NotesContentValues;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private TextView etTitle;
    private TextView etDescription;

    public DetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        etTitle = (TextView) view.findViewById(R.id.etTitle);
        etDescription = (TextView) view.findViewById(R.id.etDescription);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.miCancel) {
            getActivity().finish();
            return true;
        }

        if (id == R.id.miOK) {
            saveItem();
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveItem() {
        NotesContentValues values = new NotesContentValues();
        values.putTitle(etTitle.getText().toString());
        values.putDescription(etTitle.getText().toString());

        values.insert(getContext().getContentResolver());
    }

}
