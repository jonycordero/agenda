package com.example.agenda.controller.dto;

public record TaskRequestDto ( Long id,
                              String title,
                              String description,
                              boolean completed)
{}
