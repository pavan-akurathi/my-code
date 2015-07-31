package com.verizon.cab.management.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.cab.management.domain.Album;
import com.verizon.cab.management.repositories.AlbumRepository;

@Repository
public interface JpaAlbumRepository extends JpaRepository<Album, String>, AlbumRepository {
}
