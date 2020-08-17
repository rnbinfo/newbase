package com.rnb.demo.server.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueMessage implements Delayed {
    private Long createTimestamp;
    private String jsonContent; // {"accessorId":1,"mobile":"13811110001","stationId":2}
    private long executeTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public DelayQueueMessage(Long createTimestamp, String jsonContent, long delayTime) {
        this.createTimestamp = createTimestamp;
        this.jsonContent = jsonContent;
        this.executeTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MINUTES) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        DelayQueueMessage msg = (DelayQueueMessage) delayed;
        return this.createTimestamp.compareTo(msg.createTimestamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DelayQueueMessage) {
            return this.getCreateTimestamp().equals(((DelayQueueMessage) obj).getCreateTimestamp());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("createTimestamp=").append(createTimestamp)
                .append(", jsonContent").append(jsonContent)
                .append(", executeTime").append(executeTime);
        return stringBuffer.toString();
    }
}
