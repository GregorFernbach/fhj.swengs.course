package at.fh.ima.swengs.moviedbv2.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends PagingAndSortingRepository<Actor,Long> {
}
