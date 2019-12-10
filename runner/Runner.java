/*
 * Created on Wed Dec 09 2019
 * 
 *  Program to perform actions on Parking Slot For ClassKlap
 * 
 *  Assumptions considered for the problem:
 *        1) The parking area has only one common entry / exit point, hence operations performed with Single Thread
 *    
 *        
 *  Author Vinayak K S
 */

package runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.CarModel;
import model.ParkingSlotModel;

// Commands that user can execute from CLI
enum Commands {
    create_parking_lot, park, leave, status, registration_numbers_for_cars_with_colour,
    slot_numbers_for_cars_with_colour, slot_number_for_registration_number
}

class Runner {

    private static ParkingSlotModel parkingSlotModel = ParkingSlotModel.getInstanceModel();
    static Commands commands;

    public static void main(String[] args) throws IOException {
        System.out.print("PLease enter the Commands\n");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while ((command = bufferedReader.readLine()) != null) {
            String commandArray[] = command.split(" ");
            commands = Commands.valueOf(commandArray[0]);
            switch (commands) {
            case create_parking_lot:
                try {
                    int n = Integer.parseInt(commandArray[1]);
                    createParkingSlot(n);
                } catch (Exception e) {
                    throw e;
                }
                break;
            case park:
                parkCar(commandArray[1], commandArray[2]);
                break;
            case status:
                avaialbleSlots();
                break;
            case leave:
                try {
                    int slotNo = Integer.parseInt(commandArray[1]);
                    leaveCar(slotNo);

                } catch (Exception e) {
                    System.out.println("Please enter the valid slot no");
                }
                break;
            case registration_numbers_for_cars_with_colour:
                findCarsWithColours(commandArray[1]);
                break;
            case slot_numbers_for_cars_with_colour:
                findSlotNumberswithColour(commandArray[1]);
                break;
            case slot_number_for_registration_number:
                findSlotNumberWithRegNo(commandArray[1]);
                break;
            default:
                System.out.println("Please enter valid command");
            }
        }
        System.out.println();
    }

    
    /** 
     * creates the parking area with n slots
     * @param n
     */
    public static void createParkingSlot(int n) {
        try {

            parkingSlotModel.getAvaialbleSlots()
                    .addAll(IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList()));

            System.out.println(String.format("Created a parking lot with %d slots", n));

        } catch (Exception e) {
            System.out.println("Sorry Cannot create the parking slots");
        }

    }

    
    /** 
     * this method allots the parking slot if available
     * @param regNo
     * @param carColour
     */
    public static void parkCar(String regNo, String carColour) {

        if (parkingSlotModel.getAvaialbleSlots().size() > 0) {
            CarModel carModel = new CarModel(regNo, carColour);
            int ticketId = (Integer) parkingSlotModel.getAvaialbleSlots().first();
            parkingSlotModel.getTickets().put(ticketId, carModel);
            parkingSlotModel.getAvaialbleSlots().remove(ticketId);
            System.out.println(String.format("Allocated slot number: %d", ticketId));
        } else {
            System.out.println("Sorry, parking lot is full");
        }

    }

    
    /** 
     * this method clears the slot when car is left
     * @param slotNo
     */
    public static void leaveCar(int slotNo) {
        if (parkingSlotModel.getAvaialbleSlots().size() <= 0) {
            System.out.println("Sorry, No Cars to Leave");
        } else {
            parkingSlotModel.getAvaialbleSlots().add(slotNo);
            parkingSlotModel.getTickets().remove(slotNo);
            System.out.println(String.format("Slot number %d is free", slotNo));
        }

    }

    /** 
     * this method prints the all the available slots
     * 
     */
    public static void avaialbleSlots() {
        System.out.println(String.format("%s%16s%12s", "Slot No.", "Registration No", "Color"));
        System.out.println();
        for (Integer key : parkingSlotModel.getTickets().keySet()) {
            CarModel model = parkingSlotModel.getTickets().get(key);
            System.out.println(String.format("%d%22s%13s", key, model.getRegisteredNumber(), model.getCarColour()));
        }

    }

    
    /** 
     * this method prints the cars parked with the given Colour
     * @param carColour
     */
    public static void findCarsWithColours(String carColour) {
        for (Integer key : parkingSlotModel.getTickets().keySet()) {

            CarModel model = parkingSlotModel.getTickets().get(key);
            if (model.getCarColour().equals(carColour)) {
                System.out.println(String.format("%16s,", model.getRegisteredNumber()));
            }
        }

    }

    
    /** 
     * This method finds the slot numbers and prints them for the given Car colour
     * @param color
     */
    public static void findSlotNumberswithColour(String color) {
        boolean slotFound = false;

        for (Integer key : parkingSlotModel.getTickets().keySet()) {

            CarModel model = parkingSlotModel.getTickets().get(key);
            if (model.getCarColour().equals(color)) {
                slotFound = true;
                System.out.print(String.format("%d,", key));
            }
        }
        if (!slotFound) {
            System.out.println("Not found");
        }
        System.out.println();
    }

    
    /** 
     * This Method Finds the Parkingslot Number for the given car's Registration Number
     * @param regNo
     */
    public static void findSlotNumberWithRegNo(String regNo) {
        boolean slotFound = false;
        for (Integer key : parkingSlotModel.getTickets().keySet()) {

            CarModel model = parkingSlotModel.getTickets().get(key);
            if (model.getRegisteredNumber().equals(regNo)) {
                slotFound = true;
                System.out.print(String.format("%d", key));
            }
        }

        if (!slotFound) {
            System.out.println("Not found");
        }
        System.out.println();

    }

}
