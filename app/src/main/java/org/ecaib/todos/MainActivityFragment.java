package org.ecaib.todos;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
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
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

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
                null,//cursor
                new String[] { NotesColumns.TITLE },//campos
                new int[] { R.id.tvTitle },//donde tiene que ir el campo
                0//Más tarde lo sabremos
        );

        lvTodos.setAdapter(adapter);

        lvTodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("item_id", id);//Pasamos el id de la fila
                startActivity(i);
            }
        });

        //inicializamos el Loader
        getLoaderManager().initLoader(0,null,this);//El this es xa q modifique daties en este fragment
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private void refresh() {
        /*
        Cursor cursor = getContext().getContentResolver().query(
                NotesColumns.CONTENT_URI,//tabla
                null,// Equivalente al Select (columna)
                null,//Equivalente al Where
                null,
                null//Order
        );
        adapter.swapCursor(cursor);//usa este cursor
        */
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new android.support.v4.content.CursorLoader(getContext(),
                NotesColumns.CONTENT_URI,//tabla
                null,// Equivalente al Select (columna)
                null,//Equivalente al Where
                null,
                null//Order
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader,Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
