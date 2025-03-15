package com.foxconn.sw.data.dto.request.project;

import lombok.Data;

@Data
public class ProjectEeParams {
    private Integer id;

    private String sensorSupplier;

    private Integer bondPadQuantity;

    private String opticalFormat;

    private String sensorModel;

    private String imagPixelSize;

    private Integer cra;

    private String monoRgb;

    private Integer dieThickness;

    private Integer sensorPadQuantity;

    private String dieSize;

    private String connSupplier;

    private String connModel;

    private Integer pitch;

    private String fpcRepcsSupplier;

    private Integer layers;

    private String flexBarcode;

    private String type;

}
