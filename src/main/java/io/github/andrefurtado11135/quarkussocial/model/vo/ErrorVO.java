package io.github.andrefurtado11135.quarkussocial.model.vo;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ErrorVO {

    private String error;

    public ErrorVO(String error){
        this.error = error;
    }

    public static List<ErrorVO> mapErrors(Throwable... throwables){
        return Arrays.stream(throwables).map(x -> new ErrorVO(x.getMessage())).collect(Collectors.toList());
    }
}
