package com.romanf.demo.handler.service;

import com.example.grpc.Message;
import com.example.grpc.MessageServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MessageHandlerService extends MessageServiceGrpc.MessageServiceImplBase {

    @Override
    public void sendMessage(Message.MessageRequest request, StreamObserver<Message.MessageResponse> responseObserver) {

        Message.MessageResponse response = Message.MessageResponse.newBuilder()
                .setOperId(request.getOperId())
                .setStatus("Request with operId=" + request.getOperId() + " received")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted(); //сервер ответил, больше ответов не будет
    }
}
