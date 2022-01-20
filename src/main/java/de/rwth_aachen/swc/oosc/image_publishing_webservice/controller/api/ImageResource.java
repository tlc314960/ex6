package de.rwth_aachen.swc.oosc.image_publishing_webservice.controller.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import de.rwth_aachen.swc.oosc.image_publishing_webservice.domain.Image;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/images")
public class ImageResource {

    /**
     * A a logger you may use for logging
     */
    private Logger logger;

    /**
     * Next new image's id
     */
    private int nextId = 0;

    // a dbController can be changed dynamically, mangoDB, mySQL or local files
    private DBController dbController = new MyDBController();

    /**
     * Get all images in the standard representation format defined by the Domain Entities
     **/
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Image> getAllImages() throws IOException {
        List<Image> images = dbController.getAllImages();
        logger.info("Sending {} Images", images.size());
        return images;
    }

    /**
     * Gets a single image for a given id
     * @param id id
     * @return Image object or error 404 not found if no image exists for the given id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Image getImageRequest(@PathVariable("id") int id) {

        Image image = dbController.getImageForGivenId(id) ;
        if(image != null) {
            return image;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.");
        }
    }

    public Image createImageRequest(/* todo */) {
        // todo: implement (you may need to add more than just the method signature and body)
        return null;
    }

    public Image updateImagePropertiesRequest(/* todo */) {
        // todo: implement (you may need to add more than just the method signature and body)
        return null;
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.PUT)
    public void deleteImageRequest(@PathVariable("id") int id) {
        dbController.deleteImage(id);
    }

    /**
     * Setup called on initialization
     */
    @PostConstruct
    public void setup() throws Exception {
        logger = LoggerFactory.getLogger(this.getClass());
        dbController.getAllImages();
        nextId = dbController.getNextID();
    }


}
