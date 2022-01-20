package de.rwth_aachen.swc.oosc.image_publishing_webservice.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import de.rwth_aachen.swc.oosc.image_publishing_webservice.domain.Image;
import org.springframework.aop.aspectj.SimpleAspectInstanceFactory;
import org.apache.commons.lang3.StringEscapeUtils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MyDBController extends DBController{
    final static String FILE_PATH = "myJson.json";
    private IOManager ioManager = new IOManager();
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Upload and create a new image
     * @param imageData image data string
     * @return created image
     * @throws MalformedURLException
     */
    public Image uploadAndCreateImage(String imageData) throws MalformedURLException {

        // todo: implement

        return null;
    }

    //TODO add not implement
    /**
     * Create a new image
     * @param url URL to the image
     * @return created image
     */
    @Override
    public Image createImage(URL url, int id, Image images) {
        Image image = new Image(id, url);
        id++;
//        images.add(image);
        return image;
    }

    /**
     * Searches in the list of all images for the image with the given id
     * @param id id
     * @return image with given id or null
     */
    @Override
    public Image getImageForGivenId(int id) {
//        return images.stream().filter(img -> img.getId() == id).findAny().orElse(null);
        return null;
    }

    @Override
    public void deleteImage(int id) {
        //修改image 并修改json

    }

    @Override
    public void modifyImage(int id) {

    }

    @Override
    List<Image> loadAllImages() throws IOException {
        String str = "";
        createImagesJson();

        //read
        try {
            BufferedReader in = new BufferedReader(new FileReader(FILE_PATH));
            str = in.readLine();
        } catch (IOException e) {
            createImagesJson();
        }

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        str = StringEscapeUtils.unescapeJava(str);
        str = str.substring(1, str.length() - 1);

        //parse
        List<Image> images = mapper.readValue(str, new TypeReference<List<Image>>() {
        });

        return images;
    }

    @Override
    public int getNextID() {
        return 0;
    }

    private void createImagesJson() throws IOException {
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

}
