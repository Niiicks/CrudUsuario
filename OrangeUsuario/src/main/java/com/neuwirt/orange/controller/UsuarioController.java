package com.neuwirt.orange.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.neuwirt.orange.dto.UsuarioDto;
import com.neuwirt.orange.model.Usuario;
import com.neuwirt.orange.repositorio.UsuarioRepository;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
		
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid Usuario usuario){		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDto(usuario));
        try {    
        	usuarioRepository.save(usuario);


        } catch (SQLIntegrityConstraintViolationException e) {
            if(e instanceof SQLIntegrityConstraintViolationException) {
                if(e.getSQLState().equals("23000")) {
                    if(e.getMessage().contains("Duplicate")) {
                        if (e.getMessage().contains("email")) {
                            System.out.println("Duplicate username"); 
                        } else {
                            System.out.println("Duplicate email"); 
                        }
                    }
                }
            }
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> consultar(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(usuario.get()));			
		}		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Verifique o ID do usuário!");
	}
}