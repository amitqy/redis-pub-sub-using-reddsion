package listener;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryExpiredListener;

import config.UserDetails;

public class RMapEntryExpiredListener implements EntryExpiredListener<String, UserDetails> {

private RedissonClient redissonCleint;

	public RMapEntryExpiredListener(RedissonClient redisClient) {
		this.redissonCleint = redisClient;
	}

	@Override
	public void onExpired(EntryEvent<String, UserDetails> event) {
		 UserDetails userDetails = event.getValue();
		 RTopic topic = redissonCleint.getTopic("expiredPassword");
		 String message = event.getKey();
		 topic.publish(message);
	}

}
