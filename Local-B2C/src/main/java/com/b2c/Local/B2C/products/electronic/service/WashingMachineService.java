package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.WashingMachineRepository;
import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.WashingMachineDto;
import com.b2c.Local.B2C.products.electronic.model.WashingMachine;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class WashingMachineService {

    WashingMachineRepository washingMachineRepository;

    LocalStoreRepository localStoreRepository;

    EntityManager entityManager;

    @Autowired
    public WashingMachineService(WashingMachineRepository washingMachineRepository, LocalStoreRepository localStoreRepository, EntityManager entityManager) {
        this.washingMachineRepository = washingMachineRepository;
        this.localStoreRepository = localStoreRepository;
        this.entityManager = entityManager;
    }

    public WashingMachine addWashingMachine(WashingMachineDto washingMachineDto){
        if (localStoreRepository.findById(washingMachineDto.getLocalStoreId()).isPresent()){
            WashingMachine washingMachine = new WashingMachine();
            washingMachine.setModel(washingMachineDto.getModel());
            washingMachine.setBrand(washingMachineDto.getBrand());
            washingMachine.setDryer(washingMachineDto.isDryer());
            washingMachine.setFunctionType(washingMachineDto.getFunctionType());
            washingMachine.setCapacityInKg(washingMachineDto.getCapacityInKg());
            washingMachine.setPowerInStar(washingMachineDto.getPowerInStar());
            washingMachine.setTimer(washingMachineDto.isTimer());
            washingMachine.setColour(washingMachineDto.getColour());
            washingMachine.setWarranty(washingMachineDto.getWarranty());
            washingMachine.setDigitalDisplay(washingMachineDto.isDigitalDisplay());
            washingMachine.setChildLock(washingMachineDto.isChildLock());
            washingMachine.setShockProof(washingMachineDto.isShockProof());
            washingMachine.setWeight(washingMachineDto.getWeight());
            washingMachine.setDiscountPercentage(washingMachineDto.getDiscountPercentage());
            washingMachine.setAvailability(washingMachineDto.getAvailability());
            washingMachine.setPrice(washingMachineDto.getPrice());
            washingMachine.setLocalStore(localStoreRepository.findById(washingMachineDto.getLocalStoreId()).get());
            washingMachine.setActive(true);
            washingMachineRepository.save(washingMachine);
            return washingMachine;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<WashingMachine> getAllByStoreId(UUID uuid){
        if (localStoreRepository.existsById(uuid)) {
            return washingMachineRepository.findByLocalStore_IdAndActiveTrue(uuid);
        }else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public WashingMachine updateWashingMachineById(WashingMachineDto washingMachineDto, Long id){
        if (washingMachineRepository.existsByWashingMachineIdAndActiveTrue(id)){
            WashingMachine washingMachine = washingMachineRepository.findById(id).get();
            washingMachine.setModel(washingMachineDto.getModel());
            washingMachine.setBrand(washingMachineDto.getBrand());
            washingMachine.setDryer(washingMachineDto.isDryer());
            washingMachine.setFunctionType(washingMachineDto.getFunctionType());
            washingMachine.setCapacityInKg(washingMachineDto.getCapacityInKg());
            washingMachine.setPowerInStar(washingMachineDto.getPowerInStar());
            washingMachine.setTimer(washingMachineDto.isTimer());
            washingMachine.setColour(washingMachineDto.getColour());
            washingMachine.setWarranty(washingMachineDto.getWarranty());
            washingMachine.setDigitalDisplay(washingMachineDto.isDigitalDisplay());
            washingMachine.setChildLock(washingMachineDto.isChildLock());
            washingMachine.setShockProof(washingMachineDto.isShockProof());
            washingMachine.setWeight(washingMachineDto.getWeight());
            washingMachine.setDiscountPercentage(washingMachineDto.getDiscountPercentage());
            washingMachine.setAvailability(washingMachineDto.getAvailability());
            washingMachine.setPrice(washingMachineDto.getPrice());
            washingMachine.setLocalStore(localStoreRepository.findById(washingMachineDto.getLocalStoreId()).get());
            washingMachine.setActive(true);
            washingMachineRepository.save(washingMachine);
            return washingMachine;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public WashingMachine getWashingMachineById(Long id){
        return washingMachineRepository.findByWashingMachineIdAndActiveTrue(id);
    }

    public String deactivateById(Long id){
        if (washingMachineRepository.findById(id).isPresent() && washingMachineRepository.findById(id).get().getActive()){
            WashingMachine washingMachine = washingMachineRepository.findById(id).get();
            washingMachine.setActive(false);
            washingMachineRepository.save(washingMachine);
            return "Deactivate WashingMachine";
        }else {
            throw new NotFound404Exception("Washing Machine not found!");
        }
    }

    public List<WashingMachine> getAllByModel(String model){
        return washingMachineRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<WashingMachine> getAllByBrand(String brand){
        return washingMachineRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public List<WashingMachine> getAllByBrandAndPincode(String brand, int pinCode) {
        if (!washingMachineRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode).isEmpty())
            return washingMachineRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode);
        else
            return null;
    }

    public List<WashingMachine> getAllByModelAndPincode(String model, int pinCode) {
        if (!washingMachineRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode).isEmpty())
            return washingMachineRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode);
        else
            return null;
    }

    public Map<String, Object> getFilteredWashingMachine(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<WashingMachine> criteriaQuery = criteriaBuilder.createQuery(WashingMachine.class);
            Root<WashingMachine> acRoot = criteriaQuery.from(WashingMachine.class);
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
            List<WashingMachine> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }
}
