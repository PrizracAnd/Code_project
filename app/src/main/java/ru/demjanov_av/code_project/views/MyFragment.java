package ru.demjanov_av.code_project.views;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;

import ru.demjanov_av.code_project.presenters.MainView;
import ru.demjanov_av.code_project.views.dialogs.FragmentDialogYesNo;

/**
 * Created by demjanov on 01.02.2019.
 */

public abstract class MyFragment extends Fragment implements MainView, FragmentDialogYesNo.BackDialogYN{

    /////////////////////////////////////////////////////
    // Methods of interfaces
    ////////////////////////////////////////////////////
    //-----Begin-----------------------------------------
    //-----Begin methods of MainView---------------------
    @Override
    public void startLoad() {

    }

    @Override
    public void endLoad() {

    }

    @Override
    public void setError(int number, @Nullable String message) {

    }

    @Override
    public void setData(int dataType) {

    }
    //-----End methods of MainView-----------------------

    //-----Begin methods of BackDialogYN-----------------
    @Override
    public void onBackDYN(boolean yes) {

    }
    //-----End methods of BackDialogYN-------------------
    //-----End-------------------------------------------
}
