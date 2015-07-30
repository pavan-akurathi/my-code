package com.gopivotal.cf.workshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gopivotal.cf.workshop.entity.Contact;
public interface ContactRepository extends MongoRepository<Contact, String> {}
