package com.cavdardevelopment.morsecode;

import android.text.Editable;
import android.text.TextWatcher;

public class MyTextWatcher implements TextWatcher {

    private MainActivity mainActivity;

    public MyTextWatcher(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        char[] pass = new char[editable.length()];
        editable.getChars(0, editable.length(), pass, 0);
        mainActivity.translateMorseToLatin(pass);
    }
}
