package listener;

import org.redisson.api.RedissonClient;
import org.redisson.api.map.event.EntryCreatedListener;
import org.redisson.api.map.event.EntryEvent;

import config.UserDetails;

public class RMapEntryCreatedListener  implements EntryCreatedListener<String, UserDetails> {


	private RedissonClient redissonCleint;

	public RMapEntryCreatedListener(RedissonClient redisClient) {
		this.redissonCleint = redisClient;
	}

	@Override
	public void onCreated(EntryEvent<String, UserDetails> event) {
		System.out.println("Entry created!!!!");
	}

}



