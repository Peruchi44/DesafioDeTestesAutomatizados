package com.java.projetofiap.dta.dto;

import java.time.LocalDateTime;

public class TelemetriaResponse {

    private String id;
    private String status;
    private String dispositivoId;
    private String setor;
    private Double consumoKwh;
    private Double emissaoEstimadaKgCo2;
    private LocalDateTime timestamp;

    public TelemetriaResponse() {
    }

    public TelemetriaResponse(String id, String status, String dispositivoId, String setor, Double consumoKwh, Double emissaoEstimadaKgCo2, LocalDateTime timestamp) {
        this.id = id;
        this.status = status;
        this.dispositivoId = dispositivoId;
        this.setor = setor;
        this.consumoKwh = consumoKwh;
        this.emissaoEstimadaKgCo2 = emissaoEstimadaKgCo2;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDispositivoId() { return dispositivoId; }
    public void setDispositivoId(String dispositivoId) { this.dispositivoId = dispositivoId; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public Double getConsumoKwh() { return consumoKwh; }
    public void setConsumoKwh(Double consumoKwh) { this.consumoKwh = consumoKwh; }

    public Double getEmissaoEstimadaKgCo2() { return emissaoEstimadaKgCo2; }
    public void setEmissaoEstimadaKgCo2(Double emissaoEstimadaKgCo2) { this.emissaoEstimadaKgCo2 = emissaoEstimadaKgCo2; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}