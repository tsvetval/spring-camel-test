package ru.camel.example.model.payment;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

/**
 *
 */

@Component
public class PaymentRequestValidator implements Predicate {
    @Override
    public boolean matches(Exchange exchange) {
        return true;
    }
}
