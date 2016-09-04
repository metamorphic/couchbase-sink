package cxp.ingest.dao;

import org.springframework.messaging.Message;

import java.io.Serializable;

/**
 * Created by markmo on 12/07/2015.
 */
public interface MessageDao {

	/**
	 * Saves the message to the database
	 *
	 * @param <T>
	 *          Message payload type. This can save a message with any payload
	 *          type as long as the payload type can be serialized.
	 * @param message
	 *          message to save
	 */
	public <T extends Serializable> void save(Message<T> message);
}
