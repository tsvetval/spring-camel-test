package ru.camel.example.route;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.camel.example.model.Payment;
import ru.camel.example.model.payment.PaymentNumRequest;
import ru.camel.example.model.payment.PaymentNumResponse;
import ru.camel.example.model.payment.PaymentRequestValidator;
import ru.camel.example.route.transformer.ErrorTransformer;

import static org.apache.camel.Exchange.CONTENT_TYPE;
import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;
import static org.springframework.http.MediaType.*;

/**
 *
 */
@Component
public class ApplicationCamelRouter extends RouteBuilder {

    @Autowired
    PaymentRequestValidator paymentRequestValidator;
    @Autowired
    ErrorTransformer errorTransformer;

    public final String paymentsPostDirect = "direct:payments.post";
    public final String paymentsSessionGetDirect = "direct:payments.session.get";

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.auto).dataFormatProperty("prettyPrint", "true");

        onException(Throwable.class)
                .handled(true)
                .setHeader(HTTP_RESPONSE_CODE, constant(400))
                .setHeader(CONTENT_TYPE, constant(APPLICATION_JSON_VALUE))
                .transform(errorTransformer)
                .marshal().json(JsonLibrary.Jackson)
                .end();

        rest()
                .get("/ping").route().transform().constant("").setHeader(CONTENT_TYPE, constant(TEXT_PLAIN_VALUE)).endRest()
                .post("/payments").consumes(APPLICATION_JSON_VALUE)
                    .type(PaymentNumRequest.class)
                    //.outType(PaymentNumResponse.class)
                    .route()
                    .validate(paymentRequestValidator)
                    .to(paymentsPostDirect)
                    .endRest()
                .get("/payments/{session}").consumes(APPLICATION_JSON_VALUE)
                    .route()
                    .to(paymentsSessionGetDirect)
                    .endRest()
                .post("/payments/{session}/cancel")
                    .route()
                    .to(paymentsSessionGetDirect)
                    .endRest()
                .post("/payments/{session}/confirm")
                    .route()
                    .to(paymentsSessionGetDirect)
                    .endRest();


        from(paymentsPostDirect)
                .setHeader(CONTENT_TYPE, constant(APPLICATION_JSON))
                .setBody(new Expression() {
                    @Override
                    public <T> T evaluate(Exchange exchange, Class<T> type) {
                        PaymentNumResponse response = new PaymentNumResponse();
                        response.setSession("testSession");

                        Payment payment = new Payment();
                        payment.setSum(100d);
                        payment.setPayType(Payment.PayType.CARD);
                        response.setPayment(payment);
                        return (T) response;
                    }
                });


        from(paymentsSessionGetDirect)
                .setHeader(CONTENT_TYPE, constant(APPLICATION_JSON))
                .setBody(new Expression() {
                    @Override
                    public <T> T evaluate(Exchange exchange, Class<T> type) {
                        String session = (String)exchange.getIn().getHeader("session");
                        Payment payment = new Payment();
                        payment.setSum(100d);
                        payment.setPayType(Payment.PayType.CARD);
                        return (T) payment;
                    }
                });
    }
}
