/*
 * Created on Wed Dec 09 2019
 * 
 *  Model Class for Car
 * 
 *  Author Vinayak K S
 */
package model;

public class CarModel {
    String registeredNumber;
    String carColour;

    
    public CarModel(String registeredNumber, String carColour) {
        this.registeredNumber = registeredNumber;
        this.carColour = carColour;
    }

    public CarModel() {
    }

    public String getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public String getCarColour() {
        return carColour;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    @Override
    public String toString() {
        return "CarModel [carColour=" + carColour + ", registeredNumber=" + registeredNumber + "]";
    }
    

    
    
}