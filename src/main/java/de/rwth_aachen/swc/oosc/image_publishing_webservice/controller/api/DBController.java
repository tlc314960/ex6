package de.rwth_aachen.swc.oosc.image_publishing_webservice.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.rwth_aachen.swc.oosc.image_publishing_webservice.domain.Image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public abstract class DBController {
    /**
     * List of all images
     */
    private List<Image> images;
    public abstract Image uploadAndCreateImage(String imageData) throws MalformedURLException;
    public abstract Image createImage(URL url, int id, Image images);
    public abstract Image getImageForGivenId(int id);
    public abstract void deleteImage(int id);
    public abstract void modifyImage(int id);

    public List<Image> getAllImages() throws IOException {
        if (images == null){
            images = loadAllImages();
        }
        return images;
    }

    abstract List<Image> loadAllImages() throws IOException;
}
