package com.dragocxz.emistream.repository;

import com.dragocxz.emistream.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {

}
