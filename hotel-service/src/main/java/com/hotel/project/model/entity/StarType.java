package com.hotel.project.model.entity;

public enum StarType {
    ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5);

    private int value;

    public int getValue(){
        return this.value;
    }

    StarType(int value){
        this.value=value;
    }
}
