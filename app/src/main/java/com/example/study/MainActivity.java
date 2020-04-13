package com.example.study;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityGroup;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class MainActivity extends ActivityGroup implements View.OnClickListener {
    private static final String TAG = "TabGroupActivity";
    private Bundle mBundle = new Bundle();
    private LinearLayout ll_container, ll_first, ll_second, ll_third,ll_fourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        ll_first = (LinearLayout) findViewById(R.id.ll_first);
        ll_second = (LinearLayout) findViewById(R.id.ll_second);
        ll_third = (LinearLayout) findViewById(R.id.ll_third);
        ll_fourth=(LinearLayout) findViewById(R.id.ll_fourth);
        ll_first.setOnClickListener(this);
        ll_second.setOnClickListener(this);
        ll_third.setOnClickListener(this);
        ll_fourth.setOnClickListener(this);
        mBundle.putString("tag", TAG);
        changeContainerView(ll_first);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.ll_first || v.getId()==R.id.ll_second
                || v.getId()==R.id.ll_third || v.getId()==R.id.ll_fourth) {
            changeContainerView(v);
        }
    }

    private void changeContainerView(View v) {
        ll_first.setSelected(false);
        ll_second.setSelected(false);
        ll_third.setSelected(false);
        ll_fourth.setSelected(false);
        v.setSelected(true);
        if (v == ll_first) {
            toActivity("first", TabFirstActivity.class);
        } else if (v == ll_second) {
            toActivity("second", TabSecondActivity.class);
        } else if (v == ll_third) {
            toActivity("third", TabThirdActivity.class);
        } else if (v == ll_fourth) {
            toActivity("fourth", TabFourthActivity.class);
        }
    }

    private void toActivity(String label, Class<?> cls) {
        Intent intent = new Intent(this, cls).putExtras(mBundle);
        ll_container.removeAllViews();
        View v = getLocalActivityManager().startActivity(label, intent).getDecorView();
        v.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ll_container.addView(v);
    }

}
