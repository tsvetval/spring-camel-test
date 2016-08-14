package ru.camel.example.model;

import java.util.List;

/**
 *
 */
public class Ticket {
    public enum Code{OK , SUSPENDED , UNWRITTEN}
    public class Status{
        private String message;
        private Code code;

    }

    private String name;
    private List<Service> currentServices;
    private List<Service> unwrittenServices;
    private List<ServicePrice> availableServices;
    private List<Service> expiredServices;
    private String number;

    /*
    *   MOSMETRO - Билет Московского метрополитена;
        MOSGORTRANS - Билет Мосгортранса;
        WALLET - Электронный кошелек;
        UNICARD - Универсальная Транспортная карта; числовой код - прочие.
    * */
    private String code;
    private Status status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Service> getCurrentServices() {
        return currentServices;
    }

    public void setCurrentServices(List<Service> currentServices) {
        this.currentServices = currentServices;
    }

    public List<Service> getUnwrittenServices() {
        return unwrittenServices;
    }

    public void setUnwrittenServices(List<Service> unwrittenServices) {
        this.unwrittenServices = unwrittenServices;
    }

    public List<ServicePrice> getAvailableServices() {
        return availableServices;
    }

    public void setAvailableServices(List<ServicePrice> availableServices) {
        this.availableServices = availableServices;
    }

    public List<Service> getExpiredServices() {
        return expiredServices;
    }

    public void setExpiredServices(List<Service> expiredServices) {
        this.expiredServices = expiredServices;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
