package com.mkudryavtsev.springapp.security.twilio;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TwilioVerifyService {
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.service.sid}")
    private String SERVICE_SID;

    @PostConstruct
    protected void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void startVerification(String phoneNumber) {
        Verification verification = Verification.creator(SERVICE_SID, phoneNumber, "sms").create();
    }

    public String checkVerification(String phoneNumber, String verifyCode) {
        VerificationCheck verificationCheck =
                VerificationCheck.creator(SERVICE_SID, verifyCode).setTo(phoneNumber).create();
        return verificationCheck.getStatus();
    }


}
