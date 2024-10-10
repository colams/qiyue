package com.foxconn.sw.service.processor.universal;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.common.utils.UploadUtils;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.entity.universal.UploadResult;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.common.utils.FilePathUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UploadProcessor {

    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    SwAppendResourceBusiness resourceBusiness;

    public List<UploadResult> uploadFiles(MultipartFile[] fileList, String uploadType) throws FileNotFoundException {
        if (fileList.length <= 0) {
            throw new BizException(RetCode.EMPTY_FILE_ERROR);
        }

        if (StringUtils.isBlank(uploadType)) {
            throw new BizException(RetCode.UPLOAD_FILE_TYPE_ERROR);
        }

        String fileBaseUrl = filePathUtils.getFilePath(uploadType);

        List<UploadResult> uploadResults = new ArrayList<>();
        for (MultipartFile file : fileList) {
            String path = UploadUtils.upload(fileBaseUrl, file);
            int resourceID = 0;

            if (StringUtils.isNoneBlank(path)) {
                resourceID = resourceBusiness.saveResource(path, file.getOriginalFilename(), uploadType);
            }

            UploadResult result = new UploadResult();
            result.setFilePath(path);
            result.setFileId(resourceID);
            uploadResults.add(result);
        }
        return uploadResults;
    }
}
