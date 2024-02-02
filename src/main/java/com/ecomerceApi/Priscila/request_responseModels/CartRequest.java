package com.ecomerceApi.Priscila.request_responseModels;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    @NotEmpty
    @NonNull
    private Long productId;

    @NotEmpty
    @NonNull
    @Min(value = 1)
    private Long quantity;

}
