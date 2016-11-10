package com.lukeshannon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleTask {
	
	private static final Logger log = LoggerFactory.getLogger(SimpleTask.class);
	
	@Scheduled(fixedRate = 5000)
    public void sayHi() {
		
        log.info("Hello, every 5000 seconds I say this");
    }

}
