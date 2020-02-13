package org.socialnetwork.presentation.web.converter;

import org.socialnetwork.domain.models.User;
import org.socialnetwork.presentation.web.binding.UserRegisterForm;

public interface UserFormConverter {
    User convertToUser(UserRegisterForm userRegisterForm);
}
