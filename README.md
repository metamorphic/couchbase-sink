## Custom Message Source Template

This is an example project showing how to create a basic message passing
application with the Spring Integration framework using a custom channel
adapter and data access object for persistent messaging.

To build the project:

    ./gradlew clean build

Spring XD deployable JAR created under build/libs/custom-source-template-1.0.jar

Start xd-shell

Issue the following commands in the shell:

    module upload --type source --name teapot --file ./build/libs/custom-source-template-1.0.jar

    stream create --name teapottest --definition "teapot | log" --deploy


### Conceptual Overview

Inbound channel adapter receives message from external system inward relative to Spring Integration code.

    public <T extends Serializable> Message<T> removeNext() {
        return messageDao.getNextMessage();
    }


    <int:channel id="input"/>

    <bean id="messageAdapter" class="cxp.ingest.service.PersistentMessageAdapterImpl">
        <property name="messageDao" ref="messageDao"/>
    </bean>

    <int:inbound-channel-adapter ref="messageAdapter" method="removeNext" channel="input">
        <int:poller fixed-delay="1000"/>
    </int:inbound-channel-adapter>
