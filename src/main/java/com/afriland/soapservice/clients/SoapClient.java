package com.afriland.soapservice.clients;

import feign.Headers;
import feign.RequestLine;

public interface SoapClient {
    @RequestLine("POST")
    @Headers({"Content-Type:text/xml;charset:utf-8","Accept:text/xml"})
    public String checkIfISBNIsValid(String responseSoap);
}
