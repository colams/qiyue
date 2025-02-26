package com.foxconn.sw.data.mapper.extension;

import com.foxconn.sw.data.entity.extension.SwAnnouncementExtension;
import com.foxconn.sw.data.mapper.auto.SwAnnouncementMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwAnnouncementExtensionMapper extends SwAnnouncementMapper {
    @Select({"<script>",
            "SELECT a.*, r.id as rid\n",
            "FROM sw_announcement a\n",
            "         left join sw_read_status r on r.foreign_id = a.id\n",
            "    and r.module_type = 'Announcement'\n",
            "    and r.employee_no = #{employeeNo,jdbcType=VARCHAR} ",
            "where a.is_delete = 0\n",
            "  and a.status = 'R'",
            "</script>"})
    List<SwAnnouncementExtension> selectAnnounces(String employeeNo);
}
