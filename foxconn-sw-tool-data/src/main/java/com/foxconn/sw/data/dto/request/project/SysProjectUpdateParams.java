package com.foxconn.sw.data.dto.request.project;

public class SysProjectUpdateParams {

    private Integer id;

    private ProjectMeParams projectMeParams;

    private ProjectNpdParams projectNpdParams;

    private ProjectOtParams projectOtParams;

    private ProjectEeParams projectSensor;

    private ProjectTeParams projectTe;

    public Integer getDri() {
        return dri;
    }

    public void setDri(Integer dri) {
        this.dri = dri;
    }

    private Integer dri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProjectMeParams getProjectMeParams() {
        return projectMeParams;
    }

    public void setProjectMeParams(ProjectMeParams projectMeParams) {
        this.projectMeParams = projectMeParams;
    }

    public ProjectNpdParams getProjectNpdParams() {
        return projectNpdParams;
    }

    public void setProjectNpdParams(ProjectNpdParams projectNpdParams) {
        this.projectNpdParams = projectNpdParams;
    }

    public ProjectOtParams getProjectOtParams() {
        return projectOtParams;
    }

    public void setProjectOtParams(ProjectOtParams projectOtParams) {
        this.projectOtParams = projectOtParams;
    }

    public ProjectEeParams getProjectSensor() {
        return projectSensor;
    }

    public void setProjectSensor(ProjectEeParams projectSensor) {
        this.projectSensor = projectSensor;
    }

    public ProjectTeParams getProjectTe() {
        return projectTe;
    }

    public void setProjectTe(ProjectTeParams projectTe) {
        this.projectTe = projectTe;
    }
}
