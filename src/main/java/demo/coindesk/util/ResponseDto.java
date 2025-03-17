package demo.coindesk.util;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto<T> {
    private String status;
    private T data;
    private List<String> errorMessageList;
}
