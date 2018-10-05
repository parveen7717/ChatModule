package com.example.chatmodule.controller;

import android.app.Activity;

import com.example.chatmodule.Interface.ChatResponse;
import com.example.chatmodule.model.ChatModel;
import com.example.chatmodule.model.DateInChat;

public class ChatController {
    private Activity activity;
    private ChatResponse response;

    public ChatController(Activity activity) {
        this.activity = activity;
    }

    public void registerChatResponse(ChatResponse response){
        this.response = response;
    }
    public void getChatFromDataBase(){
        DateInChat dateInChat=new DateInChat();
        dateInChat.setDate("21 Aug 2018");
        ChatModel chatModel = new ChatModel();
        chatModel.setType(2);
        chatModel.setDate("21 Aug 2018");
        chatModel.setMessage("Hi How are you?");
        chatModel.setMessageType("text");
        chatModel.setImage("https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        response.showChatting(dateInChat,chatModel);
    }
}
