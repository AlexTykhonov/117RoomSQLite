package com.tae.a117roomsqlite.repository;

import android.support.v7.util.DiffUtil;

import com.tae.a117roomsqlite.DATA.User;

import java.util.List;

public class ProductDiffUtilCallback extends DiffUtil.Callback {
 
   private final List<User> oldList;
   private final List<User> newList;
 
   public ProductDiffUtilCallback(List<User> oldList, List<User> newList) {
       this.oldList = oldList;
       this.newList = newList;
   }
 
   @Override
   public int getOldListSize() {
       return oldList.size();
   }
 
   @Override
   public int getNewListSize() {
       return newList.size();
   }
 
   @Override
   public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
       User oldProduct = oldList.get(oldItemPosition);
       User newProduct = newList.get(newItemPosition);
       return oldProduct.getId() == newProduct.getId();
   }
 
   @Override
   public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
       User oldUser = oldList.get(oldItemPosition);
       User newUser = newList.get(newItemPosition);
       return oldUser.getName().equals(newUser.getName())
               && oldUser.getYear() == newUser.getYear();
   }
}