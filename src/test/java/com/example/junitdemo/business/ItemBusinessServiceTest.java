package com.example.junitdemo.business;

import com.example.junitdemo.data.ItemRepository;
import com.example.junitdemo.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ItemBusinessServiceTest {

    @InjectMocks
    ItemBusinessService itemBusinessService;

    @Mock
    ItemRepository itemRepository;


    //Item(int id, String name, int price, int quantity)
    @Test
    public void retrieveAllItems_basic() {

        Mockito.when(itemRepository.findAll()).thenReturn(Arrays.asList(new Item(1, "", 100, 100),
                new Item(2, "", 200, 200)));

        List<Item> list = itemBusinessService.retrieveAllItems();

        Assertions.assertEquals(0, list.get(0).getValue());
        Assertions.assertEquals(2, list.get(1).getValue());

    }

}
