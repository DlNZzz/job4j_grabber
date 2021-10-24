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

    public static void main(String[] args) {
        AlertRabbit alertRabbit = new AlertRabbit();
        config = alertRabbit.getProperties("rabbit.properties");
        try (Connection con = alertRabbit.init()) {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            JobDataMap data2 = new JobDataMap();
            data2.put("init", con);
            JobDetail jobDetail = newJob(Rabbit.class)
                    .usingJobData(data2)
                    .build();
            Trigger trigger2 = newTrigger()
                    .startNow()
                    .build();
            scheduler.scheduleJob(jobDetail, trigger2);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(config.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(Integer.parseInt(config.getProperty("thread.sleep")));
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

    public Connection init() throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("driver-class-name"));
        return DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password")
        );
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
            try {
                if (connection != null && !connection.isClosed()) {
                    try (PreparedStatement statement = connection.prepareStatement(
                            "insert into rabbit(created_date) values (?);")) {
                        statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                        statement.execute();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}