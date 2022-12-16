package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.TelevisionRepository;
import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.TelevisionDto;
import com.b2c.Local.B2C.products.electronic.model.Television;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class TelevisionService {

    TelevisionRepository televisionRepository;

    LocalStoreRepository localStoreRepository;

    EntityManager entityManager;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository, LocalStoreRepository localStoreRepository, EntityManager entityManager) {
        this.televisionRepository = televisionRepository;
        this.localStoreRepository = localStoreRepository;
        this.entityManager = entityManager;
    }

    public Television addTelevision(TelevisionDto televisionDto){
        if (localStoreRepository.existsByIdAndActiveTrue(televisionDto.getLocalStoreId())){
            Television television = new Television();
            television.setModel(televisionDto.getModel());
            television.setBrand(televisionDto.getBrand());
            television.setPrice(televisionDto.getPrice());
            television.setAvailability(televisionDto.getAvailability());
            television.setDisplayType(televisionDto.getDisplayType());
            television.setDisplaySizeInInch(televisionDto.getDisplaySizeInInch());
            television.setScreenResolution(televisionDto.getScreenResolution());
            television.setNoOfHdmiPorts(televisionDto.getNoOfHdmiPorts());
            television.setNoOfUsbPorts(televisionDto.getNoOfUsbPorts());
            television.setFeatures(televisionDto.getFeatures());
            television.setWiFi(televisionDto.isWiFi());
            television.setEthernet(televisionDto.isEthernet());
            television.setRamSizeGb(televisionDto.getRamSizeGb());
            television.setMemorySizeGb(televisionDto.getMemorySizeGb());
            television.setDisplayRefreshRate(televisionDto.getDisplayRefreshRate());
            television.setNoOfCpuCore(televisionDto.getNoOfCpuCore());
            television.setNoOfSpeakers(televisionDto.getNoOfSpeakers());
            television.setDiscountPercentage(televisionDto.getDiscountPercentage());
            television.setColour(televisionDto.getColor());
            television.setWarranty(televisionDto.getWarranty());
            television.setLocalStore(localStoreRepository.findById(televisionDto.getLocalStoreId()).get());
            television.setActive(true);
            televisionRepository.save(television);
            return television;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<Television> getAllByStoreId(UUID uuid){
        if (localStoreRepository.existsById(uuid)) {
            return televisionRepository.findByLocalStore_IdAndActiveTrue(uuid);
        }else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public Television updateTelevisionById(TelevisionDto televisionDto, Long id){
        if (televisionRepository.existsByTelevisionIdAndActiveTrue(id)){
            Television television = televisionRepository.findById(id).get();
            television.setModel(televisionDto.getModel());
            television.setBrand(televisionDto.getBrand());
            television.setPrice(televisionDto.getPrice());
            television.setAvailability(televisionDto.getAvailability());
            television.setDisplayType(televisionDto.getDisplayType());
            television.setDisplaySizeInInch(televisionDto.getDisplaySizeInInch());
            television.setScreenResolution(televisionDto.getScreenResolution());
            television.setNoOfHdmiPorts(televisionDto.getNoOfHdmiPorts());
            television.setNoOfUsbPorts(televisionDto.getNoOfUsbPorts());
            television.setFeatures(televisionDto.getFeatures());
            television.setWiFi(televisionDto.isWiFi());
            television.setEthernet(televisionDto.isEthernet());
            television.setRamSizeGb(televisionDto.getRamSizeGb());
            television.setMemorySizeGb(televisionDto.getMemorySizeGb());
            television.setDisplayRefreshRate(televisionDto.getDisplayRefreshRate());
            television.setNoOfCpuCore(televisionDto.getNoOfCpuCore());
            television.setNoOfSpeakers(televisionDto.getNoOfSpeakers());
            television.setDiscountPercentage(televisionDto.getDiscountPercentage());
            television.setColour(televisionDto.getColor());
            television.setWarranty(televisionDto.getWarranty());
            television.setLocalStore(localStoreRepository.findById(televisionDto.getLocalStoreId()).get());
            television.setActive(true);
            televisionRepository.save(television);
            return television;
        }else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public Television getTelevisionById(Long id){
        return televisionRepository.findByTelevisionIdAndActiveTrue(id);
    }

    public String deactivateById(Long id){
        if (televisionRepository.findById(id).isPresent() && televisionRepository.findById(id).get().getActive()){
            Television television = televisionRepository.findById(id).get();
            television.setActive(false);
            televisionRepository.save(television);
            return "Deactivate television";
        }else {
            throw new NotFound404Exception("Television not found");
        }
    }

    public List<Television> getAllByModel(String model){
        return televisionRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<Television> getAllByBrand(String brand){
        return televisionRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public List<Television> getAllByBrandAndPincode(String brand, int pinCode) {
        if (!televisionRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode).isEmpty())
            return televisionRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode);
        else
            return null;
    }

    public List<Television> getAllByModelAndPincode(String model, int pinCode) {
        if (!televisionRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode).isEmpty())
            return televisionRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode);
        else
            return null;
    }

    public Map<String, Object> getFilteredTelevision(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Television> criteriaQuery = criteriaBuilder.createQuery(Television.class);
            Root<Television> acRoot = criteriaQuery.from(Television.class);
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
            List<Television> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(televisionRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(televisionRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(televisionRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(televisionRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(televisionRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }

    public List<Television> televisionSearchKeyword(String keyword, int page, int size) {
        if (size > 0) {
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Television> criteriaQuery = criteriaBuilder.createQuery(Television.class);
            Root<Television> localStoreRoot = criteriaQuery.from(Television.class);
            Predicate predicateForData = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("model")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("brand")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("colour")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("availability")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("warranty")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("displayType")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("screenResolution")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("features")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("displayRefreshRate")), "%" + keyword.toUpperCase() + "%")
            );
            criteriaQuery.select(localStoreRoot).where(predicateForData).distinct(true);
            List<Television> resource = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            return resource;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }
}
