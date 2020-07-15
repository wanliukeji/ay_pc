package com.example.demo.Utils;

//import com.example.demo.entity.FileEntity;

import com.example.demo.entity.FileEntity;
import com.example.demo.entity.mk.MkFile;
import lombok.Synchronized;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 通用文件上传工具类
 */
public class FileUploadTool {

    TransfMediaTool transfMediaTool = new TransfMediaTool();
    // 文件最大500M
    private static long upload_maxsize = 800 * 1024 * 1024;
    // 文件允许格式
//    private static String[] allowFiles = {".rar", ".doc", ".docx", ".zip",
//            ".pdf", ".txt", ".swf", ".xlsx", ".gif", ".png", ".jpg", ".jpeg",
//            ".bmp", ".xls", ".mp4", ".flv", ".ppt", ".avi", ".mpg", ".wmv",
//            ".3gp", ".mov", ".asf", ".asx", ".vob", ".wmv9", ".rm", ".rmvb", ".mp3"};


    private static String[] allowFiles = {
             ".gif", ".png", ".jpg", ".jpeg"
           };

    // 允许转码的视频格式（ffmpeg）
    private static String[] allowFLV = {".avi", ".mpg", ".wmv", ".3gp",
            ".mov", ".asf", ".asx", ".vob"};
    // 允许的视频转码格式(mencoder)
    private static String[] allowAVI = {".wmv9", ".rm", ".rmvb"};

    public FileEntity createFile(MultipartFile multipartFile, HttpServletRequest request, Long fiedId) {
        FileEntity entity = new FileEntity();
        boolean bflag = false;
        String fileName = multipartFile.getOriginalFilename().toString();
        // 判断文件不为空
        if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
            bflag = true;
            // 判断文件大小
            if (multipartFile.getSize() <= upload_maxsize) {
                bflag = true;
                // 文件类型判断
                if (this.checkFileType(fileName)) {
                    bflag = true;
                } else {
                    bflag = false;
                    System.out.println("文件类型不允许");
                }
            } else {
                bflag = false;
                System.out.println("文件大小超范围");
            }
        } else {
            bflag = false;
            System.out.println("文件为空");
        }
        if (bflag) {
            String name = fileName.substring(0, fileName.lastIndexOf("."));

            String hexName = ConvertUtil.mixStr2Hex(name);
            // 新的文件名
            String newFileName = this.getName(fileName) + hexName + DateUtil.getDateYMDHMS();
            // 文件扩展名
            String fileEnd = this.getFileExt(fileName);
            String logoPathDir = logoPathDir(fileEnd);
            //保存本地项目
            //保存本地项目
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

            String logoRealPathDir = path + "/src/main/resources/static" + logoPathDir;

            File logoSaveFile = new File(logoRealPathDir);

            if (!logoSaveFile.exists()) {
                logoSaveFile.mkdirs();
            }
            // 绝对路径
            String fileNamedirs = logoRealPathDir + File.separator + newFileName + fileEnd;
            File filedirs = new File(fileNamedirs);
            // 上传文件
            try {
                transferToFile(multipartFile, filedirs);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 相对路径
            entity.setType(fileEnd.replace(".", ""));
            String fileDir = logoPathDir + newFileName + fileEnd;
            StringBuilder builder = new StringBuilder(fileDir);
            String finalFileDir = builder.substring(1);
            // size存储为String
            String size = this.getSize(filedirs);
            // 源文件保存路径
            String aviPath = filedirs.getAbsolutePath();
            // 转码Avi
//            boolean flag = false;
            if (this.checkAVIType(fileEnd)) {
                // 设置转换为AVI格式后文件的保存路径
                String codcAviPath = logoRealPathDir + File.separator + newFileName + ".avi";
                // 获取配置的转换工具（mencoder.exe）的存放路径
                String mencoderPath = request.getSession().getServletContext().getRealPath("/tools/mencoder.exe");
                aviPath = transfMediaTool.processAVI(mencoderPath, filedirs.getAbsolutePath(), codcAviPath);
                fileEnd = this.getFileExt(codcAviPath);
            }
            if (aviPath != null) {
                // 转码Flv
                if (this.checkMediaType(fileEnd)) {
                    try {
                        // 设置转换为flv格式后文件的保存路径
                        String codcFilePath = logoRealPathDir + File.separator + newFileName + ".flv";
                        // 获取配置的转换工具（ffmpeg.exe）的存放路径
                        String ffmpegPath = request.getSession().getServletContext().getRealPath("/tools/ffmpeg.exe");
                        transfMediaTool.processFLV(ffmpegPath, aviPath, codcFilePath);
                        fileDir = logoPathDir + newFileName + ".flv";
                        builder = new StringBuilder(fileDir);
                        finalFileDir = builder.substring(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                entity.setSize(size);
                entity.setPath("\\static\\upload"+File.separator + newFileName + fileEnd);
                entity.setFileName(name);
                entity.setNfileName(newFileName);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                entity.setUploadTime(timestamp);
                entity.setFiedId(fiedId);
                return entity;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }


    public MkFile createFile(MultipartFile multipartFile, HttpServletRequest request) {
        MkFile entity = new MkFile();
        boolean bflag = false;
        String fileName = multipartFile.getOriginalFilename().toString();
        // 判断文件不为空
        if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
            bflag = true;
            // 判断文件大小
            if (multipartFile.getSize() <= upload_maxsize) {
                bflag = true;
                // 文件类型判断
                if (this.checkFileType(fileName)) {
                    bflag = true;
                } else {
                    bflag = false;
                    System.out.println("文件类型不允许");
                }
            } else {
                bflag = false;
                System.out.println("文件大小超范围");
            }
        } else {
            bflag = false;
            System.out.println("文件为空");
        }
        if (bflag) {
            String name = fileName.substring(0, fileName.lastIndexOf("."));

            String hexName = ConvertUtil.mixStr2Hex(name);
            // 新的文件名
            String newFileName = this.getName(fileName) + hexName + DateUtil.getDateYMDHMS();
            // 文件扩展名
            String fileEnd = this.getFileExt(fileName);
            String logoPathDir = logoPathDir(fileEnd);
            //保存本地项目
            //保存本地项目
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();

            String logoRealPathDir = path + "/static" + logoPathDir;
            File logoSaveFile = new File(logoRealPathDir);


            if (!logoSaveFile.exists()) {
                logoSaveFile.mkdirs();
            }
            // 绝对路径
            String fileNamedirs = logoRealPathDir + File.separator + newFileName + fileEnd;
            File filedirs = new File(fileNamedirs);
            // 上传文件
            try {
                transferToFile(multipartFile, filedirs);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 相对路径
            entity.setFtype(fileEnd.replace(".", ""));
            String fileDir = logoPathDir + newFileName + fileEnd;
            StringBuilder builder = new StringBuilder(fileDir);
            String finalFileDir = builder.substring(1);
            // size存储为String
            String size = this.getSize(filedirs);
            // 源文件保存路径
            String aviPath = filedirs.getAbsolutePath();
            // 转码Avi
//            boolean flag = false;
            if (this.checkAVIType(fileEnd)) {
                // 设置转换为AVI格式后文件的保存路径
                String codcAviPath = logoRealPathDir + File.separator + newFileName + ".avi";
                // 获取配置的转换工具（mencoder.exe）的存放路径
                String mencoderPath = request.getSession().getServletContext().getRealPath("/tools/mencoder.exe");
                aviPath = transfMediaTool.processAVI(mencoderPath, filedirs.getAbsolutePath(), codcAviPath);
                fileEnd = this.getFileExt(codcAviPath);
            }
            if (aviPath != null) {
                // 转码Flv
                if (this.checkMediaType(fileEnd)) {
                    try {
                        // 设置转换为flv格式后文件的保存路径
                        String codcFilePath = logoRealPathDir + File.separator + newFileName + ".flv";
                        // 获取配置的转换工具（ffmpeg.exe）的存放路径
                        String ffmpegPath = request.getSession().getServletContext().getRealPath("/tools/ffmpeg.exe");
                        transfMediaTool.processFLV(ffmpegPath, aviPath, codcFilePath);
                        fileDir = logoPathDir + newFileName + ".flv";
                        builder = new StringBuilder(fileDir);
                        finalFileDir = builder.substring(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                entity.setFsize(size);
//                entity.setFpath("\\static\\upload"+File.separator + newFileName + fileEnd);

                String contextPath = request.getContextPath();
                String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                        request.getServerPort()+contextPath+"/";
                String path2 = basePath + "static" + logoPathDir + "/" + newFileName + fileEnd;

                entity.setFpath(path2);
                entity.setFileName(name);
                entity.setRemark(newFileName);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                entity.setCreadDate(timestamp);
                return entity;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    /**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    private boolean checkFileType(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 视频类型判断(flv)
     *
     * @param fileEnd
     * @return
     */
    private boolean checkMediaType(String fileEnd) {
        Iterator<String> type = Arrays.asList(allowFLV).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileEnd.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 视频类型判断(AVI)
     *
     * @param fileEnd
     * @return
     */
    private boolean checkAVIType(String fileEnd) {
        Iterator<String> type = Arrays.asList(allowAVI).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileEnd.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     *
     * @return
     */
    private String getName(String fileName) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.contains(ext)) {
                String newFileName = fileName.substring(0, fileName.lastIndexOf(ext));
                return newFileName;
            }
        }
        return "";
    }

    /**
     * 文件大小，返回kb.mb
     *
     * @return
     */
    private String getSize(File file) {
        String size = "";
        long fileLength = file.length();
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1024) {
            size = df.format((double) fileLength) + "BT";
        } else if (fileLength < 1048576) {
            size = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824) {
            size = df.format((double) fileLength / 1048576) + "MB";
        } else {
            size = df.format((double) fileLength / 1073741824) + "GB";
        }
        return size;
    }

    /**
     * 根据文件类型分类存储
     *
     * @param fileEnd
     * @return
     */
    public String logoPathDir(String fileEnd) {
        String logoPathDir = "";
        //判断文件属于什么类型
        if (fileEnd.contains("jpg") || fileEnd.contains("png") ||
                fileEnd.contains("gif") || fileEnd.contains("jpeg")) {
            logoPathDir = "/upload";
        } else if (fileEnd.contains("mp4") || fileEnd.contains("rmvb") ||
                fileEnd.contains("avi") || fileEnd.contains("flv")) {
            logoPathDir = "/video";
        } else if (fileEnd.contains("mp3") || fileEnd.contains("ogg")) {
            logoPathDir = "/audio";
        } else {
            logoPathDir = "/file";
        }
        return logoPathDir;
    }

    public Synchronized transferToFile(MultipartFile multipartFile, File file) throws IOException {

        try {

            multipartFile.transferTo(file);

        } catch (IllegalStateException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }

    public static void main(String[] args) {
        try {

        } catch (Exception e) {

        }

//       String path =  ClassUtils.getDefaultClassLoader().getResource("static").getPath();

    }
}