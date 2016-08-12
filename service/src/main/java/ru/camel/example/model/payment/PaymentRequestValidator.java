package ru.camel.example.model.payment;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

/**
 *
 */

public class PaymentRequestValidator implements Predicate {
    @Override
    public boolean matches(Exchange exchange) {
        return true;
    }
}
