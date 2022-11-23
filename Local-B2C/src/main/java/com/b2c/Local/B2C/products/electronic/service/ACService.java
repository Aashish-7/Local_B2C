package com.b2c.Local.B2C.products.electronic.service;

import com.b2c.Local.B2C.exception.BadRequest400Exception;
import com.b2c.Local.B2C.exception.NotFound404Exception;
import com.b2c.Local.B2C.products.electronic.dao.ACRepository;
import com.b2c.Local.B2C.products.electronic.dto.ACDto;
import com.b2c.Local.B2C.products.electronic.dto.ElectronicFilterDto;
import com.b2c.Local.B2C.products.electronic.model.AC;
import com.b2c.Local.B2C.store.dao.LocalStoreRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.*;

@Service
public class ACService {

    ACRepository acRepository;

    LocalStoreRepository localStoreRepository;

    EntityManager entityManager;

    @Autowired
    public ACService(ACRepository acRepository, LocalStoreRepository localStoreRepository, EntityManager entityManager) {
        this.acRepository = acRepository;
        this.localStoreRepository = localStoreRepository;
        this.entityManager = entityManager;
    }

    public AC addAc(ACDto acDto) {
        if (localStoreRepository.findById(acDto.getLocalStoreId()).isPresent()) {
            AC ac = new AC();
            ac.setModel(acDto.getModel());
            ac.setBrand(acDto.getBrand());
            ac.setColour(acDto.getColour());
            ac.setWarranty(acDto.getWarranty());
            ac.setDigitalDisplay(acDto.isDigitalDisplay());
            ac.setWeightInKg(acDto.getWeightInKg());
            ac.setDiscountPercentage(acDto.getDiscountPercentage());
            ac.setPowerInStar(acDto.getPowerInStar());
            ac.setAvailability(acDto.getAvailability());
            ac.setBuiltInStabilizer(ac.isBuiltInStabilizer());
            ac.setCapacityInTon(acDto.getCapacityInTon());
            ac.setMode(acDto.getMode());
            ac.setTimer(acDto.isTimer());
            ac.setWiFi(acDto.isWiFi());
            ac.setAirConditionerType(acDto.getAirConditionerType());
            ac.setPrice(acDto.getPrice());
            ac.setLocalStore(localStoreRepository.findById(acDto.getLocalStoreId()).get());
            ac.setActive(true);
            acRepository.save(ac);
            return ac;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    @Cacheable(value = "getAllByStoreId", key = "#uuid")
    public List<AC> getAllByStoreId(UUID uuid) {
        if (localStoreRepository.existsById(uuid)) {
            return acRepository.findByLocalStore_IdAndActiveTrue(uuid);
        } else {
            throw new NotFound404Exception("Store Not Found");
        }
    }

    public AC updateAcById(ACDto acDto, Long id) {
        if (acRepository.existsByAcIdAndActiveTrue(id)) {
            AC ac = acRepository.findById(id).get();
            if (acDto.getModel() != null) {
                ac.setModel(acDto.getModel());
            }
            if (acDto.getBrand() != null) {
                ac.setBrand(acDto.getBrand());
            }
            if (acDto.getColour() != null) {
                ac.setColour(acDto.getColour());
            }
            if (acDto.getWarranty() != null) {
                ac.setWarranty(acDto.getWarranty());
            }
            if (acDto.isDigitalDisplay()) {
                ac.setDigitalDisplay(acDto.isDigitalDisplay());
            } else {
                ac.setDigitalDisplay(acDto.isDigitalDisplay());
            }
            if (Objects.nonNull(acDto.getWeightInKg())) {
                ac.setWeightInKg(acDto.getWeightInKg());
            }
            if (Objects.nonNull(acDto.getDiscountPercentage())) {
                ac.setDiscountPercentage(acDto.getDiscountPercentage());
            }
            if (Objects.nonNull(acDto.getPowerInStar())) {
                ac.setPowerInStar(acDto.getPowerInStar());
            }
            if (acDto.getAvailability() != null) {
                ac.setAvailability(acDto.getAvailability());
            }
            if (acDto.isBuiltInStabilizer()) {
                ac.setBuiltInStabilizer(acDto.isBuiltInStabilizer());
            } else {
                ac.setBuiltInStabilizer(acDto.isBuiltInStabilizer());
            }
            if (Objects.nonNull(acDto.getCapacityInTon())) {
                ac.setCapacityInTon(acDto.getCapacityInTon());
            }
            if (acDto.getMode() != null) {
                ac.setMode(acDto.getMode());
            }
            if (acDto.isTimer()) {
                ac.setTimer(acDto.isTimer());
            } else {
                ac.setTimer(acDto.isTimer());
            }
            if (acDto.isWiFi()) {
                ac.setWiFi(acDto.isWiFi());
            } else {
                ac.setWiFi(acDto.isWiFi());
            }
            if (acDto.getAirConditionerType() != null) {
                ac.setAirConditionerType(acDto.getAirConditionerType());
            }
            if (Objects.nonNull(acDto.getPrice())) {
                ac.setPrice(acDto.getPrice());
            }
            acRepository.save(ac);
            return ac;
        } else {
            throw new NotFound404Exception("Ac not found");
        }
    }

    public AC updateAcByIdByPut(ACDto acDto, Long id) {
        if (acRepository.existsByAcIdAndActiveTrue(id)) {
            AC ac = acRepository.findById(id).get();
            ac.setModel(acDto.getModel());
            ac.setBrand(acDto.getBrand());
            ac.setColour(acDto.getColour());
            ac.setWarranty(acDto.getWarranty());
            ac.setDigitalDisplay(acDto.isDigitalDisplay());
            ac.setWeightInKg(acDto.getWeightInKg());
            ac.setDiscountPercentage(acDto.getDiscountPercentage());
            ac.setPowerInStar(acDto.getPowerInStar());
            ac.setAvailability(acDto.getAvailability());
            ac.setBuiltInStabilizer(ac.isBuiltInStabilizer());
            ac.setCapacityInTon(acDto.getCapacityInTon());
            ac.setMode(acDto.getMode());
            ac.setTimer(acDto.isTimer());
            ac.setWiFi(acDto.isWiFi());
            ac.setAirConditionerType(acDto.getAirConditionerType());
            ac.setPrice(acDto.getPrice());
            ac.setLocalStore(localStoreRepository.findById(acDto.getLocalStoreId()).get());
            ac.setActive(true);
            acRepository.save(ac);
            return ac;
        } else {
            throw new NotFound404Exception("Store not found");
        }
    }

    public AC getAcById(Long id) {
        return acRepository.findByAcIdAndActiveTrue(id);
    }

    public String deactivateById(Long id) {
        if (acRepository.findById(id).isPresent() && acRepository.findById(id).get().getActive()) {
            AC ac = acRepository.findById(id).get();
            ac.setActive(false);
            acRepository.save(ac);
            return "Deactivate AC";
        } else {
            throw new NotFound404Exception("Ac not found!");
        }
    }

    public ByteArrayInputStream downloadACbyLocalStoreId(UUID id) throws DocumentException {
        if (!acRepository.findByLocalStore_IdAndActiveTrue(id).isEmpty()) {
            Document document = new Document(PageSize.A4, 20, 20, 20, 20);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, os);
            document.open();
            Paragraph title = new Paragraph("Ac Products", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 0)));
            document.add(title);
            PdfPTable table = new PdfPTable(6);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            PdfPCell c1 = new PdfPCell(new Phrase("id"));
            table.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("model"));
            table.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("brand"));
            table.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("price"));
            table.addCell(c4);
            PdfPCell c5 = new PdfPCell(new Phrase("colour"));
            table.addCell(c5);
            PdfPCell c6 = new PdfPCell(new Phrase("availability"));
            table.addCell(c6);
            acRepository.findByLocalStore_IdAndActiveTrue(id).forEach(ac -> {
                table.addCell(String.valueOf(ac.getAcId()));
                table.addCell(ac.getModel());
                table.addCell(ac.getBrand());
                table.addCell(String.valueOf(ac.getPrice()));
                table.addCell(ac.getColour());
                table.addCell(ac.getAvailability());
            });
            document.add(table);
            document.close();
            return new ByteArrayInputStream(os.toByteArray());
        } else {
            throw new NotFound404Exception("Not Found");
        }
    }

    public List<AC> getAllByModel(String model) {
        return acRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<AC> getAllByBrand(String brand) {
        return acRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public List<AC> getAllByBrandAndPincode(String brand, int pinCode) {
        if (!acRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode).isEmpty())
            return acRepository.findAllByActiveTrueAndBrandLikeAndLocalStore_PinCode(brand, pinCode);
        else
            return null;
    }

    public List<AC> getAllByModelAndPincode(String model, int pinCode) {
        if (!acRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode).isEmpty())
            return acRepository.findAllByActiveTrueAndModelLikeAndLocalStore_PinCode(model, pinCode);
        else
            return null;
    }

    public Map<String, Object> getFilteredAc(int page, int size, ElectronicFilterDto electronicFilterDto) {
        Map<String, Object> map = new HashMap<>();
        if (size != 0) {
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AC> criteriaQuery = criteriaBuilder.createQuery(AC.class);
            Root<AC> acRoot = criteriaQuery.from(AC.class);
            Join setJoin = acRoot.join("localStore");
            List<Predicate> predicates = new ArrayList<>();
            if (electronicFilterDto.getModel() != null && !electronicFilterDto.getModel().isEmpty()) {
                predicates.add(criteriaBuilder.and(acRoot.get("model").in(electronicFilterDto.getModel())));
            }
            if (electronicFilterDto.getBrand() != null && !electronicFilterDto.getBrand().isEmpty()) {
                predicates.add(criteriaBuilder.and(acRoot.get("brand").in(electronicFilterDto.getBrand())));
            }
            if (electronicFilterDto.getColour() != null && !electronicFilterDto.getColour().isEmpty()) {
                predicates.add(criteriaBuilder.and(acRoot.get("colour").in(electronicFilterDto.getColour())));
            }
            if (electronicFilterDto.getAvailability() != null && !electronicFilterDto.getAvailability().isEmpty()) {
                predicates.add(criteriaBuilder.and(acRoot.get("availability").in(electronicFilterDto.getAvailability())));
            }
            if (Objects.nonNull(electronicFilterDto.getPrice())) {
                predicates.add(criteriaBuilder.and(acRoot.get("price").in(electronicFilterDto.getPrice())));
            }
            if (electronicFilterDto.getPinCode() != null) {
                predicates.add(criteriaBuilder.equal(setJoin.get("pinCode"), electronicFilterDto.getPinCode()));
            }
            if (electronicFilterDto.getCity() != null && !electronicFilterDto.getCity().isEmpty()) {
                predicates.add(criteriaBuilder.equal(setJoin.get("city"), electronicFilterDto.getCity()));
            }
            predicates.add(criteriaBuilder.equal(acRoot.get("active"), true));
            criteriaQuery.select(acRoot).where(predicates.toArray(new Predicate[0])).distinct(true);
            session.createQuery(criteriaQuery);
            List<AC> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(acRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(acRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(acRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(acRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(acRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }
}
