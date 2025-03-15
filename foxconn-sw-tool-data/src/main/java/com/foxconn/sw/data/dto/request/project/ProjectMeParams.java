package com.foxconn.sw.data.dto.request.project;

import lombok.Data;

@Data
public class ProjectMeParams {
    private Integer id;

    private String pixel;

    private String resolution;

    private String pack;

    private String moduleSize;

    private String focusType;

    private Integer bondPadQuantity;

    private String sensorActiveArea;

    private String efl;

    private String fNo;

    private String cubeSizw;

    private String irTopSensorDist;

    private String type;

    private String glassSupplier;

    private String blackMask;

    private String glass;

    private String stiffenerSupplier;

    private String stiffenerThickness;

    private String stiffener3d2d;

}
