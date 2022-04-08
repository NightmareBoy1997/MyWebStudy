package com.javasm.supermarket.util;

import java.io.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * @author: Lisa
 * @className: FileUtil
 * @description:
 * @date: 2022/3/14 16:21
 * @version: 0.1
 * @since: jdk11
 */
public class FileUtil {
    private static final String PRODUCT_PARENT_DIRECTORY = "upload/prod/";
    private static final String MEMBER_PARENT_DIRECTORY = "upload/user/";

    private FileUtil() {
    }

    public static String uploadProductImage(String sourcePath) throws FileNotFoundException {
        return upload(sourcePath, PRODUCT_PARENT_DIRECTORY);
    }

    public static String uploadMemberImage(String sourcePath) throws FileNotFoundException {
        return upload(sourcePath, MEMBER_PARENT_DIRECTORY);
    }

    private static String upload(String sourcePath, String parentDirectory) throws FileNotFoundException {
        Objects.requireNonNull(sourcePath);
        String curDateStr = LocalDate.now().toString();
        File childParentDirectory = new File(parentDirectory, curDateStr);
        if (!childParentDirectory.exists()) {
            childParentDirectory.mkdirs();
        }

        String targetFileName = UUID.randomUUID().toString().replaceAll("-", "") + sourcePath.substring(sourcePath.lastIndexOf("."));

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourcePath));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(childParentDirectory, targetFileName)));

        try (inputStream; outputStream) {
            int len;
            while ((len = inputStream.read()) != -1) {
                outputStream.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload/user/" + curDateStr + "/" + targetFileName;
    }
}
