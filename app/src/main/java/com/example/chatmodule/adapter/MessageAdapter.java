package com.example.chatmodule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chatmodule.R;
import com.example.chatmodule.model.ChatModel;
import com.example.chatmodule.model.DateInChat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import customfonts.MyTextView_Roboto_Regular;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    public LayoutInflater inflater;
    private final int SENT_MESSAGE = 1, RECEIVED_MESSAGE = 2, DATE_MESSAGE = 3;
    private ArrayList<Object> arrayList;
    private List<Integer> type;


    public MessageAdapter(Context context, ArrayList<Object> arrayList, List<Integer> type) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
        this.type = type;
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        CircleImageView receiverImg;
        MyTextView_Roboto_Regular receiverMessage;
        ReceiverViewHolder(View view) {
            super(view);
            receiverImg = itemView.findViewById(R.id.receiverImg);
            receiverMessage = itemView.findViewById(R.id.receiverMessage);
        }
    }


    public class SenderViewHolder extends RecyclerView.ViewHolder {
        MyTextView_Roboto_Regular sender_message, text_msg_time;

        SenderViewHolder(View view) {
            super(view);
            sender_message = itemView.findViewById(R.id.sender_message);
            text_msg_time = itemView.findViewById(R.id.text_msg_time);
        }
    }


    public class DateViewHolder extends RecyclerView.ViewHolder {
        TextView dateTv;

        DateViewHolder(View view) {
            super(view);
            dateTv = view.findViewById(R.id.dateTv);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case SENT_MESSAGE:
                View sender = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sender_chat, viewGroup, false);
                return new SenderViewHolder(sender);
            case RECEIVED_MESSAGE:
                View receiver = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.receiver_chat, viewGroup, false);
                return new ReceiverViewHolder(receiver);
            default:
                View bannerLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.date_item, viewGroup, false);
                return new DateViewHolder(bannerLayoutView);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case SENT_MESSAGE:
                final ChatModel messageModel = (ChatModel) arrayList.get(position);
                SenderViewHolder senderH = (SenderViewHolder) holder;
               /* if (messageModel.getMessageSeen().equals("unSeen")) {
                    senderH.text_msg_time.setVisibility(View.GONE);
                } else {
                    senderH.text_msg_time.setVisibility(View.VISIBLE);
                }*/
                if (messageModel.getMessageType().equals("photo")) {
                    senderH.sender_message.setVisibility(View.GONE);
                }
                else {
                    senderH.sender_message.setVisibility(View.VISIBLE);
                    senderH.sender_message.setText(messageModel.getMessage());
                }
                break;


            case RECEIVED_MESSAGE:
                ReceiverViewHolder receiverH = (ReceiverViewHolder) holder;
                final ChatModel messageModell = (ChatModel) arrayList.get(position);

                Picasso.get().load(messageModell.getImage()).placeholder(R.drawable.ic_launcher_background).into(receiverH.receiverImg);

                if (messageModell.getMessageType().equals("photo")) {
                    receiverH.receiverMessage.setVisibility(View.GONE);
                } else {
                    receiverH.receiverMessage.setVisibility(View.VISIBLE);
                    receiverH.receiverMessage.setText(messageModell.getMessage());
                }
                break;

            default:
                final DateInChat dateInChat = (DateInChat) arrayList.get(position);
                DateViewHolder dateH = (DateViewHolder) holder;
                dateH.dateTv.setText(dateInChat.getDate());
                dateH.dateTv.setText("21 Aug 2018");

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(type.get(position)==1){
            return SENT_MESSAGE;
        }else if(type.get(position)==2) {
            return RECEIVED_MESSAGE;
        }else {
            return DATE_MESSAGE;
        }    }
}
