package io.github.andrefurtado11135.quarkussocial.repository;

import io.github.andrefurtado11135.quarkussocial.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
