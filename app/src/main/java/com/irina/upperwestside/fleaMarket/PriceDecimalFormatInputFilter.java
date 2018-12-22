package com.irina.upperwestside.fleaMarket;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceDecimalFormatInputFilter implements InputFilter {
   private  Pattern mPattern;

    PriceDecimalFormatInputFilter() {
       mPattern=Pattern.compile("[0-9]*((\\.[0-9]?)?)");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher=mPattern.matcher(dest);
        if(!matcher.matches())
            return "";
        return null;
    }

}


