package org.socialnetwork.presentation.web.converter;

import org.socialnetwork.domain.user.User;
import org.socialnetwork.presentation.web.binding.UserRegisterForm;
import org.springframework.stereotype.Component;

@Component
public class UserFormConverterImpl implements UserFormConverter {

    @Override
    public User convertToUser(UserRegisterForm userRegisterForm) {
        User user = new User(userRegisterForm.getFirstName(),userRegisterForm.getLastName(),userRegisterForm.getEmail(),userRegisterForm.getPassword());
        return user;
    }
}
