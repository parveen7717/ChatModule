package com.example.chatmodule.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.example.chatmodule.Interface.ChatResponse;
import com.example.chatmodule.R;
import com.example.chatmodule.adapter.MessageAdapter;
import com.example.chatmodule.controller.ChatController;
import com.example.chatmodule.model.ChatModel;
import com.example.chatmodule.model.DateInChat;

import java.util.ArrayList;
import java.util.List;

import customfonts.EditText_Helvatica_Meidum;

public class MainActivity extends AppCompatActivity implements ChatResponse{
    ImageView imageView;
    ArrayList<Object> arrayList = new ArrayList<>();
    List<Integer> type=new ArrayList<>();
    RecyclerView view_frame;
    MessageAdapter messageAdapter;
    EditText_Helvatica_Meidum message_area;
    private ChatController chatController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatController = new ChatController(this);
        chatController.registerChatResponse(this);
        imageView = findViewById(R.id.send);
        view_frame = findViewById(R.id.view_frame);
        message_area = findViewById(R.id.message_area);
        messageAdapter = new MessageAdapter(this,arrayList,type);
        view_frame.setLayoutManager( new LinearLayoutManager(this));
        view_frame.setAdapter(messageAdapter);
        view_frame.scrollToPosition(messageAdapter.getItemCount()-1);
        messageAdapter.notifyDataSetChanged();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(message_area.getText().toString().trim())) {
                    String messageTxt = message_area.getText().toString();
                    ChatModel chatModel = new ChatModel();
                    chatModel.setType(1);
                    chatModel.setDate("21 Aug 2018");
                    chatModel.setMessage(messageTxt);
                    chatModel.setMessageType("text");
                    chatModel.setImage("https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    type.add(chatModel.getType());
                    arrayList.add(chatModel);
                    view_frame.scrollToPosition(messageAdapter.getItemCount() - 1);
                    messageAdapter.notifyDataSetChanged();
                    message_area.setText("");
                }
            }
        });
        chatController.getChatFromDataBase();

    }

    @Override
    public void showChatting(DateInChat date, ChatModel model) {
        type.clear();
        arrayList.clear();
        type.add(3);
        arrayList.add(date);
        type.add(model.getType());
        arrayList.add(model);
        view_frame.scrollToPosition(messageAdapter.getItemCount() - 1);
        messageAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chatController = null;
    }
}
