package com.model;

import java.time.Duration;
import java.util.Date;

public class Parking {

    private Vehicle vehicle;
    private Date startTimeStamp;
    private int parkingSlotNumber;
    private Date endTimeStamp;
    private Duration parkingduration;

    public Parking(Vehicle v, Date startDT, int parkingSlotNum, Date endDT)
    {
        this.vehicle = v;
        this.startTimeStamp = startDT;
        this.parkingSlotNumber = parkingSlotNum;
        this.endTimeStamp = endDT;
    }
    public Vehicle getVehicle()
    {
        return this.vehicle;
    }
    public Date getStartTimeStamp()
    {
        return this.startTimeStamp;
    }
    public int getParkingSlotNumber()
    {
        return this.parkingSlotNumber;
    }
    public Date getEndTimeStamp()
    {
        return this.endTimeStamp;
    }
}
