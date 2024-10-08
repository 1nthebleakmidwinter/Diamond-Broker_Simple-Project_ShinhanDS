package com.personal.db;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class DiaDTO {
    private int dia_id;
    private double carat;
    private String cut;
    private String color;
    private String clarity;
    private double depth;
    private double table;
    private double x,y,z;
    private int price;
    private String owner;
}
