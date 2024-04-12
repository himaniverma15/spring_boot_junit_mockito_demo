package com.example.junitdemo.data;

import com.example.junitdemo.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository repository;

    @Test
    public void testFindAll() {
        List<Item> items = repository.findAll();
        assertEquals(3, items.size());
    }

    @Test
    public void testFindOne() {
        Item item = repository.findById(10001).get();

        assertEquals("Item1", item.getName());
    }
}
/*
The @DataJpaTest annotation does the following stuff:

It scans the @Entity classes and Spring Data JPA repositories.
Set the spring.jpa.show-sql property to true and enable the SQL queries logging.
Default, JPA test data are transactional and roll back at the end of each test; it means we do not need to clean up saved or modified table data after each test.
Replace the application data source, run and configure the embedded database on classpath.
 */