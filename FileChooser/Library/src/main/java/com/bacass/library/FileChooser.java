package com.bacass.library;

import android.app.Activity;
import android.content.Intent;

public class FileChooser {

    private Activity activity;
    private boolean isMultiple;
    private String mimeType = "*/*"; // */*, image/*, video/*, audio/*, application/pdf, application/zip ...

    public FileChooser(Activity activity) {
        this.activity = activity;
    }

    public FileChooser setMultipleChoose(boolean isMultiple) {
        this.isMultiple = isMultiple;
        return this;
    }

    public FileChooser setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public void start(int requestCode) {
        Intent intent = new Intent(activity, FileChooserActivity.class);
        intent.putExtra("isMultiple", isMultiple);
        intent.putExtra("mimeType", mimeType);
        activity.startActivityForResult(intent, requestCode);
    }
}
