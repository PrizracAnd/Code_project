package ru.demjanov_av.code_project.views.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.demjanov_av.code_project.R;
import ru.demjanov_av.code_project.views.MyFragment;

/**
 * Created by demjanov on 01.02.2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDialogYesNo extends DialogFragment implements DialogInterface.OnClickListener {

    //-----Class variables begin-------------------------
    private MyFragment fragment;
    private String title;
    private String message;
    //-----Class variables begin-------------------------


    /////////////////////////////////////////////////////
    // Interface BackDialogYN
    ////////////////////////////////////////////////////
    public interface BackDialogYN{
        void onBackDYN(boolean yes);
    }


    /////////////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////////////
    @SuppressLint("ValidFragment")
    public FragmentDialogYesNo(MyFragment target, @Nullable String title, @Nullable String message) {
        this.fragment = target;
        this.title = title;
        this.message = message;
    }


    /////////////////////////////////////////////////////
    // Getters and Setters
    ////////////////////////////////////////////////////
    //-----Begin----------------------------------------
//    public void setTarget(MyFragment target) {
//        this.fragment = fragment;
//    }
    //-----End------------------------------------------

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle(this.title)
                .setPositiveButton(R.string.yes, this)
                .setNegativeButton(R.string.no, this)
                .setMessage(this.message);
        return adb.create();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                this.fragment.onBackDYN(true);
                break;
            case Dialog.BUTTON_NEGATIVE:
                this.fragment.onBackDYN(false);
                break;
            default:
                this.fragment.onBackDYN(false);
                break;
        }
        dismiss();
    }


    @Override
    public void onCancel(DialogInterface dialogInterface){
        super.onCancel(dialogInterface);
        this.fragment.onBackDYN(false);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface){
        super.onDismiss(dialogInterface);
    }
}
