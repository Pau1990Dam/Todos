package org.ecaib.todos.provider.notes;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.ecaib.todos.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code notes} table.
 */
public class NotesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return NotesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable NotesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable NotesSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public NotesContentValues putTitle(@Nullable String value) {
        mContentValues.put(NotesColumns.TITLE, value);
        return this;
    }

    public NotesContentValues putTitleNull() {
        mContentValues.putNull(NotesColumns.TITLE);
        return this;
    }

    public NotesContentValues putDescription(@Nullable String value) {
        mContentValues.put(NotesColumns.DESCRIPTION, value);
        return this;
    }

    public NotesContentValues putDescriptionNull() {
        mContentValues.putNull(NotesColumns.DESCRIPTION);
        return this;
    }
}
