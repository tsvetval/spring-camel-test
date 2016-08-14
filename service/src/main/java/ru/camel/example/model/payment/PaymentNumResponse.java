package ru.camel.example.model.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.camel.example.model.Payment;
import ru.camel.example.model.Ticket;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 *
 */
@XmlRootElement(name = "PaymentNumResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(NON_NULL)
public class PaymentNumResponse {
    @XmlAttribute
    private String session;

    private List<Ticket> tickets = new ArrayList<>();
    private Payment payment;

    public PaymentNumResponse() {
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
