package com.bacass.library;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class FileChooserActivity extends AppCompatActivity {
    private final String LOG_TAG = FileChooserActivity.class.getSimpleName();

    private final int RESULT_CODE_FILESELECT = 30129;
    private final int REQUEST_CODE_PERMISSION = 17498;

    private boolean isMultiple;
    private String mimeType = "*/*";

    private Button btnSearch;
    private TextView tvFilePath, tvFileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isMultiple = getIntent().getBooleanExtra("isMultiple", false);
        mimeType = getIntent().getStringExtra("mimeType");

        checkPermissionAndSelectFile();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_CODE_FILESELECT:
                if (resultCode == Activity.RESULT_OK) {
                    setResult(Activity.RESULT_OK, data);
                    finish();
                } else {
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void checkPermissionAndSelectFile() {
        // With Android Level >= 23, you have to ask the user
        // for permission to access External Storage.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // Level 23

            // Check if we have Call permission
            int permisson = ActivityCompat.checkSelfPermission(FileChooserActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);

            if (permisson != PackageManager.PERMISSION_GRANTED) {
                // If don't have permission so prompt the user.
                this.requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_PERMISSION
                );
                return;
            }
        }
        this.doSelectFile(isMultiple, mimeType);
    }

    private void doSelectFile(boolean isMultiple, String mimeType) {
        Intent chooseFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFileIntent.setType(mimeType); // */*, image/*, video/*, audio/*, application/pdf, application/zip ...

        // Only return URIs that can be opened with ContentResolver
        chooseFileIntent.addCategory(Intent.CATEGORY_OPENABLE);

        if (isMultiple) {
            chooseFileIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }

        chooseFileIntent = Intent.createChooser(chooseFileIntent, "Select a file");
        startActivityForResult(chooseFileIntent, RESULT_CODE_FILESELECT);
    }

    // When you have the request results
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_PERMISSION: {

                // Note: If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.i(LOG_TAG, "Permission granted!");
//                    Toast.makeText(FileChooserActivity.this, "Permission granted!", Toast.LENGTH_SHORT).show();

                    this.doSelectFile(isMultiple, mimeType);
                } else {
                    Log.i(LOG_TAG, "Permission denied!");
//                    Toast.makeText(FileChooserActivity.this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

}