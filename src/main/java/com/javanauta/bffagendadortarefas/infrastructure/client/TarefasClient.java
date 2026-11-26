package com.javanauta.bffagendadortarefas.infrastructure.client;


import com.javanauta.bffagendadortarefas.business.dto.request.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.response.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefas", url = "${tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefa(@RequestBody TarefasDTORequest tarefasDTO,
                                    @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                         @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> BuscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                               @RequestParam("id") String id,
                                               @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefa(@RequestBody TarefasDTORequest tarefasDTO,
                                    @RequestParam("id") String id,
                                    @RequestHeader("Authorization") String token);

}
