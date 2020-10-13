package app.controllers;

import app.entities.Item2080;
import app.entities.ItemAvs;
import app.entities.ItemError;
import app.services.BlenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class BlenderController {

    @Autowired
    private BlenderService blenderService;

    @GetMapping("/avs")
    public ResponseEntity<?> getAvs(@RequestParam("item") int item){

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
                                     @RequestParam("store") int store){

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
            case 0:
                resultItem.setStatus("80/20");
                break;
            case 1:
                resultItem.setStatus("20/80");
                break;

        }

        return new ResponseEntity<>(resultItem, HttpStatus.OK);

    }

}
