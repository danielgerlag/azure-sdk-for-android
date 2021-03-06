// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.android.communication.chat.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.threeten.bp.OffsetDateTime;

/**
 * The ChatThreadInfo model.
 */
@Fluent
public final class ChatThreadInfo {
    /*
     * Chat thread id.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * Chat thread topic.
     */
    @JsonProperty(value = "topic")
    private String topic;

    /*
     * Flag if a chat thread is soft deleted.
     */
    @JsonProperty(value = "isDeleted")
    private Boolean isDeleted;

    /*
     * The timestamp when the last message arrived at the server. The timestamp
     * is in ISO8601 format: `yyyy-MM-ddTHH:mm:ssZ`.
     */
    @JsonProperty(value = "lastMessageReceivedOn", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime lastMessageReceivedOn;

    /**
     * Get the id property: Chat thread id.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the topic property: Chat thread topic.
     * 
     * @return the topic value.
     */
    public String getTopic() {
        return this.topic;
    }

    /**
     * Set the topic property: Chat thread topic.
     * 
     * @param topic the topic value to set.
     * @return the ChatThreadInfo object itself.
     */
    public ChatThreadInfo setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    /**
     * Get the isDeleted property: Flag if a chat thread is soft deleted.
     * 
     * @return the isDeleted value.
     */
    public Boolean isDeleted() {
        return this.isDeleted;
    }

    /**
     * Set the isDeleted property: Flag if a chat thread is soft deleted.
     * 
     * @param isDeleted the isDeleted value to set.
     * @return the ChatThreadInfo object itself.
     */
    public ChatThreadInfo setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    /**
     * Get the lastMessageReceivedOn property: The timestamp when the last
     * message arrived at the server. The timestamp is in ISO8601 format:
     * `yyyy-MM-ddTHH:mm:ssZ`.
     * 
     * @return the lastMessageReceivedOn value.
     */
    public OffsetDateTime getLastMessageReceivedOn() {
        return this.lastMessageReceivedOn;
    }
}
