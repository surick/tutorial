package com.jinaiya.utils.service;

import com.jinaiya.utils.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Jin
 * @date 2018/12/27
 */
public interface UserRepository extends MongoRepository<User, Long> {

    User insert(User user);

    User findById(String id);

    List<User> deleteById(String id);
}
