package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Ad> findAll(){
        return entityManager.createQuery("SELECT a FROM Ad a", Ad.class).getResultList();
    }

    @Transactional
    public Ad save(Ad ad){
        return entityManager.merge(ad);
    }

    public List<Ad> findByMinMax(int min, int max){
        return entityManager.createQuery("SELECT a FROM Ad a WHERE a.price >= :min AND a.price <= :max", Ad.class).setParameter("min", min).setParameter("max", max).getResultList();
    }

    @Transactional
    public Boolean modify(Ad modified){
        Ad original = entityManager.find(Ad.class, modified.getId());
        if(original != null) {
            if (original.getCode().equals(modified.getCode())){
                entityManager.merge(modified);
                return true;
            }
        }
        return false;
    }

    public List<Ad> findByTag(String tag){
        return entityManager.createQuery("SELECT a FROM Ad a WHERE :tag MEMBER OF a.tags", Ad.class).setParameter("tag", tag).getResultList();
    }

    @Transactional
    public void delete(Ad ad){
        entityManager.remove(ad);
    }
}
