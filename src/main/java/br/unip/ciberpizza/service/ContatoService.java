package br.unip.ciberpizza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ciberpizza.model.Contato;
import br.unip.ciberpizza.repository.ContatoRepository;

@Service
public class ContatoService {
    @Autowired
    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public void salvarContato(Contato contato) {
        contatoRepository.save(contato);
    }
}
