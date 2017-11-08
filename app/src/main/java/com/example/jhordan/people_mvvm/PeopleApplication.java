/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jhordan.people_mvvm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.jhordan.people_mvvm.data.PeopleFactory;
import com.example.jhordan.people_mvvm.data.PeopleService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class PeopleApplication extends Application {

  private PeopleService peopleService;
  private Scheduler scheduler;

  private static PeopleApplication get(Context context) {
    Log.i("tag1",PeopleApplication.class.getName()+ " constructor");
    return (PeopleApplication) context.getApplicationContext();
  }

  public static PeopleApplication create(Context context) {
    Log.i("tag1",PeopleApplication.class.getName()+ " create PeopleApplication instance");

    return PeopleApplication.get(context);
  }

  public PeopleService getPeopleService() {
    Log.i("tag1",PeopleApplication.class.getName()+ " get people service");

    if (peopleService == null) {
      peopleService = PeopleFactory.create();
    }

    return peopleService;
  }

  public Scheduler subscribeScheduler() {
    Log.i("tag1",PeopleApplication.class.getName()+ " subscribe scheduler");

    if (scheduler == null) {
      scheduler = Schedulers.io();
    }

    return scheduler;
  }

  public void setPeopleService(PeopleService peopleService) {
    Log.i("tag1",PeopleApplication.class.getName()+ " set people service");

    this.peopleService = peopleService;
  }

  public void setScheduler(Scheduler scheduler) {
    Log.i("tag1",PeopleApplication.class.getName()+ " set scheduler");

    this.scheduler = scheduler;
  }
}
