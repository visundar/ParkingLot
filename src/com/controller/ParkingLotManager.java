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
    private List<Parking> activeParking = new ArrayList<>();
    private List<Parking> parkedVehiclesHistory = new ArrayList<>();

    private static ParkingLotManager parkingLotinstance = null;
    private boolean parkingLotInitialized = false;

    public ParkingLotManager()
    {
        super();
    }
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
        if(!parkingLotInitialized)
            return;
        if(isParkingFull()) {
            vehiclesWaiting.add(v);
            System.out.printf("Vehicle with reg number %s waiting for parking.. \n",v.getRegistrationNum());
            return;
        }
        for(ParkingSlot ps:parkingSlots)
        {
            if(ps.isAvailable()) {
                int slotNumber =  ps.parkVehicle();
                System.out.println("Allocated slot number " +slotNumber);
                Parking parking = new Parking(v, ps, new Date(),null);
                activeParking.add(parking);
                break;
            }
        }
    }

    public void leave(int slotNumber)
    {
        if(!parkingLotInitialized)
            return;
        Parking parkingToLeave = null;
        for(Parking p: activeParking)
        {
            ParkingSlot ps = p.getParkingSlot();
            if(ps.getParkingSlotNumber() == slotNumber) {
                p.setEndTimeStamp(new Date());
                ps.unparkVehicle();
                parkedVehiclesHistory.add(p);
		        parkingToLeave = p;
            }
        }
        if(parkingToLeave == null)
            System.out.printf("Slot number %s might be free \n",slotNumber);
        else
            System.out.printf("Slot number %s is free \n", slotNumber);

        activeParking.remove(parkingToLeave);
        //System.out.println("Count of active parking:"+activeParking.size());
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
    public String getRegistrationNumbersForColor(String color)
    {
        StringBuilder regNumConcat= new StringBuilder();

        for(Parking p: activeParking)
        {
            ParkingSlot ps = p.getParkingSlot();
            if(!ps.isAvailable() && p.getVehicle().getColor().toLowerCase().trim().equals(color.toLowerCase().trim()))
                if(regNumConcat.toString().isEmpty())
                    regNumConcat.append(p.getVehicle().getRegistrationNum());
                else
                    regNumConcat.append(", ").append(p.getVehicle().getRegistrationNum());
        }

        if(regNumConcat.toString().isEmpty())
            System.out.println("Not found");
        else
            System.out.println(regNumConcat);
        return regNumConcat.toString();
    }

    public void getSlotNumbersForColor(String color)
    {
        StringBuilder slotNumConcat= new StringBuilder("");

        for(Parking p: activeParking)
        {
            ParkingSlot ps = p.getParkingSlot();
            if(!ps.isAvailable() && p.getVehicle().getColor().toLowerCase().trim().equals(color.toLowerCase().trim()))
                if(slotNumConcat.toString().isEmpty())
                    slotNumConcat.append(ps.getParkingSlotNumber());
                else
                    slotNumConcat.append(", ").append(ps.getParkingSlotNumber());

        }
        if(slotNumConcat.toString().isEmpty())
            System.out.println("Not found");
        else
          System.out.println(slotNumConcat);
    }

    public void getSlotNumberForReg(String regNumber)
    {
        StringBuilder slotNumConcat= new StringBuilder("");

        for(Parking p: activeParking)
        {
            if(!p.getParkingSlot().isAvailable()) {
                Vehicle v =   p.getVehicle();
                if(v.getRegistrationNum().toLowerCase().trim().replaceAll(" ","").equals(regNumber.toLowerCase().trim().replaceAll(" ","")))
                    if(slotNumConcat.toString().isEmpty())
                        slotNumConcat.append(p.getParkingSlot().getParkingSlotNumber()) ;
                    else
                        slotNumConcat.append(", ").append(p.getParkingSlot().getParkingSlotNumber());
            }
        }
        if(slotNumConcat.toString().isEmpty())
            System.out.println("Not found");
        else
            System.out.println(slotNumConcat);
    }

    public void printParkingLotStatus()
    {
        if(!parkingLotInitialized)
            return;
        System.out.println("Slot No.     Registration No     Colour");
        for(Parking p: activeParking) {
            if(!p.getParkingSlot().isAvailable())
            System.out.println(p.getParkingSlot().getParkingSlotNumber() + "      " + p.getVehicle().getRegistrationNum() + "       " + p.getVehicle().getColor());
        }
    }

    public int initParkingLot(int slotCount)
    {
        this.parkingSlots = new ArrayList<>(slotCount);
        // allocate parkingSlot Number and initialize
        for(int i=1;i<=slotCount;i++)
        {
        ParkingSlot parkingSlot= new ParkingSlot(i);
        this.parkingSlots.add(parkingSlot);
        //System.out.println("Allocated slot number: "+i);
        }
        parkingLotInitialized = true;
        return slotCount;
        //this.printParkingLotStatus();
    }

    public boolean isParkingLotInitialized()
    {
        return parkingLotInitialized;
    }
    public void getHistory()
    {
        System.out.println("Slot No.     Registration No     Colour          StartDT                     EndDT");

        for(Parking p: parkedVehiclesHistory)
        {
            System.out.println(p.getParkingSlot().getParkingSlotNumber() + "         " + p.getVehicle().getRegistrationNum() + "       " + p.getVehicle().getColor()+"      "+p.getStartTimeStamp()+"       "+p.getEndTimeStamp());
        }
    }
}
