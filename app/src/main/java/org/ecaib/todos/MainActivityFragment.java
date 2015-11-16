package org.ecaib.todos;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.ecaib.todos.provider.notes.NotesColumns;
import org.ecaib.todos.provider.notes.NotesSelection;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

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

        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        NotesSelection where = new NotesSelection();
        return new CursorLoader(getContext(), where.uri(), null, where.sel(), where.args(), null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

}
