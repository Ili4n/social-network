package org.socialnetwork.presentation.web.security;

public interface SecurityUserRepository {

    SecurityUser findByEmail(String email);
}
