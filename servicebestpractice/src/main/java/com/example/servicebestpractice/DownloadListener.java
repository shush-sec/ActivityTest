package com.example.servicebestpractice;

/**
 * Created by shush on 2017/5/20.
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
