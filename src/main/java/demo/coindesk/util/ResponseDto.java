package demo.coindesk.util;

import demo.coindesk.enums.StatusCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDto<T> {
    private String status;
    private T data;
    private List<String> errorMessageList;

    public static <T> ResponseDto<T> success(T data) {
        return ResponseDto.<T>builder()
                .status(StatusCodeEnum.SUCCESS.name())
                .data(data)
                .build();
    }

    public static <T> ResponseDto<T> fail(List<String> errorMessageList) {
        return ResponseDto.<T>builder()
                .status(StatusCodeEnum.FAIL.name())
                .errorMessageList(errorMessageList)
                .build();
    }
}
