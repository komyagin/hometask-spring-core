package io.github.komyagin.services;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.komyagin.dao.entity.User;

public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable User getUserByEmail(@Nonnull String email);

}
