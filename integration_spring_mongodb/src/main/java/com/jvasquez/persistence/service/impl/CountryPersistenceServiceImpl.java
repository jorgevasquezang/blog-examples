package com.jvasquez.persistence.service.impl;

import com.jvasquez.persistence.dto.CountryDTO;
import com.jvasquez.persistence.model.Country;
import com.jvasquez.persistence.repository.BasicRepository;
import com.jvasquez.persistence.service.CountryPersistenceService;
import com.jvasquez.persistence.util.PersistenceDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ricco on 30/08/2015.
 */
@Component("countryPersistenceService")
public class CountryPersistenceServiceImpl implements CountryPersistenceService {

    @Autowired
    private BasicRepository<Country> countryRepository;


    public void addCollection(List<Country> countries) {
        countryRepository.insertCollection(countries);
    }

    @Override
    public Map<String, String> getCountryMap() {
        Map<String, String> map = new HashMap();
        for (CountryDTO categoryDTO : getDTOCollection()) {
            map.put(categoryDTO.getId(), categoryDTO.getName());
        }
        return map;
    }

    @Override
    public CountryDTO getCountry(String countryId) {
        final Country country = countryRepository.getById(countryId);
        if (country != null) {
            return (CountryDTO) PersistenceDataUtil.transformObject(new CountryDTO(), country);
        } else {
            return null;
        }

    }

    public void addNewEntity(CountryDTO countryDTO) {
        countryRepository.insertObject(PersistenceDataUtil.transformObject(new Country(), countryDTO));
    }

    public List<CountryDTO> getDTOCollection() {
        List<Country> list = countryRepository.getAllCollection();
        return PersistenceDataUtil.generateList(list, CountryDTO.class);
    }


    public void removeCollection() {
        countryRepository.removeCollection();
    }
}
