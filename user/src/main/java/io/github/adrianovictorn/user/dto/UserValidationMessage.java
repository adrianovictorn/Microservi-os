package io.github.adrianovictorn.user.dto;

import java.io.Serializable;

public class UserValidationMessage implements Serializable {
    private Long userId;
    private Long orderId;
    private boolean isValid;

    // Construtores
    public UserValidationMessage() {
    }

    public UserValidationMessage(Long userId, Long orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }

    // Getters e Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "UserValidationMessage{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                ", isValid=" + isValid +
                '}';
    }
}

