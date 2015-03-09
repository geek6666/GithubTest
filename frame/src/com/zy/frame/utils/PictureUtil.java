package com.zy.frame.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;

import java.io.*;

public class PictureUtil {

    /**
     * 把bitmap转换成String
     *
     * @param filePath
     * @return
     */
    public static InputStream bitmapToInputStream(String filePath) {

        Bitmap bm = getSmallBitmap(filePath, 720, 1280);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(b);

        return inputStream;
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }


    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     *
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath, int requestWidth, int requestHight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, requestWidth, requestHight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }


    /**
     * 根据路径删除图片
     *
     * @param path
     */
    public static void deleteTempFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 添加到图库
     */
    public static void galleryAddPic(Context context, String path) {
        Intent mediaScanIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    /**
     * 获取保存图片的目录
     *
     * @return
     */
    public static File getAlbumDir() {
        File dir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                getAlbumName());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 获取保存 隐患检查的图片文件夹名称
     *
     * @return
     */
    public static String getAlbumName() {
        return "sheguantong";
    }

    /**
     * 把bitmap转换成String
     *
     * @param filePath
     * @return
     */
//    public static File bitmapToFile(String filePath) {
//        String[] split = filePath.split("\\.");
//        String endFile = split[split.length - 1];
//        Md5FileNameGenerator md5FileNameGenerator = new Md5FileNameGenerator();
//        String generate = md5FileNameGenerator.generate(filePath);
//        String fileName = String.valueOf(generate + "." + endFile);
//
//        File file = new File(FileUtils.getExternalStoragePath(), fileName);
//        try {
//            Bitmap bitmap = getSmallBitmap(filePath, 720, 1280);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            int quality = 100;//压缩的质量，100表示不压缩
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//            while (baos.toByteArray().length / 1024 > 300) {
//                baos.reset();
//                quality -= 10;
//                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//            }
//
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(baos.toByteArray());
//            fos.flush();
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return file;
//    }

    public static String saveBitmap2Local(Bitmap bitmap) {
        File mypath = new File(FileUtils.getExternalStoragePath(), SystemClock.currentThreadTimeMillis() + ".png");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mypath.getAbsolutePath();
    }
}