package de.rwth_aachen.swc.oosc.image_publishing_webservice.controller.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import de.rwth_aachen.swc.oosc.image_publishing_webservice.domain.Image;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/floorplans")
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
    private DBController dbController = new LocalJSONDBController();

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
    public Image getImageRequest(@PathVariable("id") int id) throws IOException {

        Image image = dbController.getImageForGivenId(id) ;
        if(image != null) {
            return image;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.");
        }
    }

    /**
     * Create a single image
     * @param
     * @return Image object or error 404 not found if no image exists for the given id
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Image createImageRequest(@RequestBody Map<String,Object> map, HttpServletResponse response) throws IOException {
        // todo: imageDAta in the request then response
        URL url = new URL((String) map.get("url"));
        int id = dbController.getNextID() + 1;
        Image image = dbController.createImage(url, id);
        if(image != null) {
            response.setStatus(200);
        }
        return image;
    }

    /**
     * Update a single image for a given id
     * @param id id
     * @param favourite favourite
     * @return Image object or error 404 not found if no image exists for the given id
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Image updateImagePropertiesRequest(@PathVariable("id") int id, @RequestParam("favorite") boolean favourite) throws IOException {
        // todo: favorite in request
        Image image = dbController.modifyImage(id, favourite);
        if(image != null) {
            return image;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.");
        }
    }

    /**
     * Delete a single image for a given id
     * @param id id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteImageRequest(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        dbController.deleteImage(id);
        //TODO response
        if (dbController.getImageForGivenId(id) == null){
            response.setStatus(200);
        }
    }

    /**
     * Setup called on initialization
     */
    @PostConstruct
    public void setup() throws Exception {
        logger = LoggerFactory.getLogger(this.getClass());
        dbController.getAllImages();
        System.out.println(nextId = dbController.getNextID());
    }


}
