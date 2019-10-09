package com.example.demo.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.ImageUtil;
import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.Reader;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import net.sf.jasperreports.components.barcode4j.BarcodeXmlWriter;
import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 二维码/条形码生成工具
 */
public class CodeUtil {

    //    保存路径
    private static final String QCR_CODE_IMAGE_PATH = "D:\\Work\\ay_pc\\src\\main\\resources\\static\\Qrc\\myQRC.png";
    private static final String BAR_CODE_IMAGE_PATH = "D:\\Work\\ay_pc\\src\\main\\resources\\static\\Qrc\\myBar.png";
    private static final String INTER_PATH = "http://localhost:7171/";

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static final String ENCODE_UTF = "utf-8";
    private static final Integer BARWIDTH = 105;
    private static final Integer BARHEIGTH = 50;
    private static final Integer QCRWIDTH = 350;
    private static final Integer QCRHEIGTH = 350;

    /**
     * 二维码生成
     *
     * @param text
     * @param filePath
     * @throws WriterException
     * @throws IOException
     */
    private static void generateQRCodeImage(String text, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, QCRWIDTH, QCRHEIGTH);

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
            File file = new File(qrcPath);
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


    /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 生成字节
     *
     * @param msg
     * @return
     */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }

    /**
     * 生成到流
     *
     * @param msg
     * @param ous
     */
    public static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }

        Code39Bean bean = new Code39Bean();

        // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);

        // 配置对象
        bean.setModuleWidth(moduleWidth);
        bean.setWideFactor(3);
        bean.doQuietZone(false);

        String format = "image/png";
        try {

            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            // 生成条形码
            bean.generateBarcode(canvas, msg);

            // 结束绘制
            canvas.finish();
            ous.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 条形码生成
     *
     * @param contents
     * @param imgPath
     */
    public static void generateBAR(String contents, String imgPath) {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, BARWIDTH);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_13, codeWidth, BARHEIGTH, null);

            MatrixToImageWriter
                    .writeToFile(bitMatrix, "png", new File(imgPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 条形码解析
     *
     * @param imgPath
     * @return String
     */
    public static String readBAR(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
//            generateBAR("6923450657713",BAR_CODE_IMAGE_PATH);
            String barMsg = readBAR(BAR_CODE_IMAGE_PATH);
            System.out.println(barMsg);
        } catch (Exception ex) {
        }
    }
}