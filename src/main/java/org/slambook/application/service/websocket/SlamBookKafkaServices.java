package org.slambook.application.service.websocket;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

@Component
public class SlamBookKafkaServices {

	@Value("${kafkatopic}")
	private String kafkaTopic;
	
	@Autowired
	private TaskExecutor executor;
	
	private ConsumerConfig consumerConfig;
	
	public SlamBookKafkaServices(){
		init();
	}
	
	private void init() {
		consumerConfig = createConsumerConfig();
	}

	@PostConstruct
	private void startListener(){
		startKafkaListener();
	}
	
	
	private void startKafkaListener() {
		ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
		Map<String, List<KafkaStream<byte[], byte[]>>> messageMap = consumerConnector.createMessageStreams(ImmutableMap.of(kafkaTopic, 1));
		List<KafkaStream<byte[], byte[]>> streams = messageMap.get(kafkaTopic);
		int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.execute(new KafkaMessageHandler(stream, threadNumber));
            threadNumber++;
        }
	}
	
	private ConsumerConfig createConsumerConfig(){
		  Properties props=new Properties();
		  props.put("zk.connect","localhost:2181");
		  props.put("groupid","slambook");
		  props.put("zk.sessiontimeout.ms","400");
		  props.put("zk.synctime.ms","200");
		  props.put("autocommit.interval.ms","1000");
		  return new ConsumerConfig(props);
		}
}
