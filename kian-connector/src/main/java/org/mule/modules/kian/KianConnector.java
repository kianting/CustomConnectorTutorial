package org.mule.modules.kian;

import java.io.IOException;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.lifecycle.OnException;
import org.mule.api.lifecycle.InitialisationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mule.modules.kian.config.ConnectorConfig;

@Connector(name="kian", friendlyName="Kian")
public class KianConnector {

    @Config
    ConnectorConfig config;

    Logger log = LoggerFactory.getLogger(KianConnector.class);
    
    /**
     * Custom processor
     *
     * @param friend Name to be used to generate a greeting message.
     * @return A greeting message
     */
    @Processor
    public MuleEvent doSomething(final MuleEvent event, MuleMessage muleMessage) 
    		throws InitialisationException, IOException, NullPointerException, MuleException {
    	try{
		 String corelationId =  event.getMessage().getCorrelationId(); //check
		 log.info(String.format("corelationId: %s", corelationId));
		 
		 int correlationGroupSize = event.getMessage().getCorrelationGroupSize();
		 log.info(String.format("correlationGroupSize: %s", correlationGroupSize));

		 int correlationSequence = event.getMessage().getCorrelationSequence();
		 log.info(String.format("correlationSequence: %s", correlationSequence));
		 
		 String encoding = event.getMessage().getEncoding(); // check
		 log.info(String.format("encoding: %s", encoding));

		 Object OriginalPayload = event.getMessage().getOriginalPayload();
		 log.info(String.format("OriginalPayload: %s", OriginalPayload));

		 String messageRootId = event.getMessage().getMessageRootId(); // check
		 log.info(String.format("messageRootId: %s", messageRootId));
		 
		 Object error = event.getMessage().getExceptionPayload(); // check
		 log.info(String.format("error: %s", error));
		 
	     String payload = event.getMessage().getPayloadAsString();// check
	     log.info(String.format("Payload: %s", payload));
	     
	     log.info(String.format("Connector Settings (Greeting): %s", config.getGreeting()));
	     log.info(String.format("Connector Settings (Reply): %s", config.getReply()));

    	}catch(Exception ex){
    		log.error("Error Encountered from custom component ",ex);
    	}
	     return event;
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}