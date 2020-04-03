package com.epass.backgroundrop.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.epass.backgroundrop.gateway.ObjectStorageGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buckets")
public class BucketController {

    @Autowired
    private ObjectStorageGateway objectStorageProvider;

    @GetMapping
    public ResponseEntity<List<String>> getBuckets() {
        return ResponseEntity.ok().body(objectStorageProvider.getBuckets());
    }

    @GetMapping(path = "/{id_bucket}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bucket> getBucket(@PathVariable(name = "id_bucket", required = true) String id) {
        var bucket = objectStorageProvider.getBucket(id);
        if (bucket.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bucket.get());
    }

    @GetMapping(path = "/{id_bucket}/objects/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getObjects(@PathVariable(name = "id_bucket", required = true) String id){
        return ResponseEntity.ok().body(objectStorageProvider.getObject());
    }

}
