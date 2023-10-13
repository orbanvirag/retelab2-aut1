package hu.bme.aut.retelab2.component;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class Scheduler {
    @Autowired
    private AdRepository adRepository;

    @Scheduled(fixedDelay = 6000)
    public void deleteExpired(){
        List<Ad> ads = adRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for(Ad a: ads){
            if(now.isAfter(a.getExpiration()))
                adRepository.delete(a);
        }
    }
}
