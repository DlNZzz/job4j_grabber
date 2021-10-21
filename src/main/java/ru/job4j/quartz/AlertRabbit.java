package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    private static Properties config;
    private Connection cn;

    public static void main(String[] args) {
        try {
            AlertRabbit alertRabbit = new AlertRabbit();
            config = alertRabbit.getProperties("rabbit.properties");
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            JobDetail jobDetail = newJob(Rabbit.class)
                    .usingJobData(alertRabbit.init())
                    .build();
            SimpleScheduleBuilder times2 = simpleSchedule()
                    .repeatSecondlyForTotalCount(1, 1)
                    .repeatForever();
            Trigger trigger2 = newTrigger()
                    .startNow()
                    .withSchedule(times2)
                    .build();
            //scheduler.scheduleJob(jobDetail, trigger2);
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

    public Properties getProperties(String file) {
        Properties config = null;
        try (InputStream inputStream =
                     AlertRabbit.class.getClassLoader().getResourceAsStream(file)) {
            config = new Properties();
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public JobDataMap init() {
        try {
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("init", cn);
        return new JobDataMap();
    }

    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
            List<Long> store = (List<Long>) context.getJobDetail().getJobDataMap().get("store");
            if (store != null) {
                store.add(System.currentTimeMillis());
            }
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("init");
            if (connection != null) {
                System.out.println(connection + " ++++++++++++++++++++++++++++");
                try (PreparedStatement statement = connection.prepareStatement(
                        "insert into rabbit(timestamp) values (?);")) {
                    statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                    statement.execute();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}