package com.chay.test.chaysizemanager.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Chay on 2016/11/17.
 */

public class HeadUtils {

    //处理裁剪的相关数据
    public static Intent fixIntent(Uri uri) {
        //通过FileProvider创建一个content类型的Uri
        Intent intent = new Intent("com.android.camera.action.CROP");
        Uri outputUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "xiu_size_head_icon.jpg");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
//            outputUri = FileProvider.getUriForFile(mPersonalView.getContext(), "com.xiu.app.fileprovider", file);
            outputUri = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "xiu_size_head_icon.jpg"));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
        } else {
            //uritempFile为Uri类变量，实例化uritempFile
            outputUri = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "xiu_size_head_icon.jpg"));
        }
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        return intent;
    }

    public static Drawable cutPicToView(Context context, Intent picdata, String path) {
        Uri uritempFile;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "xiu_size_head_icon.jpg");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            uritempFile = FileProvider.getUriForFile(context, "com.xiu.app.fileprovider", file);
            picdata.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }else {
            uritempFile = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "xiu_size_head_icon.jpg"));
        }
        if (uritempFile != null) {
            Bitmap photo = null;
            try {
                photo = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uritempFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Drawable drawable = new BitmapDrawable(null, photo);

            File headImage = new File(path);

            if (headImage.exists()) {
                headImage.delete();
            }
            try {
                FileOutputStream out = new FileOutputStream(headImage);
                photo.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return drawable;
        }
        return null;

    }

}
