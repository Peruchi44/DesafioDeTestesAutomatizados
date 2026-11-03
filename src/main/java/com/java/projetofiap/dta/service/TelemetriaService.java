package com.java.projetofiap.dta.service;

import com.java.projetofiap.dta.dto.TelemetriaRequest;
import com.java.projetofiap.dta.dto.TelemetriaResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class TelemetriaService {

    // Simulação em memória para a execução dos testes
    private final Map<String, TelemetriaResponse> repositorio = new ConcurrentHashMap<>();
    private static final double FATOR_EMISSAO_CO2 = 0.08; // 0.08 kg de CO2 por cada kWh consumido

    public TelemetriaResponse registrar(TelemetriaRequest request) {
        String idGerado = UUID.randomUUID().toString();
        double emissao = Math.round((request.getConsumoKwh() * FATOR_EMISSAO_CO2) * 100.0) / 100.0;

        TelemetriaResponse response = new TelemetriaResponse(
                idGerado,
                "REGISTRADO",
                request.getDispositivoId(),
                request.getSetor(),
                request.getConsumoKwh(),
                emissao,
                LocalDateTime.now()
        );

        repositorio.put(idGerado, response);
        return response;
    }

    public List<TelemetriaResponse> listarTodos(String setor) {
        if (setor != null && !setor.isBlank()) {
            return repositorio.values().stream()
                    .filter(t -> t.getSetor().equalsIgnoreCase(setor))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>(repositorio.values());
    }

    public Optional<TelemetriaResponse> buscarPorId(String id) {
        return Optional.ofNullable(repositorio.get(id));
    }
}