package com.madscientists.algoliademo.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.madscientists.algoliademo.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by madscientist on 7/10/17.
 */

public class Utils {

    public static String intFormater(double n, int iteration) {
        if (n<1000 && iteration==0){
            return String.valueOf((int)n);
        }
        char[] c = new char[]{'k', 'm', 'b', 't'};

        double d = ((long) n / 100) / 10.0;
        boolean isRound = (d * 10) % 10 == 0;//true if the decimal part is equal to 0 (then it's trimmed anyway)
        return (d < 1000 ? //this determines the class, i.e. 'k', 'm' etc
                ((d > 99.9 || isRound || (!isRound && d > 9.99) ? //this decides whether to trim the decimals
                        (int) d * 10 / 10 : d + "" // (int) d * 10 / 10 drops the decimal
                ) + "" + c[iteration])
                : intFormater(d, iteration + 1));
    }
    public static void setDefaultFontForActivity(Context context){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(context.getString(R.string.font_regular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static String formatDate(String preFormattedDate) throws ParseException {
        return new SimpleDateFormat("MMM `YY").format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").
                parse(preFormattedDate.replaceAll("Z$", "+0000")));
    }
    public static void hideKeyboard(Context context) {
        View view = ((Activity)context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
