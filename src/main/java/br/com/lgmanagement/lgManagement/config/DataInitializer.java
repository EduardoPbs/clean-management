package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CaixaRepository caixaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (caixaRepository.count() == 0) {
            final CaixaEntity caixaEntity = new CaixaEntity(BigDecimal.ZERO);
            caixaRepository.save(caixaEntity);
        }
    }
}
