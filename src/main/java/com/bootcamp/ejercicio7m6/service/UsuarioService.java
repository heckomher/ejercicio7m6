package com.bootcamp.ejercicio7m6.service;

import com.bootcamp.ejercicio7m6.domain.Usuario;
import com.bootcamp.ejercicio7m6.model.UsuarioDTO;
import com.bootcamp.ejercicio7m6.repos.UsuarioRepository;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("id"));
        return usuarios.stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getId();
    }

    public void update(final Long id, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setContrasenha(usuario.getContrasenha());
        usuarioDTO.setTipoUsuario(usuario.getTipoUsuario());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setContrasenha(usuarioDTO.getContrasenha());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        return usuario;
    }

    public boolean nombreUsuarioExists(final String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuarioIgnoreCase(nombreUsuario);
    }

}
