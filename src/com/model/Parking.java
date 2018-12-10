package com.model;

import java.time.Duration;
import java.util.Date;

public class Parking {

    private Vehicle vehicle;
    private Date startTimeStamp;
    ParkingSlot parkingSlot;
    private Date endTimeStamp;
    private Duration parkingduration;

    public Parking(Vehicle v, ParkingSlot parkingSlot, Date startDT, Date endDT)
    {
        this.vehicle = v;
        this.startTimeStamp = startDT;
        this.parkingSlot = parkingSlot;
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
    public ParkingSlot getParkingSlot()
    {
        return this.parkingSlot;
    }
    public Date getEndTimeStamp()
    {
        return this.endTimeStamp;
    }

    public void setEndTimeStamp(Date dt)
    {
        endTimeStamp = dt;
    }
}
