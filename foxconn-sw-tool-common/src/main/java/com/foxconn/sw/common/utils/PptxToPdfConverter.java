package com.foxconn.sw.common.utils;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.xslf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.concurrent.Future;


public class PptxToPdfConverter {
    private final static Logger log = LoggerFactory.getLogger(PptxToPdfConverter.class);


    /**
     * pptx转为pdf
     *
     * @param sourcePath 源文件地址 如 D:\\1.pptx
     */
    public static byte[] pptx2Pdf(String sourcePath) {
        Document document = null;
        XMLSlideShow slideShow = null;
        PdfWriter pdfWriter = null;
        ByteArrayOutputStream baos = null;
        byte[] resBytes = null;
        try {
            baos = new ByteArrayOutputStream();
            //使用输入流pptx文件
            slideShow = new XMLSlideShow(new FileInputStream(new File(sourcePath)));
            //获取幻灯片的尺寸
            Dimension dimension = slideShow.getPageSize();
            //创建一个写内容的容器
            document = new Document(PageSize.A4, 72, 72, 72, 72);
            //使用输出流写入
            pdfWriter = PdfWriter.getInstance(document, baos);
            //使用之前必须打开
            document.open();
            pdfWriter.open();
            PdfPTable pdfPTable = new PdfPTable(1);
            //获取幻灯片
            java.util.List<XSLFSlide> slideList = slideShow.getSlides();
            for (int i = 0, row = slideList.size(); i < row; i++) {
                //获取每一页幻灯片
                XSLFSlide slide = slideList.get(i);
                for (XSLFShape shape : slide.getShapes()) {
                    //判断是否是文本
                    if (shape instanceof XSLFTextShape) {
                        // 设置字体, 解决中文乱码
                        XSLFTextShape textShape = (XSLFTextShape) shape;
                        for (XSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
                            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                                textRun.setFontFamily("宋体");
                            }
                        }
                    }
                }
                //根据幻灯片尺寸创建图形对象
                BufferedImage bufferedImage = new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2d = bufferedImage.createGraphics();
                graphics2d.setPaint(Color.white);
                graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));
                //把内容写入图形对象
                slide.draw(graphics2d);
                graphics2d.dispose();
                //封装到Image对象中
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(bufferedImage, null);
                image.scalePercent(50f);
                // 写入单元格
                pdfPTable.addCell(new PdfPCell(image, true));
                document.add(image);
            }
            document.close();
            pdfWriter.close();
            resBytes = baos.toByteArray();

            FileOutputStream outputStream = new FileOutputStream("D:\\1.pdf");
            outputStream.write(resBytes);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            log.error("pptx转pdf异常：" + e.getMessage(), e);
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
                log.error("pptx转pdf关闭io流异常：" + e.getMessage(), e);
            }
        }
        return resBytes;
    }


    /**
     * pptx转为pdf
     *
     * @param sourcePath 源文件地址 如 D:\\1.ppt
     */
    public static byte[] ppt2Pdf(String sourcePath) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] resBytes = null;
        InputStream inputStream = null;
        try {
            Document pdfDocument = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(pdfDocument, baos);
            inputStream = new FileInputStream(new File(sourcePath));
            HSLFSlideShow hslfSlideShow = new HSLFSlideShow(inputStream);
            double zoom = 1; // 该位置我是参考原博主的代码根据自己需要进行修改的，我理解的是这个数越大，ppt转化出的图片越小
            if (hslfSlideShow == null) {
                XMLSlideShow ppt = new XMLSlideShow(inputStream);
                if (ppt == null) {
                    throw new NullPointerException("获取ppt文件数据失败");
                }
                Dimension pgsize = ppt.getPageSize();
                java.util.List<XSLFSlide> slide = ppt.getSlides();
                AffineTransform at = new AffineTransform();
                at.setToScale(zoom, zoom);
                pdfDocument.setPageSize(new com.itextpdf.text.Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
                pdfWriter.open();
                pdfDocument.open();
                PdfPTable table = new PdfPTable(1);
                for (XSLFSlide xslfSlide : slide) {
                    BufferedImage img = new BufferedImage((int) Math.ceil(pgsize.width * zoom), (int) Math.ceil(pgsize.height * zoom), BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics = img.createGraphics();
                    graphics.setTransform(at);

                    graphics.setPaint(Color.white);
                    graphics.fill(new java.awt.geom.Rectangle2D.Float(0, 0, (float) (pgsize.width * zoom), (float) (pgsize.height * zoom)));// 这个地方如果不乘zoom，则意味着没有铺满
                    xslfSlide.draw(graphics);
                    graphics.getPaint();
                    com.itextpdf.text.Image slideImage = com.itextpdf.text.Image.getInstance(img, null);
                    table.addCell(new PdfPCell(slideImage, true));
                }
                ppt.close();
                pdfDocument.add(table);
                pdfDocument.close();
                pdfWriter.close();
                resBytes = baos.toByteArray();
            }
            Dimension pgsize = hslfSlideShow.getPageSize();
            List<HSLFSlide> slides = hslfSlideShow.getSlides();
            pdfDocument.setPageSize(new com.itextpdf.text.Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
            pdfWriter.open();
            pdfDocument.open();
            AffineTransform at = new AffineTransform();
            PdfPTable table = new PdfPTable(1);
            for (HSLFSlide hslfSlide : slides) {
                BufferedImage img = new BufferedImage((int) Math.ceil(pgsize.width * zoom), (int) Math.ceil(pgsize.height * zoom), BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                graphics.setTransform(at);

                graphics.setPaint(Color.white);
                graphics.fill(new java.awt.geom.Rectangle2D.Float(0, 0, (float) (pgsize.width * zoom), (float) (pgsize.height * zoom)));
                hslfSlide.draw(graphics);
                graphics.getPaint();
                com.itextpdf.text.Image slideImage = com.itextpdf.text.Image.getInstance(img, null);
                table.addCell(new PdfPCell(slideImage, true));
            }
            hslfSlideShow.close();
            pdfDocument.add(table);
            pdfDocument.close();
            pdfWriter.close();
            resBytes = baos.toByteArray();


            FileOutputStream outputStream = new FileOutputStream("D:\\1.pdf");
            outputStream.write(resBytes);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            log.error("ppt转为pdf异常：" + e.getMessage(), e);
        }
        return resBytes;
    }

    /**
     * 实现word转pdf
     *
     * @param sourcePath 源文件地址 如 D:\\1.doc
     */
    public static byte[] wordToPdf(String sourcePath) throws IOException {
        // 创建本地转换器实例
        IConverter converter = LocalConverter.builder().build();
        File inputWord = new File(sourcePath);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] resBytes = null;

        try {
            // 提交转换任务
            Future<Boolean> conversion = converter.convert(inputWord).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).prioritizeWith(1000).schedule();
            // 等待转换完成
            conversion.get();
            resBytes = outputStream.toByteArray();

            FileOutputStream outputStream1 = new FileOutputStream("D:\\1.pdf");
            outputStream1.write(resBytes);
            outputStream1.flush();
            outputStream1.close();

            return resBytes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("转换失败", e);
        } finally {
            // 关闭转换器，释放资源
            converter.shutDown();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
