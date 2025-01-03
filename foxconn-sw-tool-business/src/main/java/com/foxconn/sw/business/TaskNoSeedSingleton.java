package com.foxconn.sw.business;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.entity.TaskNoSeed;
import com.foxconn.sw.data.entity.TaskNoSeedExample;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.extension.oa.TaskNoSeedExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.SYSTEM_ERROR;

@Component
public class TaskNoSeedSingleton {

    private final ReentrantLock lock = new ReentrantLock();

    @Autowired
    private TaskNoSeedExtensionMapper taskNoSeedExtensionMapper;

    private TaskNoSeedSingleton() {
    }

    public Long getTaskNo() {
        Long taskNumber = null;
        lock.lock();
        try {
            String date = DateTimeUtils.formatYMD();
            taskNumber = getLastSeed();

            if (!taskNumber.toString().startsWith(date)) {
                taskNumber = Long.valueOf(String.format("%s0000", date));
            }
            insertSeed(++taskNumber);
        } catch (Exception e) {
            throw new BizException(SYSTEM_ERROR);
        } finally {
            lock.unlock();
        }

        if (Objects.isNull(taskNumber)) {
            throw new BizException(SYSTEM_ERROR);
        }
        return taskNumber;
    }

    private boolean insertSeed(Long seed) {
        TaskNoSeed noSeed = new TaskNoSeed();
        noSeed.setSeed(seed);
        noSeed.setStatus(1);
        return taskNoSeedExtensionMapper.insertSelective(noSeed) > 0;
    }


    private Long getLastSeed() {
        TaskNoSeedExample example = new TaskNoSeedExample();
        example.setOrderByClause(" id desc limit 1");
        List<TaskNoSeed> seeds = taskNoSeedExtensionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(seeds)) {
            return 0L;
        }
        return seeds.get(0).getSeed();
    }
}
