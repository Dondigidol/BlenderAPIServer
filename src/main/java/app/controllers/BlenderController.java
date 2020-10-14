package app.controllers;

import app.entities.Item2080;
import app.entities.ItemAvs;
import app.entities.ItemError;
import app.services.BlenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/product")
public class BlenderController {

    private static final Logger logger = LoggerFactory.getLogger(BlenderController.class);

    @Autowired
    private BlenderService blenderService;

    @GetMapping("/avs")
    public ResponseEntity<?> getAvs(@RequestParam("item") int item,
                                    HttpServletRequest request){

        logger.info("Client: " + request.getRemoteAddr() + ", request: AVS for '" + item + "'" );

        String targetItem = String.valueOf(item);
        ItemAvs resultItem = blenderService.getAvs(targetItem);
        if (resultItem == null){
            ItemError itemError = new ItemError();
            itemError.setItem(targetItem);
            itemError.setError("AVS date for item '" +targetItem+ "' does not exist!");
            return new ResponseEntity<>(itemError, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultItem, HttpStatus.OK);
    }

    @GetMapping("/2080")
    public ResponseEntity<?> get2080(@RequestParam("item") int item,
                                     @RequestParam("store") int store,
                                     HttpServletRequest request){

        logger.info("Client: " + request.getRemoteAddr() + ", request: 2080 for '" + item + "' in store '" + store + "'" );

        String targetStore = String.valueOf(store);
        while (targetStore.length()<3){
            targetStore = "0" + targetStore;
        }


        Item2080 resultItem = blenderService.get2080(item, targetStore);
        if (resultItem == null){
            ItemError itemError = new ItemError();
            itemError.setItem(String.valueOf(item));
            itemError.setError("Status of item '" +String.valueOf(item)+ "' does not exist OR your parameters are wrong!");
            return new ResponseEntity<>(itemError, HttpStatus.NOT_FOUND);
        }
        switch (resultItem.getStatus_code()){
            case 1:
                resultItem.setStatus("20/80");
                break;
            default:
                resultItem.setStatus("80/20");
                break;

        }

        return new ResponseEntity<>(resultItem, HttpStatus.OK);

    }

}
