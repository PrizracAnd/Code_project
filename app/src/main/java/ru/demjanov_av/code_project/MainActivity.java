package ru.demjanov_av.code_project;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.demjanov_av.code_project.views.CryptoTestFragment;
import ru.demjanov_av.code_project.views.PswFragment;

public class MainActivity extends AppCompatActivity implements PswFragment.EnterCodeSuccess {
    //-----Class variables begin-------------------------
    private FragmentManager fm;
    //-----Class variables end-------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeElements();
    }

    /////////////////////////////////////////////////////
    // Method initializeElements
    ////////////////////////////////////////////////////
    private void initializeElements() {
        fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment = new PswFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }


    /////////////////////////////////////////////////////
    // Methods of interfaces
    ////////////////////////////////////////////////////
    //-----Begin-----------------------------------------
    @Override
    public void onEnterCode(byte[] keySpecBytes) {
        CryptoTestFragment ctf = new CryptoTestFragment();

        fm.beginTransaction()
                .replace(R.id.fragment_container, ctf)
                .commit();

        ctf.setKeySpecBytes(keySpecBytes);
    }
    //-----End-------------------------------------------
}
