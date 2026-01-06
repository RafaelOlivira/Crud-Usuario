package com.springboot.cadastro_usuario.business;

import com.springboot.cadastro_usuario.infrastructure.entitys.Usuario;
import com.springboot.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado!")
        );
    }

    public void deletarUsuarioEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Long id, Usuario usuario) {
        Usuario usuarioEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado!")
        );
        Usuario usuarioAtualizado = Usuario
                .builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                .id(usuario.getId())
                .build();

        repository.save(usuarioAtualizado);
    }


}
