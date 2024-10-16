package com.desafio.picpay.services;

import com.desafio.picpay.domain.usuario.TipoUsuario;
import com.desafio.picpay.domain.usuario.Usuario;
import com.desafio.picpay.domain.usuario.UsuarioDTO;
import com.desafio.picpay.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validarTransacao(Usuario remetente, BigDecimal valor) throws Exception {
        if (remetente.getTipoUsuario() == TipoUsuario.LOJISTAS) {
            throw new Exception("Usuário do tipo Lojista não está autorizado a realizar transação");
        }

        if (remetente.getSaldo().compareTo(valor) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public Usuario encontrarUsuarioPorId(Long id) throws Exception {
        return this.usuarioRepository.findUsuarioById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void salvarUsuario(Usuario usuario) {
        this.usuarioRepository.save(usuario);
    }

    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = new Usuario(usuarioDTO);
        this.salvarUsuario(novoUsuario);
        return novoUsuario;
    }

    public List<Usuario> listarUsuarios() {
        return this.usuarioRepository.findAll();
    }
}
