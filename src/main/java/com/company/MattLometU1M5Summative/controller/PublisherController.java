package com.company.MattLometU1M5Summative.controller;

import com.company.MattLometU1M5Summative.dao.PublisherDaoImpl;
import com.company.MattLometU1M5Summative.dto.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    // thought we needed these but i believe after reading the instructions we do not
    // they do work tho

    @Autowired
    PublisherDaoImpl publishDao;

    @GetMapping("/id/{id}")
    public Publisher getPublisher(@PathVariable int id){
        return publishDao.getPublisher(id);
    }

    @GetMapping("/all")
    public List<Publisher> getAllPublishers(){
        return publishDao.getAllPublishers();
    }

    @PostMapping("/create")
    public Publisher createPublisher(@RequestBody Publisher publisher){
        return publishDao.addPublisher(publisher);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePublisher(@PathVariable int id){
        publishDao.deletePublisher(id);
    }

}
