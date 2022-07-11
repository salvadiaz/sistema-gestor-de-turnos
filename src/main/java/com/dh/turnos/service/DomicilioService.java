package com.dh.turnos.service;

import com.dh.turnos.exceptions.NotFoundException;
import com.dh.turnos.model.Domicilio;
import com.dh.turnos.repository.DomicilioReporitory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService {
    public static Logger logger = Logger.getLogger(DomicilioService.class);

    @Autowired
    private DomicilioReporitory domicilioReporitory;

    public Domicilio crearDomicilio(Domicilio domicilio) {
        logger.info("Creando domicilio " + domicilio);
        return domicilioReporitory.save(domicilio);
    }

    public Domicilio buscarPorId(Long id) {
        return domicilioReporitory.findById(id)
                .orElseThrow(() -> new NotFoundException("Domicilio no encontrado"));
    }

    public List<Domicilio> listar() {
        return domicilioReporitory.findAll();
    }

    public Domicilio modificar(Domicilio domicilio) {
        Domicilio dom = buscarPorId(domicilio.getId());
        dom.setCalle(domicilio.getCalle());
        dom.setLocalidad(domicilio.getLocalidad());
        dom.setNumero(domicilio.getNumero());
        dom.setProvincia(domicilio.getProvincia());

        return domicilioReporitory.save(dom);
    }

    public void eliminar(Long id) {
        Domicilio domicilio = domicilioReporitory.findById(id).orElseThrow(() -> new NotFoundException("El domicilio que quiere eliminar no existe"));
        logger.info("Se elimino el domicilio " + domicilio);
    }
}
