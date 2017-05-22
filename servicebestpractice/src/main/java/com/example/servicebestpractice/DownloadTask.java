package com.example.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shush on 2017/5/20.
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSE = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener listener;
    private boolean isCanceled = false;
    private boolean isPauseed = false;
    private int lastProgress;

    @Override
    protected Integer doInBackground(String... params) {
        
        InputStream is = null;
        RandomAccessFile saveFile = null;
        File file = null;

        long downloadLength = 0;
        String downloadUrl = params[0];
        String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
        String directory = Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS).getPath();
        file = new File(directory + fileName);
        if (file.exists())
            downloadLength = file.length();
        try {
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadLength) {
                return TYPE_SUCCESS;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //断点下载，指定从哪个字节开始下载
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                saveFile = new RandomAccessFile(file, "rw");
                saveFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != 1) {
                    if (isCanceled)
                        return TYPE_CANCELED;
                    else if (isPauseed)
                        return TYPE_PAUSE;
                    else
                        total += len;

                    saveFile.write(b, 0, len);
                    //计算已下载的百分比
                    int progress = (int) ((total + downloadLength) * 100 / contentLength);
                    publishProgress(progress);
                }
            }

            response.body().close();
            return TYPE_SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (saveFile != null)
                    saveFile.close();
                if (isCanceled && file != null)
                    file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return TYPE_FAILED;
    }

    @Override
    protected void onPostExecute(Integer Status) {
        switch (Status) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSE:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }

        return 0;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

    public void pauseDownload() {
        isPauseed = true;
    }
}
