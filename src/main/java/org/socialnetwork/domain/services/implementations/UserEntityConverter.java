package org.socialnetwork.domain.services.implementations;

import org.socialnetwork.dal.entities.UserEntity;
import org.socialnetwork.domain.models.User;

public interface UserEntityConverter {
    UserEntity covert(User user);
}
