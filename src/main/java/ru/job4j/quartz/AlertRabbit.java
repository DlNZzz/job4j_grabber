package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    private Connection cn;

    public static void main(String[] args) {
        try {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            Properties config = new AlertRabbit().init("rabbit.properties");
            JobDetail jobDetail = new JobDetailImpl(Rabbit.class)
                    .usingJobData(config)
                    .build();
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt((String) config.get("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(Integer.parseInt((String) config.get("thread.sleep")));
            scheduler.shutdown();
            System.out.println(store);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public Properties init(String file) {
        Properties config = null;
        try (InputStream inputStream =
                AlertRabbit.class.getClassLoader().getResourceAsStream(file)) {
            config = new Properties();
            config.load(inputStream);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
            List<Long> store = (List<Long>) context.getJobDetail().getJobDataMap().get("store");
            store.add(System.currentTimeMillis());
        }
    }
}