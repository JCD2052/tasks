package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserProductInfo {
    private String productName;
    private String os;
    private String email;
    private String password;
}
