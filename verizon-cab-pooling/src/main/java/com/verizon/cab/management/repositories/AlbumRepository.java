package com.verizon.cab.management.repositories;

import org.springframework.data.repository.CrudRepository;

import com.verizon.cab.management.domain.Album;

public interface AlbumRepository extends CrudRepository<Album, String> {
}
