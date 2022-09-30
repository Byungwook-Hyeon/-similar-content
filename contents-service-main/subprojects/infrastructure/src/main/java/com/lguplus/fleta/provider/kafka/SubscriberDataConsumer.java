package com.lguplus.fleta.provider.kafka;

import com.lguplus.fleta.service.DataManipulationListener;
import com.lguplus.fleta.service.ManipulationDataDispatcher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Profile("!local")
@Slf4j
@Component
@AllArgsConstructor
public class SubscriberDataConsumer {

    private final ManipulationDataDispatcher manipulationDataDispatcher;

    @Qualifier("subscriberManipulationListener")
    private final DataManipulationListener subscriberManipulationListener;

    @KafkaListener(topics = "MYLGDB.PVSUSER.XCION_SBC_TBL_UNITED", groupId = "contents.SubscriberDataConsumer")
    public void onSubscriberManipulated(final String message) {
        log.debug("Manipulation data for MYLGDB.PVSUSER.XCION_SBC_TBL_UNITED: {}", message);
        manipulationDataDispatcher.dispatch(subscriberManipulationListener, message);
    }
}
