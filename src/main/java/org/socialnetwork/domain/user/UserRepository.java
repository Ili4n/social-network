package org.socialnetwork.domain.user;

import java.util.List;

public interface UserRepository{

    User findById(long userId);

    void save(User user);

    List<User> findUsers(String firstName, String email);
}
