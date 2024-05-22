package com.project.sigpag.sigpagapi.domain.service;

import com.project.sigpag.sigpagapi.domain.exception.NegocioException;
import com.project.sigpag.sigpagapi.domain.model.Cliente;
import com.project.sigpag.sigpagapi.domain.model.Parcelamento;
import com.project.sigpag.sigpagapi.domain.repository.ClienteRepository;
import com.project.sigpag.sigpagapi.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {

    private final ParcelamentoRepository parcelamentoRepository;
    private final CadastroClienteService cadastroClienteService;
    @Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelamento) {
        if (novoParcelamento.getId() != null) {
            throw new NegocioException("Parcelamento a ser criado não deve possuir um código");
        }

        //validação para garantir que o cliente existe
        //entenda: se tiver um cliente, ele retorna um Optional
        //senão, lançamos uma excessão (orElseThrow())
        Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());

        novoParcelamento.setCliente(cliente);
        novoParcelamento.setDataCriacao(LocalDateTime.now());

        return parcelamentoRepository.save(novoParcelamento);
    }
}
