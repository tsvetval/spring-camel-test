package ru.camel.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.camel.OutHeaders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 *
 */
@XmlRootElement(name = "Error")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(NON_NULL)
public   class Error {
    private String message;
    private String code;

    public Error() {
    }

    public Error(String message) {
        this.message = message;
    }

    public Error(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


   public static Error processError(@Headers Map<?, ?> in, @Body String payload, @OutHeaders Map<String, Object> out){
        return null;
    }
}
