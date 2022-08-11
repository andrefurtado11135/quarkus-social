package io.github.andrefurtado11135.quarkussocial.service;

import io.github.andrefurtado11135.quarkussocial.dto.CreatePostRequest;
import io.github.andrefurtado11135.quarkussocial.entity.Post;
import io.github.andrefurtado11135.quarkussocial.entity.User;
import io.github.andrefurtado11135.quarkussocial.exception.ForbiddenRequestException;
import io.github.andrefurtado11135.quarkussocial.exception.InvalidParamException;
import io.github.andrefurtado11135.quarkussocial.repository.FollowerRepository;
import io.github.andrefurtado11135.quarkussocial.repository.PostRepository;
import io.github.andrefurtado11135.quarkussocial.vo.PostResponseVO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PostService {

    private UserService userService;

    private PostRepository postRepository;

    private FollowerRepository followerRepository;

    @Inject
    public PostService(UserService userService, PostRepository postRepository, FollowerRepository followerRepository){
        this.userService = userService;
        this.postRepository = postRepository;
        this.followerRepository = followerRepository;
    }

    @Transactional
    public void savePost(Long userId, CreatePostRequest createPostRequest){
        User user = userService.findUserById(userId);
        Post post = new Post();
        post.setText(createPostRequest.getText());
        post.setDateTime(LocalDateTime.now());
        post.setUser(user);
        postRepository.persist(post);
    }

    public List<PostResponseVO> getPosts(Long userId, Long followerId){

        if (followerId == null){
           throw new InvalidParamException("Header param followerId not found");
        }

        Optional<User> follower = userService.findOptionalUserById(followerId);

        if (follower.isEmpty()){
            throw new InvalidParamException("followerId not exists");
        }

        User user = userService.findUserById(userId);

        if (followerRepository.alreadyFollows(user, follower.get())){
            PanacheQuery<Post> query = postRepository.find("user", Sort.descending("date_time"), user);
            return query.list().stream().map(PostResponseVO::fromEntity).collect(Collectors.toList());
        }else{
            throw new ForbiddenRequestException("You are not allowed to see this user's posts");
        }
    }
}
