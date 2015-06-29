package hotelvirtual.model;

import java.util.Date;

public class Request {

    private int id;
    private int guests;
    //private int customerId;
    private Customer customer;
    private Date checkInDate;
    private Date checkOutDate;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getGuests() { return guests; }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /*public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }*/

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
