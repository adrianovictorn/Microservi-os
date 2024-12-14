package io.github.adrianovictorn.order.shared;

public class UserValidationMessage {
    private Long userId;
    private Long orderId;
    private boolean isValid;

    public UserValidationMessage() {}

    public UserValidationMessage(Long userId, Long orderId, boolean isValid) {
        this.userId = userId;
        this.orderId = orderId;
        this.isValid = isValid;
    }

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
}
