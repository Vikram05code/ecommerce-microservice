package com.ecommerce.user.repository;

import com.ecommerce.user.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends MongoRepository<User, Long> {
}
