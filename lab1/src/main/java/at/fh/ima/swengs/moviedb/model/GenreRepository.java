package at.fh.ima.swengs.moviedb.model;
 
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface GenreRepository  extends PagingAndSortingRepository<Genre,Long> {
}