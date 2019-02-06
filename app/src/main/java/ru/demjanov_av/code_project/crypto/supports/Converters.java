package ru.demjanov_av.code_project.crypto.supports;

import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by demjanov on 05.02.2019.
 */

public class Converters {
    //-----Constants variables begin---------------------
    public final static String SYMBOL_CODE_NAME = "UTF-8";
    //-----Constants variables end-----------------------


    /////////////////////////////////////////////////////
    // Method listLongToByteArray
    ////////////////////////////////////////////////////
    public static byte[] listLongToByteArray(List<Long> longList) {
        byte[] bytes = new byte[8 * longList.size()];
        int i = 0;

        for (long longIn: longList) {
            for (int j = 0; j < 8; j++) {
                bytes[i] = (byte) (longIn >>> (j * 8));
                i++;
            }
        }

        return bytes;
    }


    /////////////////////////////////////////////////////
    // Method byteArrayToListLong
    ////////////////////////////////////////////////////
    public static List<Long> byteArrayToListLong(byte[] bytes){
        List<Long> longList = new ArrayList<Long>();
        long item = 0L;

        for (int i = 0; i < bytes.length;){
            item ^= item;
            for (int j = 0; j < 8; j++){
                if(i >= bytes.length) break;
                item |= (long)bytes[i] << (j * 8);
                i++;
            }
            longList.add(item);
        }

        return longList;
    }


    /////////////////////////////////////////////////////
    // Method longArrayToByteArray
    ////////////////////////////////////////////////////
    public static byte[] longArrayToByteArray(long[] longs) {
        byte[] bytes = new byte[8 * longs.length];
        int k = 0;

        for (long aLong : longs) {
            for (int j = 0; j < 8; j++) {
                bytes[k] = (byte) (aLong >>> (j * 8));
                k++;
            }
        }

        return bytes;
    }


    /////////////////////////////////////////////////////
    // Method byteArrayToLongArray
    ////////////////////////////////////////////////////
    public static long[] byteArrayToLongArray(byte[] bytes){
        List<Long> longList = byteArrayToListLong(bytes);
        long[] longs = new long[longList.size()];

        for (int i = 0; i < longList.size(); i++){
            longs[i] = longList.get(i);
        }

        return longs;
    }


    /////////////////////////////////////////////////////
    // Method strToListLong
    ////////////////////////////////////////////////////
    public static List<Long> strToListLong(String str) throws UnsupportedEncodingException {
        List<Long> longList = new ArrayList<Long>();
        byte[] bytes = str.getBytes(SYMBOL_CODE_NAME);

        return byteArrayToListLong(bytes);
    }


    /////////////////////////////////////////////////////
    // Method listLongToStr
    ////////////////////////////////////////////////////
    @NonNull
    public static String listLongToStr(List<Long> longList) throws UnsupportedEncodingException {
        byte[] bytes = listLongToByteArray(longList);
        int i = bytes.length;

        for (int j = i - 1; j >= 0; j--){
            i = j;
            if(bytes[j] != 0){
                break;
            }
        }

        byte[] bytes1 = Arrays.copyOf(bytes, i + 1);

        return new String(bytes1, SYMBOL_CODE_NAME);
    }

    /////////////////////////////////////////////////////
    // Method bytesToIntSBOX
    ////////////////////////////////////////////////////
    public static int[][] bytesToIntSBOX (byte[] sBoxBytes){
        int[][] sBox;

        if(sBoxBytes.length < 64){
            sBox = new int[0][0];
        }else {
            sBox = new int[8][16];
            int k = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 16; j++) {
                    sBox[i][j] = (sBoxBytes[k] & 15);
                    j++;
                    sBox[i][j] = (sBoxBytes[k] >>> 4);
                    k++;
                }
            }
        }
        return sBox;
    }
}
