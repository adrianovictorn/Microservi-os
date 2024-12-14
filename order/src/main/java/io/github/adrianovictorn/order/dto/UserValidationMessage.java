package io.github.adrianovictorn.order.dto;

import java.io.Serializable;

public class UserValidationMessage implements Serializable {
    private Long userId;
    private Long orderId;
    private boolean isValid; // Indica se o usuário é válido

    public UserValidationMessage(Long userId, Long orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }

    public UserValidationMessage(Long userId, Long orderId, boolean isValid) {
        this.userId = userId;
        this.orderId = orderId;
        this.isValid = isValid;
    }

    // Getters e Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public boolean isValid() { return isValid; }
    public void setValid(boolean valid) { isValid = valid; }
}
