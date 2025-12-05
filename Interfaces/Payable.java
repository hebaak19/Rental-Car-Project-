package Interfaces;

import Enum.PaymentMethod;

public interface Payable {
    public void processPayment(PaymentMethod method);

    public void refund();

}
