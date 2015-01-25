package com.zero.myapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zero.myapp.R;
import com.zero.myapp.framework.BaseFragment;

/**
 * Created with IntelliJ IDEA.
 * 创建人: wangzhe
 * 创建日期: 2015/1/23
 * 时间: 16:45
 */
public class MainFragment extends BaseFragment {
    private View convertView;

    @Override
    public View initView(LayoutInflater inflater) {
        convertView = inflater.inflate(R.layout.main_fragment, null);
        return convertView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}