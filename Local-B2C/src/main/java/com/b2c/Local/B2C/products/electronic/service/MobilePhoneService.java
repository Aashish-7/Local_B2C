package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.MobilePhoneRepository;
import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.dto.MobilePhoneDto;
import com.b2c.Local.B2C.products.electronic.model.MobilePhone;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class MobilePhoneService {

    MobilePhoneRepository mobilePhoneRepository;

    LocalStoreRepository localStoreRepository;

    EntityManager entityManager;
    @Autowired
    public MobilePhoneService(MobilePhoneRepository mobilePhoneRepository, LocalStoreRepository localStoreRepository, EntityManager entityManager) {
        this.mobilePhoneRepository = mobilePhoneRepository;
        this.localStoreRepository = localStoreRepository;
        this.entityManager = entityManager;
    }

    public MobilePhone addMobilePhone(MobilePhoneDto mobilePhoneDto){
        if (localStoreRepository.existsById(mobilePhoneDto.getLocalStoreId())){
            MobilePhone mobilePhone = new MobilePhone();
            mobilePhone.setModel(mobilePhoneDto.getModel());
            mobilePhone.setBrand(mobilePhoneDto.getBrand());
            mobilePhone.setNetworkConnectivity(mobilePhoneDto.getNetworkConnectivity());
            mobilePhone.setSimType(mobilePhoneDto.getSimType());
            mobilePhone.setDisplayType(mobilePhoneDto.getDisplayType());
            mobilePhone.setDisplayResolution(mobilePhoneDto.getDisplayResolution());
            mobilePhone.setDisplaySize(mobilePhoneDto.getDisplaySize());
            mobilePhone.setOs(mobilePhoneDto.getOs());
            mobilePhone.setBrandUi(mobilePhoneDto.getBrandUi());
            mobilePhone.setChipset(mobilePhoneDto.getChipset());
            mobilePhone.setCpuCore(mobilePhoneDto.getCpuCore());
            mobilePhone.setCpuClockSpeed(mobilePhoneDto.getCpuClockSpeed());
            mobilePhone.setGpu(mobilePhoneDto.getGpu());
            mobilePhone.setMemoryCordSlotSupported(mobilePhoneDto.isMemoryCordSlotSupported());
            mobilePhone.setInternalMemorySize(mobilePhoneDto.getInternalMemorySize());
            mobilePhone.setMainCameraCount(mobilePhoneDto.getMainCameraCount());
            mobilePhone.setMainCameraSpecs(mobilePhoneDto.getMainCameraSpecs());
            mobilePhone.setFrontCamera(mobilePhoneDto.isFrontCamera());
            mobilePhone.setFrontCameraSpecs(mobilePhoneDto.getFrontCameraSpecs());
            mobilePhone.setBatterySize(mobilePhoneDto.getBatterySize());
            mobilePhone.setChargingType(mobilePhoneDto.getChargingType());
            mobilePhone.setChargerOutput(mobilePhoneDto.getChargerOutput());
            mobilePhone.setColor(mobilePhoneDto.getColor());
            mobilePhone.setWeightInGrams(mobilePhoneDto.getWeightInGrams());
            mobilePhone.setPrice(mobilePhoneDto.getPrice());
            mobilePhone.setBluetooth(mobilePhoneDto.isBluetooth());
            mobilePhone.setGps(mobilePhoneDto.isGps());
            mobilePhone.setNfc(mobilePhoneDto.isNfc());
            mobilePhone.setRadio(mobilePhoneDto.isRadio());
            mobilePhone.setUsbType(mobilePhoneDto.getUsbType());
            mobilePhone.setAudioJack(mobilePhoneDto.isAudioJack());
            mobilePhone.setWlan(mobilePhoneDto.isWlan());
            mobilePhone.setAvailability(mobilePhoneDto.getAvailability());
            mobilePhone.setDiscountPercentage(mobilePhoneDto.getDiscountPercentage());
            mobilePhone.setLocalStore(localStoreRepository.findById(mobilePhoneDto.getLocalStoreId()).get());
            mobilePhone.setActive(true);
            mobilePhoneRepository.save(mobilePhone);
            return mobilePhone;
        }
        else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<MobilePhone> getAllByStoreId(UUID uuid){
        if (localStoreRepository.existsById(uuid)) {
            return mobilePhoneRepository.findByLocalStore_IdAndActiveTrue(uuid);
        }else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public MobilePhone updateMobilePhoneById(MobilePhoneDto mobilePhoneDto, Long id){
        if (mobilePhoneRepository.existsByMobilePhoneIdAndActiveTrue(id)){
            MobilePhone mobilePhone = mobilePhoneRepository.findById(id).get();
            mobilePhone.setModel(mobilePhoneDto.getModel());
            mobilePhone.setBrand(mobilePhoneDto.getBrand());
            mobilePhone.setNetworkConnectivity(mobilePhoneDto.getNetworkConnectivity());
            mobilePhone.setSimType(mobilePhoneDto.getSimType());
            mobilePhone.setDisplayType(mobilePhoneDto.getDisplayType());
            mobilePhone.setDisplayResolution(mobilePhoneDto.getDisplayResolution());
            mobilePhone.setDisplaySize(mobilePhoneDto.getDisplaySize());
            mobilePhone.setOs(mobilePhoneDto.getOs());
            mobilePhone.setBrandUi(mobilePhoneDto.getBrandUi());
            mobilePhone.setChipset(mobilePhoneDto.getChipset());
            mobilePhone.setCpuCore(mobilePhoneDto.getCpuCore());
            mobilePhone.setCpuClockSpeed(mobilePhoneDto.getCpuClockSpeed());
            mobilePhone.setGpu(mobilePhoneDto.getGpu());
            mobilePhone.setMemoryCordSlotSupported(mobilePhoneDto.isMemoryCordSlotSupported());
            mobilePhone.setInternalMemorySize(mobilePhoneDto.getInternalMemorySize());
            mobilePhone.setMainCameraCount(mobilePhoneDto.getMainCameraCount());
            mobilePhone.setMainCameraSpecs(mobilePhoneDto.getMainCameraSpecs());
            mobilePhone.setFrontCamera(mobilePhoneDto.isFrontCamera());
            mobilePhone.setFrontCameraSpecs(mobilePhoneDto.getFrontCameraSpecs());
            mobilePhone.setBatterySize(mobilePhoneDto.getBatterySize());
            mobilePhone.setChargingType(mobilePhoneDto.getChargingType());
            mobilePhone.setChargerOutput(mobilePhoneDto.getChargerOutput());
            mobilePhone.setColor(mobilePhoneDto.getColor());
            mobilePhone.setWeightInGrams(mobilePhoneDto.getWeightInGrams());
            mobilePhone.setPrice(mobilePhoneDto.getPrice());
            mobilePhone.setBluetooth(mobilePhoneDto.isBluetooth());
            mobilePhone.setGps(mobilePhoneDto.isGps());
            mobilePhone.setNfc(mobilePhoneDto.isNfc());
            mobilePhone.setRadio(mobilePhoneDto.isRadio());
            mobilePhone.setUsbType(mobilePhoneDto.getUsbType());
            mobilePhone.setAudioJack(mobilePhoneDto.isAudioJack());
            mobilePhone.setWlan(mobilePhoneDto.isWlan());
            mobilePhone.setAvailability(mobilePhoneDto.getAvailability());
            mobilePhone.setDiscountPercentage(mobilePhoneDto.getDiscountPercentage());
            mobilePhone.setLocalStore(localStoreRepository.findById(mobilePhoneDto.getLocalStoreId()).get());
            mobilePhone.setActive(true);
            mobilePhoneRepository.save(mobilePhone);
            return mobilePhone;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public MobilePhone getMobilePhoneById(Long id){
        return mobilePhoneRepository.findByMobilePhoneIdAndActiveTrue(id);
    }


    public String deactivateById(Long id){
        if (mobilePhoneRepository.findById(id).isPresent() && mobilePhoneRepository.findById(id).get().getActive()){
            MobilePhone mobilePhone =mobilePhoneRepository.findById(id).get();
            mobilePhone.setActive(false);
            mobilePhoneRepository.save(mobilePhone);
            return "Deactivate MobilePhone";
        }else {
            throw new NotFound404Exception("Mobile not found");
        }
    }

    public List<MobilePhone> getAllByModel(String model){
        return mobilePhoneRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<MobilePhone> getAllByBrand(String brand){
        return mobilePhoneRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public List<MobilePhone> getAllByBrandAndPincode(String brand, int pinCode) {
        if (!mobilePhoneRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode).isEmpty())
            return mobilePhoneRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode);
        else
            return null;
    }

    public List<MobilePhone> getAllByModelAndPincode(String model, int pinCode) {
        if (!mobilePhoneRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode).isEmpty())
            return mobilePhoneRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode);
        else
            return null;
    }

    public Map<String, Object> getFilteredMobilePhone(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<MobilePhone> criteriaQuery = criteriaBuilder.createQuery(MobilePhone.class);
            Root<MobilePhone> acRoot = criteriaQuery.from(MobilePhone.class);
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
            List<MobilePhone> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }
}
