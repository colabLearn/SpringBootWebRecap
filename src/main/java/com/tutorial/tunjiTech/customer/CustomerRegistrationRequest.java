package com.tutorial.tunjiTech.customer;

public record CustomerRegistrationRequest (
        String name,
        String email,
        Integer age
) {
}
