package io.github.andrefurtado11135.quarkussocial.service;

import io.github.andrefurtado11135.quarkussocial.dto.CreateUserRequest;
import io.github.andrefurtado11135.quarkussocial.entity.User;
import io.github.andrefurtado11135.quarkussocial.exception.EntityNotFoundException;
import io.github.andrefurtado11135.quarkussocial.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    private UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(CreateUserRequest createUserRequest){
        User user = new User();
        user.setAge(createUserRequest.getAge());
        user.setName(createUserRequest.getName());
        userRepository.persist(user);
        return user;
    }

    public List<User> getAll(){
        PanacheQuery<User> usersQuery = userRepository.findAll();
        return usersQuery.list();
    }

    @Transactional
    public void deleteUser(Long id){
        User userToDelete = findUserById(id);
        userRepository.delete(userToDelete);
    }

    @Transactional
    public void updateUser(Long id, CreateUserRequest createUserRequest){
        User userToUpdate = findUserById(id);
        userToUpdate.setName(createUserRequest.getName());
        userToUpdate.setAge(createUserRequest.getAge());
    }

    public User findUserById(Long id){
        return userRepository.findByIdOptional(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public Optional<User> findOptionalUserById(Long id){
        return userRepository.findByIdOptional(id);
    }
}
