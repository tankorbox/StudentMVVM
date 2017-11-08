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

package com.example.jhordan.people_mvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jhordan.people_mvvm.R;
import com.example.jhordan.people_mvvm.databinding.ItemPeopleBinding;
import com.example.jhordan.people_mvvm.model.People;
import com.example.jhordan.people_mvvm.recyclerAdapter.RecyclerViewAdapter;
import com.example.jhordan.people_mvvm.viewmodel.ItemPeopleViewModel;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerViewAdapter<People> {
  @Override public PeopleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemPeopleBinding itemPeopleBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people,
            parent, false);
    return new PeopleAdapterViewHolder(itemPeopleBinding);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    PeopleAdapterViewHolder viewHolder = (PeopleAdapterViewHolder) holder;
    viewHolder.bindPeople(getItems().get(position));
  }

  public static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
    ItemPeopleBinding mItemPeopleBinding;

    public PeopleAdapterViewHolder(ItemPeopleBinding itemPeopleBinding) {
      super(itemPeopleBinding.itemPeople);
      this.mItemPeopleBinding = itemPeopleBinding;
    }

    void bindPeople(People people) {
      if (mItemPeopleBinding.getPeopleViewModel() == null) {
        mItemPeopleBinding.setPeopleViewModel(
            new ItemPeopleViewModel(people, itemView.getContext()));
      } else {
        mItemPeopleBinding.getPeopleViewModel().setPeople(people);
      }
    }
  }
}
