package com.zero.myapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.zero.myapp.R;
import com.zero.myapp.fragment.MainFragment;
import com.zero.myapp.framework.BaseFragment;
import com.zero.myapp.framework.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity {

    @Override
    public void onCreateView(Bundle bundle) {
        setContentView(R.layout.main);
        hideBackButton();
        initView();
    }

    private void initView() {

    }

    @Override
    public void onClickView(View view) {

    }

    @Override
    public void setTitleStr(TextView title) {
        title.setText("第一个标题");
    }

    @Override
    public void onReceiveMessage(Context context, Intent intent) {
        String action = intent.getAction();
    }

    @Override
    public void onDataChange(String tag, Intent intent) {

    }
}
