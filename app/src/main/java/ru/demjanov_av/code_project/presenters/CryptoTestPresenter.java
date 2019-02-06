package ru.demjanov_av.code_project.presenters;

import android.content.Context;

import ru.demjanov_av.code_project.crypto.AES;
import ru.demjanov_av.code_project.crypto.EncryptorGOST;
import ru.demjanov_av.code_project.crypto.supports.Converters;
import ru.demjanov_av.code_project.save_load.Preferencer;
import ru.demjanov_av.code_project.views.CryptoTestFragment;
import ru.demjanov_av.code_project.views.PswFragment;

/**
 * Created by demjanov on 06.02.2019.
 */

public class CryptoTestPresenter {

    //-----Class variables begin--------------------------
    private CryptoTestFragment ctFragment;
    private EncryptorGOST encryptorGOST;
    private String strEncrypt;
    private String strDecrypt;
    //-----Class variables end----------------------------


    /////////////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////////////
    public CryptoTestPresenter(CryptoTestFragment ctFragment, Context context, byte[] keySpecBytes) {
        this.ctFragment = ctFragment;
        Preferencer preferencer = new Preferencer(context);
        preferencer.setAes(new AES(keySpecBytes));
        this.encryptorGOST = new EncryptorGOST(preferencer);

    }

    /////////////////////////////////////////////////////
    // Getters and Setters
    ////////////////////////////////////////////////////
    //-----Begin----------------------------------------

    public String getStrEncrypt() {
        return strEncrypt;
    }

    public String getStrDecrypt() {
        return strDecrypt;
    }

    //-----End------------------------------------------

    /////////////////////////////////////////////////////
    // Method runTest
    ////////////////////////////////////////////////////
    public void runTest(String openText){
        this.strEncrypt = null;
        this.strDecrypt = null;

        this.strEncrypt = this.encryptorGOST.encryptGamma(openText);
        if (this.strEncrypt != null) {
            this.strDecrypt = this.encryptorGOST.decryptGamma(this.strEncrypt);
        }

        if(this.strEncrypt != null && this.strDecrypt != null) {
            this.ctFragment.setData(0);
        }else {
            this.ctFragment.setError(0, "Error !!!");
        }
    }
}
