package pro.sky.skyprospringtyulkovcw2.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectAmountException extends  RuntimeException{
}
