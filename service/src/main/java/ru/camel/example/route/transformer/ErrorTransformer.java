package ru.camel.example.route.transformer;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.springframework.stereotype.Component;
import ru.camel.example.model.Error;

/**
 *
 */
@Component
public class ErrorTransformer implements Expression {
    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        return (T) new Error(exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class).getMessage());
    }
}
