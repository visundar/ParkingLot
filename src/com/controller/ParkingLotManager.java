package com.controller;

import com.model.Parking;
import com.model.ParkingSlot;
import com.model.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Date;

public class ParkingLotManager {

    private List<ParkingSlot> parkingSlots;
    private Queue<Vehicle> vehiclesWaiting = new PriorityQueue<Vehicle>();
    private List<Parking> parkedVehicles = new ArrayList<>();
    private static ParkingLotManager parkingLotinstance = null;

    public static ParkingLotManager getParkingLotinstance() {
        if(parkingLotinstance == null)
            parkingLotinstance = new ParkingLotManager();
        return parkingLotinstance;
    }

    public boolean isParkingFull()
    {
        boolean parkingFull = true;
        for(ParkingSlot ps:parkingSlots)
        {
            if(ps.isAvailable()) {
                parkingFull = false;
                break;
            }
        }
        return parkingFull;
    }

    public void park(Vehicle v)
    {
        if(isParkingFull()) {
            vehiclesWaiting.add(v);
            System.out.printf("Vehicle with reg number %s waiting for parking.. \n",v.getRegistrationNum());
            return;
        }
        for(ParkingSlot ps:parkingSlots)
        {
            if(ps.isAvailable()) {
                ps.parkVehicle(v);
                break;
            }
        }
    }

    public void leave(int slotNumber)
    {
        System.out.printf("Slot number %s is free \n", slotNumber);

        for(ParkingSlot ps:parkingSlots)
        {
            if(ps.getParkingSlotNumber() == slotNumber) {
                Parking p = new Parking(ps.getParkedVehicleDetails(),ps.getStartTimeStamp(),ps.getParkingSlotNumber(),new Date());
                parkedVehicles.add(p);
                ps.unparkVehicle();
            }
        }

        checkVehiclesWaitingForParking();

    }
    public void checkVehiclesWaitingForParking()
    {
        if(vehiclesWaiting.size() > 0)
            park(vehiclesWaiting.poll());
       // System.out.println("Number of vehicles waiting to get space "+vehiclesWaiting.size());
    }
    public void issueTicket()
    {

    }
    public void getRegistrationNumbersForColor(String color)
    {
        StringBuilder regNumConcat= new StringBuilder();

        for(ParkingSlot ps:parkingSlots)
        {
            if(!ps.isAvailable()) {
               Vehicle v =   ps.getParkedVehicleDetails();
               if(v.getColor().toLowerCase().trim().equals(color.toLowerCase().trim()))
                   if(regNumConcat.toString().isEmpty())
                       regNumConcat.append(ps.getParkedVehicleDetails().getRegistrationNum());
                   else
                       regNumConcat.append(", ").append(ps.getParkedVehicleDetails().getRegistrationNum());
            }
        }
        if(regNumConcat.toString().isEmpty())
            System.out.println("Not found");
        else
            System.out.println(regNumConcat);
    }

    public void getSlotNumbersForColor(String color)
    {
        StringBuilder slotNumConcat= new StringBuilder("");

        for(ParkingSlot ps:parkingSlots)
        {
            if(!ps.isAvailable()) {
                Vehicle v =   ps.getParkedVehicleDetails();
                if(v.getColor().toLowerCase().trim().equals(color.toLowerCase().trim()))
                    if(slotNumConcat.toString().isEmpty())
                        slotNumConcat.append(ps.getParkingSlotNumber()) ;
                     else
                         slotNumConcat.append(", ").append(ps.getParkingSlotNumber());
            }
        }
        if(slotNumConcat.toString().isEmpty())
            System.out.println("Not found");
        else
          System.out.println(slotNumConcat);
    }

    public void getSlotNumberForReg(String regNumber)
    {
        StringBuilder slotNumConcat= new StringBuilder("");

        for(ParkingSlot ps:parkingSlots)
        {
            if(!ps.isAvailable()) {
                Vehicle v =   ps.getParkedVehicleDetails();
                if(v.getColor().toLowerCase().trim().replaceAll(" ","").equals(regNumber.toLowerCase().trim().replaceAll(" ","")))
                    if(slotNumConcat.toString().isEmpty())
                        slotNumConcat.append(ps.getParkingSlotNumber()) ;
                    else
                        slotNumConcat.append(", ").append(ps.getParkingSlotNumber());
            }
        }
        if(slotNumConcat.toString().isEmpty())
            System.out.println("Not found");
        else
            System.out.println(slotNumConcat);
    }

    public void printParkingLotStatus()
    {
        System.out.println("Slot No.     Registration No     Colour");
        for(ParkingSlot ps:parkingSlots) {
            if(!ps.isAvailable())
            System.out.println(ps.getParkingSlotNumber() + "      " + ps.getParkedVehicleDetails().getRegistrationNum() + "       " + ps.getParkedVehicleDetails().getColor());
        }
    }

    public void initParkingLot(int slotCount)
    {
        this.parkingSlots = new ArrayList<>(slotCount);
        // allocate parkingSlot Number and initialize
        for(int i=1;i<=slotCount;i++)
        {
        ParkingSlot parkingSlot= new ParkingSlot(i);
        this.parkingSlots.add(parkingSlot);
        //System.out.println("Allocated slot number: "+i);
        }
        //this.printParkingLotStatus();
    }

    public void getHistory()
    {

    }
}
