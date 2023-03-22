package curso.springboot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.model.Pessoa;
import curso.springboot.model.Role;
import curso.springboot.model.Usuario;
import curso.springboot.repository.PessoaRepository;
import curso.springboot.repository.RoleRepository;
import curso.springboot.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private RoleRepository roleRepository;

	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "**/usuarioscad")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("cadastro/usuarioscad");
		modelAndView.addObject("userobj", new Usuario());
		Iterable<Usuario> pessoasIt = usuarioRepository.findAll();
		modelAndView.addObject("usuario", pessoasIt);
		modelAndView.addObject("roles",roleRepository.findAll());
		return modelAndView;
	}
	@PostMapping( value = "**/salvareditado")
	public ModelAndView salvarEditado(@Valid Usuario usuario) throws IOException {
		usuarioRepository.save(usuario);

		ModelAndView andView = new ModelAndView("cadastro/usuarioscad");
		andView.addObject("roles", roleRepository.findAll());
		andView.addObject("usuario", usuarioRepository.findAll());
		andView.addObject("userobj", new Usuario());
			
		return andView;

	}
	
	@GetMapping("/editarusuario/{idusuario}")
	public ModelAndView editar(@PathVariable("idusuario") Long idusuario) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(idusuario);

		ModelAndView modelAndView = new ModelAndView("cadastro/usuarioscad");
		modelAndView.addObject("userobj", usuario.get());
		modelAndView.addObject("roles",roleRepository.findAll());
		return modelAndView;
		
	}
	
	@GetMapping("/removerusuario/{idusuario}")
	public ModelAndView excluir(@PathVariable("idusuario") Long idusuario) {
		
		usuarioRepository.deleteById(idusuario);	
		
		ModelAndView modelAndView = new ModelAndView("cadastro/usuarioscad");
		modelAndView.addObject("usuario", usuarioRepository.findAll());
		modelAndView.addObject("userobj", new Usuario());
		return modelAndView;
		
	}
	
	
}
