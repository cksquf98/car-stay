package com.carstay.demo.src.simulation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class GetSimulationRes {
    GetSimulationRes() { }
    public GetSimulationRes(String friendId) { }

    private String userId;
    private float xLocation;
    private float yLocation;
    private String itemImage;
    private String itemName;
    private Float itemWidth;
    private Float itemHeight;
    private String carName;
    private float carWidth;
    private float carLength;
    private float carHeight;




}
