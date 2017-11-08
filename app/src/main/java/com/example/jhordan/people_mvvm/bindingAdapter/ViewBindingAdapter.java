package com.example.jhordan.people_mvvm.bindingAdapter;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by hoanglinhtan on 07/11/2017.
 */

public class ViewBindingAdapter {

    @BindingAdapter({"isVisibleOrGone"})
    public static void setVisibleOrGone(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"isVisibleOrInvisible"})
    public static void setVisibleOrInvisible(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }
}
