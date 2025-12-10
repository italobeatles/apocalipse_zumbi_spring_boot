package com.apocalipse.zumbi.dto;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class TradeRequest {
    private Long sobrevivente_origem;
    private Long sobrevivente_destino;
    private List<TradeItem> itens_origem = new ArrayList<>();
    private List<TradeItem> itens_destino = new ArrayList<>();
}
