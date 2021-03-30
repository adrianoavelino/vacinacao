package br.com.zup.vacinacao.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDto> erros = new ArrayList<>();
		
		List<FieldError> camposComErros = exception.getBindingResult().getFieldErrors();
		camposComErros.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDto erro = new ErroDto(e.getField(), mensagem);
			erros.add(erro);
		});
		
		return erros;
	}
	
    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErroDto handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest webRequest) {
        String mensagem = "já está sendo utilizado";
        String campo = webRequest.getDescription(false);
        if (ex.getMessage().contains("uc_email")) {
            campo = "email";
        }
        if (ex.getMessage().contains("uc_cpf")) {
            campo = "cpf";
        }
        if (ex.getMessage().contains("uc_nome")) {
        	campo = "nome";
        }
        return new ErroDto(campo,mensagem);
    }
    
    @ExceptionHandler({InvalidFormatException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErroDto handleInvalidFormatException(InvalidFormatException ex, WebRequest webRequest) {
    	String mensagem = ex.getMessage();
    	String campo = webRequest.getDescription(false);
    	if (ex.getMessage().contains("Failed to deserialize java.time.LocalDate")) {
    		mensagem = "Data inválida. Ex: 2021-01-01 ";
    		campo = "dataNascimento";
    	}
    	return new ErroDto(campo,mensagem);
    }
    
}
