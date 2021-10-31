package com.example.myfrags;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

public class FragsData extends ViewModel{
    public final MutableLiveData<Integer> counter = new MutableLiveData<>(0);
}
