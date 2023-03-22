package curso.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import curso.springboot.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{

}
