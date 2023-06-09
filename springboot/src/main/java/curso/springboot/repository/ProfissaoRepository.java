package curso.springboot.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import curso.springboot.model.Profissao;

@Transactional
@Repository
public interface ProfissaoRepository extends CrudRepository<Profissao, Long>{
	

}
