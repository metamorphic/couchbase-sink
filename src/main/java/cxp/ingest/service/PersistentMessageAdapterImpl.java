package cxp.ingest.service;

import cxp.ingest.dao.MessageDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandlingException;

import java.io.Serializable;

/**
 * Created by markmo on 12/07/2015.
 */
public class PersistentMessageAdapterImpl implements PersistentMessageAdapter {

    private static final Log log = LogFactory.getLog(PersistentMessageAdapterImpl.class);

    private MessageDao messageDao;

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public <T extends Serializable> void save(Message<T> message) {
        // Uses the Message DAO to save the message
        log.info("Saving message " + message.getHeaders().getId());
        messageDao.save(message);
    }

    @Override
    public void handleFailedMessage(Message<MessageHandlingException> message) {
        MessageHandlingException exception = message.getPayload();
        log.error(exception);
    }
}
