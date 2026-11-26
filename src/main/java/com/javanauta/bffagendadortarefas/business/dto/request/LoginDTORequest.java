package com.javanauta.bffagendadortarefas.business.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTORequest {

    private String email;
    private String senha;
}
