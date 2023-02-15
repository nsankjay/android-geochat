package com.zv.geochat.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zv.geochat.Constants;
import com.zv.geochat.R;
import com.zv.geochat.service.ChatService;

import java.util.Random;

public class ChatActivityFragment extends Fragment {
    private static final String TAG = "ChatActivityFragment";
    EditText edtMessage;
    public ChatActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        Button btnJoinChat = (Button) v.findViewById(R.id.btnJoinChat);
        btnJoinChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending to Chat Service: Join", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                joinChat();
            }
        });

        Button btnLeaveChat = (Button) v.findViewById(R.id.btnLeaveChat);
        btnLeaveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending to Chat Service: Leave", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                leaveChat();
            }
        });

        Button btnSendMessage = (Button) v.findViewById(R.id.btnSendMessage);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending Message...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                sendMessage(edtMessage.getText().toString());
            }
        });

        Button btnReceiveMessage = (Button) v.findViewById(R.id.btnReceiveMessage);
        btnReceiveMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "New Message Arrived...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                simulateOnMessage();
            }
        });

        Button btnSendConnectError = (Button) v.findViewById(R.id.btnSendConnectError); //Assignment 1 send connect error
        btnSendConnectError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "There has been an error", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                simulateSendError();
            }
        });

        Button btnSendRandomID = (Button) v.findViewById(R.id.btnSendRandomID); //Assignment 1 send Random ID
        btnSendRandomID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending Random ID...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                simulateRandomID();
            }
        });

        edtMessage = (EditText) v.findViewById(R.id.edtMessage);

        return v;
    }

    private void joinChat(){
        Bundle data = new Bundle();
        data.putInt(ChatService.MSG_CMD, ChatService.CMD_JOIN_CHAT);
        Intent intent = new Intent(getContext(), ChatService.class);
        intent.putExtras(data);
        getActivity().startService(intent);
    }

    private void leaveChat(){
        Bundle data = new Bundle();
        data.putInt(ChatService.MSG_CMD, ChatService.CMD_LEAVE_CHAT);
        Intent intent = new Intent(getContext(), ChatService.class);
        intent.putExtras(data);
        getActivity().startService(intent);
    }

    private void sendMessage(String messageText){
        Bundle data = new Bundle();
        data.putInt(ChatService.MSG_CMD, ChatService.CMD_SEND_MESSAGE);
        data.putString(ChatService.KEY_MESSAGE_TEXT, messageText);
        Intent intent = new Intent(getContext(), ChatService.class);
        intent.putExtras(data);
        getActivity().startService(intent);
    }

    private void simulateOnMessage(){
        Bundle data = new Bundle();
        data.putInt(ChatService.MSG_CMD, ChatService.CMD_RECEIVE_MESSAGE);
        Intent intent = new Intent(getContext(), ChatService.class);
        intent.putExtras(data);
        getActivity().startService(intent);
    }

    private void simulateSendError() { //Assignment 1 send connect error
        Bundle data = new Bundle();
        data.putInt(ChatService.MSG_CMD, ChatService.CMD_CONNECT_ERROR_00);
        Intent intent = new Intent(getContext(), ChatService.class);
        intent.putExtras(data);
        getActivity().startService(intent);
    }

    private void simulateRandomID() { //Assignment 1 send Random ID
        Bundle data = new Bundle();
        Random random = new Random();
        String randomID = String.format("%8d", random.nextInt(10000000));
        data.putInt(ChatService.MSG_CMD, ChatService.CMD_SEND_RANDOM_ID);
        data.putString(ChatService.KEY_MESSAGE_TEXT, randomID.toString());
        Intent intent = new Intent(getContext(), ChatService.class);
        intent.putExtras(data);
        getActivity().startService(intent);
    }

}
