package traking.tools;

import java.text.ParseException;
import java.util.List;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import traking.dao.TrackingDAO;
import traking.model.TrackingModel;

public class EnvioPrograma {
	public void startSendingData() throws SchedulerException, ParseException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
               
       	List<TrackingModel> datos = (new TrackingDAO().read());
       	JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("datos", datos);
       	
        JobDetail job = JobBuilder.newJob(EnvioDatosJob.class)
                .withIdentity("dataSendingJob", "dataSenderGroup")
                .usingJobData(jobDataMap)
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

