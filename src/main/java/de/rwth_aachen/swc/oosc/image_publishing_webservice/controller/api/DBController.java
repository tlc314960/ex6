package de.rwth_aachen.swc.oosc.image_publishing_webservice.controller.api;

import de.rwth_aachen.swc.oosc.image_publishing_webservice.domain.Image;

import java.net.MalformedURLException;
import java.net.URL;

public interface DBController {
    public Image uploadAndCreateImage(String imageData) throws MalformedURLException;
    public Image createImage(URL url, int id, Image images);
    public Image getImageForGivenId(int id);
    public void deleteImage(int id);
    public void modifyImage(int id);
}
