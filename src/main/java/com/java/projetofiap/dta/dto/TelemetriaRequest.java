package com.java.projetofiap.dta.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TelemetriaRequest {

    @NotBlank(message = "O identificador do dispositivo e obrigatorio")
    private String dispositivoId;

    @NotBlank(message = "O setor e obrigatorio")
    private String setor;

    @NotNull(message = "O consumo em kWh e obrigatorio")
    @DecimalMin(value = "0.01", message = "O consumo em kWh deve ser maior que zero")
    private Double consumoKwh;

    public TelemetriaRequest() {
    }

    public TelemetriaRequest(String dispositivoId, String setor, Double consumoKwh) {
        this.dispositivoId = dispositivoId;
        this.setor = setor;
        this.consumoKwh = consumoKwh;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Double getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(Double consumoKwh) {
        this.consumoKwh = consumoKwh;
    }
}