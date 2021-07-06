package com.example.todo.payload.response;

public class DeleteResponse {
    private String deleteMessage;

    public DeleteResponse(String deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(String deleteMessage) {
        this.deleteMessage = deleteMessage;
    }
}
