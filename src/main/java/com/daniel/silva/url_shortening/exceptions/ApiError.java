package com.daniel.silva.url_shortening.exceptions;

import java.time.LocalDateTime;

public record ApiError(
        LocalDateTime localDateTime,
        int status,
        String error,
        String message,
        String path
){}
