package com.foxconn.sw.data.dto.request.forums;

import java.util.List;

public class PostsParams {
    private String title;
    private String content;
    private Integer purview;
    private List<String> participants;
    private List<Integer> resources;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPurview() {
        return purview;
    }

    public void setPurview(Integer purview) {
        this.purview = purview;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<Integer> getResources() {
        return resources;
    }

    public void setResources(List<Integer> resources) {
        this.resources = resources;
    }
}
