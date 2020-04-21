package com.rnb.newbase.toolkit.util;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    /**
     * 判断指定文件是否存在
     *
     * @param filename 包含路径的文件名
     * @return
     */
    public static boolean isExistFile(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    /**
     * 获取指定路径下所有文件名称集合
     *
     * @param path 所需查询的路径
     * @param suffix 文件后缀
     * @return
     */
    public static List<String> getFileList(String path, String suffix) {
        List<String> filelist = new ArrayList<String>();
        File dir = new File(path);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (!files[i].isDirectory() && fileName.endsWith(suffix)) { // 判断是文件还是文件夹
                    filelist.add(files[i].getName());
                } else {
                    continue;
                }
            }
        }
        return filelist;
    }

    /**
     * 删除文件
     *
     * @param fileName 包含路径的文件名
     */
    public static void delFile(String fileName) {
        try {
            String filePath = fileName;
            java.io.File delFile = new java.io.File(filePath);
            if (delFile.exists())
                delFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a
     *         deletion fails, the method stops attempting to delete and returns
     *         "false".
     */
    public static boolean delDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = delDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 复制单个文件
     *
     * @param srcFile 包含路径的源文件 如：E:/phsftp/src/abc.txt
     * @param dirDest 目标文件目录，如：E:/phsftp/dest
     * @throws IOException
     */
    public static void copyFile(String srcFile, String dirDest) {
        try {
            FileInputStream in = new FileInputStream(srcFile);
            FileOutputStream out = new FileOutputStream(dirDest + "/" + new File(srcFile).getName());
            int len;
            byte buffer[] = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移动文件到指定目录
     *
     * @param oldFile 包含路径的文件名 如：E:/phsftp/src/ljq.txt
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     */
    public static void moveFile(String oldFile, String newPath) {
        copyFile(oldFile, newPath);
        delFile(oldFile);
    }

    /**
     * 按行读取并返回内容。根据通达信格式，去除第一行标题和最后两行落款
     *
     * @param filename
     * @return
     */
    public static List<String> getSourceDatas(String filename) {
        List<String> datas = new ArrayList<String>();
        File file = new File(filename);
        try {
            datas = FileUtils.readLines(file, "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 去除第一行和最后两行落款及空行数据
        datas.remove(0);
        int size = datas.size();
        if (size > 2) {
            datas.remove(size - 1);
            datas.remove(size - 2);
        }
        return datas;

    }

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static byte[] readFileByBytes(String filename) {
        byte[] buffer = new byte[(int) getFileSize(filename)];
        FileInputStream input = null;
        try {
            input = new FileInputStream(filename);
            while (true) {
                int len = input.read(buffer);
                if (len == -1) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return buffer;
    }

    /**
     * 按行读取文件
     * @param filename 含路径文件
     * @param character 文件字符集
     * @return
     */
    public static List<String> readFileByLine(String filename, String character) {
        List<String> contents = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), character));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                contents.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return contents;
    }

    public static long getFileSize(String filename) {
        File f = new File(filename);
        if (f.exists() && f.isFile()) {
            return f.length();
        } else {
            return 0;
        }
    }
}
