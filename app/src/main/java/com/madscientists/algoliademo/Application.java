package com.madscientists.algoliademo;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by madscientist on 7/10/17.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_regular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
