package com.foxconn.sw.service.processor.universal;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.UploadUtils;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.entity.universal.UploadResult;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
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
    @Autowired
    CommonUserUtils commonUserUtils;

    public List<UploadResult> uploadFiles(MultipartFile[] fileList, String uploadType, String reqJson) throws FileNotFoundException {
        String operator = getCurrentEmployeeNo(reqJson);
        if (fileList.length <= 0) {
            throw new BizException(RetCode.EMPTY_FILE_ERROR);
        }

        if (StringUtils.isBlank(uploadType)) {
            throw new BizException(RetCode.UPLOAD_FILE_TYPE_ERROR);
        }

        String fileBaseUrl = filePathUtils.getFilePath(uploadType);

        List<UploadResult> uploadResults = new ArrayList<>();
        int index = 0;
        for (MultipartFile file : fileList) {
            String path = UploadUtils.upload(fileBaseUrl, file, index++);
            int resourceID = 0;

            if (StringUtils.isNoneBlank(path)) {
                resourceID = resourceBusiness.saveResource(path, file.getOriginalFilename(), uploadType, operator);
            }

            UploadResult result = new UploadResult();
            result.setFilePath(path);
            result.setFileId(resourceID);
            uploadResults.add(result);
        }
        return uploadResults;
    }

    private String getCurrentEmployeeNo(String reqJson) {
        if (StringUtils.isEmpty(reqJson)) {
            return "sys";
        }
        Request<IntegerParams> request = JsonUtils.deserialize(reqJson, Request.class, IntegerParams.class);
        UserInfo userInfo = commonUserUtils.queryUserInfo(request.getHead().getToken());
        return userInfo.getEmployeeNo();
    }
}
