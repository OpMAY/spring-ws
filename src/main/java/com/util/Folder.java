package com.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;

@Slf4j
public class Folder {
    /**
     * @param : full_path (C:\Users\zlzld\OneDrive\Desktop\projects\sustable\spring-master\out\artifacts\webapplication_Web_exploded\files\test.txt)
     * @return : boolean
     * Description : 해당 path의 파일이 있는지 없는지 확인하는 함수
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean isFileExist(String full_path) {
        File f = new File(full_path);
        if (f.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param : absolute_path + file_path
     *          (C:\Users\zlzld\OneDrive\Desktop\projects\sustable\spring-master\out\artifacts\webapplication_Web_exploded\
     *          + files\test.txt)
     * @return : boolean
     * Description : 해당 path의 파일이 있는지 없는지 확인하는 함수
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean isFileExist(String absolute_path, String file_path) {
        File f = new File(absolute_path + file_path);
        if (f.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param : absolute_path(/앞에 포함), file_path (/앞에 미포함)
     * @return : boolean
     * Description : 파일인지를 검사하는 함수, 파일이 존재하지 않거나 디렉토리 이면 false 파일이면 true
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean isFile(String absolute_path, String file_path) {
        File f = new File(absolute_path + file_path);
        if (f.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param : full_path
     * @return : boolean
     * Description : 파일인지를 검사하는 함수, 파일이 존재하지 않거나 디렉토리 이면 false 파일이면 true
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean isFile(String full_path) {
        File f = new File(full_path);
        if (f.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param : absolute_path + file_path
     * @return : String or null
     * Description : 파일인지를 검사하는 함수, 파일이 존재하지 않거나 디렉토리 이면 false 파일이면 true
     * Date : 2021-11-24
     * Version : 1
     */
    public static String getFileName(String absolute_path, String file_path) {
        File f = new File(absolute_path + file_path);
        if (f.isFile()) {
            String name = f.getName();
            return name;
        }
        return null;
    }

    /**
     * @param : full_path
     * @return : String or null
     * Description : 파일인지를 검사하는 함수, 파일이 존재하지 않거나 디렉토리 이면 false 파일이면 true
     * Date : 2021-11-24
     * Version : 1
     */
    public static String getFileName(String full_path) {
        File f = new File(full_path);
        if (f.isFile()) {
            String name = f.getName();
            return name;
        }
        return null;
    }

    /**
     * @param : File file
     * @return : path or null
     * Description : 파일의 전체 경로와 이름을 반환하는 함수
     * Date : 2021-11-24
     * Version : 1
     */
    public static String getPath(File f) {
        if (f.exists()) {
            String path = f.getPath();
            return path;
        }
        return null;
    }

    /**
     * @param : absolute_path + directory_path
     * @return : ArrayList<File>()
     * Description : 해당 디렉토리 아래 파일들 전체 반환
     * Date : 2021-11-24
     * Version : 1
     */
    public static ArrayList<File> getListFiles(String absolute_path, String directory_path) {
        File f = new File(absolute_path + directory_path);
        if (f.isDirectory()) {
            ArrayList<File> files = new ArrayList<>();
            for (File file : f.listFiles()) {
                if (isFile(file.getAbsolutePath())) {
                    files.add(file);
                }
            }
            return files;
        }
        return new ArrayList<File>();
    }

    /**
     * @param : full_path
     * @return : ArrayList<File>()
     * Description : 해당 디렉토리 아래 파일들 전체 반환
     * Date : 2021-11-24
     * Version : 1
     */
    public static ArrayList<File> getListFiles(String full_path) {
        File f = new File(full_path);
        if (f.isDirectory()) {
            ArrayList<File> files = new ArrayList<>();
            for (File file : f.listFiles()) {
                if (isFile(file.getAbsolutePath())) {
                    files.add(file);
                }
            }
            return files;
        }
        return new ArrayList<File>();
    }

    /**
     * @param : absolute_path + file_path
     * @return : ArrayList<String>()
     * Description : 해당 디렉토리 아래 파일이름들을 전체 반환
     * Date : 2021-11-24
     * Version : 1
     */
    public static ArrayList<String> getListFileNames(String absolute_path, String directory_path) {
        File f = new File(absolute_path + directory_path);
        if (f.isDirectory()) {
            ArrayList<String> files = new ArrayList<>();
            for (File file : f.listFiles()) {
                if (isFile(file.getAbsolutePath())) {
                    files.add(file.getName());
                }
            }
            return files;
        }
        return new ArrayList<String>();
    }

    /**
     * @param : full_path
     * @return : ArrayList<String>()
     * Description : 해당 디렉토리 아래 파일이름들을 전체 반환
     * Date : 2021-11-24
     * Version : 1
     */
    public static ArrayList<String> getListFileNames(String full_path) {
        File f = new File(full_path);
        if (f.isDirectory()) {
            ArrayList<String> files = new ArrayList<>();
            for (File file : f.listFiles()) {
                if (isFile(file.getAbsolutePath())) {
                    files.add(file.getName());
                }
            }
            return files;
        }
        return new ArrayList<String>();
    }

    /**
     * @param : full_path
     * @return : boolean
     * Description : 디렉토리 만들기 실패시 mkdirs로 바꿈
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean mkdir(String full_path) {
        File f = new File("C://test/test2");
        boolean result = f.mkdir();
        if (result) {
            return true;
        } else {
            if (f.mkdirs()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @param : absolute_path + directory_path
     * @return : boolean
     * Description : 디렉토리 만들기 실패시 mkdirs로 바꿈
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean mkdir(String absolute_path, String directory_path) {
        File f = new File(absolute_path + directory_path);
        boolean result = f.mkdir();
        if (result) {
            return true;
        } else {
            if (f.mkdirs()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @param : File file
     * @return : boolean
     * Description : 디렉토리 만들기 실패시 mkdirs로 바꿈
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean mkdir(File f) {
        boolean result = f.mkdir();
        if (result) {
            return true;
        } else {
            if (f.mkdirs()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @param : full_path
     * @return : boolean
     * Description : 디렉토리들 만들기 (하위 디렉토리 포함해서 만들기)
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean mkdirs(String full_path) {
        File f = new File(full_path);
        boolean result = f.mkdirs();
        if (result) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param : String absolute_path, String directory_path
     * @return : boolean
     * Description : 디렉토리들 만들기 (하위 디렉토리 포함해서 만들기)
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean mkdirs(String absolute_path, String directory_path) {
        File f = new File(absolute_path + directory_path);
        boolean result = f.mkdirs();
        if (result) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param : File file
     * @return : boolean
     * Description : 디렉토리들 만들기 (하위 디렉토리 포함해서 만들기)
     * Date : 2021-11-24
     * Version : 1
     */
    public static boolean mkdirs(File f) {
        boolean result = f.mkdirs();
        if (result) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param : File file
     * @return : String (파일 확장자)
     * Description : 파일 확장자 구하기
     * Date : 2021-11-24
     * Version : 1
     */
    public static String getFileType(File file) {
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }

    public static void deleteFile(String file_path) {
        File target = new File(file_path);
        if (target.delete()) {
            log.info("File Deleted : {}", target.getName());
        } else {
            log.info("Failed to delete file : {}", target.getName());
        }
    }
}
