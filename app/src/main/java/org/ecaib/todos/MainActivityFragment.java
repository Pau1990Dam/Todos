package org.ecaib.todos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.ecaib.todos.provider.notes.NotesColumns;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private SimpleCursorAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvTodos = (ListView) view.findViewById(R.id.lvTodos);

        adapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.notes_row,
                null,
                new String[] { NotesColumns.TITLE },
                new int[] { R.id.tvTitle },
                0
        );

        lvTodos.setAdapter(adapter);

        lvTodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("item_id", id);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private void refresh() {
        Cursor cursor = getContext().getContentResolver().query(
                NotesColumns.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        adapter.swapCursor(cursor);
    }
}
