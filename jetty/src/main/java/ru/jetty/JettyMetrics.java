package ru.jetty;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
@Component
public class JettyMetrics implements PublicMetrics {
    @Autowired
    QueuedThreadPool threadPool;
    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metrics = new ArrayList<>();
        metrics.add(new Metric<Integer>("jetty.treadpool.threads.count",threadPool.getThreads()));
        metrics.add(new Metric<Integer>("jetty.treadpool.threads.buzy",threadPool.getBusyThreads()));
        metrics.add(new Metric<Integer>("jetty.treadpool.threads.idle",threadPool.getIdleThreads()));
        metrics.add(new Metric<Integer>("jetty.treadpool.threads.min",threadPool.getMinThreads()));
        metrics.add(new Metric<Integer>("jetty.treadpool.threads.max",threadPool.getMaxThreads()));
        metrics.add(new Metric<Integer>("jetty.treadpool.queue.size",threadPool.getQueueSize()));
        return metrics;
    }
}
