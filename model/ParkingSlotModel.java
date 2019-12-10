/*
 * Created on Wed Dec 09 2019
 * 
 *  Model Class for ParkingSlot
 * 
 *  Author Vinayak K S
 */

package model;

import java.util.HashMap;
import java.util.TreeSet;

public class ParkingSlotModel{
    private static ParkingSlotModel parkingSlot =  null;
     TreeSet<Integer> avaialbleSlots;
     HashMap<Integer , CarModel> tickets;

    public TreeSet getAvaialbleSlots() {
        return avaialbleSlots;
    }


    public HashMap<Integer , CarModel> getTickets() {
        return tickets;
    }

    private ParkingSlotModel() {
        avaialbleSlots = new TreeSet<>();
        tickets = new HashMap<Integer , CarModel>();
    }

    public static ParkingSlotModel getInstanceModel(){
        if(parkingSlot == null){
            parkingSlot = new ParkingSlotModel();
        }
        return parkingSlot;
    }
    

}