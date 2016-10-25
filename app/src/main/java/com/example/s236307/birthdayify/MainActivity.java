package com.example.s236307.birthdayify;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.s236307.birthdayify.fragments.RegisterContactFragment;

public class MainActivity extends Activity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RegisterContactFragment registerContactFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        fragmentManager = getFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        RegisterContactFragment registerContactFragment = new RegisterContactFragment();
//        fragmentTransaction.replace(android.R.id.content, registerContactFragment);
//        fragmentTransaction.commit();
    }

}
