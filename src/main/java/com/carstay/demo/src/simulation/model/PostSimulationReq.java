package com.carstay.demo.src.simulation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostSimulationReq {
    private float xLocation;
    private float yLocation;
    private int itemNum;
    private int carNum;
}
