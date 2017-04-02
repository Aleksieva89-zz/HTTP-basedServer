package user;

import org.springframework.data.repository.CrudRepository;

import user.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
