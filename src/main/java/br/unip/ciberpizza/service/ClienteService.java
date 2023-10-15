package br.unip.ciberpizza.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente encontrarClientePorId(UUID id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente atualizarCliente(UUID id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id).orElse(null);

        if (clienteExistente != null) {
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setEmail(clienteAtualizado.getEmail());
            clienteExistente.setCpf(clienteAtualizado.getCpf());
            clienteExistente.setEnderecoEntrega(clienteAtualizado.getEnderecoEntrega());
            clienteExistente.setTelefone(clienteAtualizado.getTelefone());

            return clienteRepository.save(clienteExistente);
        } else {
            return null;
        }
    }

    public void deletarCliente(UUID id) {
        clienteRepository.deleteById(id);
    }
}
