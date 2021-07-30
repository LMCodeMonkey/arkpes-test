package com.arkpes.arkpes_test;

import org.springframework.data.repository.CrudRepository;

public interface InvestorRepository extends CrudRepository<Investor, Integer> {

    Investor findByName(String investorName);
}