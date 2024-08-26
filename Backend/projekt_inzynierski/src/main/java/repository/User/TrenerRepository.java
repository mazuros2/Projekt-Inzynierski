package repository.User;

import models.users.Trener;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrenerRepository extends JpaRepository<Trener,Long> {
}
