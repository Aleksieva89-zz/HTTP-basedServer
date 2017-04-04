package app;

import org.springframework.data.repository.CrudRepository;

import app.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
