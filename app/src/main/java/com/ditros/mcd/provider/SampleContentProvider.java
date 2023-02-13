package com.ditros.mcd.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ditros.mcd.app.AppConfig;
import org.jetbrains.annotations.NotNull;

public class SampleContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.ditros.mcd";


    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CODE_TYPEROUTE_DIR = 1;

    static {
            MATCHER.addURI(AUTHORITY, AppConfig.TABLE_TYPEROUTE, CODE_TYPEROUTE_DIR);
         }

        public SampleContentProvider() {
        }


        @Override
        public boolean onCreate() {
            return true;
        }

        @Nullable
        @Override
        public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                            @Nullable String[] selectionArgs, @Nullable String sortOrder) {

            return null;
        }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public String getType(@NonNull @NotNull Uri uri) {
        return null;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public Uri insert(@NonNull @NotNull Uri uri, @Nullable @org.jetbrains.annotations.Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull @NotNull Uri uri, @Nullable @org.jetbrains.annotations.Nullable String s, @Nullable @org.jetbrains.annotations.Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull @NotNull Uri uri, @Nullable @org.jetbrains.annotations.Nullable ContentValues contentValues, @Nullable @org.jetbrains.annotations.Nullable String s, @Nullable @org.jetbrains.annotations.Nullable String[] strings) {
        return 0;
    }
}
