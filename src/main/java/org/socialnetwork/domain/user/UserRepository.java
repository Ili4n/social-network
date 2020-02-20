package org.socialnetwork.domain.user;

public interface UserRepository {

    User findById(long userId);

    void save(User user);
}
