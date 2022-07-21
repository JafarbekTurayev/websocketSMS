package com.twilio.WebSocketSMS;
import com.twilio.WebSocketSMS.domain.SMS;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.net.URI;

@Component
public class SMSService {

//    @Value("#{systemEnvironment['TWILIO_ACCOUNT_SID']}")
    private String ACCOUNT_SID = "ACb45ed0986a998cb5649e217e8c07afb4";

    /*
    @Value("#{systemEnvironment['COMPONENT_PARAM_CORS'] ?: 'DEFAULT_VALUE'}")
private String COMPONENT_PARAM_CORS;
     */

//    @Value("#{systemEnvironment['TWILIO_AUTH_TOKEN']}")
    private String AUTH_TOKEN = "93e9b5e67ac093005bd43290598e99df";

//    @Value("#{systemEnvironment['TWILIO_PHONE_NUMBER']}")
    private String FROM_NUMBER ="+19378702799";

    public void send(SMS sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        // to number, from: always twilio snd a message
        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .setStatusCallback(URI.create("http://677add1a.ngrok.io/smscallback"))
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }
}
