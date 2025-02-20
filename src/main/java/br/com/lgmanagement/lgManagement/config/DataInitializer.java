package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.domain.entities.Endereco;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (caixaRepository.count() == 0) {
            final CaixaEntity caixaEntity = new CaixaEntity(BigDecimal.ZERO);
            caixaRepository.save(caixaEntity);
        }

        if (usuarioRepository.count() == 0) {
            final UsuarioEntity usuarioEntity = new UsuarioEntity(
                    UUID.randomUUID().toString(),
                    "root@root.com",
                    "$2a$12$W3XkW3Mb3IuBh6TobyrMR.7G3FQDYf4ioCzv5Zbifo5z.3lT//Z2G",
                    Boolean.TRUE
            );

            final FuncionarioEntity funcionarioEntity = new FuncionarioEntity(
                    UUID.randomUUID().toString(),
                    "Root",
                    "12345678900",
                    new Endereco("Rua 0", "Bairro 0", "0", "Complemento 0"),
                    usuarioEntity,
                    new ArrayList<>()
            );

            usuarioRepository.save(usuarioEntity);
            funcionarioRepository.save(funcionarioEntity);
        }
    }
}
