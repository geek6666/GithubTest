package com.zy.frame.utils;

import android.os.Environment;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * create by zy on 2014年9月17日
 */
public class FileUtils {

    public static final String ROOT_DIR = "house";
    public static final String DOWNLOAD_DIR = "download";
    public static final String CACHE_DIR = "cache";
    public static final String ICON_DIR = "icon";

    public static final long B = 1;
    public static final long KB = B * 1024;
    public static final long MB = KB * 1024;
    public static final long GB = MB * 1024;
    private static final int BUFFER = 8192;

    /**
     * 格式化文件大小<b> 带有单位
     *
     * @param size
     * @return
     */
    public static String formatFileSize(long size) {
        StringBuilder sb = new StringBuilder();
        String u = null;
        double tmpSize = 0;
        if (size < KB) {
            sb.append(size).append("B");
            return sb.toString();
        } else if (size < MB) {
            tmpSize = getSize(size, KB);
            u = "KB";
        } else if (size < GB) {
            tmpSize = getSize(size, MB);
            u = "MB";
        } else {
            tmpSize = getSize(size, GB);
            u = "GB";
        }
        return sb.append(twodot(tmpSize)).append(u).toString();
    }

    /**
     * 保留两位小数
     *
     * @param d
     * @return
     */
    public static String twodot(double d) {
        return String.format("%.2f", d);
    }

    private static double getSize(long size, long u) {
        return (double) size / (double) u;
    }

    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取下载目录
     */
    public static String getDownloadDir() {
        return getDir(DOWNLOAD_DIR);
    }

    /**
     * 获取缓存目录
     */
    public static String getCacheDir() {
        return getDir(CACHE_DIR);
    }

    /**
     * 获取icon目录
     */
    public static String getIconDir() {
        return getDir(ICON_DIR);
    }

    /**
     * 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录
     */
    public static String getDir(String name) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath());
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取SD下的应用目录
     */
    public static String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取应用的cache目录
     */
    public static String getCachePath() {
        File f = UIUtils.getContext().getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }

    /**
     * 创建文件夹
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 复制文件，可以选择是否删除源文件
     */
    public static boolean copyFile(String srcPath, String destPath, boolean deleteSrc) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        return copyFile(srcFile, destFile, deleteSrc);
    }

    /**
     * 复制文件，可以选择是否删除源文件
     */
    public static boolean copyFile(File srcFile, File destFile, boolean deleteSrc) {
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = in.read(buffer)) > 0) {
                out.write(buffer, 0, i);
                out.flush();
            }
            if (deleteSrc) {
                srcFile.delete();
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            return false;
        } finally {
            IOUtils.close(out);
            IOUtils.close(in);
        }
        return true;
    }

    /**
     * 判断文件是否可写
     */
    public static boolean isWriteable(String path) {
        try {
            if (StringUtils.isEmpty(path)) {
                return false;
            }
            File f = new File(path);
            return f.exists() && f.canWrite();
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            return false;
        }
    }

    /**
     * 修改文件的权限,例如"777"等
     */
    public static void chmod(String path, String mode) {
        try {
            String command = "chmod " + mode + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    /**
     * 把数据写入文件
     *
     * @param is       数据流
     * @param path     文件路径
     * @param recreate 如果文件存在，是否需要删除重建
     * @return 是否写入成功
     */
    public static boolean writeFile(InputStream is, String path, boolean recreate) {
        boolean res = false;
        File f = new File(path);
        FileOutputStream fos = null;
        try {
            if (recreate && f.exists()) {
                f.delete();
            }
            if (!f.exists() && null != is) {
                File parentFile = new File(f.getParent());
                parentFile.mkdirs();
                int count = -1;
                byte[] buffer = new byte[1024];
                fos = new FileOutputStream(f);
                while ((count = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, count);
                }
                res = true;
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        } finally {
            IOUtils.close(fos);
            IOUtils.close(is);
        }
        return res;
    }

    /**
     * 获取一个文件夹大小
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static long getFileSize(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

//    public static String getFiles(String path){
//
//        List<File> list;
//        File file=new File(path);
//        if(file.exists()){
////            if(file.isDirectory())
//            File files[]=file.listFiles();
//            file.list
//            for(int i=0;i<files.length;i++){
//
//            }
//
//        }
//
//        return null;
//    }


    /**
     * 将文本内容写入文件
     *
     * @param file
     * @param str
     * @throws java.io.IOException
     */
    public static void writeTextFile(File file, String str) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
            out.write(str.getBytes());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(byte[] content, String path, boolean append) {
        boolean res = false;
        File f = new File(path);
        RandomAccessFile raf = null;
        try {
            if (f.exists()) {
                if (!append) {
                    f.delete();
                    f.createNewFile();
                }
            } else {
                f.createNewFile();
            }
            if (f.canWrite()) {
                raf = new RandomAccessFile(f, "rw");
                raf.seek(raf.length());
                raf.write(content);
                res = true;
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        } finally {
            IOUtils.close(raf);
        }
        return res;
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(String content, String path, boolean append) {
        return writeFile(content.getBytes(), path, append);
    }

    /**
     * 把键值对写入文件
     *
     * @param filePath 文件路径
     * @param key      键
     * @param value    值
     * @param comment  该键值对的注释
     */
    public static void writeProperties(String filePath, String key, String value, String comment) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);// 先读取文件，再把键值对追加到后面
            p.setProperty(key, value);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        } finally {
            IOUtils.close(fis);
            IOUtils.close(fos);
        }
    }

    /**
     * 根据值读取
     */
    public static String readProperties(String filePath, String key, String defaultValue) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(filePath)) {
            return null;
        }
        String value = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            value = p.getProperty(key, defaultValue);
        } catch (IOException e) {
            LogUtils.e(e.getMessage());
        } finally {
            IOUtils.close(fis);
        }
        return value;
    }

    /**
     * 把字符串键值对的map写入文件
     */
    public static void writeMap(String filePath, Map<String, String> map, boolean append,
                                String comment) {
        if (map == null || map.size() == 0 || StringUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            Properties p = new Properties();
            if (append) {
                fis = new FileInputStream(f);
                p.load(fis);// 先读取文件，再把键值对追加到后面
            }
            p.putAll(map);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        } finally {
            IOUtils.close(fis);
            IOUtils.close(fos);
        }
    }

    /**
     * 读取文件
     *
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public static String readTextFile(File file) throws IOException {
        String text = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            text = readTextInputStream(is);
            ;
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return text;
    }

    /**
     * 从流中读取文件
     *
     * @param is
     * @return
     * @throws java.io.IOException
     */
    public static String readTextInputStream(InputStream is) throws IOException {
        StringBuffer strbuffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                strbuffer.append(line).append("\r\n");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return strbuffer.toString();
    }

    /**
     * 把字符串键值对的文件读入map
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map<String, String> readMap(String filePath, String defaultValue) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        Map<String, String> map = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            map = new HashMap<String, String>((Map) p);// 因为properties继承了map，所以直接通过p来构造一个map
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        } finally {
            IOUtils.close(fis);
        }
        return map;
    }

    /**
     * 改名
     */
    public static boolean copy(String src, String des, boolean delete) {
        File file = new File(src);
        if (!file.exists()) {
            return false;
        }
        File desFile = new File(des);
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(desFile);
            byte[] buffer = new byte[1024];
            int count = -1;
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
                out.flush();
            }
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
            return false;
        } finally {
            IOUtils.close(in);
            IOUtils.close(out);
        }
        if (delete) {
            file.delete();
        }
        return true;
    }

    /**
     * 根据时间清除超时的文件
     */
    public static boolean reduceFileCache(String cacheDirFullPath, long expirationPeriod) {
        File cacheDir = new File(cacheDirFullPath);
        if (cacheDir.isDirectory()) {
            File[] children = cacheDir.listFiles();
            long lastModifiedThreashold = System.currentTimeMillis() - expirationPeriod;
            for (File f : children) {
                if (f.lastModified() < lastModifiedThreashold) {
                    f.delete();
                }
            }
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
    }

}
