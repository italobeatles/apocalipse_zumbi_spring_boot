package com.apocalipse.zumbi.dto;

import java.util.ArrayList;
import java.util.List;

public class TradeRequest {
    private Long sobrevivente_origem;
    private Long sobrevivente_destino;
    private List<TradeItem> itens_origem = new ArrayList<>();
    private List<TradeItem> itens_destino = new ArrayList<>();

    public Long getSobrevivente_origem() {
        return sobrevivente_origem;
    }

    public void setSobrevivente_origem(Long sobrevivente_origem) {
        this.sobrevivente_origem = sobrevivente_origem;
    }

    public Long getSobrevivente_destino() {
        return sobrevivente_destino;
    }

    public void setSobrevivente_destino(Long sobrevivente_destino) {
        this.sobrevivente_destino = sobrevivente_destino;
    }

    public List<TradeItem> getItens_origem() {
        return itens_origem;
    }

    public void setItens_origem(List<TradeItem> itens_origem) {
        this.itens_origem = itens_origem;
    }

    public List<TradeItem> getItens_destino() {
        return itens_destino;
    }

    public void setItens_destino(List<TradeItem> itens_destino) {
        this.itens_destino = itens_destino;
    }
}
