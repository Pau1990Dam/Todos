package org.ecaib.todos.provider.notes;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import org.ecaib.todos.provider.base.AbstractSelection;

/**
 * Selection for the {@code notes} table.
 */
public class NotesSelection extends AbstractSelection<NotesSelection> {
    @Override
    protected Uri baseUri() {
        return NotesColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code NotesCursor} object, which is positioned before the first entry, or null.
     */
    public NotesCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new NotesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public NotesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code NotesCursor} object, which is positioned before the first entry, or null.
     */
    public NotesCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new NotesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public NotesCursor query(Context context) {
        return query(context, null);
    }


    public NotesSelection id(long... value) {
        addEquals("notes." + NotesColumns._ID, toObjectArray(value));
        return this;
    }

    public NotesSelection idNot(long... value) {
        addNotEquals("notes." + NotesColumns._ID, toObjectArray(value));
        return this;
    }

    public NotesSelection orderById(boolean desc) {
        orderBy("notes." + NotesColumns._ID, desc);
        return this;
    }

    public NotesSelection orderById() {
        return orderById(false);
    }

    public NotesSelection title(String... value) {
        addEquals(NotesColumns.TITLE, value);
        return this;
    }

    public NotesSelection titleNot(String... value) {
        addNotEquals(NotesColumns.TITLE, value);
        return this;
    }

    public NotesSelection titleLike(String... value) {
        addLike(NotesColumns.TITLE, value);
        return this;
    }

    public NotesSelection titleContains(String... value) {
        addContains(NotesColumns.TITLE, value);
        return this;
    }

    public NotesSelection titleStartsWith(String... value) {
        addStartsWith(NotesColumns.TITLE, value);
        return this;
    }

    public NotesSelection titleEndsWith(String... value) {
        addEndsWith(NotesColumns.TITLE, value);
        return this;
    }

    public NotesSelection orderByTitle(boolean desc) {
        orderBy(NotesColumns.TITLE, desc);
        return this;
    }

    public NotesSelection orderByTitle() {
        orderBy(NotesColumns.TITLE, false);
        return this;
    }

    public NotesSelection description(String... value) {
        addEquals(NotesColumns.DESCRIPTION, value);
        return this;
    }

    public NotesSelection descriptionNot(String... value) {
        addNotEquals(NotesColumns.DESCRIPTION, value);
        return this;
    }

    public NotesSelection descriptionLike(String... value) {
        addLike(NotesColumns.DESCRIPTION, value);
        return this;
    }

    public NotesSelection descriptionContains(String... value) {
        addContains(NotesColumns.DESCRIPTION, value);
        return this;
    }

    public NotesSelection descriptionStartsWith(String... value) {
        addStartsWith(NotesColumns.DESCRIPTION, value);
        return this;
    }

    public NotesSelection descriptionEndsWith(String... value) {
        addEndsWith(NotesColumns.DESCRIPTION, value);
        return this;
    }

    public NotesSelection orderByDescription(boolean desc) {
        orderBy(NotesColumns.DESCRIPTION, desc);
        return this;
    }

    public NotesSelection orderByDescription() {
        orderBy(NotesColumns.DESCRIPTION, false);
        return this;
    }
}
