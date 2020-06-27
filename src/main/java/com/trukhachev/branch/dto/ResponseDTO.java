package com.trukhachev.branch.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseDTO {

    private long id;

    private String title;

    private double lon;

    private double lat;

    private String address;

    private int distance;
}
