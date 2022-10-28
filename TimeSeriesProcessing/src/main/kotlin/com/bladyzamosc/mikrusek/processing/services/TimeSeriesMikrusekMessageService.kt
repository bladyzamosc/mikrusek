package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.protocol.MikrusekMessage
import com.bladyzamosc.protocol.MikrusekMessageReply
import com.bladyzamosc.protocol.MikrusekMessageServiceGrpc
import com.google.gson.Gson
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

/**
 * User: Z6EKI
 * Date: 28.10.2022
 */
@GRpcService
class TimeSeriesMikrusekMessageService(private val producer: KafkaProducer) :
    MikrusekMessageServiceGrpc.MikrusekMessageServiceImplBase() {

    override fun accept(request: MikrusekMessage?, responseObserver: StreamObserver<MikrusekMessageReply>?) {
        super.accept(request, responseObserver)
        val reply = MikrusekMessageReply.newBuilder()
            .setReply("Request created: " + request?.header?.messageId)
            .build();
        val gson = Gson()
        producer.sendMessage("ADD_TIME_SERIES", gson.toJson(request))
        responseObserver?.onNext(reply)
        responseObserver?.onCompleted()
    }

}