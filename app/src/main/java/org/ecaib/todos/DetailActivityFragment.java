package org.ecaib.todos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ecaib.todos.provider.notes.NotesColumns;
import org.ecaib.todos.provider.notes.NotesContentValues;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private TextView etTitle;
    private TextView etDescription;
    private long itemId = -1;

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

        Intent i = getActivity().getIntent();
        itemId = i.getLongExtra("item_id", -1);
        if (itemId != -1) {//Si da -1 es xq el intent no había sido creado antes
            loadItem();
        }

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
        if (id == R.id.miOK) {
            saveItem();
            getActivity().finish();
        }

        if (id == R.id.miCancel) {
            if(itemId != -1) {
                deleteItem();
            }
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveItem() {//Gestiona la funcion de actualizar y guardar
        NotesContentValues values = new NotesContentValues();//Creamos el objeto q vamos a guardar. El primo del cursor. Es para meter info
        values.putTitle(etTitle.getText().toString());
        values.putDescription(etDescription.getText().toString());

        if (itemId == -1) {
            insertItem(values);
        } else {
            updateItem(values);
        }
    }

    private void loadItem() {
        Cursor cursor = getContext().getContentResolver().query(
                NotesColumns.CONTENT_URI,
                null,
                NotesColumns._ID + " = ?",//Esto es para substituir los caracteres no conocidos o especiales xa ejecutar comandos por interrogante
                new String[]{String.valueOf(itemId)},
                null
        );

        if (cursor != null) {//Si el cursor me ha devuelto algo...
            //Ens situem en el primer valor q  es -1, es decir desde antes de los resultados.
            cursor.moveToNext();//si no se hace esto x lo expuesto antes el programa casca

            String title = cursor.getString(cursor.getColumnIndex(NotesColumns.TITLE));//Pilla el indice en formato string
            etTitle.setText(title);
            String description = cursor.getString(cursor.getColumnIndex(NotesColumns.DESCRIPTION));
            etDescription.setText(description);
        }
    }

    private void updateItem(NotesContentValues values) {
        getContext().getContentResolver().update(
                NotesColumns.CONTENT_URI,
                values.values(),
                NotesColumns._ID + " = ?",
                new String[]{String.valueOf(itemId)});
    }

    private void insertItem(NotesContentValues values) {
        getContext().getContentResolver().insert(
                NotesColumns.CONTENT_URI,
                values.values());
    }

    private void deleteItem() {
        getContext().getContentResolver().delete(
                NotesColumns.CONTENT_URI,
                NotesColumns._ID + " = ?",
                new String[]{String.valueOf(itemId)}
        );
    }
}
