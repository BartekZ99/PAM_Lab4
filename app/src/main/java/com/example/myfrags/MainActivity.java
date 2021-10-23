package com.example.myfrags;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PrimitiveIterator;

public class MainActivity extends FragmentActivity implements Fragment1.OnButtonClickListener{

   // private FragmentManager fragmentManager;
   // private Fragment fragment1, fragment2, fragment3, fragment4;

    private int[] frames;
    private boolean hiden;
    private int[] sequence; //tablica przechowująca sekwencję fragmentów

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            frames = new int[]{R.id.frame1, R.id.frame2, R.id.frame3, R.id.frame4};
            hiden = false;

            sequence = new int[]{0,1,2,3}; //początkowa sekwencja fragmentów

            Fragment[] fragments = new Fragment[]{new Fragment1(), new Fragment2(), new Fragment3(), new Fragment4()};
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            for(int i = 0; i < 4; i++){
                transaction.add(frames[i], fragments[i]);
            }
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            frames = savedInstanceState.getIntArray("FRAMES");
            hiden = savedInstanceState.getBoolean("HIDEN");

            sequence = savedInstanceState.getIntArray("SEQUENCE");
        }
    }

    private void newFragments(){
        Fragment[] newFragments = new Fragment[]{new Fragment1(), new Fragment2(), new Fragment3(), new Fragment4()};

        Fragment[] inSequence = new Fragment[]{newFragments[sequence[0]], newFragments[sequence[1]], newFragments[sequence[2]], newFragments[sequence[3]]};
        newFragments = inSequence;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for(int i = 0; i < 4; i++){
            transaction.replace(frames[i], newFragments[i]);
            if(hiden && !(newFragments[i] instanceof Fragment1)) transaction.hide(newFragments[i]);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putIntArray("FRAMES", frames);
        outState.putBoolean("HIDEN", hiden);

        outState.putIntArray("SEQUENCE", sequence);
    }

    @Override
    public void onButtonClickShuffle() {
       /* List<Integer> list = new ArrayList<Integer>(Arrays.asList(frames[0], frames[1], frames[2], frames[3]));
        Collections.shuffle(list);
        for(int i = 0; i < 4; i++){
            frames[i] = list.get(i).intValue();
        }
        newFragments();*/
        //Toast.makeText(getApplicationContext(), "Shuffle", Toast.LENGTH_SHORT).show();

        List<Integer> s = new ArrayList<>(Arrays.asList(sequence[0], sequence[1], sequence[2], sequence[3]));
        Collections.shuffle(s);
        for(int i = 0; i < 4; i++) sequence[i] = s.get(i);
        newFragments();
    }

    @Override
    public void onButtonClickClockwise() {
        int t = frames[0];
        frames[0] = frames[1];
        frames[1] = frames[2];
        frames[2] = frames[3];
        frames[3] = t;

        newFragments();

        //Toast.makeText(getApplicationContext(), "Clockwise", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClickHide() {
        if(hiden) return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        for(Fragment f : fragmentManager.getFragments()){
            if(f instanceof Fragment1) continue;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(f);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        hiden = true;

        //Toast.makeText(getApplicationContext(), "Hide", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClickRestore() {
        if(!hiden) return;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for(Fragment f : fragmentManager.getFragments()){
            if(f instanceof Fragment1) continue;
            transaction.show(f);
        }
        transaction.addToBackStack(null);
        transaction.commit();

        hiden = false;

       // Toast.makeText(getApplicationContext(), "Restore", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment){
        super.onAttachFragment(fragment);

        if(fragment instanceof Fragment1){
            ((Fragment1) fragment).setOnButtonClickListener(this);
        }
    }
}
