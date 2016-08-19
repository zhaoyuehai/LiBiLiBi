package com.libilibi.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by 月海 on 2016/8/19.
 */

public abstract class RxBaseFragment extends RxFragment {

    private LayoutInflater mInflater;
    private View mParentView;
    private FragmentActivity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        mParentView = inflater.inflate(getLayoutResId(), container, false);
        mActivity = getActivity();
        return mParentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }
    public Context getApplicationContext() {
        return mActivity == null ? (getActivity() == null ? null : getActivity().getApplicationContext()) : mActivity.getApplicationContext();
    }

    public LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    public View getmParentView() {
        return mParentView;
    }

    public FragmentActivity getSupportActivity() {
        return mActivity;
    }

    protected abstract void finishCreateView(Bundle savedInstanceState);

    protected abstract int getLayoutResId();


}
