package com.app.utils;

import org.xutils.common.Callback;

/**
 * 
 */
public class HeCallBack<ResultType> implements Callback.CommonCallback<ResultType> {
    @Override
    public void onSuccess(ResultType result) {
        //According to the specific needs of a unified request for successful logical processing

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //According to the specific needs of a unified request for successful logical processing

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}