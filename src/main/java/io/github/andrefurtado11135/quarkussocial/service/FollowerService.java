package io.github.andrefurtado11135.quarkussocial.service;

import io.github.andrefurtado11135.quarkussocial.dto.FollowerRequest;
import io.github.andrefurtado11135.quarkussocial.entity.Follower;
import io.github.andrefurtado11135.quarkussocial.entity.User;
import io.github.andrefurtado11135.quarkussocial.exception.InvalidFollowerException;
import io.github.andrefurtado11135.quarkussocial.repository.FollowerRepository;
import io.github.andrefurtado11135.quarkussocial.vo.FollowerResponse;
import io.github.andrefurtado11135.quarkussocial.vo.FollowersPerUserResponseVO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FollowerService {

    private FollowerRepository followerRepository;

    private UserService userService;

    @Inject
    public FollowerService(FollowerRepository followerRepository, UserService userService){
        this.followerRepository = followerRepository;
        this.userService = userService;
    }

    @Transactional
    public void followUser(Long userId, FollowerRequest followerRequest){
        if (userId.equals(followerRequest.getFollowerId())){
            throw new InvalidFollowerException("You can not follow yourself");
        }

        User user = userService.findUserById(userId);
        User follower = userService.findUserById(followerRequest.getFollowerId());

        if(!followerRepository.alreadyFollows(user, follower)){
            var entity = new Follower();
            entity.setUser(user);
            entity.setFollower(follower);
            followerRepository.persist(entity);
        }
    }

    public FollowersPerUserResponseVO getFollowersByUser(Long userId){
        userService.findUserById(userId);
        List<FollowerResponse> followers = followerRepository.findFollowersByUser(userId).stream().map(FollowerResponse::new).collect(Collectors.toList());
        FollowersPerUserResponseVO response = new FollowersPerUserResponseVO();
        response.setFollowersCount(followers.size());
        response.setContent(followers);
        return response;
    }

    @Transactional
    public void unfollowUser(Long userId, Long followerId){
        userService.findUserById(userId);
        followerRepository.deleteByFollowerIdAndUserId(userId, followerId);
    }
}
