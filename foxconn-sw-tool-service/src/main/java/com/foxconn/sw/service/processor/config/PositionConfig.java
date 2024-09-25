package com.foxconn.sw.service.processor.config;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionConfig {

    public static String getPosition(String employeeNo) {
        Map<String, String> map = new HashMap<>();
        map.put("PL1910055", "資深經理");
        map.put("PL1910121", "資深副理");
        map.put("G1654668", "副課長");
        map.put("G1651074", "副課長");
        map.put("F2827381", "經理");
        map.put("F1655140", "專理");
        map.put("G1650067", "課長");
        map.put("G1654631", "工程師");
        map.put("F1678218", "課長");
        map.put("F2829504", "專理");
        map.put("G1654707", "副課長");
        map.put("F1679627", "經理");
        map.put("F1668046", "資深專理");
        map.put("F1669075", "專理");
        map.put("G1651084", "副課長");
        map.put("G1653388", "工程師");
        map.put("G1653619", "工程師");
        map.put("F1648344", "資深副理");
        map.put("F1676766", "副課長");
        map.put("F9233118", "專理");
        map.put("F1631845", "專理");
        map.put("FSUS140", "工程師");
        map.put("G1655080", "資深專理");
        map.put("F1652662", "課長");
        map.put("G1655716", "工程師");
        map.put("G1655614", "副課長");
        map.put("PL2210065", "工程師");
        map.put("F1669092", "副課長");
        map.put("PL1910091", "經理");
        map.put("F5400084", "資深專理");
        map.put("F1666045", "專理");
        map.put("G1650865", "副課長");
        map.put("F1669081", "課長");
        return map.getOrDefault(employeeNo, "工程師");
    }

    public static String getPosition2(String employeeNo) {
        Map<String, String> map = new HashMap<>();
        map.put("PL1910055", "資深經理");
        map.put("PL1910121", "資深副理");
        map.put("G1654668", "副課長");
        map.put("G1651074", "副課長");
        map.put("F2827381", "經理");
        map.put("F1655140", "專理");
        map.put("G1650067", "課長");
        map.put("G1654631", "工程師");
        map.put("F1678218", "課長");
        map.put("F2829504", "專理");
        map.put("G1654707", "副課長");
        map.put("F1679627", "經理");
        map.put("F1668046", "資深專理");
        map.put("F1669075", "專理");
        map.put("G1651084", "副課長");
        map.put("G1653388", "工程師");
        map.put("G1653619", "工程師");
        map.put("F1648344", "資深副理");
        map.put("F1676766", "副課長");
        map.put("F9233118", "專理");
        map.put("F1631845", "專理");
        map.put("FSUS140", "工程師");
        map.put("G1655080", "資深專理");
        map.put("F1652662", "課長");
        map.put("G1655716", "工程師");
        map.put("G1655614", "副課長");
        map.put("PL2210065", "工程師");
        map.put("F1669092", "副課長");
        map.put("PL1910091", "經理");
        map.put("F5400084", "資深專理");
        map.put("F1666045", "專理");
        map.put("G1650865", "副課長");
        map.put("F1669081", "課長");
        return map.getOrDefault(employeeNo, "工程師");
    }

    public static List<String> getPositionEmployees(List<Integer> positionIds) {
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(2, Lists.newArrayList("PL1910055"));
        map.put(3, Lists.newArrayList("PL1910121", "F2827381", "F1679627", "G1655080", "PL2210065", "PL1910091"));
        map.put(4, Lists.newArrayList("G1654668", "G1651074", "F1655140", "G1650067", "G1654631", "F1678218", "F2829504", "G1654707", "F1668046", "F1669075", "G1651084", "G1653388", "G1653619", "F1648344", "F1676766", "F9233118", "F1631845", "FSUS140", "F1652662", "G1655716", "G1655614", "F1669092", "F5400084", "F1666045", "G1650865", "F1669081"));

        List<String> employeeNos = new ArrayList<>();
        positionIds.forEach(e -> {
            employeeNos.addAll(map.getOrDefault(e, Lists.newArrayList()));
        });
        return employeeNos;
    }

}
