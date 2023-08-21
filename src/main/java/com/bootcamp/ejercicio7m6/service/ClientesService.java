package com.bootcamp.ejercicio7m6.service;

import com.bootcamp.ejercicio7m6.domain.Clientes;
import com.bootcamp.ejercicio7m6.domain.Usuarios;
import com.bootcamp.ejercicio7m6.model.ClientesDTO;
import com.bootcamp.ejercicio7m6.repos.ClientesRepository;
import com.bootcamp.ejercicio7m6.repos.UsuariosRepository;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ClientesService {

    private final ClientesRepository clientesRepository;
    private final UsuariosRepository usuariosRepository;

    public ClientesService(final ClientesRepository clientesRepository,
            final UsuariosRepository usuariosRepository) {
        this.clientesRepository = clientesRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<ClientesDTO> findAll() {
        final List<Clientes> clientess = clientesRepository.findAll(Sort.by("idCliente"));
        return clientess.stream()
                .map(clientes -> mapToDTO(clientes, new ClientesDTO()))
                .toList();
    }

    public ClientesDTO get(final Long idCliente) {
        return clientesRepository.findById(idCliente)
                .map(clientes -> mapToDTO(clientes, new ClientesDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ClientesDTO clientesDTO) {
        final Clientes clientes = new Clientes();
        mapToEntity(clientesDTO, clientes);
        return clientesRepository.save(clientes).getIdCliente();
    }

    public void update(final Long idCliente, final ClientesDTO clientesDTO) {
        final Clientes clientes = clientesRepository.findById(idCliente)
                .orElseThrow(NotFoundException::new);
        mapToEntity(clientesDTO, clientes);
        clientesRepository.save(clientes);
    }

    public void delete(final Long idCliente) {
        clientesRepository.deleteById(idCliente);
    }

    private ClientesDTO mapToDTO(final Clientes clientes, final ClientesDTO clientesDTO) {
        clientesDTO.setIdCliente(clientes.getIdCliente());
        clientesDTO.setAfp(clientes.getAfp());
        clientesDTO.setApellidos(clientes.getApellidos());
        clientesDTO.setComuna(clientes.getComuna());
        clientesDTO.setDireccion(clientes.getDireccion());
        clientesDTO.setEdad(clientes.getEdad());
        clientesDTO.setRut(clientes.getRut());
        clientesDTO.setSistemaSalud(clientes.getSistemaSalud());
        clientesDTO.setTelefono(clientes.getTelefono());
        clientesDTO.setUsuario(clientes.getUsuario() == null ? null : clientes.getUsuario().getIdUsuario());
        return clientesDTO;
    }

    private Clientes mapToEntity(final ClientesDTO clientesDTO, final Clientes clientes) {
        clientes.setAfp(clientesDTO.getAfp());
        clientes.setApellidos(clientesDTO.getApellidos());
        clientes.setComuna(clientesDTO.getComuna());
        clientes.setDireccion(clientesDTO.getDireccion());
        clientes.setEdad(clientesDTO.getEdad());
        clientes.setRut(clientesDTO.getRut());
        clientes.setSistemaSalud(clientesDTO.getSistemaSalud());
        clientes.setTelefono(clientesDTO.getTelefono());
        final Usuarios usuario = clientesDTO.getUsuario() == null ? null : usuariosRepository.findById(clientesDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("usuario not found"));
        clientes.setUsuario(usuario);
        return clientes;
    }

    public boolean usuarioExists(final Long idUsuario) {
        return clientesRepository.existsByUsuarioIdUsuario(idUsuario);
    }

}
