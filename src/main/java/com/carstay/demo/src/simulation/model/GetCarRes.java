package com.carstay.demo.src.simulation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCarRes {
    GetCarRes() {}
    private int carNum;
    private String carBrand;
    private String carName;
    private float carWidth;
    private float carLength;
    private float carHeight;
}
