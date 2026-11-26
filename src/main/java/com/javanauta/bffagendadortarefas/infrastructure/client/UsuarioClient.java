package com.javanauta.bffagendadortarefas.infrastructure.client;


import com.javanauta.bffagendadortarefas.business.dto.request.EnderecoDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.request.LoginDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.request.TelefoneDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.request.UsuarioDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.response.EnderecoDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.response.TelefoneDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.response.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login (@RequestBody LoginDTORequest usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                            @RequestHeader("Authorization") String token);


    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestHeader("Authorization") String token);

}
