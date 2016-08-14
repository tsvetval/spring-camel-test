package ru.camel.example.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement(name = "Payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment {
    public enum PayType{
        CASH, CARD, PHONE, INTERNET
    }

    public class Card {
        String uid;
    }

    //TODO
    private ServiceInfo  service;

    private PayType payType;
    private Object writeTransaction;
    private Double sum;
    private Object pos;
    private String session;
    /* DD.MM.YYYY HH:NN:SS*/
    private String time;

    private Card card;
    private PaymentStatus status;


    public ServiceInfo getService() {
        return service;
    }

    public void setService(ServiceInfo service) {
        this.service = service;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public Object getWriteTransaction() {
        return writeTransaction;
    }

    public void setWriteTransaction(Object writeTransaction) {
        this.writeTransaction = writeTransaction;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Object getPos() {
        return pos;
    }

    public void setPos(Object pos) {
        this.pos = pos;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
