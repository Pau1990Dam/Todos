package org.ecaib.todos.provider.notes;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.ecaib.todos.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code notes} table.
 */
public class NotesCursor extends AbstractCursor implements NotesModel {
    public NotesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(NotesColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(NotesColumns.TITLE);
        return res;
    }

    /**
     * Get the {@code description} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDescription() {
        String res = getStringOrNull(NotesColumns.DESCRIPTION);
        return res;
    }
}
