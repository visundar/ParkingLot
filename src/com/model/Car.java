package com.model;

public class Car extends Vehicle{
        protected int slotsNeeded = 1;
        public Car(String regNum, String color)
        {
            this.color = color;
            this.registrationNum = regNum;
        }
}
