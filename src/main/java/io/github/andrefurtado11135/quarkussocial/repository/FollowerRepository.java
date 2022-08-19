package io.github.andrefurtado11135.quarkussocial.repository;

import io.github.andrefurtado11135.quarkussocial.model.entity.Follower;
import io.github.andrefurtado11135.quarkussocial.model.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {

    public boolean alreadyFollows(User user, User follower){
        var queryParams = Parameters.with("follower", follower).and("user", user).map();
        PanacheQuery<Follower> query = find("follower = :follower and user = :user", queryParams);
        return query.firstResultOptional().isPresent();
    }

    public List<Follower> findFollowersByUser(Long userId){
        PanacheQuery<Follower> query = find("user.id", userId);
        return query.list();
    }

    public void deleteByFollowerIdAndUserId(Long userId, Long followerId){
        var queryParams = Parameters.with("userId", userId).and("followerId", followerId).map();
        delete("user.id = :userId and follower.id = :followerId", queryParams);
    }
}
