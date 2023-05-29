package traking.tools;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class EnvioPrograma {
	public void startSendingData() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail job = JobBuilder.newJob(EnvioDatosJob.class)
                .withIdentity("dataSendingJob", "dataSenderGroup")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dataSendingTrigger", "dataSenderGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")) // Ejecutar 5 cada minutos
                .build();

        scheduler.scheduleJob(job, trigger);
    }

    public void stopSendingData() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.shutdown();
    }
}

