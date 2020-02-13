package org.socialnetwork.domain.services.implementations;

import org.socialnetwork.dal.entities.UserEntity;
import org.socialnetwork.dal.repositories.UserRepository;
import org.socialnetwork.domain.models.User;
import org.socialnetwork.domain.services.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserEntityConverter userEntityConverter;

    public UserServiceImpl(UserRepository userRepository, UserEntityConverter userEntityConverter) {
        this.userRepository = userRepository;
        this.userEntityConverter = userEntityConverter;
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = userEntityConverter.covert(user);
        userRepository.save(userEntity);
    }
}
