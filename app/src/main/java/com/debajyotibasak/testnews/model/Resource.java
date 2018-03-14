package com.debajyotibasak.testnews.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.debajyotibasak.testnews.model.Status.ERROR;
import static com.debajyotibasak.testnews.model.Status.LOADING;
import static com.debajyotibasak.testnews.model.Status.SUCCESS;

/**
 * Created by debajyotibasak on 12/03/18.
 */

public class Resource<T> {
    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    public Resource(@NonNull Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }


}
