package de.rwth_aachen.swc.oosc.image_publishing_webservice.controller.api;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LocalJSONDBController extends DBController{
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
    public Image createImage(URL url, int id) throws IOException {
        List<Image> images = getAllImages();
        Image image = new Image(id, url);
        images.add(image);
        ioManager.refreshImagesJson(images);
        return image;
    }

    /**
     * Searches in the list of all images for the image with the given id
     * @param id id
     * @return image with given id or null
     */
    @Override
    public Image getImageForGivenId(int id) throws IOException {
        List<Image> images = getAllImages();
        Image image = images.stream().filter(img -> img.getId() == id).findAny().orElse(null);
        return image;
    }

    /**
     * Delete the image with the given id
     * @param id id
     *
     */
    @Override
    public void deleteImage(int id) throws IOException {
        //修改image 并修改json
        List<Image> images = getAllImages();
        Image image = images.stream().filter(img -> img.getId() == id).findAny().orElse(null);
        images.remove(image);
        ioManager.refreshImagesJson(images);
    }

    /**
     * Modify the image with the given id
     * @param id id
     *
     */
    @Override
    public Image modifyImage(int id, boolean favourite) throws IOException {
        List<Image> images = getAllImages();
        Image image = images.stream().filter(img -> img.getId() == id).findAny().orElse(null);
        image.setFavorite(favourite);
        ioManager.refreshImagesJson(images);
        return image;
    }



    @Override
    public int getNextID() throws IOException {
        List<Image> images = getAllImages();
        int nextID = images.stream().max(Comparator.comparing(Image::getId)).get().getId();
        return nextID;
    }

    List<Image> loadAllImages() throws IOException {
        String str = "";
//        ioManager.createImagesJson();

        //read
        try {
            BufferedReader in = new BufferedReader(new FileReader(FILE_PATH));
            str = in.readLine();
        } catch (IOException e) {
            ioManager.createImagesJson();
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

}
