package app.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Azizul Haque Ananto
 * @since 20/6/20
 */


@Data
@Builder
public class ResponseFormat {
    String status;
    List<String> errors;
    Object data;
}
