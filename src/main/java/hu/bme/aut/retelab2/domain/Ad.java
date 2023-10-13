package hu.bme.aut.retelab2.domain;

import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class Ad {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private int price;
    @CreatedDate
    private Date created = new Date();
    private String code;
    private LocalDateTime expiration;

    @ElementCollection
    private List<String> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public String generate(){
        char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random RND = new Random();

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 6; i++)
            sb.append(CHARS[RND.nextInt(CHARS.length)]);
        return sb.toString();
    }
}
