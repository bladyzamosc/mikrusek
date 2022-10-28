package com.bladyzamosc.mikrusek.processing.services

import com.bladyzamosc.protocol.MikrusekMessageReply
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Service

/**
 * User: Z6EKI
 * Date: 28.10.2022
 */
@Service
class MikrusekStreamObserver : StreamObserver<MikrusekMessageReply> {
    override fun onNext(p0: MikrusekMessageReply?) {
        TODO("Not yet implemented")
    }

    override fun onError(p0: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun onCompleted() {
        TODO("Not yet implemented")
    }
}