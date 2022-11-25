package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.LaptopRepository;
import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.LaptopDto;
import com.b2c.Local.B2C.products.electronic.model.Laptop;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class LaptopService {

    LaptopRepository laptopRepository;

    LocalStoreRepository localStoreRepository;

    EntityManager entityManager;

    @Autowired
    public LaptopService(LaptopRepository laptopRepository, LocalStoreRepository localStoreRepository, EntityManager entityManager) {
        this.laptopRepository = laptopRepository;
        this.localStoreRepository = localStoreRepository;
        this.entityManager = entityManager;
    }


    public Laptop addLaptop(LaptopDto laptopDto){
        if (localStoreRepository.existsByIdAndActiveTrue(laptopDto.getLocalStoreId())) {
            Laptop laptop = new Laptop();
            laptop.setModel(laptopDto.getModel());
            laptop.setBrand(laptopDto.getBrand());
            laptop.setPrice(laptopDto.getPrice());
            laptop.setWeightInKg(laptopDto.getWeightInKg());
            laptop.setScreenSizeInInch(laptopDto.getScreenSizeInInch());
            laptop.setTouchScreen(laptopDto.isTouchScreen());
            laptop.setAvailability(laptopDto.getAvailability());
            laptop.setScreenResolution(laptopDto.getScreenResolution());
            laptop.setCpuBrand(laptopDto.getCpuBrand());
            laptop.setCpuModel(laptopDto.getCpuModel());
            laptop.setCpuGeneration(laptopDto.getCpuGeneration());
            laptop.setCpuClockSpeed(laptopDto.getCpuClockSpeed());
            laptop.setNoOfCpuCore(laptopDto.getNoOfCpuCore());
            laptop.setHardDiskSize(laptopDto.getHardDiskSize());
            laptop.setRamSize(laptopDto.getRamSize());
            laptop.setRamType(laptopDto.getRamType());
            laptop.setFingerprint(laptopDto.isFingerprint());
            laptop.setOs(laptopDto.getOs());
            laptop.setWarranty(laptopDto.getWarranty());
            laptop.setBatteryBackupHour(laptopDto.getBatteryBackupHour());
            laptop.setGraphicCard(laptopDto.getGraphicCard());
            laptop.setNoOfUsbPorts(laptopDto.getNoOfUsbPorts());
            laptop.setEthernetPort(laptopDto.isEthernetPort());
            laptop.setBluetooth(laptopDto.isBluetooth());
            laptop.setHeadphoneJack(laptopDto.isHeadphoneJack());
            laptop.setHdmiPort(laptopDto.isHdmiPort());
            laptop.setColour(laptopDto.getColor());
            laptop.setDiscountPercentage(laptopDto.getDiscountPercentage());
            laptop.setNoOfSpeaker(laptopDto.getNoOfSpeaker());
            laptop.setLocalStore(localStoreRepository.findById(laptopDto.getLocalStoreId()).get());
            laptop.setActive(true);
            laptopRepository.save(laptop);
            return laptop;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<Laptop> getAllByStoreId(UUID uuid) {
        if (localStoreRepository.existsById(uuid)) {
            return laptopRepository.findByLocalStore_IdAndActiveTrue(uuid);
        } else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public Laptop updateLaptopById(LaptopDto laptopDto, Long id){
        if (laptopRepository.existsByLaptopIdAndActiveTrue(id)){
            Laptop laptop = laptopRepository.findById(id).get();
            laptop.setModel(laptopDto.getModel());
            laptop.setBrand(laptopDto.getBrand());
            laptop.setPrice(laptopDto.getPrice());
            laptop.setWeightInKg(laptopDto.getWeightInKg());
            laptop.setScreenSizeInInch(laptopDto.getScreenSizeInInch());
            laptop.setTouchScreen(laptopDto.isTouchScreen());
            laptop.setAvailability(laptopDto.getAvailability());
            laptop.setScreenResolution(laptopDto.getScreenResolution());
            laptop.setCpuBrand(laptopDto.getCpuBrand());
            laptop.setCpuModel(laptopDto.getCpuModel());
            laptop.setCpuGeneration(laptopDto.getCpuGeneration());
            laptop.setCpuClockSpeed(laptopDto.getCpuClockSpeed());
            laptop.setNoOfCpuCore(laptopDto.getNoOfCpuCore());
            laptop.setHardDiskSize(laptopDto.getHardDiskSize());
            laptop.setRamSize(laptopDto.getRamSize());
            laptop.setRamType(laptopDto.getRamType());
            laptop.setFingerprint(laptopDto.isFingerprint());
            laptop.setOs(laptopDto.getOs());
            laptop.setWarranty(laptopDto.getWarranty());
            laptop.setBatteryBackupHour(laptopDto.getBatteryBackupHour());
            laptop.setGraphicCard(laptopDto.getGraphicCard());
            laptop.setNoOfUsbPorts(laptopDto.getNoOfUsbPorts());
            laptop.setEthernetPort(laptopDto.isEthernetPort());
            laptop.setBluetooth(laptopDto.isBluetooth());
            laptop.setHeadphoneJack(laptopDto.isHeadphoneJack());
            laptop.setHdmiPort(laptopDto.isHdmiPort());
            laptop.setColour(laptopDto.getColor());
            laptop.setDiscountPercentage(laptopDto.getDiscountPercentage());
            laptop.setNoOfSpeaker(laptopDto.getNoOfSpeaker());
            laptop.setLocalStore(localStoreRepository.findById(laptopDto.getLocalStoreId()).get());
            laptop.setActive(true);
            laptopRepository.save(laptop);
            return laptop;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public Laptop getLaptopById(Long id){
        return laptopRepository.findByLaptopIdAndActiveTrue(id);
    }

    public String deactivateById(Long id){
        if (laptopRepository.findById(id).isPresent() && laptopRepository.findById(id).get().getActive()){
            Laptop laptop = laptopRepository.findById(id).get();
            laptop.setActive(false);
            laptopRepository.save(laptop);
            return "Deactivate laptop";
        }else {
            throw new NotFound404Exception("Laptop not found.");
        }
    }

    public List<Laptop> getAllByModel(String model){
        return laptopRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<Laptop> getAllByBrand(String brand){
        return laptopRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public List<Laptop> getAllByBrandAndPincode(String brand, int pinCode) {
        if (!laptopRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode).isEmpty())
            return laptopRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode);
        else
            return null;
    }

    public List<Laptop> getAllByModelAndPincode(String model, int pinCode) {
        if (!laptopRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode).isEmpty())
            return laptopRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode);
        else
            return null;
    }


    public Map<String, Object> getFilteredLaptop(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Laptop> criteriaQuery = criteriaBuilder.createQuery(Laptop.class);
            Root<Laptop> acRoot = criteriaQuery.from(Laptop.class);
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
            List<Laptop> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(laptopRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(laptopRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(laptopRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(laptopRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(laptopRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }
}
