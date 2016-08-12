package ru.camel.example.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement(name = "PaymentNumRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentNumRequest {
    @XmlAttribute
    private String num;

    public PaymentNumRequest() {
    }

    public PaymentNumRequest(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
