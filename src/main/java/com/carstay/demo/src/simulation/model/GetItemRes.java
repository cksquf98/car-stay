package com.carstay.demo.src.simulation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class GetItemRes {
    GetItemRes() {}
    private int itemNum;
    private String itemType;
    private String itemName;
    private String itemSite;
    private String itemImage;
    private float itemWidth;
    private float itemHeight;

}
