package test_demo.data;

public class Booking {
    private String firstName;
    private String lastName;
    private String totalPrice;
    private Boolean depositPaid;
    private String checkIn;
    private String checkOut;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getDepositPaid() {
        return depositPaid.toString();
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public Booking(String firstName, String lastName, String totalPrice, Boolean depositPaid, String checkIn, String checkOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
