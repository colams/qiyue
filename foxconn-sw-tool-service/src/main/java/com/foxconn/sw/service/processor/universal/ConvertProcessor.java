package com.foxconn.sw.service.processor.universal;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.common.utils.PptxToPdfConverter;
import com.foxconn.sw.data.entity.SwAppendResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConvertProcessor {

    @Autowired
    SwAppendResourceBusiness resourceBusiness;
    @Autowired
    FilePathUtils filePathUtils;

    public byte[] convertFile(Integer fileId) throws IOException {
        SwAppendResource resource = resourceBusiness.getAppendResources(fileId);
        String filePath = filePathUtils.getFilePath(resource.getUploadType()) + resource.getFilePath();
        if (filePath.endsWith("ppt")) {
            return PptxToPdfConverter.ppt2Pdf(filePath);
        } else if (filePath.endsWith("pptx")) {
            return PptxToPdfConverter.pptx2Pdf(filePath);
        } else if (filePath.endsWith("docx") || filePath.endsWith("doc")) {
            return PptxToPdfConverter.wordToPdf(filePath);
        }
        return null;
    }

}
