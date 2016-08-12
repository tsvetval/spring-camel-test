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

    //TODO
    Object writeTransaction;
    String session;
    String time;
    Double sum;
    PaymentStatus status;
    PayType payType;
    Object pos;


}
