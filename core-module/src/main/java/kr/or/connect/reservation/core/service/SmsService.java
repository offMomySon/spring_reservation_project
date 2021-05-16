package kr.or.connect.reservation.core.service;

import kr.or.connect.reservation.core.infrastructure.config.CoolSmsAPI;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class SmsService {
    private final CoolSmsAPI coolSmsAPI;

    public SmsService(CoolSmsAPI coolSmsAPI) {
        this.coolSmsAPI = coolSmsAPI;
    }

    public void sendMessage(String receiverPhoneNumber, int leftDay) {
        Message coolsms = new Message(coolSmsAPI.getKey(), coolSmsAPI.getSecret());

        try {
            coolsms.send(getMessageConfig(receiverPhoneNumber, coolSmsAPI.getSendNo(), leftDay));
        } catch (CoolsmsException e) {
            throw new RuntimeException();
        }
    }

    private HashMap<String, String> getMessageConfig(String receiverPhoneNumber, String senderPhoneNumber, int leftDay) {
        HashMap<String, String> params = new HashMap<>();
        params.put("to", receiverPhoneNumber);
        params.put("from", senderPhoneNumber);
        params.put("type", "SMS");
        params.put("text", "남은 예약 날자는 " + "[" + leftDay + "일 ]" + "입니다.");
        params.put("app_version", "test app 1.2");

        return params;
    }
}
