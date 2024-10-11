package com.foxconn.sw.business.collaboration;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressVo;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwCollaborationUser;
import com.foxconn.sw.data.entity.SwCollaborationUserExample;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.extension.oa.SwCollaborationUserExtensionMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
public class CollaborationUserBusiness {
    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    SwCollaborationUserExtensionMapper collaborationUserMapper;

    @Autowired
    SwTaskProgressBusiness progressBusiness;
    @Autowired
    SwAppendResourceBusiness resourceBusiness;

    public List<SwCollaborationUser> queryCollaborationUser(Integer taskID) {
        SwCollaborationUserExample example = new SwCollaborationUserExample();
        SwCollaborationUserExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        return collaborationUserMapper.selectByExample(example);
    }

    public List<SwCollaborationUser> queryCollaborationUser(List<Long> ids) {
        SwCollaborationUserExample example = new SwCollaborationUserExample();
        SwCollaborationUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return collaborationUserMapper.selectByExample(example);
    }

    public SwCollaborationUser queryCollaborationUserByID(Long id) {
        return collaborationUserMapper.selectByPrimaryKey(id);
    }


    public boolean acceptTask(SwTask task) {
        SwCollaborationUser user = new SwCollaborationUser();
        user.setTaskId(task.getId());
        user.setEmployeeNo(RequestContext.getEmployeeNo());
        return collaborationUserMapper.insertSelective(user) > 0;
    }


    public List<String> getTaskHeader(Integer taskID) throws FileNotFoundException {
        List<TaskProgressVo> progressVos = progressBusiness.selectTaskProcess(taskID);
        TaskProgressVo vo = progressVos.stream()
                .filter(e -> !CollectionUtils.isEmpty(e.getResourceIds()))
                .sorted(Comparator.comparing(TaskProgressVo::getCreateTime))
                .findFirst()
                .orElse(null);
        if (Objects.isNull(vo)) {
            throw new BizException(4, "参数错误");
        }
        SwAppendResource appendResource = resourceBusiness.getAppendResources(vo.getResourceIds().get(0));
        String filePath = filePathUtils.getFilePath(appendResource.getUploadType()) + appendResource.getFilePath();
        List<String> result = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Cell cell : sheet.getRow(0)) {
                result.add(cell.getStringCellValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateEvaluation(SwCollaborationUser user, Integer evaluationType) {
        user.setStatus(evaluationType);
        return collaborationUserMapper.updateByPrimaryKeySelective(user) > 0;
    }

    public Boolean updateUser(SwCollaborationUser user) {
        return collaborationUserMapper.updateByPrimaryKeySelective(user) > 0;
    }
}
