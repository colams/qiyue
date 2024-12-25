package com.foxconn.sw.common.utils;

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
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;


public class PptxToPdfConverter {
    private final static Logger log = LoggerFactory.getLogger(PptxToPdfConverter.class);


    /**
     * pptx转为pdf
     *
     * @param sourcePath 源文件地址 如 D:\\1.pptx
     * @param targetPath 目标文件地址 如 D:\\1.pdf
     */
    public static void pptToPdf(String sourcePath, String targetPath) {
        Document document = null;
        XMLSlideShow slideShow = null;
        FileOutputStream fileOutputStream = null;
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
            FileOutputStream outputStream = new FileOutputStream(targetPath);
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
    }



    /**
     * pptx转为pdf
     * @param sourcePath 源文件地址 如 D:\\1.ppt
     * @param targetPath 目标文件地址 如 D:\\1.pdf
     */
    public static void ppt2pdf(String sourcePath,String targetPath) {
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
                pdfDocument.setPageSize(new com.itextpdf.text.Rectangle((float) pgsize.getWidth(),(float) pgsize.getHeight()));
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
                FileOutputStream outputStream = new FileOutputStream(targetPath);
                outputStream.write(resBytes);
                outputStream.flush();
                outputStream.close();
                return;
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
            FileOutputStream outputStream = new FileOutputStream(targetPath);
            outputStream.write(resBytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            log.error("ppt转为pdf异常："+e.getMessage(),e);
        }
    }

    /**
     * 实现word转pdf
     *
     * @param sourcePath 源文件地址 如 D:\\1.doc
     * @param targetPath 目标文件地址 如 D:\\1.pdf
     */
    public static void wordToPdf(String sourcePath, String targetPath) {
        File inputWord = new File(sourcePath);
        File outputFile = new File(targetPath);
        try {
            InputStream docxInputStream = new FileInputStream(inputWord);
            OutputStream outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(docxInputStream)
                    .as(DocumentType.DOCX)
                    .to(outputStream)
                    .as(DocumentType.PDF).schedule().get();
            outputStream.close();
            docxInputStream.close();

            log.info("转换完毕 targetPath = {}", outputFile.getAbsolutePath()+",targetPath = " + outputFile.getAbsolutePath());
            converter.shutDown();
        } catch (Exception e) {
            log.error("word转pdf失败:"+e.getMessage(), e);
        }
    }

}
