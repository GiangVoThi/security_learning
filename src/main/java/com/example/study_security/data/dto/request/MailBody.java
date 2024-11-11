package com.example.study_security.data.dto.request;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
