package com.verizon.cab.management.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.verizon.cab.management.domain.Album;
import com.verizon.cab.management.repositories.AlbumRepository;

@Repository
public interface MongoAlbumRepository extends MongoRepository<Album, String>, AlbumRepository {
}