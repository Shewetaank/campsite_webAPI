package com.campsite.api.dao;

import com.campsite.api.entity.CampsiteRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CampsiteRegistrationDAO extends CrudRepository<CampsiteRegistration, Long> {

    List<CampsiteRegistration> findByFirstNameLike(String name);

    List<CampsiteRegistration> findByBookingNumber(String bookingNumber);

    List<CampsiteRegistration> findByEmailLike(String email);

    @Transactional
    Long deleteByBookingNumber(String bookingNumber);

}
