package io.github.andrefurtado11135.quarkussocial.service;

import io.github.andrefurtado11135.quarkussocial.dto.CreateUserRequest;
import io.github.andrefurtado11135.quarkussocial.entity.User;
import io.github.andrefurtado11135.quarkussocial.exception.ApplicationException;
import io.github.andrefurtado11135.quarkussocial.repository.UserRepository;
import io.github.andrefurtado11135.quarkussocial.type.ApplicationErrorStatus;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
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
        Optional<User> user = userRepository.findByIdOptional(id);

        if (user.isPresent()){
            User userToDelete = user.get();
            userRepository.delete(userToDelete);
        }else{
            throw new ApplicationException("User not found", Response.Status.NOT_FOUND.getStatusCode(), ApplicationErrorStatus.USER_NOT_FOUND.name());
        }
    }

    @Transactional
    public void updateUser(Long id, CreateUserRequest createUserRequest){
        Optional<User> user = userRepository.findByIdOptional(id);

        if (user.isPresent()){
            User userToUpdate = user.get();
            userToUpdate.setName(createUserRequest.getName());
            userToUpdate.setAge(createUserRequest.getAge());
        }else{
            throw new ApplicationException("User not found", Response.Status.NOT_FOUND.getStatusCode(), ApplicationErrorStatus.USER_NOT_FOUND.name());
        }
    }
}
