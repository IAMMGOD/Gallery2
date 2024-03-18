package com.example.gallery2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 1;
    private GridView gridView;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            imageAdapter.loadImages();
        } else {
            requestStoragePermission();
        }
    }

    // Method to request storage permission
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                STORAGE_PERMISSION_CODE);
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Check if permission request is for storage permission
        if (requestCode == STORAGE_PERMISSION_CODE) {
            // Check if permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, load images
                imageAdapter.loadImages();
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                // Permission denied, display message or handle gracefully
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

