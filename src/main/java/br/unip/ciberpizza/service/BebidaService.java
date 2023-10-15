package br.unip.ciberpizza.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ciberpizza.model.Bebida;
import br.unip.ciberpizza.repository.BebidaRepository;

@Service
public class BebidaService {
    @Autowired
    private final BebidaRepository bebidaRepository;

    public BebidaService(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    public Bebida salvarBebida(Bebida bebida) {
        return bebidaRepository.save(bebida);
    }

    public List<Bebida> listarBebidas() {
        return bebidaRepository.findAll();
    }

    public Bebida encontrarBebidaPorId(UUID id) {
        return bebidaRepository.findById(id).orElse(null);
    }

    public Bebida atualizarBebida(UUID id, Bebida bebidaAtualizada) {
        Bebida bebidaExistente = bebidaRepository.findById(id).orElse(null);

        if (bebidaExistente != null) {
            bebidaExistente.setNome(bebidaAtualizada.getNome());
            bebidaExistente.setDescricao(bebidaAtualizada.getDescricao());
            bebidaExistente.setValor(bebidaAtualizada.getValor());

            return bebidaRepository.save(bebidaExistente);
        } else {
            return null;
        }
    }

    public void deletarBebida(UUID id) {
        bebidaRepository.deleteById(id);
    }
}
