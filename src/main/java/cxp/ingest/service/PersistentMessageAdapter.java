package cxp.ingest.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandlingException;

import java.io.Serializable;

/**
 * Created by markmo on 12/07/2015.
 *
 * The PersistentMessageAdapter is a service that handles saving messages
 * from a {@link MessageDao}. It offers a single method for saving messages.
 * It also has a method for handling messages that failed.
 */
public interface PersistentMessageAdapter {

    /**
     * Saves the message using the {@link MessageDao}.
     *
     * @param message
     *          message to save.
     */
    public <T extends Serializable> void save(Message<T> message);

    /**
     * Handles a message that failed during processing. Different behaviors could
     * occur here such as incrementing a counter in the message for a number of
     * retries or sending the message to a different destination.
     *
     * @param message
     *          message to be handled.
     */
    void handleFailedMessage(Message<MessageHandlingException> message);
}
