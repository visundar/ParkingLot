package com.model;

import java.util.Date;

public class ParkingSlot {
    private boolean available = true;
    private Vehicle vehicle;
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

    public Vehicle getParkedVehicleDetails()
    {
        return vehicle;
    }

    public int parkVehicle(Vehicle vehicle)
    {
        System.out.println("Allocated slot number " +this.getParkingSlotNumber());
        this.available = false;
        this.vehicle = vehicle;
        this.startTimeStamp = new Date();
        return parkingSlotNumber;
    }

    public void unparkVehicle()
    {
        this.available = true;
        this.vehicle = null;
        this.startTimeStamp = null;
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
