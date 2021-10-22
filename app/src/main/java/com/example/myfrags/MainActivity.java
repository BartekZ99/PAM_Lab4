package com.example.myfrags;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

import java.security.PrivateKey;
import java.util.PrimitiveIterator;

public class MainActivity extends FragmentActivity {

   // private FragmentManager fragmentManager;
   // private Fragment fragment1, fragment2, fragment3, fragment4;

    private int[] frames;
    private boolean hiden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            frames = new int[]{R.id.frame1, R.id.frame2, R.id.frame3, R.id.frame4};
            hiden = false;
        } else {
            frames = savedInstanceState.getIntArray("FRAMES");
            hiden = savedInstanceState.getBoolean("HIDEN");
        }

        /*
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame1, fragment1);
        transaction.add(R.id.frame2, fragment2);
        transaction.add(R.id.frame3, fragment3);
        transaction.add(R.id.frame4, fragment4);
        transaction.addToBackStack(null);
        transaction.commit();
        */

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putIntArray("FRAMES", frames);
        outState.putBoolean("HIDEN", hiden);
    }
}
