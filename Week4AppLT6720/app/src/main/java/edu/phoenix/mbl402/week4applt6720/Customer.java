package edu.phoenix.mbl402.week4applt6720;

// Creator: Larissa Thompson
// Course: MBL/402
// Created Date: 5/29/2017
// App Description: Application uses a local database to track customers at a car lot. Uses the SQLite
//      abilities of Android.
// Class Description: Object Class to hold a customer

public class Customer {

    private int customerID;
    private String firstName;
    private String lastName;
    private String carMake;
    private String carModel;
    private double carCost;

    public Customer(String firstName, String lastName, String carMake, String carModel, double carCost) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carCost = carCost;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public double getCarCost() {
        return carCost;
    }

    public void setCarCost(double carCost) {
        this.carCost = carCost;
    }
}
