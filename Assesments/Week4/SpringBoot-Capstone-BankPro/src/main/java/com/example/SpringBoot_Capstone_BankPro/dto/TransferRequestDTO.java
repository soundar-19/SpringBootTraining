package com.example.SpringBoot_Capstone_BankPro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO {
    @NotBlank(message = "Sender's Account Number is required")
    private String senderAC;
    @NotBlank(message = "Receiver's Account Number is required")
    private String receiverAC;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount can't be negative")
    private double amount;
}
