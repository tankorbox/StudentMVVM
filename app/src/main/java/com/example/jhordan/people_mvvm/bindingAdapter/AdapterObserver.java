package com.example.jhordan.people_mvvm.bindingAdapter;

import java.util.List;

/**
 * Created by hoanglinhtan on 07/11/2017.
 */

public interface AdapterObserver<T> {

    void setItems(List<T> items);

    List<T> getItems();

}
