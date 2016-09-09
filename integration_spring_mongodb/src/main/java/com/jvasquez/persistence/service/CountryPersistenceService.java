package com.jvasquez.persistence.service;

import com.jvasquez.persistence.dto.CountryDTO;
import com.jvasquez.persistence.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by jorgevasquezang on 8/22/16.
 */

@Repository
public interface CountryPersistenceService {

    void addCollection(List<Country> countries);

    Map<String, String> getCountryMap();

    CountryDTO getCountry(String countryId);

    List<CountryDTO> getDTOCollection();

    void removeCollection();

    void addNewEntity(CountryDTO countryDTO);

}
