package com.backend.test.bookstore.cronjob;

import com.backend.test.bookstore.serviceimpl.JwtServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class EveryMorning10AmCronJobs {

    private final JwtServiceImpl jwtService;

    public EveryMorning10AmCronJobs(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void clearExpiredTokens() {
        jwtService.cleanUpExpiredTokens();
        System.out.println("Cleaning up expired tokens");
    }


}
