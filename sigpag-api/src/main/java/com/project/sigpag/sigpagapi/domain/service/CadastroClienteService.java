package com.project.sigpag.sigpagapi.domain.service;

import com.project.sigpag.sigpagapi.domain.exception.NegocioException;
import com.project.sigpag.sigpagapi.domain.model.Cliente;
import com.project.sigpag.sigpagapi.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service //torna um componente do spring mas sendo uma classe de serviço
public class CadastroClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente))
                //gerando um novo optional onde c (var) não é igual ao cliente que está
                //sendo cadastrado/atualizado, retorne um novo optional. Requer a
                //implementação correta do EqualsHashCode
                .isPresent(); //se tiver algo, já existe um email cadastrado


        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com este email");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
