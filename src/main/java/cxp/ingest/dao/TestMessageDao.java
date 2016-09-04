package cxp.ingest.dao;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by markmo on 12/07/2015.
 */
public class TestMessageDao implements MessageDao, InitializingBean, DisposableBean {

    private static final Log log = LogFactory.getLog(TestMessageDao.class);

    private Cluster cluster;
    private Bucket defaultBucket;

    @Override
    public void afterPropertiesSet() throws Exception {
        cluster = CouchbaseCluster.create();
        defaultBucket = cluster.openBucket();
    }

    @Override
    public void destroy() throws Exception {
        cluster.disconnect();
        defaultBucket.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Serializable> void save(final Message<T> message) {
        log.info("Outputting payload");
        log.info(message);
        System.out.println("Outputting payload");
        System.out.println(message);
        JsonObject content = JsonObject.fromJson(message.getPayload().toString());
        String docId;
        if (content.containsKey("siebel_customer_number")) {
            docId = content.getString("siebel_customer_number");
        } else {
            docId = UUID.randomUUID().toString();
        }
        JsonDocument doc = JsonDocument.create(docId, content);
        defaultBucket.upsert(doc);
    }
}
