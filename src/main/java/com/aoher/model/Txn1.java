package com.aoher.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Entity
@Table(name = "TRANSACTION")
public class Txn1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private long id;

    @Column(name = "txn_date")
    private Date date;

    @Column(name = "txn_total")
    private double total;

    @OneToOne(mappedBy = "txn")
    @Cascade(value = SAVE_UPDATE)
    private Customer1 customer;

    public Txn1() {
    }

    public Txn1(long id, Date date, double total, Customer1 customer) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Customer1 getCustomer() {
        return customer;
    }

    public void setCustomer(Customer1 customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Txn{" +
                "id=" + id +
                ", total=" + total +
                ", customerName=" + customer.getName() +
                ", customerEmail=" + customer.getEmail() +
                ", customerAddress=" + customer.getAddress();
    }
}
