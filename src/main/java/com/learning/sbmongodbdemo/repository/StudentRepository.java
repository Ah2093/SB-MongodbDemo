package com.learning.sbmongodbdemo.repository;

import com.learning.sbmongodbdemo.collection.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {

}
