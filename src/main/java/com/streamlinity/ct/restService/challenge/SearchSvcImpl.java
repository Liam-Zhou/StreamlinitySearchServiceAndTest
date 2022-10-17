package com.streamlinity.ct.restService.challenge;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamlinity.ct.model.Item;

/*
 * Provide your implementation of the SearchSvcImpl here.
 * Also annotate your methods with Rest end point wrappers as required in the problem statement
 *
 * You can create any auxiliary classes or interfaces in this package if you need them.
 *
 * Do NOT add annotations as a Bean or Component or Service.   This is being handled in the custom Config class
 * PriceAdjustConfiguration
 */

public class SearchSvcImpl implements SearchSvcInterface {
    @Autowired
    private ObjectMapper objectMapper;

    private List<Item> items;
    @Override
    public void init(String itemPriceJsonFileName) {
        String path = SearchSvcImpl.class.getClassLoader().getResource(itemPriceJsonFileName).getPath();
        File f = new File(path);
        init(f);
    }

    @Override
    public void init(File itemPriceJsonFile) {
        try {
            items = objectMapper.readValue(itemPriceJsonFile, new TypeReference<List<Item>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public List<Item> getItems(String category) {
        return items.stream().filter(item -> item.getCategory_short_name().equals(category)).collect(Collectors.toList());
    }

    @Override
    public List<Item> getItem(String itemShortName) {

        return items.stream().filter(item -> item.getShort_name().equals(itemShortName)).collect(Collectors.toList());
    }
}
