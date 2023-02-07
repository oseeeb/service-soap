package com.afriland.soapservice.clients;

import feign.Feign;
import feign.hc5.ApacheHttp5Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoapClientTest{
    private SoapClient client = Feign.builder()
            .client(new ApacheHttp5Client())
            .target(SoapClient.class, "http://webservices.daehosting.com/services/isbnservice.wso");
    @Test
    public void givenPayloadText_whenRequest_theNotThrowsException(){
        assertDoesNotThrow(()->client.checkIfISBNIsValid(payload()));
    }

    @Test
    public void givenPayloadText_whenRequest_theResponseIsNotNull(){
        assertNotNull(client.checkIfISBNIsValid(payload()));
    }

    @Test
    public void givenPayloadText_whenRequest_theResponseContainsResult(){
        String partResult = "IsValidISBN10Result";
        String soapResponse = client.checkIfISBNIsValid(payload());
        assertTrue(soapResponse.contains(partResult));
    }

    public String payload(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <IsValidISBN10 xmlns=\"http://webservices.daehosting.com/ISBN\">\n" +
                "      <sISBN>0-19-852663-6</sISBN>\n" +
                "    </IsValidISBN10>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
    }
}
