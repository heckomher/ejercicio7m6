package com.bootcamp.ejercicio7m6.service;

import com.bootcamp.ejercicio7m6.domain.Contacto;
import com.bootcamp.ejercicio7m6.model.ContactoDTO;
import com.bootcamp.ejercicio7m6.repos.ContactoRepository;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoService(final ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    public List<ContactoDTO> findAll() {
        final List<Contacto> contactos = contactoRepository.findAll(Sort.by("idContacto"));
        return contactos.stream()
                .map(contacto -> mapToDTO(contacto, new ContactoDTO()))
                .toList();
    }

    public ContactoDTO get(final Integer idContacto) {
        return contactoRepository.findById(idContacto)
                .map(contacto -> mapToDTO(contacto, new ContactoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ContactoDTO contactoDTO) {
        final Contacto contacto = new Contacto();
        mapToEntity(contactoDTO, contacto);
        return contactoRepository.save(contacto).getIdContacto();
    }

    public void update(final Integer idContacto, final ContactoDTO contactoDTO) {
        final Contacto contacto = contactoRepository.findById(idContacto)
                .orElseThrow(NotFoundException::new);
        mapToEntity(contactoDTO, contacto);
        contactoRepository.save(contacto);
    }

    public void delete(final Integer idContacto) {
        contactoRepository.deleteById(idContacto);
    }

    private ContactoDTO mapToDTO(final Contacto contacto, final ContactoDTO contactoDTO) {
        contactoDTO.setIdContacto(contacto.getIdContacto());
        contactoDTO.setCorreo(contacto.getCorreo());
        contactoDTO.setMensaje(contacto.getMensaje());
        contactoDTO.setNombre(contacto.getNombre());
        return contactoDTO;
    }

    private Contacto mapToEntity(final ContactoDTO contactoDTO, final Contacto contacto) {
        contacto.setCorreo(contactoDTO.getCorreo());
        contacto.setMensaje(contactoDTO.getMensaje());
        contacto.setNombre(contactoDTO.getNombre());
        return contacto;
    }

}
