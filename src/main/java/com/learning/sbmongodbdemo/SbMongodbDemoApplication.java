package com.learning.sbmongodbdemo;


import com.learning.sbmongodbdemo.collection.Address;
import com.learning.sbmongodbdemo.collection.Gender;
import com.learning.sbmongodbdemo.collection.Student;
import com.learning.sbmongodbdemo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;


@SpringBootApplication

public class SbMongodbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbMongodbDemoApplication.class, args);
    }
    @Bean
    CommandLineRunner Runner (StudentRepository studentRepository,
                              MongoTemplate mongoTemplate)
    {
        return args -> {
            Address address = new Address("EG", "1234","Cairo");
            Student student = new Student(
                    "ahmed",
                    "hamdi",
                    "ah2093@gamil.com",
                    Gender.MALE,
                    address,
                    List.of("ComputerScence "),
                    BigDecimal.TEN,
                    LocalDateTime.now()
            );
            String email = "ah2093@gamil.com";
            Query query =new Query();
            query.addCriteria(Criteria.where("email").is(email));

            List<Student> students = mongoTemplate.find(query, Student.class);
            if (students.size() >1 ) {
                throw new IllegalStateException(
                        "found many student with this email : " + email);
            }
            if (students.isEmpty()) {
                System.out.println("inserting student "+student);
                studentRepository.insert(student);
            }
            else {
                System.out.println(student+"    xalready exists");
            }
        };
    }

}