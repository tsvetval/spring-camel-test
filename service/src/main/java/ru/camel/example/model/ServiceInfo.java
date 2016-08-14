package ru.camel.example.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement(name = "ServiceInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceInfo {
    @XmlRootElement(name = "Ticket")
    @XmlAccessorType(XmlAccessType.FIELD)
    private class ServiceInfoTicket{
        private String number;
        //TODO
        /*Код транспортного приложения:
            MOSMETRO - Билет Московского метрополитена;
            MOSGORTRANS - Билет Мосгортранса;
            WALLET - Электронный кошелек;
            UNICARD - Универсальная Транспортная карта; числовой код - прочие.*/
        private String code;
        private String name;
    }


    private ServiceInfoTicket ticket;
    private Long id;
    private String name;

}
