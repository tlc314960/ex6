package de.rwth_aachen.swc.oosc.image_publishing_webservice.controller.api;

import de.rwth_aachen.swc.oosc.image_publishing_webservice.domain.Image;

import java.net.MalformedURLException;
import java.net.URL;

public class MyDBController implements DBController{
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

    }

    @Override
    public void modifyImage(int id) {

    }
}
