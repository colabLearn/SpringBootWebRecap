package com.tutorial.tunjiTech.customer;


public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
)
{}
