package org.bugmakers404.hermes.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

  @Value("${hermes.consumer.thread.maxpoolsize:2}")
  Integer poolSize;

  @Override
  @Bean
  public AsyncTaskExecutor getAsyncExecutor() {
    ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
    if (poolSize <= 0) {
      throw new IllegalArgumentException(
          "The value of hermes.consumer.thread.maxpoolsize must be greater than 0");
    }
    pool.setCorePoolSize(poolSize / 2);
    pool.setMaxPoolSize(poolSize);
    pool.setThreadNamePrefix("HCExec-");
    pool.setThreadPriority(Thread.NORM_PRIORITY);
    pool.initialize();
    return pool;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (ex, method, params) -> log.error("Async method {} threw an exception: {}",
        method.getName(),
        ex.getMessage(), ex);
  }
}

