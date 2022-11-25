package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.RefrigeratorRepository;
import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.RefrigeratorDto;
import com.b2c.Local.B2C.products.electronic.model.Refrigerator;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class RefrigeratorService {

    RefrigeratorRepository refrigeratorRepository;

    LocalStoreRepository localStoreRepository;

    EntityManager entityManager;

    @Autowired
    public RefrigeratorService(RefrigeratorRepository refrigeratorRepository, LocalStoreRepository localStoreRepository, EntityManager entityManager) {
        this.refrigeratorRepository = refrigeratorRepository;
        this.localStoreRepository = localStoreRepository;
        this.entityManager = entityManager;
    }

    public Refrigerator addRefrigerator(RefrigeratorDto refrigeratorDto){
        if (localStoreRepository.existsByIdAndActiveTrue(refrigeratorDto.getLocalStoreId())){
            Refrigerator refrigerator = new Refrigerator();
            refrigerator.setModel(refrigeratorDto.getModel());
            refrigerator.setBrand(refrigeratorDto.getBrand());
            refrigerator.setColour(refrigeratorDto.getColour());
            refrigerator.setWarranty(refrigeratorDto.getWarranty());
            refrigerator.setDigitalDisplay(refrigeratorDto.isDigitalDisplay());
            refrigerator.setWeight(refrigeratorDto.getWeight());
            refrigerator.setDiscountPercentage(refrigeratorDto.getDiscountPercentage());
            refrigerator.setPowerInStar(refrigeratorDto.getPowerInStar());
            refrigerator.setCapacityInLitre(refrigeratorDto.getCapacityInLitre());
            refrigerator.setMultiDoor(refrigeratorDto.isMultiDoor());
            refrigerator.setFreezerPosition(refrigeratorDto.getFreezerPosition());
            refrigerator.setAvailability(refrigeratorDto.getAvailability());
            refrigerator.setBuiltInStabilizer(refrigeratorDto.isBuiltInStabilizer());
            refrigerator.setPrice(refrigeratorDto.getPrice());
            refrigerator.setLocalStore(localStoreRepository.findById(refrigeratorDto.getLocalStoreId()).get());
            refrigerator.setActive(true);
            refrigeratorRepository.save(refrigerator);
            return refrigerator;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<Refrigerator> getAllByStoreId(UUID uuid){
        if (localStoreRepository.existsById(uuid)) {
            return refrigeratorRepository.findByLocalStore_IdAndActiveTrue(uuid);
        }else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public Refrigerator updateRefrigeratorById(RefrigeratorDto refrigeratorDto, Long id){
        if (refrigeratorRepository.existsByRefrigeratorIdAndActiveTrue(id)){
            Refrigerator refrigerator = refrigeratorRepository.findById(id).get();
            refrigerator.setModel(refrigeratorDto.getModel());
            refrigerator.setBrand(refrigeratorDto.getBrand());
            refrigerator.setColour(refrigeratorDto.getColour());
            refrigerator.setWarranty(refrigeratorDto.getWarranty());
            refrigerator.setDigitalDisplay(refrigeratorDto.isDigitalDisplay());
            refrigerator.setWeight(refrigeratorDto.getWeight());
            refrigerator.setDiscountPercentage(refrigeratorDto.getDiscountPercentage());
            refrigerator.setPowerInStar(refrigeratorDto.getPowerInStar());
            refrigerator.setCapacityInLitre(refrigeratorDto.getCapacityInLitre());
            refrigerator.setMultiDoor(refrigeratorDto.isMultiDoor());
            refrigerator.setFreezerPosition(refrigeratorDto.getFreezerPosition());
            refrigerator.setAvailability(refrigeratorDto.getAvailability());
            refrigerator.setBuiltInStabilizer(refrigeratorDto.isBuiltInStabilizer());
            refrigerator.setPrice(refrigeratorDto.getPrice());
            refrigerator.setLocalStore(localStoreRepository.findById(refrigeratorDto.getLocalStoreId()).get());
            refrigerator.setActive(true);
            refrigeratorRepository.save(refrigerator);
            return refrigerator;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public Refrigerator getRefrigeratorById(Long id){
        return refrigeratorRepository.findByRefrigeratorIdAndActiveTrue(id);
    }


    public String deactivateById(Long id){
        if (refrigeratorRepository.findById(id).isPresent() && refrigeratorRepository.findById(id).get().getActive()){
            Refrigerator refrigerator =refrigeratorRepository.findById(id).get();
            refrigerator.setActive(false);
            refrigeratorRepository.save(refrigerator);
            return "Deactivate refrigerator";
        }else {
            throw new NotFound404Exception("Mobile not found");
        }
    }

    public List<Refrigerator> getAllByModel(String model){
        return refrigeratorRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<Refrigerator> getAllByBrand(String brand){
        return refrigeratorRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public List<Refrigerator> getAllByBrandAndPincode(String brand, int pinCode) {
        if (!refrigeratorRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode).isEmpty())
            return refrigeratorRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode);
        else
            return null;
    }

    public List<Refrigerator> getAllByModelAndPincode(String model, int pinCode) {
        if (!refrigeratorRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode).isEmpty())
            return refrigeratorRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode);
        else
            return null;
    }

    public Map<String, Object> getFilteredRefrigerator(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Refrigerator> criteriaQuery = criteriaBuilder.createQuery(Refrigerator.class);
            Root<Refrigerator> acRoot = criteriaQuery.from(Refrigerator.class);
            Join setJoin = acRoot.join("localStore");
            List<Predicate> predicates = new ArrayList<>();
            if (electronicFilterDto.getModel() != null && !electronicFilterDto.getModel().isEmpty()){
                predicates.add(criteriaBuilder.and(acRoot.get("model").in(electronicFilterDto.getModel())));
            }
            if (electronicFilterDto.getBrand() != null && !electronicFilterDto.getBrand().isEmpty()){
                predicates.add(criteriaBuilder.and(acRoot.get("brand").in(electronicFilterDto.getBrand())));
            }
            if (electronicFilterDto.getColour() != null && !electronicFilterDto.getColour().isEmpty()){
                predicates.add(criteriaBuilder.and(acRoot.get("colour").in(electronicFilterDto.getColour())));
            }
            if (electronicFilterDto.getAvailability() != null && !electronicFilterDto.getAvailability().isEmpty()){
                predicates.add(criteriaBuilder.and(acRoot.get("availability").in(electronicFilterDto.getAvailability())));
            }
            if (Objects.nonNull(electronicFilterDto.getPrice())){
                predicates.add(criteriaBuilder.and(acRoot.get("price").in(electronicFilterDto.getPrice())));
            }
            if (electronicFilterDto.getPinCode() != null){
                predicates.add(criteriaBuilder.equal(setJoin.get("pinCode"), electronicFilterDto.getPinCode()));
            }
            if (electronicFilterDto.getCity() != null && !electronicFilterDto.getCity().isEmpty()){
                predicates.add(criteriaBuilder.equal(setJoin.get("city"), electronicFilterDto.getCity()));
            }
            predicates.add(criteriaBuilder.equal(acRoot.get("active"), true));
            criteriaQuery.select(acRoot).where(predicates.toArray(new Predicate[0])).distinct(true);
            session.createQuery(criteriaQuery);
            List<Refrigerator> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(refrigeratorRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(refrigeratorRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(refrigeratorRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(refrigeratorRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(refrigeratorRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }
}
