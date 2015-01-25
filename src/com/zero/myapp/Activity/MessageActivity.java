package com.zero.myapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.zero.myapp.R;
import com.zero.myapp.framework.BaseActivity;

/**
 * Created with IntelliJ IDEA.
 * 创建人: wangzhe
 * 创建日期: 2015/1/23
 * 时间: 15:54
 */
public class MessageActivity extends BaseActivity {

    @Override
    public void onCreateView(Bundle bundle) {
        setContentView(R.layout.message_layout);
    }

    @Override
    public void onClickView(View view) {

    }

    @Override
    public void setTitleStr(TextView title) {
        title.setText("消息");
    }

    @Override
    public void onReceiveMessage(Context context, Intent intent) {

    }

    @Override
    public void onDataChange(String tag, Intent intent) {

    }
}