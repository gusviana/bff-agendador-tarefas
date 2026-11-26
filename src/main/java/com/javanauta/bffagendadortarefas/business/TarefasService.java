package com.javanauta.bffagendadortarefas.business;


import com.javanauta.bffagendadortarefas.business.dto.request.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.response.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest tarefasDTO) {
        return tarefasClient.gravarTarefa(tarefasDTO, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return tarefasClient.buscaListaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.BuscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatusTarefa(StatusNotificacaoEnum statusNotificacaoEnum, String id, String token){
        return tarefasClient.alteraStatusNotificacao(statusNotificacaoEnum, id, token);
    }

    public TarefasDTOResponse updateTarefa(TarefasDTORequest tarefasDTO, String id, String token){
        return tarefasClient.updateTarefa(tarefasDTO, id, token);
    }
}
