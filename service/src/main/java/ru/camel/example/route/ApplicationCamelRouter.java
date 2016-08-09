package ru.camel.example.route;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Expression;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ApplicationCamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //TODO move to configuration
        restConfiguration().component("servlet");
        //.bindingMode(RestBindingMode.json)
        // .dataFormatProperty("prettyPrint", "true")
        // .port(8081);;


        from("servlet:///test")
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
                .end();


//        rest("/camel")
//                .get("test").to("direct:hello")/*.type(PaymentDTO.class)*//*.verb("OK from Camel").responseMessage()*/;

    }
}
