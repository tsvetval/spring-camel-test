package ru.camel.example.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import ru.camel.example.model.payment.PaymentNumRequest;
import ru.camel.example.model.payment.PaymentRequestValidator;

import static org.apache.camel.Exchange.CONTENT_TYPE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

/**
 *
 */
@Component
public class ApplicationCamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //TODO move to configuration
        restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
        //.bindingMode(RestBindingMode.json)
        // .dataFormatProperty("prettyPrint", "true")
        // .port(8081);;

        rest()
            .get("/ping").route().transform().constant("OK").setHeader(CONTENT_TYPE, constant(TEXT_PLAIN_VALUE)).endRest()
            .post("/payments").consumes("application/json")
                .type(PaymentNumRequest.class)
                .outType(PaymentNumRequest.class)
                .route()
                .validate(new PaymentRequestValidator())
                //.marshal().json(JsonLibrary.Jackson)/*.convertBodyTo(String.class)*/.setHeader(CONTENT_TYPE, constant(APPLICATION_JSON))
                .endRest()
            .post("/payments/{session}").consumes("application/json")
                .type(PaymentNumRequest.class)
                .route().transform().constant("OK").setHeader(CONTENT_TYPE, constant(TEXT_PLAIN_VALUE)).endRest()
            .get("/payments/{session}").route().transform().constant("OK").setHeader(CONTENT_TYPE, constant(TEXT_PLAIN_VALUE)).endRest()
            .post("/payments/{session}/cancel").route().transform().constant("OK").setHeader(CONTENT_TYPE, constant(TEXT_PLAIN_VALUE)).endRest()
            .post("/payments/{session}/confirm").route().transform().constant("OK").setHeader(CONTENT_TYPE, constant(TEXT_PLAIN_VALUE)).endRest();
//                .marshal().json(JsonLibrary.Jackson).convertBodyTo(String.class).setExchangePattern(ExchangePattern.RobustInOnly)
//                .end();

      /*  from("servlet:///test")
                .onException(Exception.class).handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain")).setBody(simple("Not accepted")).end()
                //.onException(ValidationException.class).handled(false).end()
                .setBody(new Expression() {
                    @Override
                    public <T> T evaluate(Exchange exchange, Class<T> type) {
                        return (T) "Test";
                    }
                })
                .validate(new Predicate() {
                    @Override
                    public boolean matches(Exchange exchange) {
                        //TODO change !
                        return true;
                    }
                })
                //.convertBodyTo(Test1.class)
                .marshal().json(JsonLibrary.Jackson).convertBodyTo(String.class)
                //.pipeline("direct:validate", "direct:transform")
                .setExchangePattern(ExchangePattern.RobustInOnly)
                .end();*/


//        rest("/camel")
//                .get("test").to("direct:hello")/*.type(PaymentDTO.class)*//*.verb("OK from Camel").responseMessage()*/;

    }
}
