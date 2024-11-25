package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.common.utils.UploadUtils;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Component
public class AvatarUploadProcessor {

    @Autowired
    UserBusiness userBusiness;
    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    SwAppendResourceBusiness resourceBusiness;

    public String updateAvatar(MultipartFile avatar) throws FileNotFoundException {
        if (avatar.isEmpty()) {
            throw new BizException(RetCode.EMPTY_FILE_ERROR);
        }
        String uploadType = "avatar";
        String fileBaseUrl = filePathUtils.getFilePath(uploadType);

        String path = UploadUtils.upload(fileBaseUrl, avatar);
        if (StringUtils.isNoneBlank(path)) {
            Integer resourceID = resourceBusiness.saveResource(path,
                    avatar.getOriginalFilename(),
                    uploadType,
                    RequestContext.getEmployeeNo());
            updateUserAvatar(resourceID);
            return resourceBusiness.getResourceUrl(resourceID);
        }
        return "";
    }

    public boolean updateUserAvatar(Integer resourceID) {
        String employeeNo = RequestContext.getEmployeeNo();
        return userBusiness.update(employeeNo, resourceID) > 0;
    }
}
