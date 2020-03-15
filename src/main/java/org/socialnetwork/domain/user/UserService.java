package org.socialnetwork.domain.user;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> findUser(String firstName,String email);
}
