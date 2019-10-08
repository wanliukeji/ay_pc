package com.example.demo.Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 二维码生成工具
 */
public class QRCUtil {

    //    保存路径
    private static final String QR_CODE_IMAGE_PATH = "D:\\Work\\ay_pc\\src\\main\\resources\\static\\Qrc\\myQRC.png";

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static final String ENCODE_UTF = "utf-8";

    /**
     * 二维码生成
     *
     * @param text
     * @param width
     * @param height
     * @param filePath
     * @throws WriterException
     * @throws IOException
     */
    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    /**
     * 二维码解析
     *
     * @param qrcPath
     */

    public static void readQRCodeImage(String qrcPath) {
        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            //读取指定的二维码文件
            File file = new File(QR_CODE_IMAGE_PATH);
            BufferedImage bufferedImage = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            //定义二维码参数
            Map hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, ENCODE_UTF);
            Result result = formatReader.decode(binaryBitmap, hints);
            //输出相关的二维码信息
            System.out.println("解析结果：" + result.toString());
            System.out.println("二维码格式类型：" + result.getBarcodeFormat());
            System.out.println("二维码文本内容：" + result.getText());
            bufferedImage.flush();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            readQRCodeImage(QR_CODE_IMAGE_PATH);
        } catch (Exception e) {
        }
    }
}