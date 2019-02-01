package ru.demjanov_av.code_project.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.demjanov_av.code_project.R;
import ru.demjanov_av.code_project.presenters.MainView;
import ru.demjanov_av.code_project.presenters.PswPresenter;

/**
 * Created by demjanov on 31.01.2019.
 */

public class PswFragment extends Fragment implements MainView {

    //-----Constants variables begin---------------------
    private final static int CODE_NUMBERS_COUNT = 4;
    private PswPresenter presenter;
    //-----Constants variables end-----------------------


    //-----Class variables begin-------------------------
    private View view;
    int[] codeNumbers = new int[CODE_NUMBERS_COUNT];
    //-----Class variables begin-------------------------


    //-----View elements variables begin-----------------

    @BindView(R.id.message_text)
    TextView textViewMsg;

    @BindView(R.id.message_text_sys)
    TextView textViewMsgSys;

    @BindView(R.id.code_text_0)
    TextView textCode0;

    @BindView(R.id.code_text_1)
    TextView textCode1;

    @BindView(R.id.code_text_2)
    TextView textCode2;

    @BindView(R.id.code_text_3)
    TextView textCode3;

    @BindView(R.id.tab_layout)
    TableLayout tableLayout;
    //-----View elements variables end-------------------


    //-----Flags variables begin-------------------------
    int counter;
    int workMode;
    //-----Flags variables End---------------------------


    /////////////////////////////////////////////////////
    // Interface EnterCodeSuccess
    ////////////////////////////////////////////////////
    public interface EnterCodeSuccess{
        void onEnterCode(String userId);
    }


    /////////////////////////////////////////////////////
    // Method onCreateView
    ////////////////////////////////////////////////////
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        this.view = inflater.inflate(R.layout.fragment_psw, viewGroup, false);

        return view;
    }


    /////////////////////////////////////////////////////
    // Method initializeElements
    ////////////////////////////////////////////////////
    private void initializeElements(View view) {
        ButterKnife.bind(this, view);


        //---Presenter_begin---
        this.presenter = new PswPresenter(this);
        //---Presenter_end---
    }


    /////////////////////////////////////////////////////
    // Methods of enterCode
    ////////////////////////////////////////////////////
    //-----Begin----------------------------------------
    private void enterCodeForward(int number){
        this.codeNumbers[this.counter] = number;
        setCodeText(this.counter, getActivity().getResources().getString(R.string.number_mask_symbol));

        this.counter++;

        if(this.counter >= CODE_NUMBERS_COUNT){
            presenter.setInputNumbers(this.codeNumbers);
        }

    }


    private void enterCodeBack(){
        if(this.counter > 0) this.counter--;

        setCodeText(this.counter, "");
    }


    private void enterCodeClear(){
        if(this.counter > 0){
            for (int i = 0; i < CODE_NUMBERS_COUNT; i++){
                setCodeText(i, "");
            }

            this.counter = 0;
        }
    }


    private void setCodeText(int textIndex, String text){
        switch (textIndex){
            case 0:
                this.textCode0.setText(text);
                break;
            case 1:
                this.textCode1.setText(text);
                break;
            case 2:
                this.textCode2.setText(text);
                break;
            case 3:
                this.textCode3.setText(text);
                break;
            default:
                break;
        }
    }
    //-----End------------------------------------------


    /////////////////////////////////////////////////////
    // Methods onClick
    ////////////////////////////////////////////////////
    //-----Begin-----------------------------------------
    @OnClick(R.id.button_0)
    private void onClick0(){
        enterCodeForward(0);
    }


    @OnClick(R.id.button_1)
    private void onClick1(){
        enterCodeForward(1);
    }


    @OnClick(R.id.button_2)
    private void onClick2(){
        enterCodeForward(2);
    }


    @OnClick(R.id.button_3)
    private void onClick3(){
        enterCodeForward(3);
    }


    @OnClick(R.id.button_4)
    private void onClick4(){
        enterCodeForward(4);
    }


    @OnClick(R.id.button_5)
    private void onClick5(){
        enterCodeForward(5);
    }


    @OnClick(R.id.button_6)
    private void onClick6(){
        enterCodeForward(6);
    }


    @OnClick(R.id.button_7)
    private void onClick7(){
        enterCodeForward(7);
    }


    @OnClick(R.id.button_8)
    private void onClick8(){
        enterCodeForward(8);
    }


    @OnClick(R.id.button_9)
    private void onClick9(){
        enterCodeForward(9);
    }


    @OnClick(R.id.button_back)
    private void onClickBack(){
        enterCodeBack();
    }


    @OnClick(R.id.button_clear)
    private void onClickClear(){
        enterCodeClear();
    }
    //-----End-------------------------------------------



    /////////////////////////////////////////////////////
    // Methods of interfaces
    ////////////////////////////////////////////////////
    //-----Begin-----------------------------------------
    @Override
    public void startLoad() {
        this.tableLayout.setClickable(false);
    }

    @Override
    public void endLoad() {
        this.tableLayout.setClickable(true);
    }

    @Override
    public void setError(int number, @Nullable String message) {
        enterCodeClear();
        this.textViewMsg.setText(getActivity().getResources().getString(R.string.enter_code));
        switch (number){
            case PswPresenter.WRONG_CODE:
                this.textViewMsgSys.setText(getActivity().getResources().getString(R.string.wrong_code));
                break;
            case PswPresenter.MISMATCH_CODE:
                this.textViewMsgSys.setText(getActivity().getResources().getString(R.string.mismatch_code));
                break;
            default:
                this.textViewMsgSys.setText(message);
        }
    }

    @Override
    public void setData(int dataType) {
        enterCodeClear();
        switch (dataType){
            case PswPresenter.ENTER_NEW_CODE:
                this.textViewMsg.setText(getActivity().getResources().getString(R.string.enter_new_code));
                break;
            case PswPresenter.REPEAT_NEW_CODE:
                this.textViewMsg.setText(getActivity().getResources().getString(R.string.repeat_code));
                break;
            case PswPresenter.ENTER_CODE_SUCCESS:
                //FixMe реализация выхода
                break;
            default:
                this.textViewMsg.setText(getActivity().getResources().getString(R.string.enter_code));
                break;
        }
    }
    //-----End-------------------------------------------


    /////////////////////////////////////////////////////
    // Method onDestroy
    ////////////////////////////////////////////////////
    @Override
    public void onDestroy() {

        this.presenter.destroy();

        super.onDestroy();
    }
}
