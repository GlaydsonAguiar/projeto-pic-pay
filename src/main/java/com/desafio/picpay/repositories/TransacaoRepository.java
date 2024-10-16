package com.desafio.picpay.repositories;

import com.desafio.picpay.domain.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
