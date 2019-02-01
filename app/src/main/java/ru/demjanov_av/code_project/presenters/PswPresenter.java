package ru.demjanov_av.code_project.presenters;

import ru.demjanov_av.code_project.views.PswFragment;

/**
 * Created by demjanov on 31.01.2019.
 */

public class PswPresenter {
    //-----Constants variables begin---------------------
    //-----Work code begin-------------------------------
    public final static int ENTER_CODE = 0;
    public final static int ENTER_NEW_CODE = 1;
    public final static int REPEAT_NEW_CODE = 2;
    //-----Work code end---------------------------------

    //-----Gut code begin--------------------------------
    public final static int ENTER_CODE_SUCCESS = 200;
    //-----Gut code end----------------------------------

    //-----Fail code begin-------------------------------
    public final static int WRONG_CODE = 0;
    public final static int MISMATCH_CODE = 1;
    //-----Fail code end----------------------------------
    //-----Constants variables end------------------------


    //-----Class variables begin--------------------------
    private int[] inputNumbers;
    private PswFragment pswFragment;
    private int workMode;
    //-----Class variables end----------------------------


    /////////////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////////////
    public PswPresenter(PswFragment pswFragment) {
        this.pswFragment = pswFragment;
        //FixME реализация загрузки кода и установки режима
    }


    /////////////////////////////////////////////////////
    // Getters and Setters
    ////////////////////////////////////////////////////
    //-----Begin----------------------------------------

    public void setInputNumbers(int[] inputNumbers) {
        this.pswFragment.startLoad();

        switch (this.workMode) {
            case ENTER_CODE:
                this.inputNumbers = inputNumbers;
                //FixMe проверка кода по хэшу!
                break;
            case ENTER_NEW_CODE:
                this.inputNumbers = inputNumbers;
                this.workMode = REPEAT_NEW_CODE;
                this.pswFragment.setData(REPEAT_NEW_CODE);
                this.pswFragment.endLoad();
                break;
            case REPEAT_NEW_CODE:
                if(isEqualsNumbers(inputNumbers, this.inputNumbers)){
                    //FixMe сохраняем хэш, генерим код
                    this.pswFragment.setData(ENTER_CODE_SUCCESS);
                }else {
                    this.pswFragment.setError(MISMATCH_CODE, null);
                    this.pswFragment.endLoad();
                }

                break;
            default: ENTER_CODE:
                this.inputNumbers = inputNumbers;
                break;
        }
    }

    //-----End------------------------------------------


    /////////////////////////////////////////////////////
    // Method isEqualsNumbers
    ////////////////////////////////////////////////////
    private boolean isEqualsNumbers (int[] a, int[] b){
        if(a.length != b.length) return false;

        for (int i = 0; i < a.length; i++){
            if(a[i] != b[i]){
                return false;
            }
        }

        return true;
    }


    public void destroy(){

    }
}
