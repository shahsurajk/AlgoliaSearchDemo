package com.madscientists.algoliademo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.madscientists.algoliademo.R;
import com.madscientists.algoliademo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Activity_Main extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setDefaultFontForActivity(this);
        setContentView(R.layout.activity_main);

        openHomeFragment();
    }

    private void openHomeFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment_Home()).commitAllowingStateLoss();
    }
}
