package com.rbkmoney.adapter.common.utils.mpi.utils;

import com.rbkmoney.adapter.common.utils.mpi.exception.ThreeDSecureException;
import com.rbkmoney.adapter.common.utils.mpi.model.Message;
import com.rbkmoney.adapter.common.utils.mpi.model.PARes;
import com.rbkmoney.adapter.common.utils.mpi.model.TX;
import com.rbkmoney.adapter.common.utils.mpi.model.ThreeDSecure;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreeDSecureUtils {

    private static JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance(ThreeDSecure.class);
        } catch (JAXBException ex) {
            log.error("Failed to create jaxb context", ex);
            throw new RuntimeException("Failed to create jaxb context", ex);
        }
    }

    public static ThreeDSecure extractThreeDSecure(String str) {
        try {
            Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(str);
            return (ThreeDSecure) jaxbMarshaller.unmarshal(reader);
        } catch (JAXBException ex) {
            log.error("Can't extract ThreeDSecure", ex);
            throw new ThreeDSecureException(ex);
        }
    }

    public static String extractEciFromPaRes(ThreeDSecure threeDSecure) {
        return Optional.ofNullable(threeDSecure)
                .map(ThreeDSecure::getMessage)
                .map(Message::getPaRes)
                .map(PARes::getTx)
                .map(TX::getEci).orElse("");
    }

}
