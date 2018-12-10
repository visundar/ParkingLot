package com.model;

import java.util.Date;

public class ParkingSlot {
    private boolean available = true;
    //private Vehicle vehicle;
    private Date startTimeStamp;
    private int parkingSlotNumber;

    public ParkingSlot(int parkingSlotNumber)
    {
        this.parkingSlotNumber = parkingSlotNumber;
        this.available = true;
    }

    public Date getStartTimeStamp()
    {
        return startTimeStamp;
    }

    public int parkVehicle()
    {
        //System.out.println("Allocated slot number " +this.getParkingSlotNumber());
        this.available = false;
        return parkingSlotNumber;
    }

    public void unparkVehicle()
    {
        this.available = true;
    }

    public boolean isAvailable()
    {
        return available;
    }
    public int getParkingSlotNumber()
    {
        return parkingSlotNumber;
    }
    public void setStartTimeStamp(Date startDT)
    {
        this.startTimeStamp = startDT;
    }
}
