package org.ecaib.todos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ListView lvTodos = (ListView) view.findViewById(R.id.toolbar);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getContext(),
                R.layout.notes_row,
                null,
                new String[] { "title" },
                new int[] { R.id.tvTitle },
                0
        );

        lvTodos.setAdapter(adapter);

        return view;
    }
}
