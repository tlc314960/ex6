package de.rwth_aachen.swc.oosc.image_publishing_webservice.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import de.rwth_aachen.swc.oosc.image_publishing_webservice.domain.Image;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IOManager {
    private ObjectMapper mapper = new ObjectMapper();
    final static String FILE_PATH = "myJson.json";


    public void createImagesJson() throws IOException {
        System.out.println("dasdasdasdsa");

        String url = "https://www.google.com";
        Image image0 = new Image(0,new URL(url),false);
        Image image1 = new Image(1,new URL(url),false);
        Image image2 = new Image(2,new URL(url),false);

        List<Image> images = new ArrayList<>();
        images.add(image0);
        images.add(image2);
        images.add(image1);


        JavaTimeModule javaTimeModule=new JavaTimeModule();
        // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String json = mapper.writeValueAsString(images);
        System.out.println("JSON: " + json);
        mapper.writeValue(new File(FILE_PATH),json);
    }

    public void refreshImagesJson(List<Image> images) throws IOException {
        JavaTimeModule javaTimeModule=new JavaTimeModule();
        // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String json = mapper.writeValueAsString(images);
        System.out.println("JSON: " + json);
        mapper.writeValue(new File(FILE_PATH),json);
    }
}
