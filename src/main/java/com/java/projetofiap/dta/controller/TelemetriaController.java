package com.java.projetofiap.dta.controller;

import com.java.projetofiap.dta.dto.TelemetriaRequest;
import com.java.projetofiap.dta.dto.TelemetriaResponse;
import com.java.projetofiap.dta.service.TelemetriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/telemetria")
public class TelemetriaController {

    private final TelemetriaService service;

    public TelemetriaController(TelemetriaService service) {
        this.service = service;
    }

    @PostMapping("/consumo")
    public ResponseEntity<TelemetriaResponse> registrar(@Valid @RequestBody TelemetriaRequest request) {
        TelemetriaResponse criada = service.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping("/consumo")
    public ResponseEntity<List<TelemetriaResponse>> listar(@RequestParam(required = false) String setor) {
        return ResponseEntity.ok(service.listarTodos(setor));
    }

    @GetMapping("/consumo/{id}")
    public ResponseEntity<TelemetriaResponse> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}