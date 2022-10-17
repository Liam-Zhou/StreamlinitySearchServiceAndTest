package com.streamlinity.ct.restService.challenge;

import com.streamlinity.ct.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * This controller needs to expose the following rest endpoints.  You need to fill in the implementation here
 *
 * Required REST Endpoints
 *
 *      /item                       Get all items
 *      /item?category=C            Get all items in category specified by Category shortName
 *      /item/{itemShortName}       Get item that matches the specified Item shortName
 */

@Profile("default")
@RestController
public class SearchRestControllerImpl {
    @Autowired
    SearchSvcInterface searchSvc;

    @GetMapping("/item")
    ResponseEntity<List<Item>> getAll(){
        return ResponseEntity.ok(searchSvc.getItems());
    }

    @GetMapping(value = "/item", params = "category")
    ResponseEntity<List<Item>> getByCategory(@RequestParam(value = "category") String category){
        return ResponseEntity.ok(searchSvc.getItems(category));
    }

    @GetMapping("/item/{itemShortName}")
    ResponseEntity<List<Item>> getByShortName(@PathVariable(value = "itemShortName") String itemShortName){
        return ResponseEntity.ok(searchSvc.getItem(itemShortName));
    }
}
