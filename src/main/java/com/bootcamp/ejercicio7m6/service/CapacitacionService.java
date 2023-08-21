package com.bootcamp.ejercicio7m6.service;

import com.bootcamp.ejercicio7m6.domain.Capacitacion;
import com.bootcamp.ejercicio7m6.domain.Clientes;
import com.bootcamp.ejercicio7m6.model.CapacitacionDTO;
import com.bootcamp.ejercicio7m6.repos.CapacitacionRepository;
import com.bootcamp.ejercicio7m6.repos.ClientesRepository;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CapacitacionService {

    private final CapacitacionRepository capacitacionRepository;
    private final ClientesRepository clientesRepository;

    public CapacitacionService(final CapacitacionRepository capacitacionRepository,
            final ClientesRepository clientesRepository) {
        this.capacitacionRepository = capacitacionRepository;
        this.clientesRepository = clientesRepository;
    }

    public List<CapacitacionDTO> findAll() {
        final List<Capacitacion> capacitacions = capacitacionRepository.findAll(Sort.by("numCapacitacion"));
        return capacitacions.stream()
                .map(capacitacion -> mapToDTO(capacitacion, new CapacitacionDTO()))
                .toList();
    }

    public CapacitacionDTO get(final Integer numCapacitacion) {
        return capacitacionRepository.findById(numCapacitacion)
                .map(capacitacion -> mapToDTO(capacitacion, new CapacitacionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final CapacitacionDTO capacitacionDTO) {
        final Capacitacion capacitacion = new Capacitacion();
        mapToEntity(capacitacionDTO, capacitacion);
        return capacitacionRepository.save(capacitacion).getNumCapacitacion();
    }

    public void update(final Integer numCapacitacion, final CapacitacionDTO capacitacionDTO) {
        final Capacitacion capacitacion = capacitacionRepository.findById(numCapacitacion)
                .orElseThrow(NotFoundException::new);
        mapToEntity(capacitacionDTO, capacitacion);
        capacitacionRepository.save(capacitacion);
    }

    public void delete(final Integer numCapacitacion) {
        capacitacionRepository.deleteById(numCapacitacion);
    }

    private CapacitacionDTO mapToDTO(final Capacitacion capacitacion,
            final CapacitacionDTO capacitacionDTO) {
        capacitacionDTO.setNumCapacitacion(capacitacion.getNumCapacitacion());
        capacitacionDTO.setCantidadAsistentes(capacitacion.getCantidadAsistentes());
        capacitacionDTO.setDetalle(capacitacion.getDetalle());
        capacitacionDTO.setDiaSemana(capacitacion.getDiaSemana());
        capacitacionDTO.setDuracion(capacitacion.getDuracion());
        capacitacionDTO.setHora(capacitacion.getHora());
        capacitacionDTO.setLugar(capacitacion.getLugar());
        capacitacionDTO.setNombre(capacitacion.getNombre());
        capacitacionDTO.setRutCliente(capacitacion.getRutCliente() == null ? null : capacitacion.getRutCliente().getIdCliente());
        return capacitacionDTO;
    }

    private Capacitacion mapToEntity(final CapacitacionDTO capacitacionDTO,
            final Capacitacion capacitacion) {
        capacitacion.setCantidadAsistentes(capacitacionDTO.getCantidadAsistentes());
        capacitacion.setDetalle(capacitacionDTO.getDetalle());
        capacitacion.setDiaSemana(capacitacionDTO.getDiaSemana());
        capacitacion.setDuracion(capacitacionDTO.getDuracion());
        capacitacion.setHora(capacitacionDTO.getHora());
        capacitacion.setLugar(capacitacionDTO.getLugar());
        capacitacion.setNombre(capacitacionDTO.getNombre());
        final Clientes rutCliente = capacitacionDTO.getRutCliente() == null ? null : clientesRepository.findById(capacitacionDTO.getRutCliente())
                .orElseThrow(() -> new NotFoundException("rutCliente not found"));
        capacitacion.setRutCliente(rutCliente);
        return capacitacion;
    }

}
