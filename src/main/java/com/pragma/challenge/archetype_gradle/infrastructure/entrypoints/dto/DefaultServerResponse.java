package com.pragma.challenge.archetype_gradle.infrastructure.entrypoints.dto;

public record DefaultServerResponse<T, E>(T data, E error) {}
