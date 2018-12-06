package com.model;

import java.util.Date;

public class Parking {
    private Vehicle vehicle;
    private Date startTimeStamp;
    private int parkingSlotNumber;
    private Date endTimeStamp;

    public Parking(Vehicle v, Date startDT, int parkingSlotNum, Date endDT)
    {
        this.vehicle = v;
        this.startTimeStamp = startDT;
        this.parkingSlotNumber = parkingSlotNum;
        this.endTimeStamp = endDT;
    }
}
