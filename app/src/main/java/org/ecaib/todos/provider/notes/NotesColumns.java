package org.ecaib.todos.provider.notes;

import android.net.Uri;
import android.provider.BaseColumns;

import org.ecaib.todos.provider.NotesProvider;
import org.ecaib.todos.provider.notes.NotesColumns;

/**
 * Columns for the {@code notes} table.
 */
public class NotesColumns implements BaseColumns {
    public static final String TABLE_NAME = "notes";
    public static final Uri CONTENT_URI = Uri.parse(NotesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TITLE = "title";

    public static final String DESCRIPTION = "description";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TITLE,
            DESCRIPTION
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(DESCRIPTION) || c.contains("." + DESCRIPTION)) return true;
        }
        return false;
    }

}
