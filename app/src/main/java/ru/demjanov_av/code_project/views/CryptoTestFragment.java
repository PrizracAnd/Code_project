package ru.demjanov_av.code_project.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.demjanov_av.code_project.R;
import ru.demjanov_av.code_project.presenters.CryptoTestPresenter;

/**
 * Created by demjanov on 06.02.2019.
 */

public class CryptoTestFragment extends MyFragment {
    //-----Class variables begin-------------------------
    private View view;
    private CryptoTestPresenter presenter = null;
    //-----Class variables begin-------------------------


    //-----View elements variables begin-----------------
    @BindView(R.id.open_text)
    EditText textOpen;

    @BindView(R.id.encrypt_text)
    TextView textEncrypted;

    @BindView(R.id.code_text_0)
    TextView textDecrypted;
    //-----View elements variables end-------------------


    /////////////////////////////////////////////////////
    // Method onCreateView
    ////////////////////////////////////////////////////
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        this.view = inflater.inflate(R.layout.fragment_psw, viewGroup, false);
        ButterKnife.bind(this, view);
        return view;
    }


    /////////////////////////////////////////////////////
    // Method initializeElements
    ////////////////////////////////////////////////////
    private void initializeElements(View view) {
        ButterKnife.bind(this, view);
    }


    /////////////////////////////////////////////////////
    // Getters and Setters
    ////////////////////////////////////////////////////
    //-----Begin----------------------------------------
    public void setKeySpecBytes(byte[] keySpecBytes){
        this.presenter = new CryptoTestPresenter(this, getActivity(), keySpecBytes);
    }
    //-----End------------------------------------------


    /////////////////////////////////////////////////////
    // Method onClick
    ////////////////////////////////////////////////////
    @OnClick(R.id.test_btn)
    private void onClickTestBtn(){
        if (this.presenter != null){
            this.presenter.runTest(this.textOpen.getText().toString());
        }
    }


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
        Toast toast = new Toast(getActivity());
        //FixMe !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    @Override
    public void setData(int dataType) {
        this.textEncrypted.setText(this.presenter.getStrEncrypt());
        this.textDecrypted.setText(this.presenter.getStrDecrypt());
    }
    //-----End methods of MainView-----------------------

    //-----Begin methods of BackDialogYN-----------------
    @Override
    public void onBackDYN(boolean yes) {

    }
    //-----End methods of BackDialogYN-------------------
    //-----End-------------------------------------------
}
