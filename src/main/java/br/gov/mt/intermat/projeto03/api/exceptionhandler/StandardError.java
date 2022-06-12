package br.gov.mt.intermat.projeto03.api.exceptionhandler;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class StandardError  implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer status;
    private String msgErro  ;
    private LocalDateTime dataHora;

   // public StandardError(Integer status, String msgErro, LocalDateTime dataHora) {
   //     this.status = status;
  //      this.msgErro = msgErro;
  //      this.dataHora = dataHora;
  //  }
}
