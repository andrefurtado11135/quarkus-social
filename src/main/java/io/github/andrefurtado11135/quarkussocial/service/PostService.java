package io.github.andrefurtado11135.quarkussocial.service;

import io.github.andrefurtado11135.quarkussocial.dto.CreatePostRequest;
import io.github.andrefurtado11135.quarkussocial.entity.Post;
import io.github.andrefurtado11135.quarkussocial.entity.User;
import io.github.andrefurtado11135.quarkussocial.exception.ApplicationException;
import io.github.andrefurtado11135.quarkussocial.repository.PostRepository;
import io.github.andrefurtado11135.quarkussocial.repository.UserRepository;
import io.github.andrefurtado11135.quarkussocial.type.ApplicationErrorStatus;
import io.github.andrefurtado11135.quarkussocial.vo.PostResponseVO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PostService {

    private UserRepository userRepository;

    private PostRepository postRepository;

    @Inject
    public PostService(UserRepository userRepository, PostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void savePost(Long userId, CreatePostRequest createPostRequest){
        Optional<User> user = userRepository.findByIdOptional(userId);
        if (user.isPresent()){
            Post post = new Post();
            post.setText(createPostRequest.getText());
            post.setDateTime(LocalDateTime.now());
            post.setUser(user.get());
            postRepository.persist(post);
        }else{
            throw new ApplicationException("User not found", Response.Status.NOT_FOUND.getStatusCode(), ApplicationErrorStatus.USER_NOT_FOUND.name());
        }
    }

    public List<PostResponseVO> getPosts(Long userId){
        Optional<User> user = userRepository.findByIdOptional(userId);
        if (user.isPresent()){
            PanacheQuery<Post> query = postRepository.find("user", Sort.descending("date_time"), user.get());
            return query.list().stream().map(PostResponseVO::fromEntity).collect(Collectors.toList());
        }else{
            throw new ApplicationException("User not found", Response.Status.NOT_FOUND.getStatusCode(), ApplicationErrorStatus.USER_NOT_FOUND.name());
        }
    }
}
