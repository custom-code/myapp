package com.zero.myapp.framework;

import android.app.AlertDialog;
import android.content.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.zero.myapp.Activity.MessageActivity;
import com.zero.myapp.R;
import com.zero.myapp.utils.Constance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * 创建人: wangzhe
 * 创建日期: 2015/1/23
 * 时间: 14:37
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener, BaseFragment.OnFragmentDataChangeListener {
    private Context context;
    private TextView mTvTitle, mTvBack;
    private ImageView mIvMessage;
    private Button mBtnButton;
    private boolean hideBack = false;
    private String buttonStr = "";
    protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Constance.DEFAULT_MESSAGE_ACTION.equals(action)) {
                //do something when get default message;
            } else {
                onReceiveMessage(context, intent);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseActivity.this;
        registerReceiver();
        onCreateView(savedInstanceState);
        initTitleView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    /**
     * 设置标题栏
     */
    private void initTitleView() {
        mTvTitle = (TextView) findViewById(R.id.base_title_title);
        mIvMessage = (ImageView) findViewById(R.id.base_title_message);
        mBtnButton = (Button) findViewById(R.id.base_title_button);
        mTvBack = (TextView) findViewById(R.id.base_title_back);
        if (hideBack) {
            mTvBack.setVisibility(View.GONE);
        }
        if (!"".equals(buttonStr)) {
            mBtnButton.setText(buttonStr);
            mBtnButton.setVisibility(View.VISIBLE);
            mTvBack.setVisibility(View.GONE);
        }
        if (mTvBack != null) {
            mTvBack.setOnClickListener(this);
        }
        if (mBtnButton != null) {
            mBtnButton.setOnClickListener(this);
        }
        if (mIvMessage != null) {
            mIvMessage.setOnClickListener(this);
        }
        setTitleStr(mTvTitle);
    }

    /**
     * 注册广播接收器
     */
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        registerReceiver(receiver, filter);
    }

    /**
     * 注销广播接收器
     */
    private void unregisterReceiver() {
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.base_title_back:
                finish();
                break;
            case R.id.base_title_message:
                intent.setClass(context, MessageActivity.class);
                startActivity(intent);
                break;
            default:
                onClickView(view);
                break;
        }
    }

    public abstract void onCreateView(Bundle bundle);

    public abstract void onClickView(View view);

    public abstract void setTitleStr(TextView title);

    public abstract void onReceiveMessage(Context context, Intent intent);

    /**
     * 隐藏返回按钮
     */
    protected void hideBackButton() {
        hideBack = true;
    }

    /**
     * 为左边按钮设置标题
     *
     * @param text 要设置的字符串
     */
    protected void setLeftButtonText(String text) {
        text = (text == null ? "" : text);
        buttonStr = text;
    }

    /**
     * 短暂显示Toast提示(来自res) *
     */
    protected void showShortToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String) *
     */
    protected void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast提示(来自res) *
     */
    protected void showLongToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自String) *
     */
    protected void showLongToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

//    /** 显示自定义Toast提示(来自res) **/
//    protected void showCustomToast(int resId) {
//        View toastRoot = LayoutInflater.from(BaseActivity.this).inflate(
//                R.layout.common_toast, null);
//        ((HandyTextView) toastRoot.findViewById(R.id.toast_text))
//                .setText(getString(resId));
//        Toast toast = new Toast(BaseActivity.this);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(toastRoot);
//        toast.show();
//    }
//
//    /** 显示自定义Toast提示(来自String) **/
//    protected void showCustomToast(String text) {
//        View toastRoot = LayoutInflater.from(BaseActivity.this).inflate(
//                R.layout.common_toast, null);
//        ((HandyTextView) toastRoot.findViewById(R.id.toast_text)).setText(text);
//        Toast toast = new Toast(BaseActivity.this);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(toastRoot);
//        toast.show();
//    }

    /**
     * 含有标题和内容的对话框 *
     */
    protected AlertDialog showAlertDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message).show();
        return alertDialog;
    }

    /**
     * 含有标题、内容、两个按钮的对话框 *
     */
    protected AlertDialog showAlertDialog(String title, String message,
                                          String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .show();
        return alertDialog;
    }

    /**
     * 含有标题、内容、图标、两个按钮的对话框 *
     */
    protected AlertDialog showAlertDialog(String title, String message,
                                          int icon, String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message).setIcon(icon)
                .setPositiveButton(positiveText, onPositiveClickListener)
                .setNegativeButton(negativeText, onNegativeClickListener)
                .show();
        return alertDialog;
    }

}