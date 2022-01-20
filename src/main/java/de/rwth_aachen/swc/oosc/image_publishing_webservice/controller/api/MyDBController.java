package de.rwth_aachen.swc.oosc.image_publishing_webservice.controller.api;

import de.rwth_aachen.swc.oosc.image_publishing_webservice.domain.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyDBController extends DBController{
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
    public List<Image> getAllImages() {
//        createImage(new URL("http://via.placeholder.com/640x360")).setFavorite(true);
//        for (int id = 1; id <= 12; id++) {
//            createImage(new URL("http://via.placeholder.com/640x360"));
//        }
        return null;
    }

    @Override
    List<Image> loadAllImages() {
//        if read找不到
//                我先自己做一个
        return null;
    }
}
