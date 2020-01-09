package com.rnb.newbase.toolkit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {
    /**
     * 解压缩文件
     *
     * @param zipFilename
     * @param zipFilepath
     * @param unzipPath
     * @return
     */
    public static List<String> unZipFile(String zipFilename, String zipFilepath, String unzipPath) {
        List<String> unzipedFiles = new ArrayList<>();
        // 懂目标目录是否存在
        File pathFile = new File(zipFilepath + unzipPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        // 解决zip文件中有中文目录或者中文文件
        try {
            ZipFile zip = new ZipFile(zipFilepath + zipFilename, Charset.forName("GBK"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (zipFilepath + unzipPath + zipEntryName).replaceAll("\\\\", "/");
                // 判断路径是否存在，不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹，如果是上面已经上传，不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                // 输出文件路径信息
                unzipedFiles.add(outPath);
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unzipedFiles;
    }
}
