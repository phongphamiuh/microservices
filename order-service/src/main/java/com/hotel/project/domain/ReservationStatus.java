package com.hotel.project.domain;

public enum ReservationStatus {
    NEW(1,"New"),APPROVED(2,"Approved"),
    MODIFIED(3,"Modified"),CLEANED(4,"Cleaned"),
    CANCELLED(5,"Cancelled"),PREPAID(6,"Prepaid"),
    PAIDCC(7,"Paidcc"),COMPLETE(8,"Complete"),
    NOSHOW(9,"Noshow"),DEBTOR(10,"Debtor");
    private int id;
    private String description;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private ReservationStatus(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
}
