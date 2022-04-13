package com.bacass.filechooser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bacass.library.FileChooser;
import com.bacass.library.FileUtils;

public class SampleActivity extends AppCompatActivity {
    private final String LOG_TAG = SampleActivity.class.getSimpleName();

    private final int RESULT_CODE_FILESELECT = 15981;

    private Button btnSearch;
    private TextView tvFilePath, tvFileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        initView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_CODE_FILESELECT:
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        if (data.getClipData() != null){
                            String filePath = "";
                            for (int index = 0; index < data.getClipData().getItemCount(); index++) {

                                Uri fileUri = data.getClipData().getItemAt(index).getUri();
                                try {
                                    filePath += FileUtils.getPath(SampleActivity.this, fileUri);
                                    filePath += "\n";
                                } catch (Exception e) {
                                    Log.e(LOG_TAG, "Error: " + e);
                                    Toast.makeText(SampleActivity.this, "Error: " + e, Toast.LENGTH_SHORT).show();
                                }
                            }
                            this.tvFilePath.setText(filePath);

                        } else {
                            Uri fileUri = data.getData();
                            Log.i(LOG_TAG, "Uri: " + fileUri);

                            String filePath = null;
                            try {
                                filePath = FileUtils.getPath(SampleActivity.this, fileUri);
                            } catch (Exception e) {
                                Log.e(LOG_TAG, "Error: " + e);
                                Toast.makeText(SampleActivity.this, "Error: " + e, Toast.LENGTH_SHORT).show();
                            }
                            this.tvFilePath.setText(filePath);
                            this.tvFileInfo.setText(FileUtils.getFileExtension(fileUri));
                        }
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        btnSearch = (Button) findViewById(R.id.btnSearch);
        tvFilePath = (TextView) findViewById(R.id.tvFilePath);
        tvFileInfo = (TextView) findViewById(R.id.tvFileInfo);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndSelectFile();
            }
        });
    }

    private void checkPermissionAndSelectFile() {
        new FileChooser(this)
                .setMimeType("*/*")
                .setMultipleChoose(true)
                .start(RESULT_CODE_FILESELECT);
    }
}