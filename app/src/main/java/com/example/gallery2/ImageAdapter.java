package com.example.gallery2;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

    public class ImageAdapter extends BaseAdapter {

        private Context context;
        private Cursor cursor;

        public ImageAdapter(Context context) {
            this.context = context;
        }

        public void loadImages() {
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.MediaColumns.DATA};
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return cursor != null ? cursor.getCount() : 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(250, 250));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }
            cursor.moveToPosition(position);
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            String imagePath = cursor.getString(columnIndex);
            Glide.with(context).load("file://" + imagePath).into(imageView);
            return imageView;
        }
}
