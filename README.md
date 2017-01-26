# cf-scheduling-tasks
This covers so basic tasks for scheduling tasks in a Pivotal Cloud Foundry environment

## Spring Boot Option

The first example uses Spring Boot to easily access Spring's Scheduling Support.

For a quick example of creating a Spring Boot Application that does scheduling:
https://spring.io/guides/gs/scheduling-tasks/

For more details around Spring Support for scheduling:

http://docs.spring.io/spring/docs/current/spring-framework-reference/html/scheduling.html

For this sample the task is here:

```java

@Component
public class SimpleTask {
	
	private static final Logger log = LoggerFactory.getLogger(SimpleTask.class);
	
	@Scheduled(fixedRate = 5000)
    public void sayHi() {
		
        log.info("Hello, every 5000 seconds I say this");
    }

}

```

After pushing application to PCF the message from the scheduling can be seen here.

```shell
➜  cf-scheduled-task-boot git:(master) ✗ cf logs cf-scheduled-task
Connected, tailing logs for app cf-scheduled-task in org cloud-native / space development as luke.shannon@gmail.com...

2016-11-10T17:39:36.65-0500 [APP/PROC/WEB/0]OUT 2016-11-10 22:39:36.649  INFO 17 --- [pool-1-thread-1] com.lukeshannon.SimpleTask               : Hello, every 5000 seconds I say this
2016-11-10T17:39:41.65-0500 [APP/PROC/WEB/0]OUT 2016-11-10 22:39:41.649  INFO 17 --- [pool-1-thread-1] com.lukeshannon.SimpleTask               : Hello, every 5000 seconds I say this
2016-11-10T17:39:46.65-0500 [APP/PROC/WEB/0]OUT 2016-11-10 22:39:46.649  INFO 17 --- [pool-1-thread-1] com.lukeshannon.SimpleTask               : Hello, every 5000 seconds I say this
```
### Limitations

This approach does not account for recovery of Jobs that were in progress should the application fail and be covered by PCF in a new container. Spring does provide support for the Quartz scheduling API, which does provide support for stateful jobs:

http://docs.spring.io/spring-framework/docs/2.5.x/api/org/springframework/scheduling/quartz/QuartzJobBean.html

A useful follow on exercise would be to leverage this in a PFC environment.

## Third Party API

Services such as Iron worker can provide a full product offering around scheduling capabilities. As they are consumed via a service broker, the service itself exists outside of the application runtime.

http://docs.run.pivotal.io/marketplace/services/ironworker.html

Temporize is another example:

http://docs.run.pivotal.io/marketplace/services/temporize.html

