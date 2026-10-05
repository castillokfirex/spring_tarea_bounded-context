package com.example.tarea.domain.chatairunmetric.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public class ChatAiRunMetricNotFoundException extends ResourceNotFoundException {

    public ChatAiRunMetricNotFoundException(ChatAiRunMetricId id) {
        super("ChatAiRunMetric with id '" + id.value() + "' was not found");
    }
}
