package com.life_manager.life_manager.user.infra.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsService {
    private static final String MESSAGE_PREFIX = "[직통] ";
    private static final String DOMAIN = "https://api.coolsms.co.kr";

    @Value("${cool-sms.api.key}")
    private String apiKey;
    @Value("${cool-sms.api.secret}")
    private String apiSecret;
    @Value("${cool-sms.sender}")
    private String sender;

    public void sendMessage(String receiver, String content) throws
            NurigoMessageNotReceivedException,
            NurigoEmptyResponseException,
            NurigoUnknownException {
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret,
                DOMAIN);

        Message message = new Message();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setText(MESSAGE_PREFIX + content);

        messageService.send(message);

    }
}