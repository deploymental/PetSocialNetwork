package com.getjavajob.common;

public class Phone {
    private int id;
    private int accId;
    private long number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;

        Phone phone = (Phone) o;

        if (accId != phone.accId) return false;
        return number == phone.number;
    }

    @Override
    public int hashCode() {
        int result = accId;
        result = 31 * result + (int) (number ^ (number >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "accId=" + accId +
                ", number=" + number +
                '}';
    }
}
