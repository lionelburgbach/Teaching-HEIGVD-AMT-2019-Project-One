package ch.heigvd.amt.projectOne.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class Trail {

    private long id;
    private String name;
    private double length;
    private double upAndDown;
    private String description;
    private int capacity;
    private Date date;
}
