package com.foxconn.sw.business.collaboration;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.ExcelUtils;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressVo;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwCollaborationUser;
import com.foxconn.sw.data.entity.SwCollaborationUserExample;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.extension.oa.SwCollaborationUserExtensionMapper;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CollaborationUserBusiness {
    private static final Logger logger = LoggerFactory.getLogger(CollaborationUserBusiness.class);

    @Autowired
    FilePathUtils filePathUtils;
    @Autowired
    SwCollaborationUserExtensionMapper collaborationUserMapper;

    @Autowired
    SwTaskProgressBusiness progressBusiness;
    @Autowired
    SwAppendResourceBusiness resourceBusiness;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<SwCollaborationUser> queryCollaborationUser(Integer taskID) {
        SwCollaborationUserExample example = new SwCollaborationUserExample();
        SwCollaborationUserExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        criteria.andIsDeleteEqualTo(0);
        return collaborationUserMapper.selectByExample(example);
    }

    public List<SwCollaborationUser> queryCollaborationUser(Integer taskID, String employeeNo) {
        SwCollaborationUserExample example = new SwCollaborationUserExample();
        SwCollaborationUserExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        criteria.andEmployeeNoEqualTo(employeeNo);
        criteria.andIsDeleteEqualTo(0);
        return collaborationUserMapper.selectByExample(example);
    }


    public List<SwCollaborationUser> queryCollaborationUser(List<Long> ids) {
        SwCollaborationUserExample example = new SwCollaborationUserExample();
        SwCollaborationUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        criteria.andEmployeeNoEqualTo(RequestContext.getEmployeeNo());
        criteria.andIsDeleteEqualTo(0);
        return collaborationUserMapper.selectByExample(example);
    }

    public List<SwCollaborationUser> queryCollaborationUser(Integer taskId, List<Long> ids) {
        SwCollaborationUserExample example = new SwCollaborationUserExample();
        SwCollaborationUserExample.Criteria criteria = example.createCriteria();
        if (!CollectionUtils.isEmpty(ids)) {
            criteria.andIdIn(ids);
        }
        criteria.andTaskIdEqualTo(taskId);
        criteria.andIsDeleteEqualTo(0);
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


    public Long acceptTask(Integer taskID, String employeeNo) {
        SwCollaborationUser user = new SwCollaborationUser();
        user.setTaskId(taskID);
        user.setEmployeeNo(employeeNo);
        collaborationUserMapper.insertSelective(user);
        return user.getId();
    }


    public ResourceVo getResourceVo(Integer taskID) throws FileNotFoundException {
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

        ResourceVo resourceVo = new ResourceVo();
        resourceVo.setId(appendResource.getId());
        resourceVo.setName(appendResource.getOriginName());
        resourceVo.setUrl(ConvertUtils.urlPreFix(appendResource.getId(), appendResource.getFilePath()));
        resourceVo.setFilePath(filePathUtils.getFilePath(appendResource.getUploadType()) + appendResource.getFilePath());
        return resourceVo;
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
                result.add(ExcelUtils.getCellValueAsString(cell));
            }
        } catch (IOException e) {
            logger.error("getTaskHeader", e);
        }
        return result;
    }

    public List<String> getExcelContent(Integer taskID) throws FileNotFoundException {
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
                result.add(ExcelUtils.getCellValueAsString(cell));
            }
        } catch (IOException e) {
            logger.error("getTaskHeader", e);
        }
        return result;
    }

    public boolean updateEvaluation(SwCollaborationUser user, Integer evaluationType) {
        user.setStatus(evaluationType);
        user.setStatus(evaluationType);
        return collaborationUserMapper.updateByPrimaryKeySelective(user) > 0;
    }

    public Boolean updateUser(SwCollaborationUser user) {
        return collaborationUserMapper.updateByPrimaryKeySelective(user) > 0;
    }

    public Boolean deleteCollaborationUser(List<Long> ids) {
        SwCollaborationUser record = new SwCollaborationUser();
        record.setIsDelete(1);

        SwCollaborationUserExample example = new SwCollaborationUserExample();
        SwCollaborationUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return collaborationUserMapper.updateByExampleSelective(record, example) > 0;
    }

    public Long insertCollaborationUser(SwCollaborationUser collaborationUser) {
        collaborationUserMapper.insertSelective(collaborationUser);
        return collaborationUser.getId();
    }

    public List<Long> queryCollaborationUserIds(Integer taskId) {
        SwCollaborationUserExample example = new SwCollaborationUserExample();
        SwCollaborationUserExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        criteria.andEmployeeNoEqualTo(RequestContext.getEmployeeNo());
        criteria.andIsDeleteEqualTo(0);
        List<SwCollaborationUser> users = collaborationUserMapper.selectByExample(example);
        return Optional.ofNullable(users)
                .orElse(Lists.newArrayList())
                .stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
    }


    public boolean insertBatchCollaborationUser(List<Map<String, String>> maps, Integer taskId) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        maps.stream().forEach(e -> {
            SwCollaborationUser user = new SwCollaborationUser();
            user.setTaskId(taskId);
            user.setEmployeeNo(RequestContext.getEmployeeNo());
            collaborationUserMapper.insertSelective(user);
        });
        sqlSession.commit();
        sqlSession.close();
        return true;
    }
}
