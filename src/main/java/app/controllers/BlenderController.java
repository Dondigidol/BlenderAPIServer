package app.controllers;

import app.entities.Item2080;
import app.entities.ItemAvs;
import app.entities.ItemError;
import app.services.BlenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${api.token.key}")
    private String xApiKey;

    @GetMapping("/avs")
    public ResponseEntity<?> getAvs(@RequestParam("item") int item,
                                    HttpServletRequest request){

        logger.info("Client: " + request.getRemoteAddr() + ", request: AVS for '" + item + "'" );

        String targetItem = String.valueOf(item);

        if (!checkToken(request)){
            String errorMessage = "AVS date for item '" +targetItem+ "' error! Your x-api-key header is wrong!";
            ItemError itemError = new ItemError(targetItem, errorMessage, HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(itemError, HttpStatus.BAD_REQUEST);
        }

        ItemAvs resultItem = blenderService.getAvs(targetItem);
        if (resultItem == null){
            String errorMessage = "AVS date for item '" +targetItem+ "' does not exist!";
            ItemError itemError = new ItemError(targetItem, errorMessage, HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(itemError, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultItem, HttpStatus.OK);
    }

    @GetMapping("/2080")
    public ResponseEntity<?> get2080(@RequestParam("item") int item,
                                     @RequestParam("store") int store,
                                     HttpServletRequest request){

        logger.info("Client: " + request.getRemoteAddr() + ", request: 2080 for '" + item + "' in store '" + store + "'" );

        if (!checkToken(request)){
            String errorMessage = "Getting status for item: '" +item+ "' error! Your x-api-key header is wrong!";
            ItemError itemError = new ItemError(String.valueOf(item), errorMessage, HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(itemError, HttpStatus.BAD_REQUEST);
        }

        String targetStore = String.valueOf(store);

        while (targetStore.length()<3){
            targetStore = "0" + targetStore;
        }


        Item2080 resultItem = blenderService.get2080(item, targetStore);
        if (resultItem == null){
            String errorMessage = "Status of item '" + item + "' does not exist OR your parameters are wrong!";
            ItemError itemError = new ItemError(String.valueOf(item), errorMessage, HttpStatus.NOT_FOUND.value());
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

    private boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("x-api-key");
        if (token == null || !token.equals(xApiKey)){
            logger.error("Client: " + request.getRemoteHost() + ", x-api-key header is Wrong OR empty, OR this header is not exist!");
            return false;
        } else {
            return true;
        }


    }

}
