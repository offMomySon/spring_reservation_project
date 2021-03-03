package kr.or.connect.reservation.infrastructure.objmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;

public class CustomObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = -2148669317097583174L;

    public CustomObjectMapper() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Double.class, new CustomDoubleSerializer());
        simpleModule.addSerializer(Date.class, new CustomDateSerializer());

        registerModule(simpleModule);
    }
}
