package org.example.bankex2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

    public class Customer {
        private long id;
        private String username;
        private double balance;
    }

