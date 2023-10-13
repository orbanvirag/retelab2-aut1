package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @PostMapping
    public ResponseEntity<Ad> create(@RequestBody Ad ad){
        ad.setId(null);
        ad.setCode(ad.generate());
        return new ResponseEntity<>(adRepository.save(ad), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Ad>> findByMinMax(@RequestParam(required = false, defaultValue = "0") int min, @RequestParam(required = false, defaultValue = "10000000") int max){
        List<Ad> modified = adRepository.findByMinMax(min, max);
        for(Ad a: modified){
            a.setCode("000000");
        }
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Ad> modify(@RequestBody Ad ad){
        boolean b = adRepository.modify(ad);
        if(b)
            return new ResponseEntity<>(ad, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping("{tag}")
    public ResponseEntity<List<Ad>> getByTags(@PathVariable String tag){
        return new ResponseEntity<>(adRepository.findByTag(tag), HttpStatus.OK);
    }
}
