package com.javanauta.bffagendadortarefas.controller;


import com.javanauta.bffagendadortarefas.business.TarefasService;
import com.javanauta.bffagendadortarefas.business.dto.request.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.response.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro e atualização de tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar tarefas", description = "Cria uma nova tarefa no banco")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefa(@RequestBody TarefasDTORequest tarefasDTO,
                                                           @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca de tarefas por período", description = "Busca as tarefas entre os periodos informados")
    @ApiResponse(responseCode = "200", description = "Tarefa encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                                                @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca tarefas por email", description = "Busca lista de tarefas por email de usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> BuscaTarefasPorEmail(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por id", description = "Deleta tarefas por id da tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(value = "Authorization", required = false) String token) {

        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Atualiza status da tarefa", description = "Atualiza o status da tarefa ")
    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatusTarefa(statusNotificacaoEnum, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualiza dados de tarefas", description = "Atualiza dados de tarefas")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefa(@RequestBody TarefasDTORequest tarefasDTO,
                                                           @RequestParam("id") String id,
                                                           @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefa(tarefasDTO, id, token));

    }

}
