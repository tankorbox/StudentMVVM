/**
 * Copyright 2016 Erik Jhordan Rey. <p/> Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at <p/> http://www.apache.org/licenses/LICENSE-2.0 <p/> Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package com.example.jhordan.people_mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.jhordan.people_mvvm.PeopleApplication;
import com.example.jhordan.people_mvvm.R;
import com.example.jhordan.people_mvvm.data.PeopleFactory;
import com.example.jhordan.people_mvvm.data.PeopleResponse;
import com.example.jhordan.people_mvvm.data.PeopleService;
import com.example.jhordan.people_mvvm.model.People;

import java.nio.channels.NetworkChannel;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.schedulers.Schedulers;

public class PeopleViewModel {

  private ObservableBoolean isPeopleLoading = new ObservableBoolean(false);
  public ObservableInt peopleRecycler;
  public ObservableInt peopleLabel;
  public ObservableField<String> messageLabel;

  private ObservableList<People> peoples = new ObservableArrayList<>();
  private Context context;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public ObservableBoolean isPeopleLoading() {
    return isPeopleLoading;
  }

  public void setPeopleLoading(boolean isPeopleLoading) {
    this.isPeopleLoading.set(isPeopleLoading);
  }



  public ObservableList<People> getPeoples() {
    return peoples;
  }

  public PeopleViewModel(@NonNull Context context) {
    Log.i("tag1", PeopleViewModel.class.getName()+" - people viewmodel");
    this.context = context;
    peopleRecycler = new ObservableInt(View.GONE);
    peopleLabel = new ObservableInt(View.VISIBLE);
    messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
  }

  public void onClickFabLoad(View view) {
    Log.i("tag1", PeopleViewModel.class.getName()+" -on fab clicked");
    initializeViews();
    fetchPeopleList();
  }

  //It is "public" to show an example of test
  public void initializeViews() {
    Log.i("tag1", PeopleViewModel.class.getName()+" - init view");
    messageLabel.set("Empty!!");
    peopleLabel.set(View.GONE);
    peopleRecycler.set(View.GONE);
    setPeopleLoading(true);
  }

  private void fetchPeopleList() {

    Log.i("tag1", PeopleViewModel.class.getName()+" -fetch people");

    PeopleService peopleService = PeopleApplication.create(context).getPeopleService();


    Disposable disposable = peopleService.fetchPeople(PeopleFactory.RANDOM_USER_URL)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<PeopleResponse>() {
          @Override public void accept(PeopleResponse peopleResponse) throws Exception {
            changePeopleDataSet(peopleResponse.getPeopleList());
            setPeopleLoading(false);
            peopleLabel.set(View.GONE);
            peopleRecycler.set(View.VISIBLE);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            messageLabel.set(context.getString(R.string.error_loading_people));
            setPeopleLoading(false);
            peopleLabel.set(View.VISIBLE);
            peopleRecycler.set(View.GONE);
          }
        });

    compositeDisposable.add(disposable);
  }

  private void changePeopleDataSet(List<People> peoples) {
    this.peoples.addAll(peoples);
  }

  private void unSubscribeFromObservable() {
    Log.i("tag1", PeopleViewModel.class.getName()+" - unSubcribeFromObservable");
    if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }

  public void reset() {
    Log.i("tag1", PeopleViewModel.class.getName()+" - reset");
    unSubscribeFromObservable();
    compositeDisposable = null;
    context = null;
  }
}
