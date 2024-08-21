package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.data.dto.entity.system.PostVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetPostListProcessor {

    public List<PostVo> getPostList() {
        List<PostVo> postVos = new ArrayList<>();
        postVos.add(new PostVo(1, "初级工程师"));
        postVos.add(new PostVo(2, "中级工程师"));
        return postVos;
    }

}
